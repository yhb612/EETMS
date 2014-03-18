package com.iss.gms.service.security;

import java.sql.SQLException;
import com.iss.gms.entity.security.Userss;

public interface LoginService {
	//获取用户信息
	public Userss getUser(Userss user)throws SQLException;

}
