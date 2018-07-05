import java.util.Scanner;

import Character.charManager;
import Monster.monsterManager;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		charManager cm = new charManager(5);
		monsterManager mm = new monsterManager(5);	mm.defaultmonster();
		menus menu = new menus();
		
		switch(menu.startMenu()) {
		case "START":
			cm.newStart(mm);
			break;
			
		case "END":
			System.out.println("Game is quit..");
			return;
		}
	}

}

class menus{
	String sel;
	Scanner scan=new Scanner(System.in);
	
	public String startMenu() {
		System.out.println("-------TEXT GAME-------");
		System.out.println("         START");
		System.out.println("          END");
		System.out.println("-----------------------");
		System.out.print("Enter the Selection : "); sel=scan.next();
		
		return sel;
	}
}