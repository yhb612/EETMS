package com.iss.gms.service.security;

import java.sql.SQLException;
import com.iss.gms.entity.security.Userss;

public interface LoginService {
	//��ȡ�û���Ϣ
	public Userss getUser(Userss user)throws SQLException;

}
