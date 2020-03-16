package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.zerock.dao.AdminBbsDAO;
import org.zerock.vo.BbsVO;

@Service
public class AdminBbsServiceImpl implements AdminBbsService {

	@Inject
	private AdminBbsDAO adminBbsDAO;	
	
	@Override
	public int getListCount(BbsVO b) {
		return this.adminBbsDAO.getListCount(b);
	}

	@Override
	public List<BbsVO> getBbsList(BbsVO b) {
		return this.adminBbsDAO.getBbsList(b);
	}

	@Override
	public void insertBbs(BbsVO b) {
		this.adminBbsDAO.isnertBbs(b);
	}

	@Override
	public BbsVO getBbsCont(int no) {
		return this.adminBbsDAO.getBbsCont(no);
	}

	@Override
	public void editBbs(BbsVO b) {
		this.adminBbsDAO.editBbs(b);		
	}

	@Override
	public void delBbs(int no) {
		this.adminBbsDAO.delBbs(no);		
	}
}

















