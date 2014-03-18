package com.iss.gms.web.action.emp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.LazyValidatorForm;

import com.iss.gms.entity.EmployeeDimissionInfo;
import com.iss.gms.entity.EmployeeVacationInfo;
import com.iss.gms.service.IEmployeeService;

public class EmployeeDimissionInfoAction extends DispatchAction{
	private IEmployeeService employeeService;
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	private static final Log log = LogFactory.getLog(EmployeeDimissionInfoAction.class);
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	

	public ActionForward initPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			//查询离职信息中的所有部门
			List<String> dpts=employeeService.queryDptmtsDim();
			request.setAttribute("dpts", dpts);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	//查询
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			LazyValidatorForm lzform = (LazyValidatorForm)form;
			String name = (String)lzform.get("ename4dim");
			String dpm = (String)lzform.get("department4dim"); 
			String dateS = (String)lzform.get("dimDateS"); 
			String dateE = (String)lzform.get("dimDateE"); 
			EmployeeDimissionInfo edi = new EmployeeDimissionInfo();
			if(!"".equals(name) && null != name){
				edi.setEmployeeName(name);
			}
			if(dateE!=null&&dateE.trim().length()>0){
				Date endDate = fmt.parse(dateE);
				edi.setEndDate(endDate);
			}
			if(dateS!=null&&dateS.trim().length()>0){
				Date startDate = fmt.parse(dateS);
				edi.setStartDate(startDate);
			}
			edi.setDepartment(dpm);
			List lt = employeeService.queryDimissionInfo(edi);
			//查询部门列表
			List<String> dpts=employeeService.queryDptmtsDim();
			request.setAttribute("dpts", dpts);
			if(lt!=null){
				request.setAttribute("ediList", lt);
				request.setAttribute("resultCount", lt.size());
				return mapping.findForward("success");
			}else{
				return mapping.findForward("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return mapping.findForward("error");
		}
	}
	//修改
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			String  str = new String(request.getParameter("str").getBytes("ISO-8859-1"),"utf-8");   
 			String eid = request.getParameter("eid");
			String [] temp = str.split(",");
			EmployeeDimissionInfo edi = new EmployeeDimissionInfo();
			edi.setResignationReasons(temp[0]);
			edi.setInitiativeOrPassive(temp[1]);
			edi.setEmployeeId(eid);
			employeeService.modifyInfoById(edi);
			return search(mapping,form,request,response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return mapping.findForward("error");
		}
	}
	
}
