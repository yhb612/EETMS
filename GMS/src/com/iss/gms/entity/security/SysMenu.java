package com.iss.gms.entity.security;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SysMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4213511925047465309L;
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
	private int	sequence;
	
	/**
	 * 是否链接0-否；1-是
	 */
	private String islinked;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 是否被授权0-否；1-是
	 */
	private int isAccredit;
	
	/**
	 * 父菜单编号
	 */
	private String parent;

	/**
	 * 功能菜单授权角色集合
	 */
	private Set roles = new HashSet();

	/**
	 * @roseuid 440BEB3C03C8
	 */
	public SysMenu() {

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

	public Object clone() throws CloneNotSupportedException {
		SysMenu menu = new SysMenu();
		menu.setIslinked(this.islinked);
		menu.setLinkAddress(this.linkAddress);
		menu.setRemark(this.remark);
		menu.setSequence(this.sequence);
		menu.setSysMenuName(this.sysMenuName);
		menu.setSysMenuNo(this.sysMenuNo);
		menu.setParent(this.parent);
		return menu;
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
	 * @return Returns the roles.
	 */
	public Set getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            The roles to set.
	 */
	public void setRoles(Set roles) {
		this.roles = roles;
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
	public String getIslinked() {
		return islinked;
	}

	/**
	 * @param islink The islink to set.
	 */
	public void setIslinked(String islink) {
		this.islinked = islink;
	}

	/**
	 * @return Returns the sequence.
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * @param sequence The sequence to set.
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return Returns the isAccredit.
	 */
	public int getIsAccredit() {
		return isAccredit;
	}

	/**
	 * @param isAccredit The isAccredit to set.
	 */
	public void setIsAccredit(int isAccredit) {
		this.isAccredit = isAccredit;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getParent() {
		return parent;
	}

}
