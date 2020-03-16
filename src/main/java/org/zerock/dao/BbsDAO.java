package org.zerock.dao;

import java.util.List;

import org.zerock.vo.BbsVO;

public interface BbsDAO {

	void insertBbs(BbsVO b);
	List<BbsVO> getBbsList(BbsVO b);
	int getRowCont(BbsVO b);
	void updateHit(int bbs_no);
	BbsVO getBbsCont(int bbs_no);
	void updateLevel(BbsVO rb);
	void replyBbs(BbsVO rb);
	void editBbs(BbsVO b);
	void delBbs(int bbs_no);

}
