package com.iss.gms.entity;

import java.util.Date;

/**
 * 
 * 员工归属汇总信息对象
 * 
 * @author Administrator
 *
 */
public class EmpRelStaInfo {

	private String employeeId;
	private Date updateTime;
	private String operate;
	private String employeeName;
	//业务条线代号
	private String businessLine;
	//业务条线名称
	private String businessLineName;
	//总人数
	private Integer empcount;
	//测试期初人数
	private Integer empCountLastWeek4t=0;
	//测试入职人数
	private Integer entryNum4t=0;
	//测试调入人数
	private Integer callInNum4t=0;
	//测试离职人数
	private Integer resignationNum4t=0;
	//测试调出人数
	private Integer callOutNum4t=0;
	//测试期末人数
	private Integer empCountEnd4t=0;
	//测试计费人数
	private Integer chargingNum4t=0;
	//测试下包商人数
	private Integer subcontractorNum4t=0;
	//测试现场储备人数
	private Integer cusReservesNum4t=0;
	//测试公司储备人数
	private Integer comReservesNum4t=0;
	//测试闲置人数
	private Integer unUsedNum4t=0;
	//测试休假人数
	private Integer vacNum4t=0;
	//测试管理人数
	private Integer mgrNum4t=0;
	//测试借出外部人数
	private Integer lendingOutNum4t=0;
	//测试售前人数
	private Integer preSalesNum4t=0;
	//测试调休人数
	private Integer txNum4t=0;
	//开发期初人数
	private Integer empCountLastWeek4d=0;
	//开发入职人数
	private Integer entryNum4d=0;
	//开发调入人数
	private Integer callInNum4d=0;
	//开发离职人数
	private Integer resignationNum4d=0;
	//开发调出人数
	private Integer callOutNum4d=0;
	//开发期末人数
	private Integer empCountEnd4d=0;
	//开发计费人数
	private Integer chargingNum4d=0;
	//开发下包商人数
	private Integer subcontractorNum4d=0;
	//开发现场储备人数
	private Integer cusReservesNum4d=0;
	//开发公司储备人数
	private Integer comReservesNum4d=0;
	//开发闲置人数
	private Integer unUsedNum4d=0;
	//开发休假人数
	private Integer vacNum4d=0;
	//开发管理人数
	private Integer mgrNum4d=0;
	//开发借出外部人数
	private Integer lendingOutNum4d=0;
	//开发售前人数
	private Integer preSalesNum4d=0;
	//开发调休人数
	private Integer txNum4d=0;
	//技术类别
	private String technology;
	//生成时间
	private Date createTime;
	//生成年月
	private String staYearMonth;
	//生成星期
	private String staWeek;
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getStaYearMonth() {
		return staYearMonth;
	}
	public void setStaYearMonth(String staYearMonth) {
		this.staYearMonth = staYearMonth;
	}
	public String getStaWeek() {
		return staWeek;
	}
	public void setStaWeek(String staWeek) {
		this.staWeek = staWeek;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
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
	public String getBusinessLineName() {
		return businessLineName;
	}
	public void setBusinessLineName(String businessLineName) {
		this.businessLineName = businessLineName;
	}
	public String getBusinessLine() {
		return businessLine;
	}
	public void setBusinessLine(String businessLine) {
		this.businessLine = businessLine;
	}
	public Integer getEmpcount() {
		return empcount;
	}
	public void setEmpcount(Integer empcount) {
		this.empcount = empcount;
	}
	public Integer getEmpCountLastWeek4t() {
		return empCountLastWeek4t;
	}
	public void setEmpCountLastWeek4t(Integer empCountLastWeek4t) {
		this.empCountLastWeek4t = empCountLastWeek4t;
	}
	public Integer getEntryNum4t() {
		return entryNum4t;
	}
	public void setEntryNum4t(Integer entryNum4t) {
		this.entryNum4t = entryNum4t;
	}
	public Integer getCallInNum4t() {
		return callInNum4t;
	}
	public void setCallInNum4t(Integer callInNum4t) {
		this.callInNum4t = callInNum4t;
	}
	public Integer getResignationNum4t() {
		return resignationNum4t;
	}
	public void setResignationNum4t(Integer resignationNum4t) {
		this.resignationNum4t = resignationNum4t;
	}
	public Integer getCallOutNum4t() {
		return callOutNum4t;
	}
	public void setCallOutNum4t(Integer callOutNum4t) {
		this.callOutNum4t = callOutNum4t;
	}
	public Integer getEmpCountEnd4t() {
		return empCountEnd4t;
	}
	public void setEmpCountEnd4t(Integer empCountEnd4t) {
		this.empCountEnd4t = empCountEnd4t;
	}
	public Integer getChargingNum4t() {
		return chargingNum4t;
	}
	public void setChargingNum4t(Integer chargingNum4t) {
		this.chargingNum4t = chargingNum4t;
	}
	public Integer getSubcontractorNum4t() {
		return subcontractorNum4t;
	}
	public void setSubcontractorNum4t(Integer subcontractorNum4t) {
		this.subcontractorNum4t = subcontractorNum4t;
	}
	public Integer getCusReservesNum4t() {
		return cusReservesNum4t;
	}
	public void setCusReservesNum4t(Integer cusReservesNum4t) {
		this.cusReservesNum4t = cusReservesNum4t;
	}
	public Integer getComReservesNum4t() {
		return comReservesNum4t;
	}
	public void setComReservesNum4t(Integer comReservesNum4t) {
		this.comReservesNum4t = comReservesNum4t;
	}
	public Integer getUnUsedNum4t() {
		return unUsedNum4t;
	}
	public void setUnUsedNum4t(Integer unUsedNum4t) {
		this.unUsedNum4t = unUsedNum4t;
	}
	public Integer getVacNum4t() {
		return vacNum4t;
	}
	public void setVacNum4t(Integer vacNum4t) {
		this.vacNum4t = vacNum4t;
	}
	public Integer getMgrNum4t() {
		return mgrNum4t;
	}
	public void setMgrNum4t(Integer mgrNum4t) {
		this.mgrNum4t = mgrNum4t;
	}
	public Integer getLendingOutNum4t() {
		return lendingOutNum4t;
	}
	public void setLendingOutNum4t(Integer lendingOutNum4t) {
		this.lendingOutNum4t = lendingOutNum4t;
	}
	public Integer getPreSalesNum4t() {
		return preSalesNum4t;
	}
	public void setPreSalesNum4t(Integer preSalesNum4t) {
		this.preSalesNum4t = preSalesNum4t;
	}
	public Integer getTxNum4t() {
		return txNum4t;
	}
	public void setTxNum4t(Integer txNum4t) {
		this.txNum4t = txNum4t;
	}
	public Integer getEmpCountLastWeek4d() {
		return empCountLastWeek4d;
	}
	public void setEmpCountLastWeek4d(Integer empCountLastWeek4d) {
		this.empCountLastWeek4d = empCountLastWeek4d;
	}
	public Integer getEntryNum4d() {
		return entryNum4d;
	}
	public void setEntryNum4d(Integer entryNum4d) {
		this.entryNum4d = entryNum4d;
	}
	public Integer getCallInNum4d() {
		return callInNum4d;
	}
	public void setCallInNum4d(Integer callInNum4d) {
		this.callInNum4d = callInNum4d;
	}
	public Integer getResignationNum4d() {
		return resignationNum4d;
	}
	public void setResignationNum4d(Integer resignationNum4d) {
		this.resignationNum4d = resignationNum4d;
	}
	public Integer getCallOutNum4d() {
		return callOutNum4d;
	}
	public void setCallOutNum4d(Integer callOutNum4d) {
		this.callOutNum4d = callOutNum4d;
	}
	public Integer getEmpCountEnd4d() {
		return empCountEnd4d;
	}
	public void setEmpCountEnd4d(Integer empCountEnd4d) {
		this.empCountEnd4d = empCountEnd4d;
	}
	public Integer getChargingNum4d() {
		return chargingNum4d;
	}
	public void setChargingNum4d(Integer chargingNum4d) {
		this.chargingNum4d = chargingNum4d;
	}
	public Integer getSubcontractorNum4d() {
		return subcontractorNum4d;
	}
	public void setSubcontractorNum4d(Integer subcontractorNum4d) {
		this.subcontractorNum4d = subcontractorNum4d;
	}
	public Integer getCusReservesNum4d() {
		return cusReservesNum4d;
	}
	public void setCusReservesNum4d(Integer cusReservesNum4d) {
		this.cusReservesNum4d = cusReservesNum4d;
	}
	public Integer getComReservesNum4d() {
		return comReservesNum4d;
	}
	public void setComReservesNum4d(Integer comReservesNum4d) {
		this.comReservesNum4d = comReservesNum4d;
	}
	public Integer getUnUsedNum4d() {
		return unUsedNum4d;
	}
	public void setUnUsedNum4d(Integer unUsedNum4d) {
		this.unUsedNum4d = unUsedNum4d;
	}
	public Integer getVacNum4d() {
		return vacNum4d;
	}
	public void setVacNum4d(Integer vacNum4d) {
		this.vacNum4d = vacNum4d;
	}
	public Integer getMgrNum4d() {
		return mgrNum4d;
	}
	public void setMgrNum4d(Integer mgrNum4d) {
		this.mgrNum4d = mgrNum4d;
	}
	public Integer getLendingOutNum4d() {
		return lendingOutNum4d;
	}
	public void setLendingOutNum4d(Integer lendingOutNum4d) {
		this.lendingOutNum4d = lendingOutNum4d;
	}
	public Integer getPreSalesNum4d() {
		return preSalesNum4d;
	}
	public void setPreSalesNum4d(Integer preSalesNum4d) {
		this.preSalesNum4d = preSalesNum4d;
	}
	public Integer getTxNum4d() {
		return txNum4d;
	}
	public void setTxNum4d(Integer txNum4d) {
		this.txNum4d = txNum4d;
	}
	
}
