package com.iss.gms.service.menu;

import java.util.List;

public interface MenuTreeService {
	
	/**
	 * 获得指定用户的授权菜单树
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List getAccreditMenu(String userNo) throws Exception;
	
	/**
	 * 获得整个菜单树
	 * @return
	 * @throws Exception
	 */
	public List getAllMenu() throws Exception;

}
