package com.iss.gms.web.action.hourswork;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.LazyValidatorForm;

import com.iss.gms.entity.EmployeeBasicInfo;
import com.iss.gms.entity.EmployeeDimissionInfo;
import com.iss.gms.entity.EmployeeHoursDetailInfo;
import com.iss.gms.entity.HoursWorkStatisticsInfo;
import com.iss.gms.service.HoursWorkService;
import com.iss.gms.service.IEmployeeService;
import com.iss.gms.utils.GmsUtils;


public class GapCheckAction extends DispatchAction {
	Logger log = Logger.getLogger(GapCheckAction.class);
	private HoursWorkService hoursWorkService ;
	private IEmployeeService employeeService;
	private String expPath;
	
	public void setExpPath(String expPath) {
		this.expPath = expPath;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setHoursWorkService(HoursWorkService hoursWorkService) {
		this.hoursWorkService = hoursWorkService;
	}
	public ActionForward initPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		LazyValidatorForm lzform = (LazyValidatorForm)form;
//		lzform.set("Id4g", "");
//		lzform.set("dateS4g", "");
//		lzform.set("dateE4g", "");
//		lzform.set("gapType", "");
//		lzform.set("workPlace", "");
		try {
			List<String> workPlaces = hoursWorkService.queryWorkPlaces();
			request.setAttribute("workPlaces", workPlaces);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			LazyValidatorForm lzform = (LazyValidatorForm)form;
			String Id4g = (String)lzform.get("Id4g"); 
			String dateS = (String)lzform.get("dateS4g"); 
			String dateE = (String)lzform.get("dateE4g"); 
			String gapType = (String)lzform.get("gapType"); 
			String workPlace = (String)lzform.get("workPlace"); 
			EmployeeHoursDetailInfo ehdi = new EmployeeHoursDetailInfo();
				ehdi.setEmployeeId(Id4g);
			if(null==dateS || dateS.trim().length()==0) return mapping.findForward("success");
				ehdi.setStartDate(GmsUtils.gmsFormToDate(dateS));
			if(null==dateE || dateE.trim().length()==0) return mapping.findForward("success");
				ehdi.setEndDate(GmsUtils.gmsFormToDate(dateE));
			//差异种类
			if("0".equals(gapType)){
				ehdi.setSignG("G");
			}else if("1".equals(gapType)){
				ehdi.setSignL("L");
			}
			//工作属地
			ehdi.setWorkPlace(workPlace);
			
			List<EmployeeHoursDetailInfo> gapChList = hoursWorkService.queryGapInfo(ehdi);
			
			for(int i=gapChList.size()-1;i>=0;i--){
				if(gapChList.get(i)!=null&&gapChList.get(i).getEmployeeId()!=null){
					List<EmployeeBasicInfo> emplist = employeeService.queryByName(gapChList.get(i).getEmployeeId());
					EmployeeDimissionInfo edi = new EmployeeDimissionInfo();
					edi.setEmployeeName(gapChList.get(i).getEmployeeId());
					List<EmployeeDimissionInfo> ediList = employeeService.queryDimissionInfo(edi);
					if(emplist!=null&&emplist.size()==1){
						//是否参与工时核查(排除不参与工时核查的员工)
						if("1".equals(emplist.get(0).getBeCheck4Gap())){
							gapChList.remove(i);
							continue;
						}
						//入职日期之前的不要
						if(emplist.get(0).getEntryDate()!=null){
							if(emplist.get(0).getEntryDate().after(gapChList.get(i).getStandardDate())){
								gapChList.remove(i);
								continue;
							}
						}
					}
					//离职日期之后的不要
					if(ediList!=null&&ediList.size()==1&&ediList.get(0).getResignationDate()!=null){
						if(ediList.get(0).getResignationDate().before(gapChList.get(i).getStandardDate())){
							gapChList.remove(i);
						}
					}
					
				}
				
			}
			for(EmployeeHoursDetailInfo tem:gapChList){
				HoursWorkStatisticsInfo hwsi =hoursWorkService.queryHoursStatistics(tem.getEmployeeId());
				if(hwsi!=null) {
					Float lsth = hwsi.getLastSwoppedTotalHours();
					tem.setHours4VacTotal(lsth);
				}
			}
			request.setAttribute("emid", Id4g);
			request.setAttribute("gapChList", gapChList);
			if(gapChList!=null) request.setAttribute("resultCount", gapChList.size());
			
			try {
				List<String> workPlaces = hoursWorkService.queryWorkPlaces();
				request.setAttribute("workPlaces", workPlaces);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			LazyValidatorForm lzform = (LazyValidatorForm)form;
			String str = (String )lzform.get("msg4Ud");
			
			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.US);
			String [] gapType2Update ={"0","3","5","6","7","8"};//会发生冲销操作的差异类型(差异类型 0迟到早退1现场储备2回公司开会3离场4公司储备5打卡异常6闲置7正常倒休8重复工时追加倒休)
			try{
				String keys [] = str.split("_-_");
				for(String key:keys){
					if(!key.isEmpty()&&key.contains(",")){
						String paras []= request.getParameterValues(key);
						EmployeeHoursDetailInfo ehdi = new EmployeeHoursDetailInfo();
						ehdi.setEmployeeId(key.substring(0,key.indexOf(",")));
						ehdi.setStandardDate(sdf.parse(key.substring(key.indexOf(",")+1)));
						ehdi.setGapType(paras[0].trim());
						ehdi.setEmployeeGapReason(paras[1].trim());
						ehdi.setCustomerResponsiblePerson(paras[2].trim());
						ehdi.setCompanyProjectManager(paras[3].trim());
						ehdi.setBelongedProject(paras[4].trim());
						EmployeeHoursDetailInfo ehditem= hoursWorkService.queryHoursDetail(ehdi);
						if(ehditem==null) continue;
						if(paras[0].trim().length()==1){//只要选择了GAP类型 就清空GAP工时
							ehdi.setCurrentGapHours(0f);
							ehdi.setWriteOffHours(ehditem.getCurrentGapHours());
						}else{
							ehdi.setCurrentGapHours(ehditem.getCurrentGapHours());
						}
						
						//差异类型为指定类型 需更新员工剩余倒休工时
						for(String tem :gapType2Update){
							if(tem.equals(paras[0].trim())){
								HoursWorkStatisticsInfo hwsi = new HoursWorkStatisticsInfo();
								hwsi.setEmployeeId(key.substring(0,paras[0].indexOf(",")));
								hwsi.setHours2udtLast(ehditem.getCurrentGapHours());
								hoursWorkService.modifyLastHours(hwsi);
								break;
							}
						}
						hoursWorkService.modifyGapInfo(ehdi);
					}
				}
				return search(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return mapping.findForward("error");
		}
	}
	
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LazyValidatorForm lzform = (LazyValidatorForm)form;
		try {
			String Id4g = (String)lzform.get("Id4g"); 
			String dateS = (String)lzform.get("dateS4g"); 
			String dateE = (String)lzform.get("dateE4g"); 
			String gapType = (String)lzform.get("gapType"); 
			String workPlace = (String)lzform.get("workPlace"); 
			EmployeeHoursDetailInfo ehdi = new EmployeeHoursDetailInfo();
				ehdi.setEmployeeId(Id4g);
			if(null!=dateS&&dateS.trim().length()>0){
				ehdi.setStartDate(GmsUtils.gmsFormToDate(dateS));
			}else{
				return mapping.findForward("success");
			}
			if(null!=dateE&&dateE.trim().length()>0){
				ehdi.setEndDate(GmsUtils.gmsFormToDate(dateE));
			}else{
				return mapping.findForward("success");
			}
			if("0".equals(gapType)){
				ehdi.setSignG("G");
			}else if("1".equals(gapType)){
				ehdi.setSignL("L");
			}
			//工作属地
			ehdi.setWorkPlace(workPlace);
			//情景一、根据条件从工时详情表查询所有人的数据
//			List<EmployeeHoursDetailInfo> gapChList = hoursWorkService.queryGapInfo(ehdi);
			//是否参与工时核查(排除不参与工时核查的员工)
//			for(int i=gapChList.size()-1;i>=0;i--){
//				if(gapChList.get(i)!=null&&gapChList.get(i).getEmployeeId()!=null){
//					List<EmployeeBasicInfo> emplist = employeeService.queryByName(gapChList.get(i).getEmployeeId());
//					if(emplist!=null&&emplist.size()==1){
//						if("1".equals(emplist.get(0).getBeCheck4Gap())){
//							gapChList.remove(i);
//						}
//					}
//				}
//			}
			
			//情景二、根据条件从工时详情表只查询与客户确认工时表相关人员的数据(客户确认工时表里没有的人不导出)
			//因不参与工时核查的人在客户确认工时表里也不会有其信息 故无须做如上的排除操作
			List<EmployeeHoursDetailInfo> gapChList = hoursWorkService.queryGapInfoWithCus(ehdi);
			EmployeeDimissionInfo edi = new EmployeeDimissionInfo();
			for(int i=gapChList.size()-1;i>=0;i--){
				if(gapChList.get(i)!=null&&gapChList.get(i).getEmployeeId()!=null){
					HoursWorkStatisticsInfo hwsi =hoursWorkService.queryHoursStatistics(gapChList.get(i).getEmployeeId());
					if(hwsi!=null) {
						gapChList.get(i).setHours4VacTotal(hwsi.getLastSwoppedTotalHours());
					}
					List<EmployeeBasicInfo> emplist = employeeService.queryByName(gapChList.get(i).getEmployeeId());
					edi.setEmployeeName(gapChList.get(i).getEmployeeId());
					List<EmployeeDimissionInfo> ediList = employeeService.queryDimissionInfo(edi);
					
					//入职日期之前的不要
					if(emplist!=null&&emplist.size()==1&&emplist.get(0).getEntryDate()!=null){
						if(emplist.get(0).getEntryDate().after(gapChList.get(i).getStandardDate())){
							gapChList.remove(i);
							continue;
						}
					}
					//离职日期之后的不要
					if(ediList!=null&&ediList.size()==1&&ediList.get(0).getResignationDate()!=null){
						if(ediList.get(0).getResignationDate().before(gapChList.get(i).getStandardDate())){
							gapChList.remove(i);
							continue;
						}
					}
				}
			}
			
//			String path = new String (expPath.getBytes("iso-8859-1"),"utf-8");
//			System.out.println("导出路径："+path);
			
			
			HSSFWorkbook wb = GmsUtils.exportExcel(gapChList);
			lzform.set("dateS4g", "");
			lzform.set("dateE4g", "");
			
			response.setContentType("application/ms-excel");
			response.setHeader("Content-Disposition", "attachment;Filename=new.xls");
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
//			try {
//				List<String> workPlaces = hoursWorkService.queryWorkPlaces();
//				request.setAttribute("workPlaces", workPlaces);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			request.setAttribute("gap_exp", "导出成功！导出数据"+gapChList.size()+"条");
//			return mapping.findForward("success");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			request.setAttribute("err_msg", e.getMessage());
			return mapping.findForward("success");
		}
	}
	
}