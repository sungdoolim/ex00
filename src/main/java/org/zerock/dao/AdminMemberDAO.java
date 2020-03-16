package org.zerock.dao;

import java.util.List;

import org.zerock.vo.MemberVO;

public interface AdminMemberDAO {

	int getListCount(MemberVO m);
	List<MemberVO> getMemberList(MemberVO m);
	MemberVO getMem(String mem_id);
	void editM(MemberVO m);
	void delM(String mem_id);

}
