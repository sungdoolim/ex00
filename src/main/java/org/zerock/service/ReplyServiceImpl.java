package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dao.BoardDAO;
import org.zerock.dao.ReplyDAO;
import org.zerock.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	
	@Inject
	private ReplyDAO replyDao;
	
	@Autowired
	private BoardDAO boardDao;
	
	
	
	@Transactional
	@Override
	public void addReply(ReplyVO vo) {
		this.replyDao.addReply(vo);//댓글 추가
		this.boardDao.updateReplyCnt(vo.getBno(),1);// 댓글 번호를 가져오고, 댓글 개수가 1 증가!
	}

	@Override
	public List<ReplyVO> listReply(int bno) {
		
		return this.replyDao.list(bno);
	}

	@Override
	public void updateReply(ReplyVO vo) {
		this.replyDao.updateReply(vo);
		
	}

	@Transactional
	@Override
	public void remove(int rno) {
		
		int bno=this.replyDao.getBno(rno);
		this.replyDao.delReply(rno);//댓글 삭제
		this.boardDao.updateReplyCnt(bno, -1);//댓글을 삭제하면 댓글 개수를 1 감소
	}
	
	
	
}
