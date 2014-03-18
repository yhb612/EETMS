package com.iss.gms.service;

import java.sql.SQLException;
import java.util.List;

import com.iss.gms.dao.UserDao;

public class UserServiceImpl implements IUserService {
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//��ѯԱ��������Ϣ
	public List queryAll() throws SQLException {
		return userDao.getAll();
	}
}
