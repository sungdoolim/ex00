package org.zerock.service;

import java.util.List;

import org.zerock.vo.BbsVO;

public interface AdminBbsService {

	int getListCount(BbsVO b);
	List<BbsVO> getBbsList(BbsVO b);
	void insertBbs(BbsVO b);
	BbsVO getBbsCont(int no);
	void editBbs(BbsVO b);
	void delBbs(int no);

}
