package com.iss.gms.web.action.security;

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
			//request.setAttribute("proMgrs4pro", proMgrs);
			if(user == null){
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
	
	//返回body页面
	public ActionForward returnBody(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("success1");
	}
	
	//返回menu页面
	public ActionForward returnMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			List sysMenus = menuTreeService.getAllMenu();	
			request.setAttribute("sysMenus", sysMenus);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return mapping.findForward("error");
		}
		return mapping.findForward("success2");
	}
	
	//返回header页面
	public ActionForward returnHeader(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("success3");
	}
	

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public LoginService getLoginService() {
		return loginService;
	}

}
