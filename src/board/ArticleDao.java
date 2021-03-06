package board;

import java.util.ArrayList;

// DAO - DATA ACCESS OBJECT : 게시물 데이터 처리 전문가
public class ArticleDao {

	ArrayList<Article> articles = new ArrayList<>();
	ArrayList<Reply> replies = new ArrayList<>();
	ArrayList<Like> likes = new ArrayList<>();
	
	int articleLastId = 4;
	int replyLastId = 1;
	int likeLastId = 1;
	
	// 생성자 이용해서 테스트 데이터 초기화
	ArticleDao() {
		Article article1 = new Article(1, "안녕", "잘가", Util.getCurrentDate(), 20, "홍길동", 1);
		Article article2 = new Article(2, "제목2", "잘가세요", Util.getCurrentDate(), 10, "이순신", 2);
		Article article3 = new Article(3, "안녕하세요", "반갑습니다.", Util.getCurrentDate(), 30, "홍길동", 1);

		articles.add(article1);
		articles.add(article2);
		articles.add(article3);
		
		for(int i = 4; i <= 100; i++) {
			Article a = new Article(i, "안녕" + i, "잘가" + i, Util.getCurrentDate(), i, "홍길동" + i, 1);
			articles.add(a);
		}
		
	}
	
	public Article getArticleById(int id) {
		int targetIdx = getIndexByArticleId(id);

		if (targetIdx != -1) {
			Article article = articles.get(targetIdx);
			return article;
		}
		
		return null;
	}
	
	
	public int getIndexByArticleId(int id) {
		int targetIdx = -1;

		for (int i = 0; i < articles.size(); i++) {
			if (articles.get(i).getId() == id) {
				targetIdx = i;
			}
		}

		return targetIdx;
	}

	public ArrayList<Reply> getReplies() {
		
		return replies;
	}
	
	public void insertReply(Reply reply) {		
		reply.setId(replyLastId);
		replyLastId++;
		replies.add(reply);
	}

	public int deleteArticleById(int id) {
		Article article = getArticleById(id);
		if(article == null) {
			return 0;
		} else {
			articles.remove(article);
			return 1;
		}
		
	}

	public int updateArticle(int id, String title, String body) {
		Article article = getArticleById(id);
		if(article == null) {
			return 0;
		} else {
			article.setTitle(title);
			article.setBody(body);
			return 1;
		}
	}

	public void insertArticle(Article article) {
		article.setId(articleLastId);
		articleLastId++;
		articles.add(article);
	}
	
	public ArrayList<Article> getSearchedArticlesByFlagAndKeyword(int targetFlag, String keyword) {
		
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
		
		return searchedArticles;
	}

	public ArrayList<Article> getArticles() {
		
		return articles;
	}

	public void insertLike(Like like) {
		
		like.setId(likeLastId);
		likeLastId++;
		likes.add(like);
		
	}
	
	public Like getLikeByArticleIdAndMemberId(int aid, int mid) {
		for(int i = 0; i < likes.size(); i++) {
			if(likes.get(i).getAid() == aid && likes.get(i).getMid() == mid) {
				return likes.get(i);
			}
		}
		
		return null;
	}

	public void deleteLike(Like targetLike) {
		likes.remove(targetLike);
	}

	public int getCountOfLikeByArticleId(int aid) {
		int cnt = 0;
		
		for(int i = 0; i < likes.size(); i++) {
			
			if(likes.get(i).getAid() == aid) {
				cnt++;
			}
		}
		
		return cnt;
	}

	public ArrayList<Like> getLikes() {
		return this.likes;
	}
	
}
