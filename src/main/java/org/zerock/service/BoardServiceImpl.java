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
	public List<BoardVO> getList(BoardVO b) {
		return this.boardDao.getList(b);
	}

	@Override
	public void insertBoard(BoardVO b) {
		this.boardDao.insertBoard(b);
		
	}

	// 스프링 aop를 통한 트랜잭션을 적용하여 데이터 일치 -- 
	@Override
	public BoardVO getCont(int bno) {
		// 두가지 기능 : content보내기, 조회수 올리기 
		
		this.boardDao.updateHit(bno);// 조회수 증가
		
		
		
		
		return this.boardDao.getCont(bno);// content 가져오기
	}

	@Override
	public BoardVO getCont2(int bno) {
		
		return this.boardDao.getCont(bno);
	}

	@Override
	public void editBoard(BoardVO eb) {
		
		this.boardDao.editBoard(eb);
		
		
	}

	@Override
	public void delBoard(int bno) {
		
		this.boardDao.delBoard(bno);
		
		
		
	}
	
	
	
	
}
