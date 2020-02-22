package org.zerock.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sqlSession;// mybatis쿼리문 수행객체 의존성 주입 = di

	@Override
	public void insertBoard(BoardVO b) {// 게시판 저장
	this.sqlSession.insert("b_in",b);//b_in은 insert아이디 명 
		
	}
	
	
	
}
