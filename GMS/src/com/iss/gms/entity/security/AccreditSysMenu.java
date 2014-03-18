package com.iss.gms.entity.security;

import java.io.Serializable;

import com.iss.gms.entity.security.SysMenu;

public class AccreditSysMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5847695811248451176L;
	/**
	 * �û����
	 */
	private String userNo;

	/**
	 * �˵����
	 */
	private String sysMenuNo;

	/**
	 * �˵�����
	 */
	private String sysMenuName;

	/**
	 * ϵͳ���ܲ˵����ӵ�ַ
	 */
	private String linkAddress;

	/**
	 * ��ʾ˳��
	 */
	private int sequence;

	/**
	 * �Ƿ�����0-��1-��
	 */
	private String islink;

	/**
	 * ��ע
	 */
	private String remark;

	/**
	 * @roseuid 440BEB3C03C8
	 */
	public AccreditSysMenu() {

	}
	
	/**
	 * ��õ�ǰ���ܲ˵����ϼ��˵����
	 * @param menuNo
	 * @return
	 * @throws Exception
	 */
	public String getParentMenuNo(String menuNo) throws Exception{
		if(menuNo.equals("") || menuNo.length() < 2) return "1";
		return menuNo.substring(0,menuNo.length() - 2);		
	}
	/**
	 * �ж�ָ�����������Ƿ�ͱ��������
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
	 * ���ɱ������hash��
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
