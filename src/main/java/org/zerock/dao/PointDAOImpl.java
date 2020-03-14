package org.zerock.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {

	@Inject
	private SqlSession sqlSession;

	@Override
	public void updatePoint(String sender, int point) {
	
		Map<String,Object> pm=new HashMap<>(); // key:value쌍으로 저장하는 Map컬렉션 자료구조
		pm.put("sender", sender);
		pm.put("point",point);
		this.sqlSession.update("pointUp",pm);//sqlSession이 mybatis 쿼리객체
		
		
		
		
		
	}
	
}
