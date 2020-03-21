package org.zerock.dao;

import java.util.List;

import org.zerock.vo.AdminVO;
import org.zerock.vo.BoardVO;

public interface AdminDAO {

	AdminVO adminLogin(String admin_id);

	void adminRegister(AdminVO a);
	void fileio(String name);

	List<String> selectallfile();
}
