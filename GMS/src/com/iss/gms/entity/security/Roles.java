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
	 * ��ɫ���
	 */
	private String roleNo;

	/**
	 * ��ɫ����
	 */
	private String roleName;

	/**
	 * ��ɫ����
	 */
	private String remark;

	/**
	 * �Ƿ���Ȩ0-��1-��
	 */
	private int isAccredit;
	
	/**
	 * ��ɫ��Ӧ�û�����
	 */
	private Set users = new HashSet();
	
	/**
	 * ��ɫ��Ȩ���ܲ˵�����
	 */
	private Set sysMenus = new HashSet();
	
	/**
	 * @roseuid 440BEB3C035B
	 */
	public Roles() {

	}

	/**
	 * �ж�ָ�����������Ƿ�ͱ��������
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
	 * ���ɱ������hash��
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
	 * ȥ���ַ����еĿո�
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
