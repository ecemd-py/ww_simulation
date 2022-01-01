import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Creatures.EnemyClass;
import Creatures.HeroClass;

public class Simulation {

	public void Simulation(String input_filepath, String output_filepath) {
		File myFile = new File(input_filepath);
		int resources = 0;
		HashMap<String, List<Integer>> enemies = new HashMap<String, List<Integer>>(); // enemy type and properties map
		HashMap<Integer, EnemyClass> road = new HashMap<Integer, EnemyClass>(); // position and enemy map
		HeroClass hero = new HeroClass(); // Create hero object
		String current_enemy = "";
		try {
			Scanner myReader = new Scanner(myFile);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();

				if (data.contains("Resources")) {// get resource info
					resources = getValue(data);
				} else if (data.contains("Hero")) { // get hero properties
					if (data.contains("hp")) {
						int hp = getValue(data);
						hero.hp = hp;
					} else if (data.contains("attack")) {
						int attack = getValue(data);
						hero.attack = attack;
					}
				} else if (data.contains("Enemy")) {// get enemy names
					int iend = data.indexOf(" is");
					String key = data.substring(0, iend);
					List<Integer> list = new ArrayList<Integer>();
					enemies.put(key, list);
				} else if (data.contains("position")) {// get positions and enemies at the position. create enemies with
														// properties of the enemy from enemies hash map
					int istart = data.indexOf("a ");
					int iend = data.indexOf(" at");
					String enemy_name = data.substring(istart + 1, iend).trim();
					int pos = getValue(data);
					int enemy_hp = enemies.get(enemy_name).get(0);
					int enemy_attack = enemies.get(enemy_name).get(1);
					road.put(pos, new EnemyClass(enemy_hp, enemy_attack, pos, enemy_name));
				} else {
					for (String key : enemies.keySet()) { // get properties of the enemy type and add them to enemies
															// hash map
						if (data.contains(key + " ")) {
							if (data.contains("hp")) {
								int hp = getValue(data);
								enemies.get(key).add(hp);
							} else if (data.contains("attack")) {
								int attack = getValue(data);
								enemies.get(key).add(attack);
							}
						}
					}
				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// Journey Start
		try {
			FileWriter myWriter = new FileWriter(output_filepath);
			myWriter.write("Hero started journey with " + hero.hp + " HP!\n");
			while (hero.position != resources && hero.isAlive()) {

				boolean possible_attack = road.containsKey(hero.position);

				if (possible_attack) { // check if there is an enemy in the current position of hero
					current_enemy = road.get(hero.position).name;
					while (hero.isAlive() && road.get(hero.position).isAlive()) { // attack until hero and enemy alive
						hero.hp = hero.getAttacked(road.get(hero.position).attack);
						road.get(hero.position).hp = road.get(hero.position).getAttacked(hero.attack);
						if (!road.get(hero.position).isAlive() && hero.isAlive()) { // check who wins
							myWriter.write("Hero defeated " + current_enemy + " with " + hero.hp + " HP remaining\n");
						} else if (road.get(hero.position).isAlive() && !hero.isAlive()) {
							myWriter.write(current_enemy + " defeated Hero with " + road.get(hero.position).hp
									+ " HP remaining\n");
							break;
						}
					}
				}

				hero.position++;
			}

			if (hero.isAlive()) { // check status of hero
				myWriter.write("Hero Survived!\n");
			} else {
				myWriter.write("Hero is Dead!! Last seen at position " + hero.position + " !!\n");
			}

			myWriter.close();

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static int getValue(String line) {
		// get the value of the line
		line = line.replaceAll("[^\\d]", " ");
		line = line.trim();
		line = line.replaceAll(" +", " ");

		return Integer.parseInt(line);
	}

}
