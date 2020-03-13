package org.zerock.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	private SqlSession sqlSession;

	@Override
	public void addReply(ReplyVO vo) {
		this.sqlSession.insert("Reply.reply_in",vo);
	}

	@Override
	public List<ReplyVO> list(int bno) {
		
		return this.sqlSession.selectList("r_list", bno);
		//list얻어오기!
	}

	@Override
	public void updateReply(ReplyVO vo) {
		this.sqlSession.update("r_edit", vo);
		
	}

	@Override
	public void delReply(int rno) {
		// TODO Auto-generated method stub
	
		this.sqlSession.delete("r_del", rno);
	}
	
}
