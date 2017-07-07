package extr;

public class PageBookmark {

	int pageNumber;
	String bookmark;
	
	public PageBookmark(int page, String bookmark){
		this.pageNumber = page;
		this.bookmark = bookmark;
	}

	public int getPage() {
		return pageNumber;
	}

	public void setPage(int page) {
		this.pageNumber = page;
	}

	public String getBookmark() {
		return bookmark;
	}

	public void setBookmark(String bookmark) {
		this.bookmark = bookmark;
	}
	
	@Override
	public String toString(){
		return (this.getPage() + "," + this.getBookmark() + "\n");
	}
}
