package board;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static MemberDao memberDao = new MemberDao();
	static ArticleDao articleDao = new ArticleDao();
	static Member loginedMember = null;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

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
				ArrayList<Article> articles = articleDao.getArticles();
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
				
		Member member = memberDao.getMemberByLoginIdAndLoginPw(inputedId, inputedPw);
		
		if(member == null) {
			System.out.println("잘못된 회원 정보입니다.");
		} else {
			System.out.println(member.getNickname() + "님 환영합니다!!");
			loginedMember = member;
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
		memberDao.insertMember(member);
		System.out.println("==== 회원가입이 완료되었습니다. ====");
	}

	// =======================================================================
	private static void searchArticles() {

		System.out.println("검색 항목을 선택해주세요 (1. 제목, 2. 내용, 3. 제목 + 내용, 4. 작성자) :");
		int targetFlag = sc.nextInt();
		System.out.println("검색 키워드를 입력해주세요 :");
		String keyword = sc.next();

		ArrayList<Article> searchedArticles = articleDao.getSearchedArticlesByFlagAndKeyword(targetFlag, keyword);
		printArticles(searchedArticles);
	}

	// =======================================================================
	private static void readArticle() {
		System.out.println("상세보기 할 게시물 번호 :");
		int id = sc.nextInt();
		
		Article article = articleDao.getArticleById(id);
			
		if(article == null) {
			System.out.println("없는 게시물입니다.");			
		} else {
			printArticle(article);
			readProcess(article);
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
		
		ArrayList<Reply> replies = new ArrayList<>();
		replies = articleDao.getReplies();
		
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

				Reply reply = new Reply(replyBody, "익명", Util.getCurrentDate(), article.getId());
				articleDao.insertReply(reply);
				
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
		
		int rst = articleDao.deleteArticleById(id);
		
		if(rst == 1) {
			System.out.println("삭제가 완료되었습니다.");
		} else {
			System.out.println("없는 게시물입니다.");
		}
	}

	// =======================================================================
	private static void updateArticle() {
		System.out.println("수정할 게시물 번호 :");
		int id = sc.nextInt();

		Article article = articleDao.getArticleById(id);
		
		if(article == null) {
			System.out.println("없는 게시물입니다.");
		} else {
			System.out.println("제목 : ");
			String title = sc.next(); // 1

			System.out.println("내용 : ");
			String body = sc.next();

			articleDao.updateArticle(id, title, body);
			
			System.out.println("수정이 완료되었습니다.");
	
		}

	}

	// =======================================================================
	private static void addArticle() {

		System.out.println("게시물 제목을 입력해주세요 :");
		String title = sc.next();

		System.out.println("게시물 내용을 입력해주세요 :");
		String body = sc.next();

		Article article = new Article(title, body, Util.getCurrentDate(), 0, "익명");
		articleDao.insertArticle(article);

		System.out.println("게시물이 등록되었습니다.");

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
}

