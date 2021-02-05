package Test;

import java.util.ArrayList;
import java.util.Scanner;

import board.Article;

public class Test9 {

	public static void main(String[] args) {

		ArrayList<Article> articles = new ArrayList<Article>();

		for (int i = 1; i <= 100; i++) {
			Article article = new Article(i, "제목" + i, "작성자" + i);
			articles.add(article);
		}

		Scanner sc = new Scanner(System.in);
		int currentPageNo = 6; // 현재 선택한 페이지 번호
		int pageCountPerBlock = 5; // 한 블록당 페이지 개수
		int itemCountPerPage = 3; // 한 페이지당 아이템 개수
		int totalCountOfItem = 100; // 아이템 100개
		int lastPageNo = (int) Math.ceil((double) totalCountOfItem / itemCountPerPage); // 페이지 개수 -> 100 / 3 == 33.333

		while (true) {

			
			System.out.println("페이지 번호 입력 : ");
			currentPageNo = Integer.parseInt(sc.nextLine());

			if (currentPageNo < 1) {
				System.out.println("시작 페이지는 1부터입니다.");
				currentPageNo = 1;
			}

			if (currentPageNo > lastPageNo) {
				System.out.println("마지막 페이지를 넘어갔습니다.");
				currentPageNo = lastPageNo;
			}

			// 현재 페이지 블록 번호 구하기 (현재페이지 / 한 블록당 페이지 개수)(올림)
			// 2 / 5 == 0.4 - 1
			// 6 / 5 == 1.xxx - 2
			// 11 / 5 == 2.xxx - 3

			double rst = (double) currentPageNo / pageCountPerBlock;
			int currentPageBlockNo = (int) Math.ceil(rst); // 현재 페이지 블록

			int startPageNoInCurrentBlock = (currentPageBlockNo - 1) * 5 + 1; // 현재 블록 내에서 시작 페이지 번호
			int endPageNoInCurrentBlock = startPageNoInCurrentBlock + (pageCountPerBlock - 1); // 현재 블록 내에서 마지막 페이지 번호

			if (endPageNoInCurrentBlock > lastPageNo) {
				endPageNoInCurrentBlock = lastPageNo;
			}

			// ================ 게시물 출력 ===============

			// 1 - 3 * (1 - 1)  ~ 2
			// 2 - 3 * (2 - 1)  ~ 5
			// 3 - 3 * (3 - 1)  ~ 7
			
			int startIndex = (currentPageNo - 1) * itemCountPerPage;
			int endIndex = startIndex + itemCountPerPage;
			
			if(endIndex > totalCountOfItem) {
				endIndex = totalCountOfItem;
			}
			for (int i = startIndex; i < endIndex; i++) {
				System.out.println("번호 : " + articles.get(i).getId());
				System.out.println("제목 : " + articles.get(i).getTitle());
				System.out.println("작성자 : " + articles.get(i).getNickname());
				System.out.println("=========================================");
			}

			// =============================================
			
			for (int i = startPageNoInCurrentBlock; i <= endPageNoInCurrentBlock; i++) {
				if (i == currentPageNo) {
					System.out.print("[" + i + "] ");
				} else {
					System.out.print(i + " ");
				}
			}
			System.out.println();
		}

	}

}
