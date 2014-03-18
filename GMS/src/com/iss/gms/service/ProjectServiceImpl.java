package com.iss.gms.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.iss.gms.dao.pro.ProjectDao;
import com.iss.gms.entity.ProjectInfo;

public class ProjectServiceImpl implements IProjectService {
	private ProjectDao projectDao;
	
	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}


	//������Ŀ���Ʋ�ѯ��Ŀ��Ϣ
	public List queryProInfoByName(String name)throws SQLException{
		return this.projectDao.queryProInfoByName(name);
	}
	
	//�����Ŀ��Ϣ
	public void addProjectInfo(ProjectInfo pj)throws SQLException{
		 this.projectDao.addProjectInfo(pj);
	}
	
	//�޸�Ա����Ŀ��Ϣ
	public void modifyProjectInfo(ProjectInfo pj)throws SQLException{
		this.projectDao.modifyProjectInfo(pj);
	}
	
	//ɾ����Ŀ��Ϣ
	public void removeProjectInfo(String pid)throws SQLException{
		this.projectDao.removeProjectInfo(pid);
	}

	public ProjectInfo queryProInfoById(String proId) throws SQLException {
		return projectDao.queryProInfoById(proId);
	}

	public List<ProjectInfo> queryProByMonth(Map<String, Date> fldates)
			throws SQLException {
		return projectDao.queryProByMonth(fldates);
	}

	public List queryProInfoLike(ProjectInfo pi) throws SQLException {
		return projectDao.queryProInfoLike(pi);
	}

	public List<String> queryProMgrs() throws SQLException {
		return projectDao.queryProMgrs();
	}

	public List<String> querydpts4pro() throws SQLException {
		return projectDao.querydpts4pro();
	}
}
