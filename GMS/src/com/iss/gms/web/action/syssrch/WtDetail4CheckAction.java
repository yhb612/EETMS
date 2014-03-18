package com.iss.gms.web.action.syssrch;

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

import com.iss.gms.entity.EmployeeBasicInfo;
import com.iss.gms.entity.EmployeeHoursDetailInfo;
import com.iss.gms.entity.HoursWorkStandardInfo;
import com.iss.gms.entity.HoursWorkStatisticsInfo;
import com.iss.gms.entity.Statistics4EveryGapType;
import com.iss.gms.service.HoursWorkService;
import com.iss.gms.service.IEmployeeService;

/**
 * @author Administrator
 *员工工时对帐单
 */
public class WtDetail4CheckAction extends DispatchAction{
	private static final Log log = LogFactory.getLog(WtDetail4CheckAction.class);
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	private HoursWorkService hoursWorkService ;
	private IEmployeeService employeeService;
	
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setHoursWorkService(HoursWorkService hoursWorkService) {
		this.hoursWorkService = hoursWorkService;
	}
	
	public ActionForward initPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LazyValidatorForm lzform = (LazyValidatorForm)form;
		lzform.set("employeeId4s", "");
		return mapping.findForward("success");
	}

	//查询
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			LazyValidatorForm lzform = (LazyValidatorForm)form;
			String employeeId = (String) lzform.get("employeeId4s");
			if(employeeId!=null && employeeId.trim().length()>0){
				EmployeeBasicInfo ebi = new EmployeeBasicInfo();
				//员工基本信息表
//				EmployeeBasicInfo ebii = hoursWorkService.queryWtDetail4CheckPart1(ebi);
				List<EmployeeBasicInfo> empList = employeeService.queryByName(employeeId);
				if(empList==null || empList.size()!=1){
					request.setAttribute("err_msg", "此员工不存在或存在多人同名，请输入员工编号！");
					return mapping.findForward("success");
				}
				EmployeeBasicInfo ebii = empList.get(0);
				
				if(ebii==null){
					return mapping.findForward("success");
				}
				ebi.setEmployeeId(ebii.getEmployeeId());
				//读取工时统计表
				HoursWorkStatisticsInfo hwsi = hoursWorkService.queryWtDetail4CheckPart3(ebi);
				//读取工时详情表
				EmployeeHoursDetailInfo edi = hoursWorkService.queryWtDetail4CheckPart2(hwsi);
				//统计工时详情表中各GAP类型的总工时数
				List<Statistics4EveryGapType> list = hoursWorkService.queryGapHoursList(ebi);
				Statistics4EveryGapType s4eg = new Statistics4EveryGapType();
				if(null!=list && list.size()>0){
					for(Statistics4EveryGapType temp:list){
						if("0".equals(temp.getGapType())){
							s4eg.setStatus0Hours(temp.getCount4Type());
						}else if("1".equals(temp.getGapType())){
							s4eg.setStatus1Hours(temp.getCount4Type());
						}else if("2".equals(temp.getGapType())){
							s4eg.setStatus2Hours(temp.getCount4Type());
						}else if("3".equals(temp.getGapType())){
							s4eg.setStatus3Hours(temp.getCount4Type());
						}else if("4".equals(temp.getGapType())){
							s4eg.setStatus4Hours(temp.getCount4Type());
						}else if("5".equals(temp.getGapType())){
							s4eg.setStatus5Hours(temp.getCount4Type());
						}else if("6".equals(temp.getGapType())){
							s4eg.setStatus6Hours(temp.getCount4Type());
						}else if("7".equals(temp.getGapType())){
							s4eg.setStatus7Hours(temp.getCount4Type());
						}
					}
				}
				//饱和度(客户确认总工时/标准总工时)
				if(hwsi!=null&&edi!=null&&edi.getStandardHoursAll()!=null&&edi.getStandardHoursAll()!=0){
					float saturation = (float)Math.round((hwsi.getCustomerSureTotalHours()/edi.getStandardHoursAll())*100)/100;
					request.setAttribute("saturation", saturation);
				}else request.setAttribute("saturation", 0.0);
				//出勤率(count(客户确认日期)/count(标准日期))
				float cusDateCount=0f;
				HoursWorkStandardInfo stdInf4query = new HoursWorkStandardInfo();
				stdInf4query.setStartDate(ebii.getEntryDate());
				if(hwsi!=null)
				stdInf4query.setEndDate(hwsi.getByTheStatisticalDate());
				HoursWorkStandardInfo stdInfResult=hoursWorkService.queryCountOfEveryType(stdInf4query);
				int stdDateCount=stdInfResult.getWorkDays();
				
				EmployeeHoursDetailInfo ediTemp4query = new EmployeeHoursDetailInfo();
				ediTemp4query.setEmployeeId(ebii.getEmployeeId());
				ediTemp4query.setStartDate(ebii.getEntryDate());
				if(hwsi!=null)
				ediTemp4query.setEndDate(hwsi.getByTheStatisticalDate());
				String te= hoursWorkService.queryCusDateCount(ediTemp4query);
				if(te!=null&&te.trim().length()>0) cusDateCount=Float.parseFloat(te);
				float attendance =(float)Math.round((cusDateCount/stdDateCount)*100)/100;
				request.setAttribute("attendance", attendance);
				
				
				request.setAttribute("ebii", ebii);
				if(null!=edi){
				request.setAttribute("edi", edi);
				}else{
					request.setAttribute("edi", new EmployeeHoursDetailInfo());
				}
				if(null!=hwsi){
				request.setAttribute("hwsi", hwsi);
				}else{
					request.setAttribute("hwsi", new HoursWorkStatisticsInfo());
				}
				request.setAttribute("s4eg", s4eg);
			}
			return mapping.findForward("success");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return mapping.findForward("error");
		}
	}
	
	
}
