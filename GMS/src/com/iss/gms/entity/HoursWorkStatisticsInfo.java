package com.iss.gms.entity;

import java.util.Date;

/**
 * @author Administrator
 *字段参照工时统计表
 */
public class HoursWorkStatisticsInfo {
	private Float standardTotalHours=0f;
	private Float customerSureTotalHours=0f;
	private Float psaWelfareHoliaysTotalHours=0f;
	private Float psaNotWelfareHoliaysTotalHours=0f;
	private Float lastSwoppedTotalHours=0f;
	private Float workOtimeAhughTotalHours=0f;
	private Float workOtimeSubsidiesTotalHours=0f;
	private Date byTheStatisticalDate;
	private String employeeId;
	private String employeeName;
	//用于更新剩余倒休时间（原剩余倒休减去本字段的值=新剩余倒休）
	private Float hours2udtLast;
	
	public Float getHours2udtLast() {
		return hours2udtLast;
	}
	public void setHours2udtLast(Float hours2udtLast) {
		this.hours2udtLast = hours2udtLast;
	}
	public Float getStandardTotalHours() {
		return standardTotalHours;
	}
	public void setStandardTotalHours(Float standardTotalHours) {
		this.standardTotalHours = standardTotalHours;
	}
	public Float getCustomerSureTotalHours() {
		return customerSureTotalHours;
	}
	public void setCustomerSureTotalHours(Float customerSureTotalHours) {
		this.customerSureTotalHours = customerSureTotalHours;
	}
	public Float getPsaWelfareHoliaysTotalHours() {
		return psaWelfareHoliaysTotalHours;
	}
	public void setPsaWelfareHoliaysTotalHours(Float psaWelfareHoliaysTotalHours) {
		this.psaWelfareHoliaysTotalHours = psaWelfareHoliaysTotalHours;
	}
	public Float getPsaNotWelfareHoliaysTotalHours() {
		return psaNotWelfareHoliaysTotalHours;
	}
	public void setPsaNotWelfareHoliaysTotalHours(
			Float psaNotWelfareHoliaysTotalHours) {
		this.psaNotWelfareHoliaysTotalHours = psaNotWelfareHoliaysTotalHours;
	}
	public Float getLastSwoppedTotalHours() {
		return lastSwoppedTotalHours;
	}
	public void setLastSwoppedTotalHours(Float lastSwoppedTotalHours) {
		this.lastSwoppedTotalHours = lastSwoppedTotalHours;
	}
	public Float getWorkOtimeAhughTotalHours() {
		return workOtimeAhughTotalHours;
	}
	public void setWorkOtimeAhughTotalHours(Float workOtimeAhughTotalHours) {
		this.workOtimeAhughTotalHours = workOtimeAhughTotalHours;
	}
	public Float getWorkOtimeSubsidiesTotalHours() {
		return workOtimeSubsidiesTotalHours;
	}
	public void setWorkOtimeSubsidiesTotalHours(Float workOtimeSubsidiesTotalHours) {
		this.workOtimeSubsidiesTotalHours = workOtimeSubsidiesTotalHours;
	}
	public Date getByTheStatisticalDate() {
		return byTheStatisticalDate;
	}
	public void setByTheStatisticalDate(Date byTheStatisticalDate) {
		this.byTheStatisticalDate = byTheStatisticalDate;
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

}
