package com.iss.gms.entity;

import java.util.Date;

public class EmpRelChangeRecord {
	private String employeeId;
	private Date updateTime;
	private String operate;
	private String employeeName;
	private Integer empCountBefore;
	private Integer empCountAfter;
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Integer getEmpCountBefore() {
		return empCountBefore;
	}
	public void setEmpCountBefore(Integer empCountBefore) {
		this.empCountBefore = empCountBefore;
	}
	public Integer getEmpCountAfter() {
		return empCountAfter;
	}
	public void setEmpCountAfter(Integer empCountAfter) {
		this.empCountAfter = empCountAfter;
	}

}
