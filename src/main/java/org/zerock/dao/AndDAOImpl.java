package org.zerock.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.vo.BoardVO;


@Repository
public class AndDAOImpl implements AndDAO {

	@Inject
	private SqlSession sqlSession;// mybatis쿼리문 수행객체 의존성 주입 = di

	@Override
	public String getpw(String id) {
		// TODO Auto-generated method stub
		System.out.println("전달 받은 id ="+id);
		String pw=sqlSession.selectOne("A_pw",id);
		System.out.println("메서드 내에 pw="+ pw);
		return pw;
	}


	
	
	
}
