package com.iss.gms.web.action.hourswork;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.iss.gms.service.HoursWorkService;
import com.iss.gms.utils.GmsUtils;

public class CustomeerHourAction extends DispatchAction {
	private HoursWorkService hoursWorkService;
	
	public void setHoursWorkService(HoursWorkService hoursWorkService) {
		this.hoursWorkService = hoursWorkService;
	}

	//跳转到导入页面
	public ActionForward toImp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			//客户确认最后一天
			Date clDate  = hoursWorkService.queryLastDate4cusVer();
			//统计截止日期最后一天
			Date slDate = hoursWorkService.queryLastDate4sta();
			if(clDate!=null){
				if(clDate.equals(slDate)){
					request.setAttribute("dataStatus", "last");
				}else{
					request.setAttribute("dataStatus", "notLast");
				}
				request.setAttribute("clDate", GmsUtils.gmsFormToString(clDate));
			}
			return mapping.findForward("success");
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("error");
		}
	}
}