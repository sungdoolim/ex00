package org.zerock.dao;

import java.util.List;

import org.zerock.vo.ReplyVO;

public interface ReplyDAO {

	void addReply(ReplyVO vo);

	List<ReplyVO> list(int bno);

	void updateReply(ReplyVO vo);
	//public abstract가 생략되어있으며 추상메서드로서 {}가 없고 호출 불가, 인터페이스는 객체 생성 못함

	void delReply(int rno);

	int getBno(int rno);//댓글 번호를 기준으로 게시물 번호값을 구함
	

}
