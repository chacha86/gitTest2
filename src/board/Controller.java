package board;

import java.util.Scanner;

public abstract class Controller {
	Scanner sc = new Scanner(System.in);
	Member loginedMember = null;
	
	abstract void doCommand(String str);
	
	public boolean isLogined() {
		if (loginedMember == null) {
			System.out.println("로그인을 해야 사용 가능합니다.");
			return false;
		}
		return true;
	}
}
