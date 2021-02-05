package board;

public class Pagination {
	private int currentPageNo; // 현재 선택한 페이지 번호
	private int pageCountPerBlock; // 한 블록당 페이지 개수
	private int itemCountPerPage; // 한 페이지당 아이템 개수
	private int totalCountOfItem; // 아이템 100개

	// 초기화
	public Pagination(int totalCountOfItem) {
		this.currentPageNo = 1;
		this.pageCountPerBlock = 5;
		this.itemCountPerPage = 3;
		this.totalCountOfItem = totalCountOfItem;
	}

	// 마지막 페이지 번호 가져오기
	public int getLastPageNo() {
		return (int) Math.ceil((double) totalCountOfItem / itemCountPerPage); // 페이지 개수 -> 100 / 3 == 33.333
	}

	// 현재 페이지 블록 번호 가져오기
	public int getCurrentPageBlockNo() {
		double rst = (double) currentPageNo / pageCountPerBlock;
		return (int) Math.ceil(rst); // 현재 페이지 블록
	}

	// 현재 블록 내에서 시작 페이지 번호 가져오기
	public int getStartPageNoInCurrentBlock() {
		return (getCurrentPageBlockNo() - 1) * pageCountPerBlock + 1; 
	}
	
	// 현재 블록 내에서 마지막 페이지 번호 가져오기
	public int getEndPageNoInCurrentBlock() { 
		
		int endPageNoInCurrentBlock = getStartPageNoInCurrentBlock() + (pageCountPerBlock - 1);
		if (endPageNoInCurrentBlock > getLastPageNo()) {
			endPageNoInCurrentBlock = getLastPageNo();
		}
		
		return endPageNoInCurrentBlock;
	}
	
	// 현재 페이지의 시작 아이템 인덱스
	public int getStartItemIndex() {
		return (currentPageNo - 1) * itemCountPerPage;
	}
	
	// 현재 페이지의 마지막 아이템 인덱스
	public int getEndItemIndex() {
		return getStartItemIndex() + itemCountPerPage;
	}
	
		
	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getPageCountPerBlock() {
		return pageCountPerBlock;
	}

	public void setPageCountPerBlock(int pageCountPerBlock) {
		this.pageCountPerBlock = pageCountPerBlock;
	}

	public int getItemCountPerPage() {
		return itemCountPerPage;
	}

	public void setItemCountPerPage(int itemCountPerPage) {
		this.itemCountPerPage = itemCountPerPage;
	}

	public int getTotalCountOfItem() {
		return totalCountOfItem;
	}

	public void setTotalCountOfItem(int totalCountOfItem) {
		this.totalCountOfItem = totalCountOfItem;
	}
}
