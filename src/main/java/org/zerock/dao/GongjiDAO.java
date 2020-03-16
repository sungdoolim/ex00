package org.zerock.dao;

import java.util.List;

import org.zerock.vo.GongjiVO;

public interface GongjiDAO {

	List<GongjiVO> getList();
	void updateHit(int gongji_no);
	GongjiVO getGCont(int gongji_no);

}
