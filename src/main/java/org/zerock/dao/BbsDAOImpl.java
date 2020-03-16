package org.zerock.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.vo.BbsVO;

@Repository
public class BbsDAOImpl implements BbsDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void insertBbs(BbsVO b) {
       this.sqlSession.insert("bbs_in",b);		
	}//자료실 저장	

	@Override
	public List<BbsVO> getBbsList(BbsVO b) {
		return this.sqlSession.selectList("bbs_list",b);
	}//자료실 목록

	@Override
	public int getRowCont(BbsVO b) {
		return this.sqlSession.selectOne("bbs_count",b);
	}

	@Override
	public void updateHit(int bbs_no) {
		this.sqlSession.update("bbs_hi",bbs_no);		
	}//조회수 증가

	@Override
	public BbsVO getBbsCont(int bbs_no) {
		return this.sqlSession.selectOne("bbs_co",bbs_no);
	}//내용보기

	@Override
	public void updateLevel(BbsVO rb) {
		this.sqlSession.update("level_up",rb);		
	}//답변 레벨 증가

	@Override
	public void replyBbs(BbsVO rb) {
		this.sqlSession.insert("reply_in2",rb);		
	}//답변 저장

	@Override
	public void editBbs(BbsVO b) {
		this.sqlSession.update("bbs_edit",b);		
	}//자료실 수정

	@Override
	public void delBbs(int bbs_no) {
	    this.sqlSession.delete("bbs_del",bbs_no);			
	}//자료실 삭제
	
}





















