package com.iss.gms.entity.security;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.iss.gms.entity.department.Dept;


public class Userss implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8913083926457705160L;
	/**
	 * �û�������
	 */
	private String userId;
	/**
	 * ��������
	 */
	private long deptId;

	/**
	 * ϵͳ�����û����
	 */
	private String userNo;

	/**
	 * �û�����
	 */
	private String userName;

	/**
	 * �û���½����
	 */
	private String loginId;

	/**
	 * ��½����
	 */
	private String password;

	/**
	 * ��ע
	 */
	private String remark;
	
	/**
	 * �Ƿ���Ȩ0-��1-��
	 */
	private int isAccredit;

	/**
	 * �û�ӵ�н�ɫ����
	 */
	private Set roles = new HashSet();
	/**
	 * ��������
	 */
	private Dept dept = null;
	
	/**
	 * @roseuid 440BEB3D00CB
	 */
	public Userss() {

	}

	/**
	 * ������ʾ�û�����
	 * @return
	 */
	public String shieldPassword(String pass) {
		String shieldPass = "";
		for (int i = 0; i < pass.trim().length(); i++) {
			shieldPass += "*";
		}
		return shieldPass;
	}

	/**
	 * �ж�ָ�����������Ƿ�ͱ��������
	 * 
	 * @param obj
	 * @return boolean
	 * @roseuid 440BD9380000
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (!(obj instanceof Userss))
			return false;

		if (!((Userss) obj).getUserNo().equals(this.getUserNo()))
			return false;

		return true;
	}

	/**
	 * ���ɱ������hash��
	 * 
	 * @return int
	 * @roseuid 440BD938000F
	 */
	public int hashCode() {
		return 0;
	}

	public Object clone() throws CloneNotSupportedException {
		Userss user = new Userss();
		user.setLoginId(this.loginId);
		user.setPassword(this.password);
		user.setRemark(this.remark);
		user.setUserName(this.userName);
		user.setUserNo(this.userNo);
		Set tmpSet = new HashSet();
		for(Iterator it=this.roles.iterator();it.hasNext();){
			tmpSet.add(((Roles)it.next()).clone());
		}
		user.setRoles(tmpSet);
		return user;
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
	 * @return Returns the dept.
	 */
	public Dept getDept() {
		return dept;
	}

	/**
	 * @param dept The dept to set.
	 */
	public void setDept(Dept dept) {
		this.dept = dept;
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

	/**
	 * @return Returns the loginId.
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId The loginId to set.
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return Returns the roles.
	 */
	public Set getRoles() {
		return roles;
	}

	/**
	 * @param roles The roles to set.
	 */
	public void setRoles(Set roles) {
		this.roles = roles;
	}

	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return Returns the userNo.
	 */
	public String getUserNo() {
		return userNo;
	}

	/**
	 * @param userNo The userNo to set.
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}


}
