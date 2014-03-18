package com.iss.gms.service.security;

import java.sql.SQLException;

import com.iss.gms.dao.security.SecurityDao;
import com.iss.gms.entity.security.Userss;

public class LoginServiceImpl implements LoginService {
	
	private SecurityDao securityDao;

	public Userss getUser(Userss user) throws SQLException {
		return securityDao.getUser(user);
	}

	public void setSecurityDao(SecurityDao securityDao) {
		this.securityDao = securityDao;
	}

	public SecurityDao getSecurityDao() {
		return securityDao;
	}

}
