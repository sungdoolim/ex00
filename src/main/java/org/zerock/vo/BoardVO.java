package org.zerock.vo;

public class BoardVO {//데이터 저장빈 클래스
	/*
	 * 테이블 컬럼명과 변수명 일치시키기
	 */
	private int bno;//게시판 번호
	private String writer;//작성자
	private String title;// 제목
	private String content;//내용
	private int viewcnt;//조회수
	private String regdate;//등록날짜
	
	
	
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	

	
	
	
	

}
