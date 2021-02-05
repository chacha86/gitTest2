package board;

public class Like {
	
	private int id; // 식별자
	private int aid; // 좋아요한 게시물 번호
	private int mid; // 좋아요한 유저 번호
	private String regDate; // 등록날짜
	
	public Like(int id, int aid, int mid, String regDate) {
		super();
		this.id = id;
		this.aid = aid;
		this.mid = mid;
		this.regDate = regDate;
	}
	
	public Like(int aid, int mid, String regDate) {
		super();
		this.aid = aid;
		this.mid = mid;
		this.regDate = regDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
}
