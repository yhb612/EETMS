package com.iss.gms.web.action.syssrch;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.LazyValidatorForm;

import com.iss.gms.entity.CusIncomeQueryInfo;
import com.iss.gms.entity.CustomerQuoteInfo;
import com.iss.gms.service.HoursWorkService;
import com.iss.gms.service.IEmployeeService;
import com.iss.gms.utils.GmsUtils;

public class CusIncomeQueryAction extends DispatchAction {
	private HoursWorkService hoursWorkService;
	private IEmployeeService employeeService;
	private String china_bank;

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setChina_bank(String chinaBank) {
		china_bank = chinaBank;
	}

	public void setHoursWorkService(HoursWorkService hoursWorkService) {
		this.hoursWorkService = hoursWorkService;
	}

	public ActionForward initPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CusIncomeQueryInfo ciqi = new CusIncomeQueryInfo();
		request.setAttribute("ciqi", ciqi);
		return mapping.findForward("success");
	}

	// 查询
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LazyValidatorForm lzForm = (LazyValidatorForm) form;
		String dateS = (String) lzForm.get("sDate4ic");
		String dateE = (String) lzForm.get("eDate4ic");
		CusIncomeQueryInfo ciqi4query = new CusIncomeQueryInfo();
		String err_msg = "";
		DecimalFormat df = new DecimalFormat("0.00");
		try {
			if (dateS != null && dateS.trim().length() > 0) {
				Date estimateLDateS = GmsUtils.gmsFormToDate(dateS);
				ciqi4query.setsDate(estimateLDateS);
			}
			if (dateE != null && dateE.trim().length() > 0) {
				Date estimateLDateE = GmsUtils.gmsFormToDate(dateE);
				ciqi4query.seteDate(estimateLDateE);
			}
			List<CusIncomeQueryInfo> ciqiList = hoursWorkService
					.queryHoursByTecAndGrade(ciqi4query);
			// 查询客户报价
			CustomerQuoteInfo cqi4check = employeeService
					.queryCstmQuoteInfo(china_bank);
			if (cqi4check == null)
				cqi4check = new CustomerQuoteInfo();
			CusIncomeQueryInfo ciqi = new CusIncomeQueryInfo();
			
			for(CusIncomeQueryInfo tem:ciqiList){
				if(tem.getTotalHours()!=null){
					if("测试".equals(tem.getTec())){
						if("初级".equals(tem.getGrade())){
							ciqi.setIc4jt(tem.getTotalHours()*(cqi4check.getPrice4JT()/8));
						}else if("中级".equals(tem.getGrade())){
							ciqi.setIc4mt(tem.getTotalHours()*(cqi4check.getPrice4MT()/8));
						}else if("高级".equals(tem.getGrade())){
							ciqi.setIc4st(tem.getTotalHours()*(cqi4check.getPrice4ST()/8));
						}
					
					}else if("开放平台".equals(tem.getTec())){
						if("初级".equals(tem.getGrade())){
							ciqi.setIc4jd(tem.getTotalHours()*(cqi4check.getPrice4JD()/8));
						}else if("中级".equals(tem.getGrade())){
							ciqi.setIc4md(tem.getTotalHours()*(cqi4check.getPrice4MD()/8));
						}else if("高级".equals(tem.getGrade())){
							ciqi.setIc4sd(tem.getTotalHours()*(cqi4check.getPrice4SD()/8));
						}
					}
				}
			}
			ciqi.setIcAll(
					ciqi.getIc4jt()+
					ciqi.getIc4mt()+
					ciqi.getIc4st()+
					ciqi.getIc4jd()+
					ciqi.getIc4md()+
					ciqi.getIc4sd()
			);
			request.setAttribute("ciqi", ciqi);
		} catch (Exception e) {
			request.setAttribute("err_msg",err_msg);
			return mapping.findForward("success");
		}
		return mapping.findForward("success");
	}
}
