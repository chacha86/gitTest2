package board;

public class Reply {
	
	private int id; // 데이터 식별
	private int parentId; // 부모글(원글) 번호
	private String body; // 댓글 내용
	private String nickname; // 댓글 작성자
	private String regDate; // 데이터 추적
	
	public Reply(int id, String body, String nickname, String regDate, int parentId) {
		super();
		this.id = id;
		this.body = body;
		this.nickname = nickname;
		this.regDate = regDate;
		this.parentId = parentId;
	}
	
	public Reply(String body, String nickname, String regDate, int parentId) {
		super();
		this.body = body;
		this.nickname = nickname;
		this.regDate = regDate;
		this.parentId = parentId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
}
