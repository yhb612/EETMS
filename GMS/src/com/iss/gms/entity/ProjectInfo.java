package com.iss.gms.entity;

import java.util.Date;

public class ProjectInfo {
	//��Ŀ���
	private String proId;
	//��Ŀ����
	private String proName;
	//��ҵ��
	private String careerdepartment;
	//ʵʩ��
	private String implementDepartment;
	//��Ŀ����
	private String projectManager;
	//�ͻ�����
	private String customerName;
	//��ʼ����
	private Date beginDate;
	//��������
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
