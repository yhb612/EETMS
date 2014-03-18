package com.iss.gms.entity;

public class CostAndIncomeInfo {
	//预测年月	
	private String yearAndMonth;
	//部门
	private String department;
	//项目经理
	private String projectManager;
	//项目编号
	private String proId;
	//项目名称
	private String projectName;
	//计费人数
	private Integer billingNumbers=0;
	//预测收入(高级x22天(入职计费时间开始算))x250报价)
	private Float predictionIncome=0f;
	//预测成本(合同工资+社保x1月份)
	private Float predictionCost=0f;
	//预测报销(每人多少钱(工程属性文件配置))
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
