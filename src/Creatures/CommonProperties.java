package Creatures;

public class CommonProperties {

	public int hp;
	public int attack;
	public int position;

	public boolean isAlive() {
		return (this.hp > 0);
	}

	public int getAttacked(int enemy_attack) {
		return this.hp - enemy_attack;
	}

}
