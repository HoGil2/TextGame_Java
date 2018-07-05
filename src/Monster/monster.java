package Monster;

public class monster {
	int id;
	String name;
	int hp;
	int power;
	int money;
	
	public monster() {}
	public monster(int _id, String _name, int _hp, int _power, int _money) {
		id=_id; name=_name; hp=_hp; power=_power; money=_money;
	}
	
	public void showInfo() { System.out.println("Name:"+name+" HP: "+hp+" Power: "+power); }
	public void showforBattle() { System.out.println("Name:"+name+" HP:"+hp+" Power:"+power); }
	public String getname() { return name; }
	public int gethp() { return hp; }
	public int getmoney() { return money; }
	public int getpower() { return power; }
}
