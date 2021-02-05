package board;

import java.util.Scanner;

public class App {
	Scanner sc = new Scanner(System.in);
	Controller controller;
	static Member loginedMember = null;
	
	void start() {
		while (true) {
			
			if(loginedMember == null) {
				System.out.println("명령어 입력해주세요:");				
			} else {
				// System.out.printf() - 권장
				System.out.println("명령어 입력해주세요[" + loginedMember.getLoginId() + "(" + loginedMember.getNickname() + ")]:");
			}
			
			String str = sc.nextLine();
			
			String[] strBits = str.split(" ");
			String module = "";
			String cmd = "";
			
			if(strBits.length == 2) {
				module = strBits[0];
				cmd = strBits[1];				
			}
			
			if(module.equals("article")) {
				controller = new ArticleController();
			} else if(module.equals("member")) {
				controller = new MemberController();
			} else {
				System.out.println("알 수 없는 명령어입니다.");
			}
			
			if(controller != null) {
				controller.doCommand(cmd);				
			}
			
			if (str.equals("exit")) {
				System.out.println("프로그램이 종료됩니다.");
				break;
			}  
		}
	}
}	
