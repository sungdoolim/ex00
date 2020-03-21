package org.zerock.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.vo.AdminVO;
import org.zerock.vo.BoardVO;

import pwdconv.PwdChange;

@Repository
public class AdminDAOImpl implements AdminDAO {
	
	@Autowired
	private SqlSession sqlSession;// 자동 의존성 주입 => mybatis쿼리문 수행객체

	
	@Override
	public AdminVO adminLogin(String admin_id) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("admin_login", admin_id);
	}

	@Override
	public void adminRegister(AdminVO a) {//Reguster Controller에서 온것
		a.setAdmin_pwd(PwdChange.getPassWordToXEMD5String(a.getAdmin_pwd()));// 객체로 받아온게 아니네
		sqlSession.insert("admin_register",a);
		
	}
	@Override
	public void fileio(String name) {//Register Controller에서 온것
		sqlSession.insert("fileupload",name);
		
	}

	@Override
	public List<String> selectallfile() {
		// TODO Auto-generated method stub
		
		return sqlSession.selectList("fileselect");
	}
	
	
}
