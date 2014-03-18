package com.iss.gms.dao.pro;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.iss.gms.entity.ProjectInfo;


public class ProjectDao extends SqlMapClientDaoSupport {
	Logger	log	= Logger.getLogger("ProjectDao.class");
	
	
	
	/**
	 * 根据项目名称查询
	 * @return List
	 * @param 项目名称 
	 * @throws SQLException 
	 */
	public List queryProInfoByName(String pname)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryProInfoByName",pname);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * 根据项目编号/项目名称  模糊查询
	 * @return List
	 * @param 项目名称 
	 * @throws SQLException 
	 */
	public List queryProInfoLike(ProjectInfo pi)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryProInfoLike",pi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * 添加项目信息
	 * @param 员工项目信息
	 * @return 
	 * @throws SQLException 
	 */
	public void addProjectInfo(ProjectInfo pj)throws SQLException{
		try {
			 getSqlMapClientTemplate().insert("addProjectInfo",pj);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	/**
	 * 修改项目信息
	 * @param 
	 * @return 
	 * @throws SQLException 
	 */
	public void modifyProjectInfo(ProjectInfo pj)throws SQLException{
		try {
			 getSqlMapClientTemplate().update("modifyProjectInfo",pj);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	/**
	 * 删除项目信息
	 * @param 
	 * @return 
	 * @throws SQLException 
	 */
	public void removeProjectInfo(String pid)throws SQLException{
		try {
			 getSqlMapClientTemplate().delete("removeProjectInfo",pid);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	public ProjectInfo queryProInfoById(String proId) throws SQLException{
		try {
			return (ProjectInfo)getSqlMapClientTemplate().queryForObject("queryProInfoById",proId);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	/**
	 * 根据月份查询项目
	 * @return List
	 * @param 项目名称 
	 * @throws SQLException 
	 */
	public List<ProjectInfo> queryProByMonth(Map<String, Date> fldates)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryProByMonth",fldates);
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * 查询所有项目经理
	 * @return List
	 * @param 
	 * @throws SQLException 
	 */
	public List<String> queryProMgrs()throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryProMgrs");
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * 查询所有项目经理
	 * @return List
	 * @param 
	 * @throws SQLException 
	 */
	public List<String> querydpts4pro()throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("querydpts4pro");
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
}
