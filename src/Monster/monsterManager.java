package Monster;

import Character.character;

public class monsterManager {
	monster marr[];
	int index;
	enum defaultStatus{
		Goblin("°íºí¸°", 6, 2, 100);
		
		private final String name;
		private final int hp;
		private final int power;
		private final int money;
		
		defaultStatus(String name, int hp, int power, int money){
			this.name = name;
			this.hp = hp;
			this.power = power;
			this.money = money;
		}
		String krname() {
			return name;
		}
		int hp() {
			return hp;
		}
		int power() {
			return power;
		}
		int money() {
			return money;
		}
	}
	
	public monsterManager() {}
	public monsterManager(int n) {
		marr = new monster[n]; index=0;
	}
	public void defaultmonster() {
		marr[index] = new monster(++index, defaultStatus.Goblin.krname(), defaultStatus.Goblin.hp(),
									defaultStatus.Goblin.power(), defaultStatus.Goblin.money());
	}
	public monster findbyID(int ID) {
		for(int i=0; i<index; i++) {
			if(marr[i].id == ID)
				return marr[i];
		}
		return null;
	}
	public void Attacked(monster mon, character player) {
		mon.hp -= player.getPower();
	}
}
