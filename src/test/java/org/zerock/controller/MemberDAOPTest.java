package org.zerock.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.dao.MemberDAO;
import org.zerock.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations= {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class MemberDAOPTest {
	
	@Autowired
	private MemberDAO dao;
	
	@Test
	public void testInsertMember()throws Exception{
		MemberVO m=new MemberVO();
		m.setUserid("aaa");
		m.setUsername("이순신");
		m.setUserpw("1232");
		m.setEmail("hong@naver.com");
		dao.insertMember(m);
		
		
	}

}
