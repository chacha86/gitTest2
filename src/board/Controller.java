package board;

import java.util.Scanner;

public abstract class Controller {
	Scanner sc = new Scanner(System.in);
	
	abstract void doCommand(String str);
	
	public boolean isLogined() {
		if (App.loginedMember == null) {
			System.out.println("로그인을 해야 사용 가능합니다.");
			return false;
		}
		return true;
	}
}
