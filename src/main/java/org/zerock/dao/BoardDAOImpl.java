package org.zerock.dao;

import java.util.List;

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

	@Override
	public int getCount() {
		//총 레코드 갯수를 리턴
		
		return this.sqlSession.selectOne("b_count");
		// mybaits에서 selectOne은 단 한개의 레코드만 반환
		// b_count는 board.xml에 설정할 유일한 select 아이디명
	}

	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		return this.sqlSession.selectList("b_list");//mybatis에서 selectList는 하나 이상의 복수개의 레코드를 검색해서 컬렉션 List로 반환, b_list.는 select의 아이디명 
	}

	@Override
	public void updateHit(int bno) {
		
		this.sqlSession.update("b_hit", bno);
		//b_hit : update아이디 명 
	}

	@Override
	public BoardVO getCont(int bno) {
		// TODO Auto-generated method stub
		
		
		return this.sqlSession.selectOne("b_cont",bno);
	}

	
	
	
}
