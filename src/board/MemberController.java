package board;

public class MemberController extends Controller {
	
	MemberDao memberDao = new MemberDao();
	
	void doCommand(String str) {
		
		if (str.equals("signup")) {
			signUpMember();
			
		} else if (str.equals("signin")) {
			signInMember();
			
		} else if(str.equals("logout")) {
			if(isLogined()) {
				logoutMember();				
			}
		}
	}
	
	// =======================================================================
	private void logoutMember() {
		loginedMember = null;
		System.out.println("로그아웃 되셨습니다.");
	}
	// =======================================================================
	private void signInMember() {
		System.out.println("아이디 :");
		String inputedId = sc.nextLine();
		System.out.println("비밀번호 :");
		String inputedPw = sc.nextLine();
				
		Member member = memberDao.getMemberByLoginIdAndLoginPw(inputedId, inputedPw);
		
		if(member == null) {
			System.out.println("잘못된 회원 정보입니다.");
		} else {
			System.out.println(member.getNickname() + "님 환영합니다!!");
			loginedMember = member;
		}
	}
	// =======================================================================
	private void signUpMember() {
		System.out.println("==== 회원 가입을 진행합니다 ====");
		System.out.println("아이디를 입력해주세요 : ");
		String loginId = sc.nextLine();
		System.out.println("비밀번호를 입력해주세요 : ");
		String loginPw = sc.nextLine();
		System.out.println("닉네임을 입력해주세요 : ");
		String nickname = sc.nextLine();

		Member member = new Member(loginId, loginPw, nickname);
		memberDao.insertMember(member);
		System.out.println("==== 회원가입이 완료되었습니다. ====");
	}
}
