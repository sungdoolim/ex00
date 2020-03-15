package org.zerock.dao;

import java.util.List;

import org.zerock.vo.BoardVO;

public interface BoardDAO {//parent가 없음 = object를 상속하지 않음

	void insertBoard(BoardVO b);//추상 : 호출 불가, 자손이 오버라이딩 필수 

	int getCount();

	List<BoardVO> getList(BoardVO b);

	void updateHit(int bno);

	BoardVO getCont(int bno);

	void editBoard(BoardVO eb);

	void delBoard(int bno);

	void updateReplyCnt(int bno, int amount);
	
	

	
	
	
	
}
