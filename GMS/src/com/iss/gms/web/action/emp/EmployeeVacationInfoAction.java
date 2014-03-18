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

import com.iss.gms.entity.EmployeeRelegationInfo;
import com.iss.gms.entity.EmployeeVacationInfo;
import com.iss.gms.service.IEmployeeService;

public class EmployeeVacationInfoAction extends DispatchAction{
	private IEmployeeService employeeService;
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	private static final Log log = LogFactory.getLog(EmployeeVacationInfoAction.class);
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	

	public ActionForward initPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			//查询部门列表
			List<String> dpts=employeeService.queryDptmtsVac();
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
			String name = (String)lzform.get("ename4vac");
			String style = (String)lzform.get("vacStyle");
			String dpm = (String)lzform.get("department4vac"); 
			String dateS = (String)lzform.get("vacDateS"); 
			String dateE = (String)lzform.get("vacDateE"); 
			EmployeeVacationInfo evi = new EmployeeVacationInfo();
			if(!"".equals(name) && null != name){
				evi.setEmployeeName(name);
			}
			if(dateE!=null&&dateE.trim().length()>0){
				Date endDate = fmt.parse(dateE);
				evi.setEndDate(endDate);
			}
			if(dateS!=null&&dateS.trim().length()>0){
				Date startDate = fmt.parse(dateS);
				evi.setStartDate(startDate);
			}
			evi.setLeaveTypeName(style);
			evi.setDepartmentName(dpm);
			List lt = employeeService.queryVacationInfo(evi);
			//查询部门列表
			List<String> dpts=employeeService.queryDptmtsVac();
			request.setAttribute("dpts", dpts);
			if(lt!=null){
				request.setAttribute("eviList", lt);
				request.setAttribute("resultCount", lt.size());
				return mapping.findForward("success");
			}else{
				return mapping.findForward("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return mapping.findForward("error");
		}
	}
	
	
}
