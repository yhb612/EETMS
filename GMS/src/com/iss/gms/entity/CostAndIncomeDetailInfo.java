package com.iss.gms.entity;

public class CostAndIncomeDetailInfo {
	//员工编号
	private String empId;
	//员工姓名
	private String empName;
	//客户收入
	private Float income=0f;
	//预测人日
	private String days;
	//客户级别
	private String cusGrade;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Float getIncome() {
		return income;
	}
	public void setIncome(Float income) {
		this.income = income;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getCusGrade() {
		return cusGrade;
	}
	public void setCusGrade(String cusGrade) {
		this.cusGrade = cusGrade;
	}
	
	
}
