package board;

import java.util.ArrayList;

// 회원 데이터 처리 전문가
public class MemberDao {
	
	ArrayList<Member> members = new ArrayList<>();
	
	public Member getMemberByLoginIdAndLoginPw(String inputedId, String inputedPw) {
		for (int i = 0; i < members.size(); i++) {
			if (inputedId.equals(members.get(i).getLoginId()) && inputedPw.equals(members.get(i).getLoginPw())) {
				return members.get(i); // Member
			} 
		}		
		
		return null; // 객체 타입을 리턴할 때, 리턴할 값이 없으면 null 리턴.
	}
	
	public void insertMember(Member member) {
		members.add(member);
	}
	
}
