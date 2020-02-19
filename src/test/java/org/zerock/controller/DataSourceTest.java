package org.zerock.controller;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations= {"file:src/main/webapp/WEB-INF/spring/*.xml"})// 경로내 모든 xml불러오기
public class DataSourceTest {

	@Inject // 자동의존성 주입 ->  ds 참조변수에 객체 주소를 주입해서 실제사용할수 있게 의존성 주입 -> DI
	private DataSource ds;
	/*
	 * 스프링 의존성 주입 방법
	 * 1 : setter()메서드 활요  2 : 생성자를 통한 의존성 주입 
	 * 
	 */
	@Test
	public void testCon() throws Exception{
		try(Connection con=ds.getConnection()){
			System.out.println(con);
		}catch(Exception e) {
			e.printStackTrace();}
	}
	
}
