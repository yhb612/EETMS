package com.iss.gms.entity;

import java.util.Date;

public class ProjectInfo {
	//项目编号
	private String proId;
	//项目名称
	private String proName;
	//事业部
	private String careerdepartment;
	//实施部
	private String implementDepartment;
	//项目经理
	private String projectManager;
	//客户名称
	private String customerName;
	//开始日期
	private Date beginDate;
	//结束日期
	private Date endDate;
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getCareerdepartment() {
		return careerdepartment;
	}
	public void setCareerdepartment(String careerdepartment) {
		this.careerdepartment = careerdepartment;
	}
	public String getImplementDepartment() {
		return implementDepartment;
	}
	public void setImplementDepartment(String implementDepartment) {
		this.implementDepartment = implementDepartment;
	}
	public String getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
