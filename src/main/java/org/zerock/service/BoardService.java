package org.zerock.service;

import java.util.List;

import org.zerock.vo.BoardVO;

public interface BoardService {

	int getCount();

	List<BoardVO> getList();

	void insertBoard(BoardVO b);

	BoardVO getCont(int bno);

	
	
}
