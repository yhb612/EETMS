package com.iss.gms.entity;

public class Statistics4EveryGapType {
	//迟到早退总工时
	private Float status0Hours=0f;
	//现场储备工时
	private Float status1Hours=0f;
	//回公司开会（培训）工时
	private Float status2Hours=0f;
	//离场工时
	private Float status3Hours=0f;
	//公司储备工时
	private Float status4Hours=0f;
	//员工打卡异常冲倒休工时
	private Float status5Hours=0f;
	//闲置工时
	private Float status6Hours=0f;
	//正常倒休工时
	private Float status7Hours=0f;
	//差异类型
	private String gapType;
	//核销总时间
	private Float count4Type=0f;
	public Float getStatus0Hours() {
		return status0Hours;
	}
	public void setStatus0Hours(Float status0Hours) {
		this.status0Hours = status0Hours;
	}
	public Float getStatus1Hours() {
		return status1Hours;
	}
	public void setStatus1Hours(Float status1Hours) {
		this.status1Hours = status1Hours;
	}
	public Float getStatus2Hours() {
		return status2Hours;
	}
	public void setStatus2Hours(Float status2Hours) {
		this.status2Hours = status2Hours;
	}
	public Float getStatus3Hours() {
		return status3Hours;
	}
	public void setStatus3Hours(Float status3Hours) {
		this.status3Hours = status3Hours;
	}
	public Float getStatus4Hours() {
		return status4Hours;
	}
	public void setStatus4Hours(Float status4Hours) {
		this.status4Hours = status4Hours;
	}
	public Float getStatus5Hours() {
		return status5Hours;
	}
	public void setStatus5Hours(Float status5Hours) {
		this.status5Hours = status5Hours;
	}
	public Float getStatus6Hours() {
		return status6Hours;
	}
	public void setStatus6Hours(Float status6Hours) {
		this.status6Hours = status6Hours;
	}
	public Float getStatus7Hours() {
		return status7Hours;
	}
	public void setStatus7Hours(Float status7Hours) {
		this.status7Hours = status7Hours;
	}
	public String getGapType() {
		return gapType;
	}
	public void setGapType(String gapType) {
		this.gapType = gapType;
	}
	public Float getCount4Type() {
		return count4Type;
	}
	public void setCount4Type(Float count4Type) {
		this.count4Type = count4Type;
	}
}
