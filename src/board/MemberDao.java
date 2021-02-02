package board;

import java.util.ArrayList;

// 회원 데이터 처리 전문가
public class MemberDao {
	
	ArrayList<Member> members = new ArrayList<>();
	int lastMemberId = 4;
	
	public MemberDao() {
		
		Member m1 = new Member(1, "hong123", "1234", "홍길동");
		Member m2 = new Member(2, "lee123", "6789", "이순신");
		Member m3 = new Member(3, "kim123", "asdf", "김좌진");
		
		members.add(m1);
		members.add(m2);
		members.add(m3);
	}
	
	public Member getMemberByLoginIdAndLoginPw(String inputedId, String inputedPw) {
		for (int i = 0; i < members.size(); i++) {
			if (inputedId.equals(members.get(i).getLoginId()) && inputedPw.equals(members.get(i).getLoginPw())) {
				return members.get(i); // Member
			} 
		}		
		
		return null; // 객체 타입을 리턴할 때, 리턴할 값이 없으면 null 리턴.
	}
	
	public void insertMember(Member member) {
		member.setId(lastMemberId);
		lastMemberId++;
		members.add(member);
	}
	
}
