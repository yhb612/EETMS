package com.iss.gms.web.action.syssrch;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.LazyValidatorForm;

import com.iss.gms.entity.CustomerQuoteInfo;
import com.iss.gms.entity.CustomerVerifyHoursInfo;
import com.iss.gms.entity.EmployeeHoursDetailInfo;
import com.iss.gms.service.HoursWorkService;
import com.iss.gms.service.IEmployeeService;
import com.iss.gms.utils.GmsUtils;

public class HoursOverTimeMsgAction extends DispatchAction {
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
	
	//��ʼ��
	public ActionForward initPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			List<String> workPlaces = hoursWorkService.queryWorkPlaces();
			request.setAttribute("workPlaces", workPlaces);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	//��ѯ
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LazyValidatorForm lzform = (LazyValidatorForm)form;
		String Id4ot = (String)lzform.get("Id4ot"); 
		String dateS = (String)lzform.get("dateS4ot"); 
		String dateE = (String)lzform.get("dateE4ot"); 
		String workPlace = (String)lzform.get("workPlace4ot"); 
		String flag = (String)lzform.get("flag"); 
		EmployeeHoursDetailInfo ehdi = new EmployeeHoursDetailInfo();
		ehdi.setEmployeeId(Id4ot);
		try {
			if(null==dateS || dateS.trim().length()==0) return mapping.findForward("success");
			ehdi.setStartDate(GmsUtils.gmsFormToDate(dateS));
			if(null==dateE || dateE.trim().length()==0) return mapping.findForward("success");
			ehdi.setEndDate(GmsUtils.gmsFormToDate(dateE));
			ehdi.setWorkPlace(workPlace);
			if("1".equals(flag)){
				ehdi.setFlag("real");
			}
			List<EmployeeHoursDetailInfo> hotList = hoursWorkService.queryOverTimeHours(ehdi);
			// ��ѯ�ͻ�����
			CustomerQuoteInfo cqi = employeeService.queryCstmQuoteInfo(china_bank);
			for(EmployeeHoursDetailInfo eh:hotList){
				CustomerVerifyHoursInfo cvhi = hoursWorkService.queryGradeAndTec(eh.getEmployeeId());
				if (cvhi != null) {
					if (cqi == null) cqi = new CustomerQuoteInfo();
					if ("����".equals(cvhi.getTechnologyPlatform())) {
						if ("�߼�".equals(cvhi.getTechnologyGrade())) {
							eh.setPayStandard(cqi.getOvertimePrice4ST());
							eh.setPay4hot(cqi.getOvertimePrice4ST()*(eh.getHoursOtSum().equals(null)?0f:eh.getHoursOtSum()));
							eh.setTgAndTp("(�߼�����)");
						} else if ("�м�".equals(cvhi.getTechnologyGrade())) {
							eh.setPayStandard(cqi.getOvertimePrice4MT());
							eh.setPay4hot(cqi.getOvertimePrice4MT()*(eh.getHoursOtSum().equals(null)?0f:eh.getHoursOtSum()));
							eh.setTgAndTp("(�м�����)");
						} else if ("����".equals(cvhi.getTechnologyGrade())) {
							eh.setPayStandard(cqi.getOvertimePrice4JT());
							eh.setPay4hot(cqi.getOvertimePrice4JT()*(eh.getHoursOtSum().equals(null)?0f:eh.getHoursOtSum()));
							eh.setTgAndTp("(��������)");
						}
					} else {
						if ("�߼�".equals(cvhi.getTechnologyGrade())) {
							eh.setPayStandard(cqi.getOvertimePrice4SD());
							eh.setPay4hot(cqi.getOvertimePrice4SD()*(eh.getHoursOtSum().equals(null)?0f:eh.getHoursOtSum()));
							eh.setTgAndTp("(�߼�����)");
						} else if ("�м�".equals(cvhi.getTechnologyGrade())) {
							eh.setPayStandard(cqi.getOvertimePrice4MD());
							eh.setPay4hot(cqi.getOvertimePrice4MD()*(eh.getHoursOtSum().equals(null)?0f:eh.getHoursOtSum()));
							eh.setTgAndTp("(�м�����)");
						} else if ("����".equals(cvhi.getTechnologyGrade())) {
							eh.setPayStandard(cqi.getOvertimePrice4JD());
							eh.setPay4hot(cqi.getOvertimePrice4JD()*(eh.getHoursOtSum().equals(null)?0f:eh.getHoursOtSum()));
							eh.setTgAndTp("(��������)");
						}
					}
				}
			}
			request.setAttribute("hoursOtList", hotList);
			request.setAttribute("dateS4ot", dateS);
			request.setAttribute("dateE4ot", dateE);
			List<String> workPlaces = hoursWorkService.queryWorkPlaces();
			request.setAttribute("workPlaces", workPlaces);
			request.setAttribute("resultCount", hotList.size());
			
			return mapping.findForward("success");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err_msg", e.getMessage());
			return mapping.findForward("success");
		}
	}
}
