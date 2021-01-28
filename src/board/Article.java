package board;

public class Article {
	
	private int id;// 번호
	private String title;// 제목
	private String body;// 내용
	private String regDate; // 등록날짜
	private int hit; // 조회수
	private String nickname; // 작성자
	
	public Article(int id, String title, String body, String regDate, int hit, String nickname) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.hit = hit;
		this.nickname = nickname;
	}
	
	public Article(String title, String body, String regDate, int hit, String nickname) {
		super();
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.hit = hit;
		this.nickname = nickname;
	}

	// 게터, 세터
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	
}

