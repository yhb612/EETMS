package com.iss.gms.entity.security;

import java.io.Serializable;

import com.iss.gms.entity.security.SysMenu;

public class AccreditSysMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5847695811248451176L;
	/**
	 * 用户编号
	 */
	private String userNo;

	/**
	 * 菜单编号
	 */
	private String sysMenuNo;

	/**
	 * 菜单名称
	 */
	private String sysMenuName;

	/**
	 * 系统功能菜单连接地址
	 */
	private String linkAddress;

	/**
	 * 显示顺序
	 */
	private int sequence;

	/**
	 * 是否链接0-否；1-是
	 */
	private String islink;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * @roseuid 440BEB3C03C8
	 */
	public AccreditSysMenu() {

	}
	
	/**
	 * 获得当前功能菜单的上级菜单编号
	 * @param menuNo
	 * @return
	 * @throws Exception
	 */
	public String getParentMenuNo(String menuNo) throws Exception{
		if(menuNo.equals("") || menuNo.length() < 2) return "1";
		return menuNo.substring(0,menuNo.length() - 2);		
	}
	/**
	 * 判断指定参数对象是否和本对象相等
	 * 
	 * @param obj
	 * @return boolean
	 * @roseuid 440BD94E02EE
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (!(obj instanceof SysMenu))
			return false;

		if (!((SysMenu) obj).getSysMenuNo().equals(this.getSysMenuNo()))
			return false;

		return true;
	}

	/**
	 * 生成本对象的hash码
	 * 
	 * @return int
	 * @roseuid 440BD94E02F0
	 */
	public int hashCode() {
		return 29 * sysMenuNo.hashCode() + super.hashCode();
	}

	/**
	 * @return Returns the linkAddress.
	 */
	public String getLinkAddress() {
		return linkAddress;
	}

	/**
	 * @param linkAddress
	 *            The linkAddress to set.
	 */
	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	/**
	 * @return Returns the remark.
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            The remark to set.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return Returns the sysMenuName.
	 */
	public String getSysMenuName() {
		return sysMenuName;
	}

	/**
	 * @param sysMenuName
	 *            The sysMenuName to set.
	 */
	public void setSysMenuName(String sysMenuName) {
		this.sysMenuName = sysMenuName;
	}

	/**
	 * @return Returns the sysMenuNo.
	 */
	public String getSysMenuNo() {
		return sysMenuNo;
	}

	/**
	 * @param sysMenuNo
	 *            The sysMenuNo to set.
	 */
	public void setSysMenuNo(String sysMenuNo) {
		this.sysMenuNo = sysMenuNo;
	}
	
	/**
	 * @return Returns the islink.
	 */
	public String getIslink() {
		return islink;
	}

	/**
	 * @param islink
	 *            The islink to set.
	 */
	public void setIslink(String islink) {
		this.islink = islink;
	}

	/**
	 * @return Returns the sequence.
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * @param sequence
	 *            The sequence to set.
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return Returns the userNo.
	 */
	public String getUserNo() {
		return userNo;
	}

	/**
	 * @param userNo
	 *            The userNo to set.
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

}
