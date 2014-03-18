package com.iss.gms.service.menu;

import java.util.List;

import com.iss.gms.dao.security.SecurityDao;

public class MenuTreeServiceImpl implements MenuTreeService {
	
	private SecurityDao securityDao;

	public SecurityDao getSecurityDao() {
		return securityDao;
	}

	public void setSecurityDao(SecurityDao securityDao) {
		this.securityDao = securityDao;
	}

	public List getAccreditMenu(String userNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List getAllMenu() throws Exception {
		return  securityDao.getAllMenu();
	}

}
