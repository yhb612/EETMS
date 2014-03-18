package com.iss.gms.service;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {

	//查询员工基本信息
	public List queryAll() throws SQLException;
}
