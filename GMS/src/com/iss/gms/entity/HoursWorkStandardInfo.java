package com.iss.gms.entity;

import java.util.Date;

public class HoursWorkStandardInfo {

	private Date standardDate;
	private String standardWeek;
	private String standardDateType;
	private Integer standardHours=0;
	private Date startDate;
	private Date endDate;
	//工作日
	private int workDays;
	//双休日
	private int weekEndDays;
	//节假日
	private int redLetterDays;
	public int getWorkDays() {
		return workDays;
	}
	public void setWorkDays(int workDays) {
		this.workDays = workDays;
	}
	public int getWeekEndDays() {
		return weekEndDays;
	}
	public void setWeekEndDays(int weekEndDays) {
		this.weekEndDays = weekEndDays;
	}
	public int getRedLetterDays() {
		return redLetterDays;
	}
	public void setRedLetterDays(int redLetterDays) {
		this.redLetterDays = redLetterDays;
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
	public Date getStandardDate() {
		return standardDate;
	}
	public void setStandardDate(Date standardDate) {
		this.standardDate = standardDate;
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
	public Integer getStandardHours() {
		return standardHours;
	}
	public void setStandardHours(Integer standardHours) {
		this.standardHours = standardHours;
	}
	
}
