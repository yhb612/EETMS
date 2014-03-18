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
			//��������
			if("0".equals(gapType)){
				ehdi.setSignG("G");
			}else if("1".equals(gapType)){
				ehdi.setSignL("L");
			}
			//��������
			ehdi.setWorkPlace(workPlace);
			
			List<EmployeeHoursDetailInfo> gapChList = hoursWorkService.queryGapInfo(ehdi);
			
			for(int i=gapChList.size()-1;i>=0;i--){
				if(gapChList.get(i)!=null&&gapChList.get(i).getEmployeeId()!=null){
					List<EmployeeBasicInfo> emplist = employeeService.queryByName(gapChList.get(i).getEmployeeId());
					EmployeeDimissionInfo edi = new EmployeeDimissionInfo();
					edi.setEmployeeName(gapChList.get(i).getEmployeeId());
					List<EmployeeDimissionInfo> ediList = employeeService.queryDimissionInfo(edi);
					if(emplist!=null&&emplist.size()==1){
						//�Ƿ���빤ʱ�˲�(�ų������빤ʱ�˲��Ա��)
						if("1".equals(emplist.get(0).getBeCheck4Gap())){
							gapChList.remove(i);
							continue;
						}
						//��ְ����֮ǰ�Ĳ�Ҫ
						if(emplist.get(0).getEntryDate()!=null){
							if(emplist.get(0).getEntryDate().after(gapChList.get(i).getStandardDate())){
								gapChList.remove(i);
								continue;
							}
						}
					}
					//��ְ����֮��Ĳ�Ҫ
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
			String [] gapType2Update ={"0","3","5","6","7","8"};//�ᷢ�����������Ĳ�������(�������� 0�ٵ�����1�ֳ�����2�ع�˾����3�볡4��˾����5���쳣6����7��������8�ظ���ʱ׷�ӵ���)
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
						if(paras[0].trim().length()==1){//ֻҪѡ����GAP���� �����GAP��ʱ
							ehdi.setCurrentGapHours(0f);
							ehdi.setWriteOffHours(ehditem.getCurrentGapHours());
						}else{
							ehdi.setCurrentGapHours(ehditem.getCurrentGapHours());
						}
						
						//��������Ϊָ������ �����Ա��ʣ�൹�ݹ�ʱ
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
			//��������
			ehdi.setWorkPlace(workPlace);
			//�龰һ�����������ӹ�ʱ������ѯ�����˵�����
//			List<EmployeeHoursDetailInfo> gapChList = hoursWorkService.queryGapInfo(ehdi);
			//�Ƿ���빤ʱ�˲�(�ų������빤ʱ�˲��Ա��)
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
			
			//�龰�������������ӹ�ʱ�����ֻ��ѯ��ͻ�ȷ�Ϲ�ʱ�������Ա������(�ͻ�ȷ�Ϲ�ʱ����û�е��˲�����)
			//�򲻲��빤ʱ�˲�����ڿͻ�ȷ�Ϲ�ʱ����Ҳ����������Ϣ �����������ϵ��ų�����
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
					
					//��ְ����֮ǰ�Ĳ�Ҫ
					if(emplist!=null&&emplist.size()==1&&emplist.get(0).getEntryDate()!=null){
						if(emplist.get(0).getEntryDate().after(gapChList.get(i).getStandardDate())){
							gapChList.remove(i);
							continue;
						}
					}
					//��ְ����֮��Ĳ�Ҫ
					if(ediList!=null&&ediList.size()==1&&ediList.get(0).getResignationDate()!=null){
						if(ediList.get(0).getResignationDate().before(gapChList.get(i).getStandardDate())){
							gapChList.remove(i);
							continue;
						}
					}
				}
			}
			
//			String path = new String (expPath.getBytes("iso-8859-1"),"utf-8");
//			System.out.println("����·����"+path);
			
			
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
//			request.setAttribute("gap_exp", "�����ɹ�����������"+gapChList.size()+"��");
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