package com.iss.gms.entity.department;

import java.io.Serializable;

public class Dept implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7286290864760425245L;
	private long deptId;
	private String deptName;
	private String deptNo;	
	private String parentNo;
	private String DWZT;
	private String BXZT;
	private String cbdwbz;   //0社保局,1参保单位
	
	public String isRootDept(long deptId){
		if(deptId == 0){
			return "1";
		}
		if(this.deptId == deptId){
			return "1";
		}
		return "0";
	}
	
	/**
	 * @return Returns the bXZT.
	 */
	public String getBXZT() {
		return BXZT;
	}
	/**
	 * @param bxzt The bXZT to set.
	 */
	public void setBXZT(String bxzt) {
		BXZT = bxzt;
	}
	/**
	 * @return Returns the deptId.
	 */
	public long getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId The deptId to set.
	 */
	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}
	/**
	 * @return Returns the deptName.
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName The deptName to set.
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return Returns the deptNo.
	 */
	public String getDeptNo() {
		return deptNo;
	}
	/**
	 * @param deptNo The deptNo to set.
	 */
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	/**
	 * @return Returns the dWZT.
	 */
	public String getDWZT() {
		return DWZT;
	}
	/**
	 * @param dwzt The dWZT to set.
	 */
	public void setDWZT(String dwzt) {
		DWZT = dwzt;
	}
	/**
	 * @return Returns the parentNo.
	 */
	public String getParentNo() {
		return parentNo;
	}
	/**
	 * @param parentNo The parentNo to set.
	 */
	public void setParentNo(String parentNo) {
		this.parentNo = parentNo;
	}

	public String getCbdwbz() {
		return cbdwbz;
	}

	public void setCbdwbz(String cbdwbz) {
		this.cbdwbz = cbdwbz;
	}

}
