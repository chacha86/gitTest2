package board;

import java.util.ArrayList;
import java.util.Scanner;

public class ArticleController {

	ArticleDao articleDao = new ArticleDao();
	Member loginedMember = null;
	Scanner sc = new Scanner(System.in);
	
	void doCommand(String str) {

		if (str.equals("add")) {
			if (isLogined()) {
				addArticle();
			}
		} else if (str.equals("list")) {
			showArticles();

		} else if (str.equals("update")) {
			updateArticle();

		} else if (str.equals("delete")) {
			deleteArticle();

		} else if (str.equals("read")) {
			if (isLogined()) {
				readArticle();
			}
		} else if (str.equals("search")) {
			searchArticles();

		}
	}

	// ================================================================
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

	// ================================================================
	private void addArticle() {

		System.out.println("게시물 제목을 입력해주세요 :");
		String title = sc.nextLine();

		System.out.println("게시물 내용을 입력해주세요 :");
		String body = sc.nextLine();

		Article article = new Article(title, body, Util.getCurrentDate(), 0, loginedMember.getNickname(),
				loginedMember.getId());
		articleDao.insertArticle(article);

		System.out.println("게시물이 등록되었습니다.");

	}

	// ================================================================
	public boolean isLogined() {
		if (loginedMember == null) {
			System.out.println("로그인을 해야 사용 가능합니다.");
			return false;
		}
		return true;
	}

	// ================================================================

	private void updateArticle() {
		System.out.println("수정할 게시물 번호 :");
		int id = Integer.parseInt(sc.nextLine());

		Article article = articleDao.getArticleById(id);

		if (article == null) {
			System.out.println("없는 게시물입니다.");
		} else {
			System.out.println("제목 : ");
			String title = sc.nextLine(); // 1

			System.out.println("내용 : ");
			String body = sc.nextLine();

			articleDao.updateArticle(id, title, body);

			System.out.println("수정이 완료되었습니다.");

		}

	}
	// ================================================================

	private void deleteArticle() {

		System.out.println("삭제할 게시물 번호 :");
		int id = Integer.parseInt(sc.nextLine());

		int rst = articleDao.deleteArticleById(id);

		if (rst == 1) {
			System.out.println("삭제가 완료되었습니다.");
		} else {
			System.out.println("없는 게시물입니다.");
		}
	}

	// ================================================================

	private void readProcess(Article article) {

		while (true) {
			System.out.println("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
			int cmd = Integer.parseInt(sc.nextLine());

			if (cmd == 1) {

				System.out.println("댓글 내용을 입력해주세요 : ");
				String replyBody = sc.nextLine();

				Reply reply = new Reply(replyBody, "익명", Util.getCurrentDate(), article.getId());
				articleDao.insertReply(reply);

				System.out.println("댓글이 등록되었습니다.");
				printArticle(article);

			} else if (cmd == 2) {
				System.out.println("[좋아요 기능 구현할 것.]");
			} else if (cmd == 3) {

				if (article.getMemberId() == loginedMember.getId()) {
					System.out.println("새 제목 : ");
					String title = sc.nextLine();
					System.out.println("새 내용 : ");
					String body = sc.nextLine();

					articleDao.updateArticle(article.getId(), title, body);

					printArticle(article);

				} else {
					System.out.println("자신의 게시물만 수정 가능합니다.");
				}
			} else if (cmd == 4) {
				if (article.getMemberId() == loginedMember.getId()) {

					articleDao.deleteArticleById(article.getId());
					break;

				} else {
					System.out.println("자신의 게시물만 삭제 가능합니다.");
				}
			} else if (cmd == 5) {
				break;
			}

		}

	}

	// ================================================================
	private void printArticle(Article article) {
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
	private void searchArticles() {

		System.out.println("검색 항목을 선택해주세요 (1. 제목, 2. 내용, 3. 제목 + 내용, 4. 작성자) :");
		int targetFlag = Integer.parseInt(sc.nextLine());
		System.out.println("검색 키워드를 입력해주세요 :");
		String keyword = sc.nextLine();

		ArrayList<Article> searchedArticles = articleDao.getSearchedArticlesByFlagAndKeyword(targetFlag, keyword);
		printArticles(searchedArticles);
	}

	// =======================================================================
	private void readArticle() {
		System.out.println("상세보기 할 게시물 번호 :");
		int id = Integer.parseInt(sc.nextLine());

		Article article = articleDao.getArticleById(id);

		if (article == null) {
			System.out.println("없는 게시물입니다.");
		} else {
			printArticle(article);
			readProcess(article);
		}
	}
	// =======================================================================
	private void showArticles() {
		ArrayList<Article> articles = articleDao.getArticles();
		printArticles(articles);
	}
}
