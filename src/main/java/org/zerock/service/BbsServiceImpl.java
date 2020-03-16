package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.dao.BbsDAO;
import org.zerock.vo.BbsVO;

@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	private BbsDAO bbsDAO;

	@Override
	public void insertBbs(BbsVO b) {
	  this.bbsDAO.insertBbs(b);	
	}

	@Override
	public List<BbsVO> getBbsList(BbsVO b) {
		return this.bbsDAO.getBbsList(b);
	}

	@Override
	public int getListCount(BbsVO b) {
    	return this.bbsDAO.getRowCont(b);
	}

	//트랜잭션 적용
	@Override
	public BbsVO getBbsCont(int bbs_no) {
		this.bbsDAO.updateHit(bbs_no);//조회수 증가
		return this.bbsDAO.getBbsCont(bbs_no);
	}//만약 내용보기가 실패하면 조회수 증가가 트랜잭션 논리적단위
	//에 의해서 rollback 즉 취소 처리된다.

	@Override
	public BbsVO getBbsCont2(int bbs_no) {
		return this.bbsDAO.getBbsCont(bbs_no);
	}//답변폼,수정폼,삭제폼

	//트랜잭션 적용부분
	@Override
	public void replyBBs(BbsVO rb) {
	  this.bbsDAO.updateLevel(rb);//답변 레벨 증가
	  this.bbsDAO.replyBbs(rb);//답변 저장
	}//답변 저장이 실패하면 답변레벨 증가를 롤백 처리

	@Override
	public void editBbs(BbsVO b) {
	  this.bbsDAO.editBbs(b);			
	}//자료실 수정

	@Override
	public void delBbs(int bbs_no) {
	  this.bbsDAO.delBbs(bbs_no);		
	}//자료실 삭제
}










