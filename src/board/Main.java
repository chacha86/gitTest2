package board;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

	static ArrayList<Article> articles = new ArrayList<>();
	static ArrayList<Reply> replies = new ArrayList<>();
	static ArrayList<Member> members = new ArrayList<>();
	static Member loginedMember = null;
	
	
	static Scanner sc = new Scanner(System.in);
	static int articleLastId = 4;
	static int replyLastId = 1;

	public static void main(String[] args) {

		Article article1 = new Article(1, "안녕", "잘가", getCurrentDate(), 0, "홍길동");
		Article article2 = new Article(2, "제목2", "잘가세요", getCurrentDate(), 0, "이순신");
		Article article3 = new Article(3, "안녕하세요", "반갑습니다.", getCurrentDate(), 0, "강감찬");

		articles.add(article1);
		articles.add(article2);
		articles.add(article3);

		while (true) {
			
			if(loginedMember == null) {
				System.out.println("명령어 입력해주세요:");				
			} else {
				// System.out.printf() - 권장
				System.out.println("명령어 입력해주세요[" + loginedMember.getLoginId() + "(" +loginedMember.getNickname() + ")]:");
			}
			String str = sc.next();
			if (str.equals("exit")) {
				System.out.println("프로그램이 종료됩니다.");
				break;
			} else if (str.equals("add")) {
				addArticle();
			} else if (str.equals("list")) {
				printArticles(articles);
			} else if (str.equals("update")) {
				updateArticle();
			} else if (str.equals("delete")) {
				deleteArticle();
			} else if (str.equals("read")) {
				readArticle();
			} else if (str.equals("search")) {
				searchArticles();
			} else if (str.equals("signup")) {
				signUpMember();
			} else if (str.equals("signin")) {
				signInMember();
			}
		}

	}

	private static void signInMember() {
		System.out.println("아이디 :");
		String inputedId = sc.next();
		System.out.println("비밀번호 :");
		String inputedPw = sc.next();
		boolean isSuccessed = true;
		
		for (int i = 0; i < members.size(); i++) {
			if (inputedId.equals(members.get(i).getLoginId()) && inputedPw.equals(members.get(i).getLoginPw())) {
				isSuccessed = false;
				loginedMember = members.get(i);
				String name = loginedMember.getNickname();
				System.out.println(name +"님 환영합니다!");
				break;
			} 
		}
		
		if(isSuccessed) {
			System.out.println("잘못된 회원정보입니다.");
		}
	}

	private static void signUpMember() {
		System.out.println("==== 회원 가입을 진행합니다 ====");
		System.out.println("아이디를 입력해주세요 : ");
		String loginId = sc.next();
		System.out.println("비밀번호를 입력해주세요 : ");
		String loginPw = sc.next();
		System.out.println("닉네임을 입력해주세요 : ");
		String nickname = sc.next();

		Member member = new Member(loginId, loginPw, nickname);
		members.add(member);

		System.out.println("==== 회원가입이 완료되었습니다. ====");
	}

	// =======================================================================
	private static void searchArticles() {

		System.out.println("검색 항목을 선택해주세요 (1. 제목, 2. 내용, 3. 제목 + 내용, 4. 작성자) :");
		int targetFlag = sc.nextInt();
		System.out.println("검색 키워드를 입력해주세요 :");
		String keyword = sc.next();

		ArrayList<Article> searchedArticles = new ArrayList<>();

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			String targetStr = "";

			if (targetFlag == 1) {
				targetStr = article.getTitle();
			} else if (targetFlag == 2) {
				targetStr = article.getBody();
			} else if (targetFlag == 3) {
				targetStr = article.getTitle() + article.getBody();
			} else if (targetFlag == 4) {
				targetStr = article.getNickname();
			}

			if (targetStr.contains(keyword)) {
				searchedArticles.add(article);
			}
		}

		printArticles(searchedArticles);
	}

	// =======================================================================
	private static void readArticle() {
		System.out.println("상세보기 할 게시물 번호 :");
		int id = sc.nextInt();
		int targetIdx = getIndexByArticleId(id);

		if (targetIdx != -1) {
			Article article = articles.get(targetIdx);
			int cnt = article.getHit() + 1;
			article.setHit(cnt);

			printArticle(article);
			readProcess(article);

		} else {
			System.out.println("없는 게시물입니다.");
		}

	}

	// =======================================================================
	private static void printArticle(Article article) {
		System.out.println("====== " + article.getId() + "번 게시물 ======");
		System.out.println("번호 : " + article.getId());
		System.out.println("제목 : " + article.getTitle());
		System.out.println("내용 : " + article.getBody());
		System.out.println("작성자 : " + article.getNickname());
		System.out.println("조회수 : " + article.getHit());
		System.out.println("등록날짜 : " + article.getRegDate());
		System.out.println("========================");
		System.out.println("==========댓글==========");
		for (int i = 0; i < replies.size(); i++) {
			Reply reply = replies.get(i);
			if (article.getId() == reply.getParentId()) {
				System.out.println("내용 : " + reply.getBody());
				System.out.println("작성자 : " + reply.getNickname());
				System.out.println("작성일 : " + reply.getRegDate());
			}
		}

	}

	// =======================================================================
	private static void readProcess(Article article) {

		while (true) {
			System.out.println("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
			int cmd = sc.nextInt();

			if (cmd == 1) {

				System.out.println("댓글 내용을 입력해주세요 : ");
				String replyBody = sc.next();

				Reply reply = new Reply(replyLastId, replyBody, "익명", getCurrentDate(), article.getId());
				replies.add(reply);

				System.out.println("댓글이 등록되었습니다.");
				printArticle(article);

			} else if (cmd == 2) {
				System.out.println("[좋아요 기능 구현할 것.]");
			} else if (cmd == 3) {
				System.out.println("[수정 기능 구현할 것.]");
			} else if (cmd == 4) {
				System.out.println("[삭제 기능 구현할 것.]");
			} else if (cmd == 5) {
				break;
			}

		}

	}

	// =======================================================================
	private static void deleteArticle() {

		System.out.println("삭제할 게시물 번호 :");
		int id = sc.nextInt();
		int targetIdx = getIndexByArticleId(id);

		if (targetIdx != -1) {

			articles.remove(targetIdx);

			System.out.println("삭제가 완료되었습니다.");
		} else {
			System.out.println("없는 게시물입니다.");
		}
	}

	// =======================================================================
	private static void updateArticle() {
		System.out.println("수정할 게시물 번호 :");
		int id = sc.nextInt();
		int targetIdx = getIndexByArticleId(id);

		if (targetIdx != -1) {
			System.out.println("제목 : ");
			String title = sc.next(); // 1

			System.out.println("내용 : ");
			String body = sc.next();

			Article article = new Article(id, title, body, getCurrentDate(), 0, "익명");

			articles.set(targetIdx, article);

			System.out.println("수정이 완료되었습니다.");

		} else {
			System.out.println("없는 게시물입니다.");
		}

	}

	// =======================================================================
	private static void addArticle() {

		System.out.println("게시물 제목을 입력해주세요 :");
		String title = sc.next();

		System.out.println("게시물 내용을 입력해주세요 :");
		String body = sc.next();

		Article article = new Article(articleLastId, title, body, getCurrentDate(), 0, "익명");
		articleLastId++;

		articles.add(article);

		System.out.println("게시물이 등록되었습니다.");

	}

	// =======================================================================
	public static int getIndexByArticleId(int id) {
		int targetIdx = -1;

		for (int i = 0; i < articles.size(); i++) {
			if (articles.get(i).getId() == id) {
				targetIdx = i;
			}
		}

		return targetIdx;
	}

	// =======================================================================
	public static void printArticles(ArrayList<Article> articles) {

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			String date = article.getRegDate();
			date = date.substring(0, 10);

			System.out.println("번호 : " + article.getId()); // 번호X, article
			System.out.println("제목 : " + article.getTitle());
			System.out.println("작성자 : " + article.getNickname());
			System.out.println("조회수 : " + article.getHit());
			System.out.println("등록날짜 : " + date);
			System.out.println("===============================");
		}
	}

	// =======================================================================
	private static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd HH:ss");
		Date time = new Date();
		String time1 = format1.format(time);

		return time1;
	}
	// =======================================================================
}
