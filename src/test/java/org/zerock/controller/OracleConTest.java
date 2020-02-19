package org.zerock.controller;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class OracleConTest {
//servlet에서 했던거네..?
	
	private static final String DRIVER=
			"oracle.jdbc.OracleDriver";
	//oracle.jdbc는 패키지명,oracledriver 는 오라클jdbc클래스명
	private static final String URL=
			"jdbc:oracle:thin:@127.0.0.1:1521:xe";
	//오라클 접속주소,1521은 포트 번호 ,xe는 db명
	private static final String USER="day";
	private static final String PW="day";
	
	@Test//junit test : 연습파일 만들기
	public void testCon() throws Exception{
		Class.forName(DRIVER);//으라이버 클래스 로드
		try(Connection con=
				DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);//db연결 객체 con이 이클립스 콘손에 출력
		}catch(Exception e) {			e.printStackTrace();		}//jdk1.7부터는 autocloseable 인터페이스 상속받은 자손은  finally에서 명시적 close() 없이 자동으로 닫힘
	}
	
	
	
	
	
	
}
