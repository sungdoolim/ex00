package org.zerock.vo;

public class ReplyVO {// 데이터 저장빈 클래스 , 댓글 테이블 컬럼명과 변수명을 일치시키는 것이 좋다

	private int rno;// 댓글 번호
	private int bno;// 게시물 번호
	private String replyer;// 작성자
	private String replytext;//댓글 내용
	private String regdate;//댓글등록 날짜
	private String updatedate;//댓글 수정 날짜
	
	
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public String getReplytext() {
		return replytext;
	}
	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	
	
	
	
	
}
