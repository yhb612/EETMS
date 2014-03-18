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
	 * ������Ŀ���Ʋ�ѯ
	 * @return List
	 * @param ��Ŀ���� 
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
	 * ������Ŀ���/��Ŀ����  ģ����ѯ
	 * @return List
	 * @param ��Ŀ���� 
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
	 * �����Ŀ��Ϣ
	 * @param Ա����Ŀ��Ϣ
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
	 * �޸���Ŀ��Ϣ
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
	 * ɾ����Ŀ��Ϣ
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
	 * �����·ݲ�ѯ��Ŀ
	 * @return List
	 * @param ��Ŀ���� 
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
	 * ��ѯ������Ŀ����
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
	 * ��ѯ������Ŀ����
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
