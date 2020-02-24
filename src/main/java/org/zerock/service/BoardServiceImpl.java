package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.dao.BoardDAO;
import org.zerock.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired // 자동 의존성 주입 ==inject 보다 많이 쓰임
	private BoardDAO boardDao;

	@Override
	public int getCount() {
		
		
		return this.boardDao.getCount();
	}

	@Override
	public List<BoardVO> getList() {
		return this.boardDao.getList();
	}
	
	
	
	
}
