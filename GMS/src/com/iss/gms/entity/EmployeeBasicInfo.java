package com.iss.gms.entity;

import java.util.Date;

public class EmployeeBasicInfo {
	//员工编号
	private String employeeId;
	//姓名
	private String employeeName;
	//客户方定义姓名
	private String chinaBankName;
	//性别
	private String employeeGender;
	//入职日期
	private Date entryDate;
	//通用职位
	private String commonPosition;	
	//成本中心
	private String costCenter;		
	//组织单位3
	private String organizationalUnit3;	
	//人事范围
	private String personnelRange;		
	//工作地文本
	private String workPlaceText;	
	//人事子范围
	private String personnelSubRange;	
	//司龄(月)
	private String drivingAgeMonth;
	//工龄(年)
	private String workingAgeYear;		
	//员工组
	private String employeeGroup;	
	//员工子组
	private String employeeSubGroup;
	//职级
	private String rank;		
	//学历
	private String educationa;		
	//专业名称
	private String specialtyName;
	//公司邮箱
	private String companyMailBox;
	//手机号码
	private String mobileNumber;	
	//证件类别
	private String credentialsType;
	//证件号码
	private String credentialsNumber;	
	//组织单位4
	private String organizationalUnit4;	
	//组织单位5
	private String organizationalUnit5;	
	//组织单位6
	private String organizationalUnit6;	
	//毕业日期
	private Date graduationDate;	
	//毕业学校/城市
	private String graduationSchoolOrCity;	
	//更新时间
	private Date updateDate;		
	//所属公司
	private String itsaffiliates;	
	//合同工资
	private Float contractWages=0f;		
	//社保
	private Float societyindemnification=0f;	
	//第一技能
	private String firstSkill;	
	//第二技能
	private String secondSkill;
	//技能说明
	private String skillDescription;	
	//密码
	private String passWord;
	//是否有加班补贴
	private String isWorkOtimeSubSidies;
	//是否参与工时核查
	private String beCheck4Gap;
	//开始日期(查询用)
	private Date startDate;
	//结束日期(查询用)
	private Date endDate;
	
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
	public String getBeCheck4Gap() {
		return beCheck4Gap;
	}
	public void setBeCheck4Gap(String beCheck4Gap) {
		this.beCheck4Gap = beCheck4Gap;
	}
	public String getIsWorkOtimeSubSidies() {
		return isWorkOtimeSubSidies;
	}
	public void setIsWorkOtimeSubSidies(String isWorkOtimeSubSidies) {
		this.isWorkOtimeSubSidies = isWorkOtimeSubSidies;
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
	public String getChinaBankName() {
		return chinaBankName;
	}
	public void setChinaBankName(String chinaBankName) {
		this.chinaBankName = chinaBankName;
	}
	public String getEmployeeGender() {
		return employeeGender;
	}
	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCommonPosition() {
		return commonPosition;
	}
	public void setCommonPosition(String commonPosition) {
		this.commonPosition = commonPosition;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getOrganizationalUnit3() {
		return organizationalUnit3;
	}
	public void setOrganizationalUnit3(String organizationalUnit3) {
		this.organizationalUnit3 = organizationalUnit3;
	}
	public String getPersonnelRange() {
		return personnelRange;
	}
	public void setPersonnelRange(String personnelRange) {
		this.personnelRange = personnelRange;
	}
	public String getWorkPlaceText() {
		return workPlaceText;
	}
	public void setWorkPlaceText(String workPlaceText) {
		this.workPlaceText = workPlaceText;
	}
	public String getPersonnelSubRange() {
		return personnelSubRange;
	}
	public void setPersonnelSubRange(String personnelSubRange) {
		this.personnelSubRange = personnelSubRange;
	}
	public String getDrivingAgeMonth() {
		return drivingAgeMonth;
	}
	public void setDrivingAgeMonth(String drivingAgeMonth) {
		this.drivingAgeMonth = drivingAgeMonth;
	}
	public String getWorkingAgeYear() {
		return workingAgeYear;
	}
	public void setWorkingAgeYear(String workingAgeYear) {
		this.workingAgeYear = workingAgeYear;
	}
	public String getEmployeeGroup() {
		return employeeGroup;
	}
	public void setEmployeeGroup(String employeeGroup) {
		this.employeeGroup = employeeGroup;
	}
	public String getEmployeeSubGroup() {
		return employeeSubGroup;
	}
	public void setEmployeeSubGroup(String employeeSubGroup) {
		this.employeeSubGroup = employeeSubGroup;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getEducationa() {
		return educationa;
	}
	public void setEducationa(String educationa) {
		this.educationa = educationa;
	}
	public String getSpecialtyName() {
		return specialtyName;
	}
	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}
	public String getCompanyMailBox() {
		return companyMailBox;
	}
	public void setCompanyMailBox(String companyMailBox) {
		this.companyMailBox = companyMailBox;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getCredentialsType() {
		return credentialsType;
	}
	public void setCredentialsType(String credentialsType) {
		this.credentialsType = credentialsType;
	}
	public String getCredentialsNumber() {
		return credentialsNumber;
	}
	public void setCredentialsNumber(String credentialsNumber) {
		this.credentialsNumber = credentialsNumber;
	}
	public String getOrganizationalUnit4() {
		return organizationalUnit4;
	}
	public void setOrganizationalUnit4(String organizationalUnit4) {
		this.organizationalUnit4 = organizationalUnit4;
	}
	public String getOrganizationalUnit5() {
		return organizationalUnit5;
	}
	public void setOrganizationalUnit5(String organizationalUnit5) {
		this.organizationalUnit5 = organizationalUnit5;
	}
	public String getOrganizationalUnit6() {
		return organizationalUnit6;
	}
	public void setOrganizationalUnit6(String organizationalUnit6) {
		this.organizationalUnit6 = organizationalUnit6;
	}
	public Date getGraduationDate() {
		return graduationDate;
	}
	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}
	public String getGraduationSchoolOrCity() {
		return graduationSchoolOrCity;
	}
	public void setGraduationSchoolOrCity(String graduationSchoolOrCity) {
		this.graduationSchoolOrCity = graduationSchoolOrCity;
	}
	public String getItsaffiliates() {
		return itsaffiliates;
	}
	public void setItsaffiliates(String itsaffiliates) {
		this.itsaffiliates = itsaffiliates;
	}
	public Float getContractWages() {
		return contractWages;
	}
	public void setContractWages(Float contractWages) {
		this.contractWages = contractWages;
	}
	public Float getSocietyindemnification() {
		return societyindemnification;
	}
	public void setSocietyindemnification(Float societyindemnification) {
		this.societyindemnification = societyindemnification;
	}
	public String getFirstSkill() {
		return firstSkill;
	}
	public void setFirstSkill(String firstSkill) {
		this.firstSkill = firstSkill;
	}
	public String getSecondSkill() {
		return secondSkill;
	}
	public void setSecondSkill(String secondSkill) {
		this.secondSkill = secondSkill;
	}
	public String getSkillDescription() {
		return skillDescription;
	}
	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
						
	
}
