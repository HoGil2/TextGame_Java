package Character;

public class character {
	int id;
	String name;
	int hp;
	int power;
	int money;
		
	public character() {}
	public character(int _id, String _name, int _hp, int _power, int _money){
		id = _id; name=_name; hp=_hp; power=_power; money=_money;
	}

	public void showInfo() { 
		System.out.println("Name:"+name+" HP:"+hp+" Power:"+power+" Money:"+money); 
	}
	public void showforBattle() { System.out.println("Name:"+name+" HP:"+hp+" Power:"+power); }
	public int getID() { return id; }
	public String getName() { return name; }
	public int getHP() { return hp; }
	public int getMoney() { return money; }
	public int getPower() { return power; }
}