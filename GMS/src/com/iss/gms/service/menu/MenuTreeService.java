package com.iss.gms.service.menu;

import java.util.List;

public interface MenuTreeService {
	
	/**
	 * ���ָ���û�����Ȩ�˵���
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List getAccreditMenu(String userNo) throws Exception;
	
	/**
	 * ��������˵���
	 * @return
	 * @throws Exception
	 */
	public List getAllMenu() throws Exception;

}
