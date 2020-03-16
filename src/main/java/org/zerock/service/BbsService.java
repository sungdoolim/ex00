package org.zerock.service;

import java.util.List;

import org.zerock.vo.BbsVO;

public interface BbsService {

	void insertBbs(BbsVO b);
	List<BbsVO> getBbsList(BbsVO b);
	int getListCount(BbsVO b);
	BbsVO getBbsCont(int bbs_no);
	BbsVO getBbsCont2(int bbs_no);
	void replyBBs(BbsVO rb);
	void editBbs(BbsVO b);
	void delBbs(int bbs_no);

}
