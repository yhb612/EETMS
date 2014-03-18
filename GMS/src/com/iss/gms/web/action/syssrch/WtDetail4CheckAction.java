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
 *Ա����ʱ���ʵ�
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

	//��ѯ
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			LazyValidatorForm lzform = (LazyValidatorForm)form;
			String employeeId = (String) lzform.get("employeeId4s");
			if(employeeId!=null && employeeId.trim().length()>0){
				EmployeeBasicInfo ebi = new EmployeeBasicInfo();
				//Ա��������Ϣ��
//				EmployeeBasicInfo ebii = hoursWorkService.queryWtDetail4CheckPart1(ebi);
				List<EmployeeBasicInfo> empList = employeeService.queryByName(employeeId);
				if(empList==null || empList.size()!=1){
					request.setAttribute("err_msg", "��Ա�������ڻ���ڶ���ͬ����������Ա����ţ�");
					return mapping.findForward("success");
				}
				EmployeeBasicInfo ebii = empList.get(0);
				
				if(ebii==null){
					return mapping.findForward("success");
				}
				ebi.setEmployeeId(ebii.getEmployeeId());
				//��ȡ��ʱͳ�Ʊ�
				HoursWorkStatisticsInfo hwsi = hoursWorkService.queryWtDetail4CheckPart3(ebi);
				//��ȡ��ʱ�����
				EmployeeHoursDetailInfo edi = hoursWorkService.queryWtDetail4CheckPart2(hwsi);
				//ͳ�ƹ�ʱ������и�GAP���͵��ܹ�ʱ��
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
				//���Ͷ�(�ͻ�ȷ���ܹ�ʱ/��׼�ܹ�ʱ)
				if(hwsi!=null&&edi!=null&&edi.getStandardHoursAll()!=null&&edi.getStandardHoursAll()!=0){
					float saturation = (float)Math.round((hwsi.getCustomerSureTotalHours()/edi.getStandardHoursAll())*100)/100;
					request.setAttribute("saturation", saturation);
				}else request.setAttribute("saturation", 0.0);
				//������(count(�ͻ�ȷ������)/count(��׼����))
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
