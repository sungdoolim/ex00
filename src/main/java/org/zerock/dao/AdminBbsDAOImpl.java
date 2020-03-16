package org.zerock.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.vo.BbsVO;

@Repository
public class AdminBbsDAOImpl implements AdminBbsDAO {

	@Inject
	private SqlSession sqlSession;

	@Override
	public int getListCount(BbsVO b) {
		return this.sqlSession.selectOne("abbs_row",b);
	}//관리자 자료실 검색전후 게시물수

	@Override
	public List<BbsVO> getBbsList(BbsVO b) {
		return this.sqlSession.selectList("abbs_list",b);
	}//관리자 자료실 검색전후 목록

	@Override
	public void isnertBbs(BbsVO b) {
		this.sqlSession.insert("abbs_in",b);		
	}//관리자 자료실 저장

	@Override
	public BbsVO getBbsCont(int no) {
		return this.sqlSession.selectOne("abbs_cont",no);
	}//관리자 자료실 내용보기

	@Override
	public void editBbs(BbsVO b) {
		this.sqlSession.update("abbs_edit",b);		
	}//관리자 자료실 수정

	@Override
	public void delBbs(int no) {
		this.sqlSession.delete("abbs_del",no);		
	}//관리자 자료실 삭제
}






















