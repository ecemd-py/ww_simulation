package Creatures;

public class EnemyClass extends CommonProperties {
	public String name;

	public EnemyClass(int hp, int attack, int pos, String name) {
		this.hp = hp;
		this.attack = attack;
		this.position = pos;
		this.name = name;
	}

}
