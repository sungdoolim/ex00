package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.zerock.dao.ReplyDAO;
import org.zerock.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	
	@Inject
	private ReplyDAO replyDao;

	@Override
	public void addReply(ReplyVO vo) {
		this.replyDao.addReply(vo);
		
		
	}

	@Override
	public List<ReplyVO> listReply(int bno) {
		
		return this.replyDao.list(bno);
	}

	@Override
	public void updateReply(ReplyVO vo) {
		this.replyDao.updateReply(vo);
		
	}

	@Override
	public void remove(int rno) {
		
		this.replyDao.delReply(rno);
	}
	
}
