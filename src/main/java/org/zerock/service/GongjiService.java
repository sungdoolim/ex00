package org.zerock.service;

import java.util.List;

import org.zerock.vo.GongjiVO;

public interface GongjiService {

	List<GongjiVO> getList();
	GongjiVO getGCont(int gongji_no);

}
