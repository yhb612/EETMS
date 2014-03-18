package com.iss.gms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


public class UserDao extends SqlMapClientDaoSupport {
	Logger	log	= Logger.getLogger(UserDao.class);
	
	
	
	/**
	 * @return 
	 * @throws SQLException 
	 */
	public List getAll()throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryForEmployeeBasicInfo");
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	
}
