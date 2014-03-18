package com.iss.gms.web.action.security;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.LazyValidatorForm;
import com.iss.gms.entity.security.Userss;
import com.iss.gms.service.menu.MenuTreeService;
import com.iss.gms.service.security.LoginService;

public class LoginAction extends DispatchAction {
	
	private LoginService loginService;
	private MenuTreeService menuTreeService;
	
	//private List sysMenus;
	
	//public List getSysMenus() {
	//	return sysMenus;
	//}

	//public void setSysMenus(List sysMenus) {
	//	this.sysMenus = sysMenus;
	//}

	public MenuTreeService getMenuTreeService() {
		return menuTreeService;
	}

	public void setMenuTreeService(MenuTreeService menuTreeService) {
		this.menuTreeService = menuTreeService;
	}

	//校验用户名和密码
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			LazyValidatorForm lzform = (LazyValidatorForm)form;
			String loginid = (String)lzform.get("txtUserName");
			String pwd = (String)lzform.get("txtUserPassword");
			Userss us = new Userss();
			us.setLoginId(loginid);
			us.setPassword(pwd);
			Userss user = loginService.getUser(us);
			List sysMenus = menuTreeService.getAllMenu();
			request.setAttribute("sysMenus", sysMenus);
			//request.setAttribute("proMgrs4pro", proMgrs);
			if(user == null){
				int i = 1;
				return mapping.findForward("error");
			}
			request.setAttribute("username",user.getUserName());	
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public LoginService getLoginService() {
		return loginService;
	}

}
