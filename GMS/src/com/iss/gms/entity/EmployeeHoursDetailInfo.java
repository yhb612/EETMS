package com.iss.gms.entity;

import java.util.Date;

/**
 * @author Administrator
 *�ֶβ��չ�ʱ�����
 */
public class EmployeeHoursDetailInfo {

	//Ա�����
	private String employeeId;
	//Ա������
	private String employeeName;
	//����
	private String standardWeek;
	//��������
	private String standardDateType;
	//Ӧ���ڹ�ʱ
	private Integer standardHours=0;
	//������Ŀ����
	private String projectName;
	//�ͻ�ȷ��������ʱ
	private Float customerVerifyHours=0f;
	//�ͻ�ȷ�ϼӰ๤ʱ
	private Float customerVerifyOvertimeHours=0f;
	//psa��ٹ�ʱ
	private Float psaLeaveHours=0f;
	//psa�������
	private String psaLeaveHoursType;
	//�����ɵ��ݹ�ʱ
	private Float currentAhughHours=0f;
	//�����ɲ�����ʱ
	private Float currentSubsidiesHours=0f;
	//����������칤ʱ
	private Float currentGapHours=0f;
	//����
	private Date standardDate;
	//��������
	private String gapType;
	//����ԭ��
	private String employeeGapReason;
	//�ͻ���������
	private String customerResponsiblePerson;
	//��˾��Ŀ����
	private String companyProjectManager;
	//�ͻ�������Ŀ
	private String belongedProject;
	//������ʱ
	private Float writeOffHours=0f;
	//ʣ��ɵ��ݹ�ʱ
	private Float hours4VacTotal=0f;
	//��ʼ���ڣ���ѯ�ã�
	private Date startDate;
	//�������ڣ���ѯ�ã�
	private Date endDate;
	//��ʶ  ���ڲ�ѯ����/�ظ����칤ʱ ���칤ʱ����0
	private String signG;
	//��ʶ  ���ڲ�ѯ����/�ظ����칤ʱ ���칤ʱС��0
	private String signL;
	//��׼��ʱ��Сʱ��
	private Integer standardHoursAll=0;
	//�ͻ�ȷ�Ϲ�ʱ��Сʱ��
	private Integer customerVerifyHoursAll=0;
	//��������
	private String workPlace;
	//����ƽ̨
	private String technologyPlatform;
	//�ڳ�����
	private String presenceOfType;
	//�����ȼ�
	private String technologyGrade;
	//�Ӱ�ɲ����ܹ�ʱ
	private Float hoursOtSum=0f;
	//������׼
	private Float payStandard=0f;
	//�Ӱ��
	private Float pay4hot=0f;
	//�Ƿ�ֻ��ʾ�мӰ���Ϣ��
	private String flag;
	//�����ȼ�������ƽ̨
	private String tgAndTp;
	//��ְ����
	private Date entryDate;
	//��ְ����
	private Date leaveDate;
	//�ͻ�������
	private String empName4Cus;
	//��Դ����
	private String resourcesType;
	//��Ŀ���
	private String projectId;
	//��Ŀ��������
	private String projectOfDepartment;
	//��Ʒ����
	private String productName;
	//�����Ʒ�����Ŷ�
	private String serviceProductsAttributiveTeam;
	//�����Ʒ��������
	private String serviceProductsDepartment;	
	//��Ӧ��
	private String providers;
	//��������
	private Date workDate;
	//������ʱ
	private Float normalWorkingHours;	
	//�Ӱ๤ʱ
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
