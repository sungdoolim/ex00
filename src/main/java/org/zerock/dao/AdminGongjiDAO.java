package org.zerock.dao;

import java.util.List;

import org.zerock.vo.GongjiVO;

public interface AdminGongjiDAO {

	int getListCount(GongjiVO g);
	List<GongjiVO> getGongjiList(GongjiVO g);
	void insertG(GongjiVO g);
	GongjiVO getGongjiCont(int no);
	void editGongji(GongjiVO g);
	void delG(int no);

}