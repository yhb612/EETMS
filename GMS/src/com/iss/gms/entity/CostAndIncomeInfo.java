package com.iss.gms.entity;

public class CostAndIncomeInfo {
	//Ԥ������	
	private String yearAndMonth;
	//����
	private String department;
	//��Ŀ����
	private String projectManager;
	//��Ŀ���
	private String proId;
	//��Ŀ����
	private String projectName;
	//�Ʒ�����
	private Integer billingNumbers=0;
	//Ԥ������(�߼�x22��(��ְ�Ʒ�ʱ�俪ʼ��))x250����)
	private Float predictionIncome=0f;
	//Ԥ��ɱ�(��ͬ����+�籣x1�·�)
	private Float predictionCost=0f;
	//Ԥ�ⱨ��(ÿ�˶���Ǯ(���������ļ�����))
	private Float predictionExpenses=0f;
	public String getYearAndMonth() {
		return yearAndMonth;
	}
	public void setYearAndMonth(String yearAndMonth) {
		this.yearAndMonth = yearAndMonth;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Integer getBillingNumbers() {
		return billingNumbers;
	}
	public void setBillingNumbers(Integer billingNumbers) {
		this.billingNumbers = billingNumbers;
	}
	public Float getPredictionIncome() {
		return predictionIncome;
	}
	public void setPredictionIncome(Float predictionIncome) {
		this.predictionIncome = predictionIncome;
	}
	public Float getPredictionCost() {
		return predictionCost;
	}
	public void setPredictionCost(Float predictionCost) {
		this.predictionCost = predictionCost;
	}
	public Float getPredictionExpenses() {
		return predictionExpenses;
	}
	public void setPredictionExpenses(Float predictionExpenses) {
		this.predictionExpenses = predictionExpenses;
	}
	
	

}
