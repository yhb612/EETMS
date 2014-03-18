package com.iss.gms.entity.security;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Roles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2611257899966491211L;
	/**
	 * 角色编号
	 */
	private String roleNo;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 角色描述
	 */
	private String remark;

	/**
	 * 是否被授权0-否；1-是
	 */
	private int isAccredit;
	
	/**
	 * 角色对应用户集合
	 */
	private Set users = new HashSet();
	
	/**
	 * 角色授权功能菜单集合
	 */
	private Set sysMenus = new HashSet();
	
	/**
	 * @roseuid 440BEB3C035B
	 */
	public Roles() {

	}

	/**
	 * 判断指定参数对象是否和本对象相等
	 * 
	 * @param obj
	 * @return boolean
	 * @roseuid 440BD94500AB
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (!(obj instanceof Roles))
			return false;

		if (!((Roles) obj).getRoleNo().equals(this.getRoleNo()))
			return false;

		return true;
	}

	/**
	 * 生成本对象的hash码
	 * 
	 * @return int
	 * @roseuid 440BD94500BB
	 */
	public int hashCode() {
		return 29 * roleNo.hashCode() + super.hashCode();
	}

	public Object clone() throws CloneNotSupportedException {
		Roles role = new Roles();
		role.setRoleNo(this.roleNo);
		role.setRoleName(this.roleName);
		role.setRemark(this.remark);
		Set tmpSet = new HashSet();
		for(Iterator it=this.sysMenus.iterator();it.hasNext();){
			tmpSet.add(((SysMenu)it.next()).clone());
		}
		role.setSysMenus(tmpSet);
		return role;
	}
	/**
	 * 去掉字符串中的空格
	 * @param param
	 * @return
	 */
	public String trim(String param){
		if(param == null) return "";
		return param.trim();
	}
	/**
	 * @return Returns the remark.
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark The remark to set.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return Returns the roleName.
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName The roleName to set.
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return Returns the roleNo.
	 */
	public String getRoleNo() {
		return roleNo;
	}

	/**
	 * @param roleNo The roleNo to set.
	 */
	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}

	
	/**
	 * @return Returns the users.
	 */
	public Set getUsers() {
		return users;
	}

	/**
	 * @param users The users to set.
	 */
	public void setUsers(Set users) {
		this.users = users;
	}

	/**
	 * @return Returns the sysMenus.
	 */
	public Set getSysMenus() {
		return sysMenus;
	}

	/**
	 * @param sysMenus The sysMenus to set.
	 */
	public void setSysMenus(Set sysMenus) {
		this.sysMenus = sysMenus;
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

}
