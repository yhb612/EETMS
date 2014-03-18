package com.iss.gms.dao.security;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.iss.gms.entity.security.Userss;


public class SecurityDao extends SqlMapClientDaoSupport {
	Logger log = Logger.getLogger(SecurityDao.class);
	//��ȡ�û���Ϣ
	public Userss getUser(Userss user)throws SQLException{
		try {
			return (Userss)getSqlMapClientTemplate().queryForObject("getUser",user);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ȡȫ���˵���Ϣ
	public List getAllMenu()throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("getAllMenu","111");
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}

}
