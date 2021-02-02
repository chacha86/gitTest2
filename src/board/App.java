package board;

import java.util.Scanner;

public class App {
	Scanner sc = new Scanner(System.in);
	Controller controller;
	Member loginedMember = null;
	
	void start() {
		while (true) {
			if(loginedMember == null) {
				System.out.println("명령어 입력해주세요:");				
			} else {
				// System.out.printf() - 권장
				System.out.println("명령어 입력해주세요[" + loginedMember.getLoginId() + "(" + loginedMember.getNickname() + ")]:");
			}
			
			System.out.println("명령어 입력해주세요:");
			String str = sc.nextLine();
			
			String[] strBits = str.split(" ");
			String module = strBits[0];
			String cmd = strBits[1];
			
			if(module.equals("article")) {
				controller = new ArticleController();
			} else if(module.equals("member")) {
				controller = new MemberController();
			}
		
			controller.doCommand(cmd);
			
			if (str.equals("exit")) {
				System.out.println("프로그램이 종료됩니다.");
				break;
			}  
		}
	}
}	
