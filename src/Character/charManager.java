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
	//캐릭터생성 후 캐릭터아이디 전달
	public int makeCharacter() {
		String name;
		Scanner scan = new Scanner(System.in);
		
		do {
			System.out.print("캐릭터의 이름을 정해주세요 : "); name = scan.next();
			if(index == 0) break;
			if(findbyName(name) != null)	break;
			else {
				System.out.println("이미 있는 이름입니다. 다른 이름을 정해주세요.");
			}
		} while(true);
		
		int id= index++;
		character player = new character(id, name, 10, 5, 0);
		carr[id] = player;
		System.out.println("캐릭터가 생성되었습니다."); 
		player.showInfo(); System.out.println("");
		
		return id;
	}
	//이름으로 carr에서 캐릭터 이름 존재 여부 확인
	public String findbyName(String name) {
		for(int i=0; i<index; i++) {
			if(carr[i].name.equals(name))
				return carr[i].name;
		}
		return null;
	}
	//아이디로 carr에서 캐릭터 찾기
	public character findbyID(int ID) {
		for(int i=0; i<index; i++) {
			if(carr[i].id == ID)
				return carr[i];
		}
		return null;
	}
	//던전진입
	public void IntoDungeon(int ID, monsterManager mm) {
		character player = new character();
		player = findbyID(ID);
		if(player == null) { System.out.println("던전에 들어간 캐릭터 오류"); }
		MeetMonster(player, mm, 1);
	}
	//몬스터와 만남
	public void MeetMonster(character player, monsterManager mm, int monsterID) {
		String sel; Scanner scan = new Scanner(System.in);
		monster mon = new monster();
		mon=mm.findbyID(monsterID);
		if(mon == null) {
			System.out.println("몬스터 번호 에러");	return;
		}
		System.out.println(mon.getname()+"등장.");
		mon.showInfo();
		
		System.out.println("-----------");
		System.out.println("어떻게 할까?");
		System.out.println("> 싸운다");
		System.out.println("-----------");
		System.out.print("선택 입력>>");	sel = scan.next();
		switch(sel) {
		case "싸운다":
			DoBattle(player, mon, mm);
			break;
		default:
			System.out.println("그런 선택은 할 수 없어.");
		}
	}
	//몬스터와 싸움 후 결과전달
	public void DoBattle(character player, monster mon, monsterManager mm) {
		System.out.println("");
		System.out.println("전투 시작");
		while(player.hp>=0) {
			Attack(player, mon, mm);
			if(mon.gethp()<=0) break;
			Attacked(player, mon);
		}
		
		if(mon.gethp()<=0) {
			System.out.println(mon.getname()+"(이)가 쓰러졌다."); System.out.println("");
			System.out.println("전투 승리!");
			getReward(player, mon);
			player.showInfo(); System.out.println("");
		} else if(player.hp<=0){
			System.out.println("GAME OVER..");
			player.showInfo();
			return;
		}
	}
	//플레이어의 일반공격
	public void Attack(character player, monster mon, monsterManager mm) {
		System.out.println(player.name+"(이)가 "+mon.getname()+"에게 일반공격");
		mm.Attacked(mon, player);
		System.out.println(mon.getname()+"(은)는 "+player.power+"만큼의 피해를 입었다.");
		mon.showforBattle();	System.out.println("");
	}
	//플레이어가 공격받음
	public void Attacked(character player, monster mon) {
		System.out.println(mon.getname()+"(이)가 "+player.name+"를 공격");
		player.hp -= mon.getpower();
		System.out.println(player.name+"(은)는 "+mon.getpower()+"만큼의 피해를 입었다.");
		player.showforBattle(); System.out.println("");
	}
	//전투보상
	public void getReward(character player, monster mon) {
		player.money += mon.getmoney();
		System.out.println(player.name+"(은)는 "+mon.getmoney()+"골드를 얻었다.");
	}
	//엔딩 및 결과
	public void EndDungeon(int ID) {
		character player = new character();
		player = findbyID(ID);
		
		if(player == null) {
			System.out.println("해당 아이디의 캐릭터 존재하지않음"); return;
		} else if(player.hp == 0) {	
			System.out.println("You didn't save the world.."); return; 
		} else {
			System.out.println("Congraturation! 모든 적을 쓰러트렸습니다.");
			System.out.println("GAME CLEAR");
			player.showInfo();
		}
	}
}
