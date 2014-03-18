package com.iss.gms.entity;

import java.util.Date;

/**
 * @author Administrator
 *字段参照工时详情表
 */
public class EmployeeHoursDetailInfo {

	//员工编号
	private String employeeId;
	//员工姓名
	private String employeeName;
	//星期
	private String standardWeek;
	//日期类型
	private String standardDateType;
	//应出勤工时
	private Integer standardHours=0;
	//所属项目名称
	private String projectName;
	//客户确认正常工时
	private Float customerVerifyHours=0f;
	//客户确认加班工时
	private Float customerVerifyOvertimeHours=0f;
	//psa请假工时
	private Float psaLeaveHours=0f;
	//psa请假类型
	private String psaLeaveHoursType;
	//新增可倒休工时
	private Float currentAhughHours=0f;
	//新增可补贴工时
	private Float currentSubsidiesHours=0f;
	//当天产生差异工时
	private Float currentGapHours=0f;
	//日期
	private Date standardDate;
	//差异类型
	private String gapType;
	//差异原因
	private String employeeGapReason;
	//客户方负责人
	private String customerResponsiblePerson;
	//公司项目经理
	private String companyProjectManager;
	//客户所属项目
	private String belongedProject;
	//核销工时
	private Float writeOffHours=0f;
	//剩余可倒休工时
	private Float hours4VacTotal=0f;
	//开始日期（查询用）
	private Date startDate;
	//结束日期（查询用）
	private Date endDate;
	//标识  用于查询正常/重复差异工时 差异工时大于0
	private String signG;
	//标识  用于查询正常/重复差异工时 差异工时小于0
	private String signL;
	//标准工时总小时数
	private Integer standardHoursAll=0;
	//客户确认工时总小时数
	private Integer customerVerifyHoursAll=0;
	//工作属地
	private String workPlace;
	//技术平台
	private String technologyPlatform;
	//在场类型
	private String presenceOfType;
	//技术等级
	private String technologyGrade;
	//加班可补贴总工时
	private Float hoursOtSum=0f;
	//补贴标准
	private Float payStandard=0f;
	//加班费
	private Float pay4hot=0f;
	//是否只显示有加班信息的
	private String flag;
	//技术等级及技术平台
	private String tgAndTp;
	//入职日期
	private Date entryDate;
	//离职日期
	private Date leaveDate;
	//客户方姓名
	private String empName4Cus;
	//资源类型
	private String resourcesType;
	//项目编号
	private String projectId;
	//项目所属部门
	private String projectOfDepartment;
	//产品名称
	private String productName;
	//服务产品所属团队
	private String serviceProductsAttributiveTeam;
	//服务产品归属部门
	private String serviceProductsDepartment;	
	//供应商
	private String providers;
	//工作日期
	private Date workDate;
	//正常工时
	private Float normalWorkingHours;	
	//加班工时
	private Float overTimeHours;
	
	public String getEmpName4Cus() {
		return empName4Cus;
	}
	public void setEmpName4Cus(String empName4Cus) {
		this.empName4Cus = empName4Cus;
	}
	public String getResourcesType() {
		return resourcesType;
	}
	public void setResourcesType(String resourcesType) {
		this.resourcesType = resourcesType;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectOfDepartment() {
		return projectOfDepartment;
	}
	public void setProjectOfDepartment(String projectOfDepartment) {
		this.projectOfDepartment = projectOfDepartment;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getServiceProductsAttributiveTeam() {
		return serviceProductsAttributiveTeam;
	}
	public void setServiceProductsAttributiveTeam(
			String serviceProductsAttributiveTeam) {
		this.serviceProductsAttributiveTeam = serviceProductsAttributiveTeam;
	}
	public String getServiceProductsDepartment() {
		return serviceProductsDepartment;
	}
	public void setServiceProductsDepartment(String serviceProductsDepartment) {
		this.serviceProductsDepartment = serviceProductsDepartment;
	}
	public String getProviders() {
		return providers;
	}
	public void setProviders(String providers) {
		this.providers = providers;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public Float getNormalWorkingHours() {
		return normalWorkingHours;
	}
	public void setNormalWorkingHours(Float normalWorkingHours) {
		this.normalWorkingHours = normalWorkingHours;
	}
	public Float getOverTimeHours() {
		return overTimeHours;
	}
	public void setOverTimeHours(Float overTimeHours) {
		this.overTimeHours = overTimeHours;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	public String getTgAndTp() {
		return tgAndTp;
	}
	public void setTgAndTp(String tgAndTp) {
		this.tgAndTp = tgAndTp;
	}
	public Float getPayStandard() {
		return payStandard;
	}
	public void setPayStandard(Float payStandard) {
		this.payStandard = payStandard;
	}
	public Float getPay4hot() {
		return pay4hot;
	}
	public void setPay4hot(Float pay4hot) {
		this.pay4hot = pay4hot;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Float getHoursOtSum() {
		return hoursOtSum;
	}
	public void setHoursOtSum(Float hoursOtSum) {
		this.hoursOtSum = hoursOtSum;
	}
	public String getTechnologyPlatform() {
		return technologyPlatform;
	}
	public void setTechnologyPlatform(String technologyPlatform) {
		this.technologyPlatform = technologyPlatform;
	}
	public String getPresenceOfType() {
		return presenceOfType;
	}
	public void setPresenceOfType(String presenceOfType) {
		this.presenceOfType = presenceOfType;
	}
	public String getTechnologyGrade() {
		return technologyGrade;
	}
	public void setTechnologyGrade(String technologyGrade) {
		this.technologyGrade = technologyGrade;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public Integer getStandardHoursAll() {
		return standardHoursAll;
	}
	public void setStandardHoursAll(Integer standardHoursAll) {
		this.standardHoursAll = standardHoursAll;
	}
	public Integer getCustomerVerifyHoursAll() {
		return customerVerifyHoursAll;
	}
	public void setCustomerVerifyHoursAll(Integer customerVerifyHoursAll) {
		this.customerVerifyHoursAll = customerVerifyHoursAll;
	}
	public Integer getStandardHours() {
		return standardHours;
	}
	public void setStandardHours(Integer standardHours) {
		this.standardHours = standardHours;
	}
	public String getSignG() {
		return signG;
	}
	public void setSignG(String signG) {
		this.signG = signG;
	}
	public String getSignL() {
		return signL;
	}
	public void setSignL(String signL) {
		this.signL = signL;
	}
	public Float getWriteOffHours() {
		return writeOffHours;
	}
	public void setWriteOffHours(Float writeOffHours) {
		this.writeOffHours = writeOffHours;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getStandardWeek() {
		return standardWeek;
	}
	public void setStandardWeek(String standardWeek) {
		this.standardWeek = standardWeek;
	}
	public String getStandardDateType() {
		return standardDateType;
	}
	public void setStandardDateType(String standardDateType) {
		this.standardDateType = standardDateType;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Float getCustomerVerifyHours() {
		return customerVerifyHours;
	}
	public void setCustomerVerifyHours(Float customerVerifyHours) {
		this.customerVerifyHours = customerVerifyHours;
	}
	public Float getCustomerVerifyOvertimeHours() {
		return customerVerifyOvertimeHours;
	}
	public void setCustomerVerifyOvertimeHours(Float customerVerifyOvertimeHours) {
		this.customerVerifyOvertimeHours = customerVerifyOvertimeHours;
	}
	public Float getPsaLeaveHours() {
		return psaLeaveHours;
	}
	public void setPsaLeaveHours(Float psaLeaveHours) {
		this.psaLeaveHours = psaLeaveHours;
	}
	public String getPsaLeaveHoursType() {
		return psaLeaveHoursType;
	}
	public void setPsaLeaveHoursType(String psaLeaveHoursType) {
		this.psaLeaveHoursType = psaLeaveHoursType;
	}
	public Float getCurrentAhughHours() {
		return currentAhughHours;
	}
	public void setCurrentAhughHours(Float currentAhughHours) {
		this.currentAhughHours = currentAhughHours;
	}
	public Float getCurrentSubsidiesHours() {
		return currentSubsidiesHours;
	}
	public void setCurrentSubsidiesHours(Float currentSubsidiesHours) {
		this.currentSubsidiesHours = currentSubsidiesHours;
	}
	public Float getCurrentGapHours() {
		return currentGapHours;
	}
	public void setCurrentGapHours(Float currentGapHours) {
		this.currentGapHours = currentGapHours;
	}
	public Date getStandardDate() {
		return standardDate;
	}
	public void setStandardDate(Date standardDate) {
		this.standardDate = standardDate;
	}
	public String getGapType() {
		return gapType;
	}
	public void setGapType(String gapType) {
		this.gapType = gapType;
	}
	public String getEmployeeGapReason() {
		return employeeGapReason;
	}
	public void setEmployeeGapReason(String employeeGapReason) {
		this.employeeGapReason = employeeGapReason;
	}
	public String getCustomerResponsiblePerson() {
		return customerResponsiblePerson;
	}
	public void setCustomerResponsiblePerson(String customerResponsiblePerson) {
		this.customerResponsiblePerson = customerResponsiblePerson;
	}
	public String getCompanyProjectManager() {
		return companyProjectManager;
	}
	public void setCompanyProjectManager(String companyProjectManager) {
		this.companyProjectManager = companyProjectManager;
	}
	public String getBelongedProject() {
		return belongedProject;
	}
	public void setBelongedProject(String belongedProject) {
		this.belongedProject = belongedProject;
	}
	public Float getHours4VacTotal() {
		return hours4VacTotal;
	}
	public void setHours4VacTotal(Float hours4VacTotal) {
		this.hours4VacTotal = hours4VacTotal;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
