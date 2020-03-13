package org.zerock.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.vo.MemberVO;

@Repository// DAO를 스프링에서 인식하게 한다 , 반드시 인터페이스를 사용해야함 
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;//의존성 주입 : mybatis 쿼리문 수행 객체 생성
	
	
	@Override
	public void insertMember(MemberVO m) {
		// TODO Auto-generated method stub
		this.sqlSession.insert("m_in",m);
		
		/*
		 * mybatis에서 insert()메서드는 레코드 저장 , m_in은 member.xml메퍼 태그에서 설정할 insert아이디명으로 유일해야함
		 */
		
		
	}

	
	
	
}
