package com.iss.gms.web.action.emp;

import java.sql.SQLException;
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

import com.iss.gms.entity.CustomerQuoteInfo;
import com.iss.gms.service.IEmployeeService;
import com.iss.gms.utils.GmsUtils;

public class CustomerQuoteInfoAction extends DispatchAction {

	private static final Log log = LogFactory.getLog(EmployeeBasicInfoAction.class);
	private IEmployeeService employeeService;
	private String china_bank;
	private String chinabank4jsp;
	
	public void setChinabank4jsp(String chinabank4jsp) {
		this.chinabank4jsp = chinabank4jsp;
	}
	public void setChina_bank(String chinaBank) {
		china_bank = chinaBank;
	}
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	public ActionForward initPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			CustomerQuoteInfo cqiTemp = employeeService.queryCstmQuoteInfo(china_bank);
			CustomerQuoteInfo cqi = new CustomerQuoteInfo();
			cqi.setCustomerName(china_bank);
			if(cqiTemp==null) employeeService.addCstmQuoteInfo(cqi);
			List<CustomerQuoteInfo> cusList = employeeService.queryCstmNames();
			request.setAttribute("cusList", cusList);
			request.setAttribute("cqi", cqi);
			request.setAttribute("china_bank", china_bank);
			request.setAttribute("chinabank4jsp",GmsUtils.fix2utf(chinabank4jsp) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			LazyValidatorForm lzform = (LazyValidatorForm)form;
			String customerName = (String)lzform.get("cust");
				CustomerQuoteInfo cqi=employeeService.queryCstmQuoteInfo(customerName);
				if(cqi!=null){
					request.setAttribute("cqi",cqi);
				}else{
					request.setAttribute("cqi",new CustomerQuoteInfo());
				}
				request.setAttribute("customerName", customerName);
				request.setAttribute("china_bank", china_bank);
				
				List<CustomerQuoteInfo> cusList = employeeService.queryCstmNames();
				request.setAttribute("cusList", cusList);
				request.setAttribute("chinabank4jsp",GmsUtils.fix2utf(chinabank4jsp) );
			return mapping.findForward("success");
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return mapping.findForward("error");
		}
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			LazyValidatorForm lzform = (LazyValidatorForm)form;
			String customerName = (String)lzform.get("cust");
			CustomerQuoteInfo cqi = new CustomerQuoteInfo();
			try{
				cqi.setPrice4ST(Float.parseFloat((String)lzform.get("price4ST")));
				cqi.setOvertimePrice4ST(Float.parseFloat((String)lzform.get("overtimePrice4ST")));
				cqi.setPrice4MT(Float.parseFloat((String)lzform.get("price4MT")));
				cqi.setOvertimePrice4MT(Float.parseFloat((String)lzform.get("overtimePrice4MT")));
				cqi.setPrice4JT(Float.parseFloat((String)lzform.get("price4JT")));
				cqi.setOvertimePrice4JT(Float.parseFloat((String)lzform.get("overtimePrice4JT")));
				cqi.setPrice4SD(Float.parseFloat((String)lzform.get("price4SD")));
				cqi.setOvertimePrice4SD(Float.parseFloat((String)lzform.get("overtimePrice4SD")));
				cqi.setPrice4MD(Float.parseFloat((String)lzform.get("price4MD")));
				cqi.setOvertimePrice4MD(Float.parseFloat((String)lzform.get("overtimePrice4MD")));
				cqi.setPrice4JD(Float.parseFloat((String)lzform.get("price4JD")));
				cqi.setOvertimePrice4JD(Float.parseFloat((String)lzform.get("overtimePrice4JD")));
			}catch (NumberFormatException e) {
				request.setAttribute("err_msg","数据格式错误，请重新输入！");
				return search(mapping,form,request,response);
			}
			cqi.setCustomerName(customerName);
			employeeService.modifyCstmQuoteInfo(cqi);
			return search(mapping,form,request,response);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return mapping.findForward("error");
		}
	}
	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LazyValidatorForm lzform = (LazyValidatorForm)form;
		lzform.set("cusName4add", "");
		lzform.set("addprice4ST", "");
		lzform.set("addovertimePrice4ST", "");
		lzform.set("addprice4MT", "");
		lzform.set("addovertimePrice4MT", "");
		lzform.set("addprice4JT", "");
		lzform.set("addovertimePrice4JT", "");
		lzform.set("addprice4SD", "");
		lzform.set("addovertimePrice4SD", "");
		lzform.set("addprice4MD", "");
		lzform.set("addovertimePrice4MD", "");
		lzform.set("addprice4JD", "");
		lzform.set("addovertimePrice4JD", "");
		return mapping.findForward("success1");
		
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LazyValidatorForm lzform = (LazyValidatorForm)form;
		String cusName4add = (String)lzform.get("cusName4add");
		CustomerQuoteInfo cqi = new CustomerQuoteInfo();
		cqi.setPrice4ST(((String)lzform.get("addprice4ST")).trim().equals("")?0f:Float.parseFloat((String)lzform.get("addprice4ST")));
		cqi.setOvertimePrice4ST(((String)lzform.get("addovertimePrice4ST")).trim().equals("")?0f:Float.parseFloat((String)lzform.get("addovertimePrice4ST")));
		cqi.setPrice4MT(((String)lzform.get("addprice4MT")).trim().equals("")?0f:Float.parseFloat((String)lzform.get("addprice4MT")));
		cqi.setOvertimePrice4MT(((String)lzform.get("addovertimePrice4MT")).trim().equals("")?0f:Float.parseFloat((String)lzform.get("addovertimePrice4MT")));
		cqi.setPrice4JT(((String)lzform.get("addprice4JT")).trim().equals("")?0f:Float.parseFloat((String)lzform.get("addprice4JT")));
		cqi.setOvertimePrice4JT(((String)lzform.get("addovertimePrice4JT")).trim().equals("")?0f:Float.parseFloat((String)lzform.get("addovertimePrice4JT")));
		cqi.setPrice4SD(((String)lzform.get("addprice4SD")).trim().equals("")?0f:Float.parseFloat((String)lzform.get("addprice4SD")));
		cqi.setOvertimePrice4SD(((String)lzform.get("addovertimePrice4SD")).trim().equals("")?0f:Float.parseFloat((String)lzform.get("addovertimePrice4SD")));
		cqi.setPrice4MD(((String)lzform.get("addprice4MD")).trim().equals("")?0f:Float.parseFloat((String)lzform.get("addprice4MD")));
		cqi.setOvertimePrice4MD(((String)lzform.get("addovertimePrice4MD")).trim().equals("")?0f:Float.parseFloat((String)lzform.get("addovertimePrice4MD")));
		cqi.setPrice4JD(((String)lzform.get("addprice4JD")).trim().equals("")?0f:Float.parseFloat((String)lzform.get("addprice4JD")));
		cqi.setOvertimePrice4JD(((String)lzform.get("addovertimePrice4JD")).trim().equals("")?0f:Float.parseFloat((String)lzform.get("addovertimePrice4JD")));
		cqi.setCustomerName(cusName4add);
		try {
			CustomerQuoteInfo cqiTemp =  employeeService.queryCstmQuoteInfo(cusName4add);
			if(cqiTemp==null){
				employeeService.addCstmQuoteInfo(cqi);
			}else{
				request.setAttribute("err_msg", "yes");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("success_msg", "yes");
		return mapping.findForward("success1");
	}
}
