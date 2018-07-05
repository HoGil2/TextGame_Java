package Character;

import java.util.Scanner;

import Monster.monster;
import Monster.monsterManager;

public class charManager {
	character carr[];
	int index;
	
	public charManager(int n) {
		carr = new character[n]; index=0;
	}
	public void newStart(monsterManager mm) {
		int ID;	
		
		ID=makeCharacter();
		IntoDungeon(ID, mm);
		EndDungeon(ID);
	}
	//ĳ���ͻ��� �� ĳ���;��̵� ����
	public int makeCharacter() {
		String name;
		Scanner scan = new Scanner(System.in);
		
		do {
			System.out.print("ĳ������ �̸��� �����ּ��� : "); name = scan.next();
			if(index == 0) break;
			if(findbyName(name) != null)	break;
			else {
				System.out.println("�̹� �ִ� �̸��Դϴ�. �ٸ� �̸��� �����ּ���.");
			}
		} while(true);
		
		int id= index++;
		character player = new character(id, name, 10, 5, 0);
		carr[id] = player;
		System.out.println("ĳ���Ͱ� �����Ǿ����ϴ�."); 
		player.showInfo(); System.out.println("");
		
		return id;
	}
	//�̸����� carr���� ĳ���� �̸� ���� ���� Ȯ��
	public String findbyName(String name) {
		for(int i=0; i<index; i++) {
			if(carr[i].name.equals(name))
				return carr[i].name;
		}
		return null;
	}
	//���̵�� carr���� ĳ���� ã��
	public character findbyID(int ID) {
		for(int i=0; i<index; i++) {
			if(carr[i].id == ID)
				return carr[i];
		}
		return null;
	}
	//��������
	public void IntoDungeon(int ID, monsterManager mm) {
		character player = new character();
		player = findbyID(ID);
		if(player == null) { System.out.println("������ �� ĳ���� ����"); }
		MeetMonster(player, mm, 1);
	}
	//���Ϳ� ����
	public void MeetMonster(character player, monsterManager mm, int monsterID) {
		String sel; Scanner scan = new Scanner(System.in);
		monster mon = new monster();
		mon=mm.findbyID(monsterID);
		if(mon == null) {
			System.out.println("���� ��ȣ ����");	return;
		}
		System.out.println(mon.getname()+"����.");
		mon.showInfo();
		
		System.out.println("-----------");
		System.out.println("��� �ұ�?");
		System.out.println("> �ο��");
		System.out.println("-----------");
		System.out.print("���� �Է�>>");	sel = scan.next();
		switch(sel) {
		case "�ο��":
			DoBattle(player, mon, mm);
			break;
		default:
			System.out.println("�׷� ������ �� �� ����.");
		}
	}
	//���Ϳ� �ο� �� �������
	public void DoBattle(character player, monster mon, monsterManager mm) {
		System.out.println("");
		System.out.println("���� ����");
		while(player.hp>=0) {
			Attack(player, mon, mm);
			if(mon.gethp()<=0) break;
			Attacked(player, mon);
		}
		
		if(mon.gethp()<=0) {
			System.out.println(mon.getname()+"(��)�� ��������."); System.out.println("");
			System.out.println("���� �¸�!");
			getReward(player, mon);
			player.showInfo(); System.out.println("");
		} else if(player.hp<=0){
			System.out.println("GAME OVER..");
			player.showInfo();
			return;
		}
	}
	//�÷��̾��� �Ϲݰ���
	public void Attack(character player, monster mon, monsterManager mm) {
		System.out.println(player.name+"(��)�� "+mon.getname()+"���� �Ϲݰ���");
		mm.Attacked(mon, player);
		System.out.println(mon.getname()+"(��)�� "+player.power+"��ŭ�� ���ظ� �Ծ���.");
		mon.showforBattle();	System.out.println("");
	}
	//�÷��̾ ���ݹ���
	public void Attacked(character player, monster mon) {
		System.out.println(mon.getname()+"(��)�� "+player.name+"�� ����");
		player.hp -= mon.getpower();
		System.out.println(player.name+"(��)�� "+mon.getpower()+"��ŭ�� ���ظ� �Ծ���.");
		player.showforBattle(); System.out.println("");
	}
	//��������
	public void getReward(character player, monster mon) {
		player.money += mon.getmoney();
		System.out.println(player.name+"(��)�� "+mon.getmoney()+"��带 �����.");
	}
	//���� �� ���
	public void EndDungeon(int ID) {
		character player = new character();
		player = findbyID(ID);
		
		if(player == null) {
			System.out.println("�ش� ���̵��� ĳ���� ������������"); return;
		} else if(player.hp == 0) {	
			System.out.println("You didn't save the world.."); return; 
		} else {
			System.out.println("Congraturation! ��� ���� ����Ʈ�Ƚ��ϴ�.");
			System.out.println("GAME CLEAR");
			player.showInfo();
		}
	}
}
