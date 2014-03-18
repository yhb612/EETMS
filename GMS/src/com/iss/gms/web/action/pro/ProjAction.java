package com.iss.gms.web.action.pro;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.LazyValidatorForm;

import com.iss.gms.entity.EmployeeBasicInfo;
import com.iss.gms.entity.ProjectInfo;
import com.iss.gms.service.IProjectService;
import com.iss.gms.utils.GmsUtils;


public class ProjAction extends DispatchAction {
	private IProjectService projectService;
	
	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}
	
	public ActionForward initPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			List<String> proMgrs = projectService.queryProMgrs();
			List<String> dpts = projectService.querydpts4pro();
			request.setAttribute("dpts4pro", dpts);
			request.setAttribute("proMgrs4pro", proMgrs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	//根据项目名称查询项目信息
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			LazyValidatorForm lzform = (LazyValidatorForm)form;
			String name = (String)lzform.get("pname");
			String projectManager = (String)lzform.get("proMgr4pro");
			String implementDepartment = (String)lzform.get("dpt4pro");
			String name4query="";
			if(name!=null&&name.length()>0) name4query = "%"+name+"%"; 
			ProjectInfo pi = new ProjectInfo();
			pi.setProId(name4query);
			pi.setProjectManager(projectManager);
			pi.setImplementDepartment(implementDepartment);
			List lt = projectService.queryProInfoLike(pi);
			request.setAttribute("lt", lt);
			if(lt!=null){
				request.setAttribute("resultCount", lt.size());
			}
			List<String> proMgrs = projectService.queryProMgrs();
			List<String> dpts = projectService.querydpts4pro();
			request.setAttribute("dpts4pro", dpts);
			request.setAttribute("proMgrs4pro", proMgrs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}
	
	//跳转到添加页面
	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			try {
				return mapping.findForward("success1");
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("error");
			}
	}
	
	//添加信息
	public ActionForward addInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			
			try {
				ProjectInfo p=new ProjectInfo();
				//项目编号
				p.setProId(BeanUtils.getProperty(form, "proId_add4pro"));
				//项目名称
				p.setProName(BeanUtils.getProperty(form, "proName_add4pro"));
				//事业部
				p.setCareerdepartment(BeanUtils.getProperty(form, "careerdepartment_add4pro"));
				//实施部
				p.setImplementDepartment(BeanUtils.getProperty(form, "implementDepartment_add4pro"));
				//项目经理
				p.setProjectManager(BeanUtils.getProperty(form, "projectManager_add4pro"));
				//客户名称
				p.setCustomerName(BeanUtils.getProperty(form, "customerName_add4pro"));
				//开始日期
				if(BeanUtils.getProperty(form, "beginDate_add4pro")!=null&&BeanUtils.getProperty(form, "beginDate_add4pro").trim().length()>0)
				p.setBeginDate(GmsUtils.gmsFormToDate(BeanUtils.getProperty(form, "beginDate_add4pro")));
				//结束日期
				if(BeanUtils.getProperty(form, "endDate_add4pro")!=null&&BeanUtils.getProperty(form, "endDate_add4pro").trim().length()>0)
				p.setEndDate(GmsUtils.gmsFormToDate(BeanUtils.getProperty(form, "endDate_add4pro")));
				
				projectService.addProjectInfo(p);
				
				BeanUtils.setProperty(form, "proId_add4pro", "");
				BeanUtils.setProperty(form, "proName_add4pro", "");
				BeanUtils.setProperty(form, "careerdepartment_add4pro", "");
				BeanUtils.setProperty(form, "implementDepartment_add4pro", "");
				BeanUtils.setProperty(form, "projectManager_add4pro", "");
				BeanUtils.setProperty(form, "customerName_add4pro", "");
				BeanUtils.setProperty(form, "beginDate_add4pro", "");
				BeanUtils.setProperty(form, "endDate_add4pro", "");
				request.setAttribute("success_msg", "yes");
				return mapping.findForward("success1");
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("error");
			}
	}
	
	//跳转到修改页面
	public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			try {
				//得到项目名称
				String pid = request.getParameter("pid");
				ProjectInfo p = projectService.queryProInfoById(pid);
					BeanUtils.setProperty(form, "proId", p.getProId()==""?"":p.getProId());
					BeanUtils.setProperty(form, "proName", p.getProName()==null?"":p.getProName());
					BeanUtils.setProperty(form, "careerdepartment", p.getCareerdepartment()==null?"":p.getCareerdepartment());
					BeanUtils.setProperty(form, "implementDepartment", p.getImplementDepartment()==null?null:p.getImplementDepartment());
					BeanUtils.setProperty(form, "projectManager", p.getProjectManager()==null?"":p.getProjectManager());
					BeanUtils.setProperty(form, "customerName", p.getCustomerName()==null?"":p.getCustomerName());
					BeanUtils.setProperty(form, "beginDate", p.getBeginDate()==null?null:GmsUtils.gmsFormToString(p.getBeginDate()));
					BeanUtils.setProperty(form, "endDate",p.getEndDate()==null?null:GmsUtils.gmsFormToString(p.getEndDate()));
				return mapping.findForward("success2");
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("error");
			}
	}
	
	//修改项目信息
	public ActionForward modifyInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			try {
				ProjectInfo p=new ProjectInfo();
				//项目编号
				p.setProId(BeanUtils.getProperty(form, "proId"));
				//项目名称
				p.setProName(BeanUtils.getProperty(form, "proName"));
				//事业部
				p.setCareerdepartment(BeanUtils.getProperty(form, "careerdepartment"));
				//实施部
				p.setImplementDepartment(BeanUtils.getProperty(form, "implementDepartment"));
				//项目经理
				p.setProjectManager(BeanUtils.getProperty(form, "projectManager"));
				//客户名称
				p.setCustomerName(BeanUtils.getProperty(form, "customerName"));
				//开始日期
				if(BeanUtils.getProperty(form, "beginDate")!=null&&BeanUtils.getProperty(form, "beginDate").trim().length()>0)
				p.setBeginDate(GmsUtils.gmsFormToDate(BeanUtils.getProperty(form, "beginDate")));
				//结束日期
				if(BeanUtils.getProperty(form, "endDate")!=null&&BeanUtils.getProperty(form, "endDate").trim().length()>0)
				p.setEndDate(GmsUtils.gmsFormToDate(BeanUtils.getProperty(form, "endDate")));
				projectService.modifyProjectInfo(p);
				request.setAttribute("success_msg", "yes");
				return mapping.findForward("success2");
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("error");
			}
	}
	
	//删除项目信息
	public ActionForward delInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			try {
				String eidGrp=request.getParameter("pidGrp");
				String str[]=eidGrp.split(",");
				if(str.length>0){
					for(int i=0;i<str.length;i++){
						projectService.removeProjectInfo(str[i]);
					}
				}
				return search(mapping,form,request,response);
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("error");
			}
	}
	
}