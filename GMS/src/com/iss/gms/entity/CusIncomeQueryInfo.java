package com.iss.gms.entity;

import java.util.Date;

public class CusIncomeQueryInfo {
	//����
	private String tec;
	//�ȼ�
	private String grade;
	//�ܹ�ʱ
	private Double totalHours=0d;
	//��ʼʱ��
	private Date sDate;
	//����ʱ��
	private Date eDate;
	//������������
	private Double ic4jt=0d;
	//�м���������
	private Double ic4mt=0d;
	//�߼���������
	private Double ic4st=0d;
	//������������
	private Double ic4jd=0d;
	//�м���������
	private Double ic4md=0d;
	//�߼���������
	private Double ic4sd=0d;
	//�ϼ�
	private Double icAll=0d;
	
	public Double getIcAll() {
		return icAll;
	}
	public void setIcAll(Double icAll) {
		this.icAll = icAll;
	}
	public Double getIc4jt() {
		return ic4jt;
	}
	public void setIc4jt(Double ic4jt) {
		this.ic4jt = ic4jt;
	}
	public Double getIc4mt() {
		return ic4mt;
	}
	public void setIc4mt(Double ic4mt) {
		this.ic4mt = ic4mt;
	}
	public Double getIc4st() {
		return ic4st;
	}
	public void setIc4st(Double ic4st) {
		this.ic4st = ic4st;
	}
	public Double getIc4jd() {
		return ic4jd;
	}
	public void setIc4jd(Double ic4jd) {
		this.ic4jd = ic4jd;
	}
	public Double getIc4md() {
		return ic4md;
	}
	public void setIc4md(Double ic4md) {
		this.ic4md = ic4md;
	}
	public Double getIc4sd() {
		return ic4sd;
	}
	public void setIc4sd(Double ic4sd) {
		this.ic4sd = ic4sd;
	}
	public Date getsDate() {
		return sDate;
	}
	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}
	public Date geteDate() {
		return eDate;
	}
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	public String getTec() {
		return tec;
	}
	public void setTec(String tec) {
		this.tec = tec;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Double getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(Double totalHours) {
		this.totalHours = totalHours;
	}
	

}
