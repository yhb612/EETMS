package com.iss.gms.web.action.emp;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
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
import com.iss.gms.service.HoursWorkService;
import com.iss.gms.service.IEmployeeService;
import com.iss.gms.utils.GmsUtils;


public class EmployeeBasicInfoAction extends DispatchAction {
	private static final Log log = LogFactory.getLog(EmployeeBasicInfoAction.class);
	private IEmployeeService employeeService;
	// 工时service
	private HoursWorkService hoursWorkService;
	
	public void setHoursWorkService(HoursWorkService hoursWorkService) {
		this.hoursWorkService = hoursWorkService;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	
	public ActionForward initPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//查询部门列表
		List<String> dpts = new ArrayList<String>();
		List<String> skills =  new ArrayList<String>();
		try {
			dpts = employeeService.queryDptmts();
			//查询技能列表
			skills = employeeService.queryFirstSkills();
			List<String> skills2 = employeeService.querySecondSkills();
			for(String sk2:skills2){
				if(!skills.contains(sk2)) skills.add(sk2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("skills", skills);
		request.setAttribute("dpts4emp", dpts);
		return mapping.findForward("success");
	}

	//查询员工基本信息
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			LazyValidatorForm lzform = (LazyValidatorForm)form;
				String nameOrId = (String)lzform.get("ename4b"); 
//				List lt = employeeService.queryByName(name);
				String dpm = (String)lzform.get("department4emp"); 
				String skill = (String)lzform.get("skill"); 
				String dateS = (String)lzform.get("empInDateS"); 
				String dateE = (String)lzform.get("empInDateE"); 
				EmployeeBasicInfo ebi = new EmployeeBasicInfo();
				if(!"".equals(nameOrId) && null != nameOrId){
					ebi.setEmployeeId(nameOrId);
				}
				if(dateE!=null&&dateE.trim().length()>0){
					Date endDate = fmt.parse(dateE);
					ebi.setEndDate(endDate);
				}
				if(dateS!=null&&dateS.trim().length()>0){
					Date startDate = fmt.parse(dateS);
					ebi.setStartDate(startDate);
				}
				ebi.setOrganizationalUnit6(dpm);
				ebi.setFirstSkill(skill);
				List lt = employeeService.queryEmpInfoList(ebi);
				//查询部门列表
				List<String> dpts=employeeService.queryDptmts();
				//查询技能列表
				List<String> skills = employeeService.queryFirstSkills();
				List<String> skills2 = employeeService.querySecondSkills();
				for(String sk2:skills2){
					if(!skills.contains(sk2)) skills.add(sk2);
				}
				request.setAttribute("skills", skills);
				request.setAttribute("dpts4emp", dpts);
				request.setAttribute("lt", lt);
				if(lt!=null) request.setAttribute("resultCount", lt.size());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}
	
	//跳转到添加页面
	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			try {
				return mapping.findForward("success1");
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("error");
			}
	}
	
	//添加信息
	public ActionForward addInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			
			try {
				List<EmployeeBasicInfo> list = employeeService.queryByName(BeanUtils.getProperty(form, "eId_add4bsc"));
				if(list.size()!=0){
					request.setAttribute("error_msg", "此员工编号已存在，请重新操作！");
					return mapping.findForward("success1");
				}
				EmployeeBasicInfo e=new EmployeeBasicInfo();
				//员工编号
				e.setEmployeeId(BeanUtils.getProperty(form, "eId_add4bsc"));
				//员工姓名
				e.setEmployeeName(BeanUtils.getProperty(form, "eName_add4bsc"));
				//员工性别
				e.setEmployeeGender(BeanUtils.getProperty(form, "eGender_add4bsc"));
				//入职日期
				if(BeanUtils.getProperty(form, "eDate_add4bsc").trim().length()>0&&BeanUtils.getProperty(form, "eDate_add4bsc")!=null)
				e.setEntryDate(GmsUtils.gmsFormToDate(BeanUtils.getProperty(form, "eDate_add4bsc")));
				//通用职位
				e.setCommonPosition(BeanUtils.getProperty(form, "cPosition_add4bsc"));
				//成本中心
				e.setCostCenter(BeanUtils.getProperty(form, "cCenter_add4bsc"));
				//组织单位3
				e.setOrganizationalUnit3(BeanUtils.getProperty(form, "ozUnit3_add4bsc"));
				//人事范围
				e.setPersonnelRange(BeanUtils.getProperty(form, "pRange_add4bsc"));
				//工作地文本
				e.setWorkPlaceText(BeanUtils.getProperty(form, "wpText_add4bsc"));
				//人事子范围
				e.setPersonnelSubRange(BeanUtils.getProperty(form, "pSRange_add4bsc"));
				//司龄(月)
				if(BeanUtils.getProperty(form, "eDate_add4bsc").trim().length()>0&&BeanUtils.getProperty(form, "eDate_add4bsc")!=null)
				e.setDrivingAgeMonth(GmsUtils.calculate(BeanUtils.getProperty(form, "eDate_add4bsc"),"m"));
				//工龄(年)
				if(BeanUtils.getProperty(form, "gDate_add4bsc").trim().length()>0&&BeanUtils.getProperty(form, "gDate_add4bsc")!=null)
				e.setWorkingAgeYear(GmsUtils.calculate(BeanUtils.getProperty(form, "gDate_add4bsc"),"y"));
				//员工组
				e.setEmployeeGroup(BeanUtils.getProperty(form, "eGroup_add4bsc"));
				//员工子组
				e.setEmployeeSubGroup(BeanUtils.getProperty(form, "esGroup_add4bsc"));
				//职级
				e.setRank(BeanUtils.getProperty(form, "rank_add4bsc"));
				//学历
				e.setEducationa(BeanUtils.getProperty(form, "edca_add4bsc"));
				//专业名称
				e.setSpecialtyName(BeanUtils.getProperty(form, "sName_add4bsc"));
				//公司邮箱
				e.setCompanyMailBox(BeanUtils.getProperty(form, "cmBox_add4bsc"));
				//手机号码
				e.setMobileNumber(BeanUtils.getProperty(form, "mNumber_add4bsc"));
				//证件类别
				e.setCredentialsType(BeanUtils.getProperty(form, "cType_add4bsc"));
				//证件号码
				e.setCredentialsNumber(BeanUtils.getProperty(form, "cNumber_add4bsc"));
				//组织单位4
				e.setOrganizationalUnit4(BeanUtils.getProperty(form, "ozUnit4_add4bsc"));
				//组织单位5
				e.setOrganizationalUnit5(BeanUtils.getProperty(form, "ozUnit5_add4bsc"));
				//组织单位6
				e.setOrganizationalUnit6(BeanUtils.getProperty(form, "ozUnit6_add4bsc"));
				//毕业日期
				if(BeanUtils.getProperty(form, "gDate_add4bsc").trim().length()>0&&BeanUtils.getProperty(form, "gDate_add4bsc")!=null)
				e.setGraduationDate(GmsUtils.gmsFormToDate(BeanUtils.getProperty(form, "gDate_add4bsc")));
				//毕业学校/城市
				e.setGraduationSchoolOrCity(BeanUtils.getProperty(form, "gsCity_add4bsc"));
				//所属公司
				e.setItsaffiliates(BeanUtils.getProperty(form, "itfs_add4bsc"));
				//合同工资
				if(BeanUtils.getProperty(form, "cWages_add4bsc")!=null&&BeanUtils.getProperty(form, "cWages_add4bsc").trim().length()>0)
				e.setContractWages(Float.parseFloat(BeanUtils.getProperty(form, "cWages_add4bsc")));
				//社保
				if(BeanUtils.getProperty(form, "cWages_add4bsc")!=null&&BeanUtils.getProperty(form, "cWages_add4bsc").trim().length()>0)
				e.setSocietyindemnification(Float.parseFloat(BeanUtils.getProperty(form, "sftn_add4bsc")));
				//第一技能
				e.setFirstSkill(BeanUtils.getProperty(form, "fSkill_add4bsc"));
				//第二技能
				e.setSecondSkill(BeanUtils.getProperty(form, "sSkill_add4bsc"));
				//技能说明
				e.setSkillDescription(BeanUtils.getProperty(form, "sDescription_add4bsc"));
				//客户方定义姓名
				e.setChinaBankName(BeanUtils.getProperty(form, "cbName_add4bsc"));
				//是否参与工时核查
				e.setBeCheck4Gap(BeanUtils.getProperty(form, "beCheck4Gap_add4bsc"));
				employeeService.addEmpInfo(e);
				//是否参与工时核查状态为“是” 才更新工时详情和工时统计表信息
				if("0".equals(BeanUtils.getProperty(form, "beCheck4Gap_add4bsc"))){
					HoursWorkStatisticsInfo tempHwsi = hoursWorkService.queryHoursStatistics(e.getEmployeeId());
					if(tempHwsi!=null){
						hoursWorkService.delHoursStatisticsInfo(e.getEmployeeId());
					}
					hoursWorkService.addHoursStatisticsInfo(e);
					
					EmployeeHoursDetailInfo ehditemp = new EmployeeHoursDetailInfo();
					ehditemp.setEmployeeId(e.getEmployeeId());
					List<EmployeeHoursDetailInfo> ehdiListTemp = hoursWorkService.queryGapInfo(ehditemp);
					if(ehdiListTemp!=null&&ehdiListTemp.size()>0){
						hoursWorkService.delHoursDetailInfo(ehditemp);
					}
					//查询所有标准日期info
					List<HoursWorkStandardInfo> stdHoursList = hoursWorkService.queryStdHourswork(new HoursWorkStandardInfo());
					for(HoursWorkStandardInfo hwsi: stdHoursList){
						EmployeeHoursDetailInfo ehdi = new EmployeeHoursDetailInfo();
						ehdi.setEmployeeId(BeanUtils.getProperty(form, "eId_add4bsc"));
						ehdi.setEmployeeName(BeanUtils.getProperty(form, "eName_add4bsc"));
						ehdi.setStandardDateType(hwsi.getStandardDateType());
						ehdi.setStandardDate(hwsi.getStandardDate());
						ehdi.setStandardHours(hwsi.getStandardHours());
						ehdi.setStandardWeek(hwsi.getStandardWeek());
						ehdi.setCurrentGapHours((float)hwsi.getStandardHours());
						//初始化该员工工时详情表信息
						hoursWorkService.addHoursDetailInfo(ehdi);
					}
				}
				BeanUtils.setProperty(form, "eId_add4bsc", "");
				BeanUtils.setProperty(form, "eName_add4bsc", "");
				BeanUtils.setProperty(form, "eGender_add4bsc", "");
				BeanUtils.setProperty(form, "eDate_add4bsc", "");
				BeanUtils.setProperty(form, "cPosition_add4bsc", "");
				BeanUtils.setProperty(form, "cCenter_add4bsc", "");
				BeanUtils.setProperty(form, "ozUnit3_add4bsc", "");
				BeanUtils.setProperty(form, "pRange_add4bsc", "");
				BeanUtils.setProperty(form, "wpText_add4bsc", "");
				BeanUtils.setProperty(form, "pSRange_add4bsc", "");
				BeanUtils.setProperty(form, "cbName_add4bsc", "");
				BeanUtils.setProperty(form, "edca_add4bsc", "");
				BeanUtils.setProperty(form, "eGroup_add4bsc", "");
				BeanUtils.setProperty(form, "esGroup_add4bsc", "");
				BeanUtils.setProperty(form, "rank_add4bsc", "");
				BeanUtils.setProperty(form, "cType_add4bsc", "");
				BeanUtils.setProperty(form, "sName_add4bsc", "");
				BeanUtils.setProperty(form, "cmBox_add4bsc", "");
				BeanUtils.setProperty(form, "mNumber_add4bsc", "");
				BeanUtils.setProperty(form, "ozUnit6_add4bsc", "");
				BeanUtils.setProperty(form, "cNumber_add4bsc", "");
				BeanUtils.setProperty(form, "ozUnit4_add4bsc", "");
				BeanUtils.setProperty(form, "ozUnit5_add4bsc","");
				BeanUtils.setProperty(form, "cWages_add4bsc", "");
				BeanUtils.setProperty(form, "gDate_add4bsc","");
				BeanUtils.setProperty(form, "gsCity_add4bsc", "");
				BeanUtils.setProperty(form, "itfs_add4bsc", "");
				BeanUtils.setProperty(form, "sDescription_add4bsc", "");
				BeanUtils.setProperty(form, "sftn_add4bsc", "");
				BeanUtils.setProperty(form, "fSkill_add4bsc", "");
				BeanUtils.setProperty(form, "sSkill_add4bsc", "");
				BeanUtils.setProperty(form, "beCheck4Gap_add4bsc", "");
				request.setAttribute("success_msg", "yes");
				return mapping.findForward("success1");
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("success1");
			}
	}
	
	//跳转到修改页面
	public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			try {
				//得到员工编号
				String eid=request.getParameter("eid");
				List lt = employeeService.queryByName(eid);
				Iterator it=lt.iterator();
				while(it.hasNext()){
					EmployeeBasicInfo e=(EmployeeBasicInfo)it.next();
					BeanUtils.setProperty(form, "eId4bscUd", e.getEmployeeId()==""?"":e.getEmployeeId());
					BeanUtils.setProperty(form, "eName4bscUd", e.getEmployeeName()==null?"":e.getEmployeeName());
					BeanUtils.setProperty(form, "eGender4bscUd", e.getEmployeeGender()==null?"":e.getEmployeeGender());
					BeanUtils.setProperty(form, "eDate4bscUd", e.getEntryDate()==null?"":GmsUtils.gmsFormToString(e.getEntryDate()));
					BeanUtils.setProperty(form, "cPosition4bscUd", e.getCommonPosition()==null?"":e.getCommonPosition());
					BeanUtils.setProperty(form, "cCenter4bscUd", e.getCostCenter()==null?"":e.getCostCenter());
					BeanUtils.setProperty(form, "ozUnit34bscUd", e.getOrganizationalUnit3()==null?"":e.getOrganizationalUnit3());
					BeanUtils.setProperty(form, "pRange4bscUd", e.getPersonnelRange()==null?"":e.getPersonnelRange());
					BeanUtils.setProperty(form, "wpText4bscUd", e.getWorkPlaceText()==null?"":e.getWorkPlaceText());
					BeanUtils.setProperty(form, "pSRange4bscUd", e.getPersonnelSubRange()==null?"":e.getPersonnelSubRange());
					BeanUtils.setProperty(form, "cbName4bscUd", e.getChinaBankName()==null?"":e.getChinaBankName());
					BeanUtils.setProperty(form, "edca4bscUd", e.getEducationa()==null?"":e.getEducationa());
					BeanUtils.setProperty(form, "eGroup4bscUd", e.getEmployeeGroup()==null?"":e.getEmployeeGroup());
					BeanUtils.setProperty(form, "esGroup4bscUd", e.getEmployeeSubGroup()==null?"":e.getEmployeeSubGroup());
					BeanUtils.setProperty(form, "rank4bscUd", e.getRank()==null?"":e.getRank());
					BeanUtils.setProperty(form, "cType4bscUd", e.getCredentialsType()==null?"":e.getCredentialsType());
					BeanUtils.setProperty(form, "sName4bscUd", e.getSpecialtyName()==null?"":e.getSpecialtyName());
					BeanUtils.setProperty(form, "cmBox4bscUd", e.getCompanyMailBox()==null?"":e.getCompanyMailBox());
					BeanUtils.setProperty(form, "mNumber4bscUd", e.getMobileNumber()==null?"":e.getMobileNumber());
					BeanUtils.setProperty(form, "ozUnit64bscUd", e.getOrganizationalUnit6()==null?"":e.getOrganizationalUnit6());
					BeanUtils.setProperty(form, "cNumber4bscUd", e.getCredentialsNumber()==null?"":e.getCredentialsNumber());
					BeanUtils.setProperty(form, "ozUnit44bscUd", e.getOrganizationalUnit4()==null?"":e.getOrganizationalUnit4());
					BeanUtils.setProperty(form, "ozUnit54bscUd",e.getOrganizationalUnit5()==null?"":e.getOrganizationalUnit5());
					BeanUtils.setProperty(form, "cWages4bscUd", e.getContractWages()==null?"":e.getContractWages());
					BeanUtils.setProperty(form, "gDate4bscUd",e.getGraduationDate()==null?"":GmsUtils.gmsFormToString(e.getGraduationDate()));
					BeanUtils.setProperty(form, "gsCity4bscUd", e.getGraduationSchoolOrCity()==null?"":e.getGraduationSchoolOrCity());
					BeanUtils.setProperty(form, "itfs4bscUd", e.getItsaffiliates()==null?"":e.getItsaffiliates());
					BeanUtils.setProperty(form, "sDescription4bscUd", e.getSkillDescription()==null?"":e.getSkillDescription());
					BeanUtils.setProperty(form, "sftn4bscUd", e.getSocietyindemnification()==null?"":e.getSocietyindemnification());
					BeanUtils.setProperty(form, "fSkill4bscUd", e.getFirstSkill()==null?"":e.getFirstSkill());
					BeanUtils.setProperty(form, "sSkill4bscUd", e.getSecondSkill()==null?"":e.getSecondSkill());
					BeanUtils.setProperty(form, "beCheck4Gap4bscUd", e.getBeCheck4Gap()==null?"":e.getBeCheck4Gap());
				}
				return mapping.findForward("success2");
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("error");
			}
	}
	
	//修改员工信息
	public ActionForward modifyInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			try {
				
				EmployeeBasicInfo e=new EmployeeBasicInfo();
				//员工编号
				e.setEmployeeId(BeanUtils.getProperty(form, "eId4bscUd"));
				//员工姓名
				e.setEmployeeName(BeanUtils.getProperty(form, "eName4bscUd"));
				//员工性别
				e.setEmployeeGender(BeanUtils.getProperty(form, "eGender4bscUd"));
				//入职日期
				if(BeanUtils.getProperty(form, "eDate4bscUd")!=null&&BeanUtils.getProperty(form, "eDate4bscUd").trim().length()>0)
				e.setEntryDate(GmsUtils.gmsFormToDate(BeanUtils.getProperty(form, "eDate4bscUd")));
				//通用职位
				e.setCommonPosition(BeanUtils.getProperty(form, "cPosition4bscUd"));
				//成本中心
				e.setCostCenter(BeanUtils.getProperty(form, "cCenter4bscUd"));
				//组织单位3
				e.setOrganizationalUnit3(BeanUtils.getProperty(form, "ozUnit34bscUd"));
				//人事范围
				e.setPersonnelRange(BeanUtils.getProperty(form, "pRange4bscUd"));
				//工作地文本
				e.setWorkPlaceText(BeanUtils.getProperty(form, "wpText4bscUd"));
				//人事子范围
				e.setPersonnelSubRange(BeanUtils.getProperty(form, "pSRange4bscUd"));
				//司龄(月)
				if(BeanUtils.getProperty(form, "eDate4bscUd")!=null&&BeanUtils.getProperty(form, "eDate4bscUd").trim().length()>0)
				e.setDrivingAgeMonth(GmsUtils.calculate(BeanUtils.getProperty(form, "eDate4bscUd"),"m"));
				//工龄(年)
				if(BeanUtils.getProperty(form, "gDate4bscUd")!=null&&BeanUtils.getProperty(form, "gDate4bscUd").trim().length()>0)
				e.setWorkingAgeYear(GmsUtils.calculate(BeanUtils.getProperty(form, "gDate4bscUd"),"y"));
				//员工组
				e.setEmployeeGroup(BeanUtils.getProperty(form, "eGroup4bscUd"));
				//员工子组
				e.setEmployeeSubGroup(BeanUtils.getProperty(form, "esGroup4bscUd"));
				//职级
				e.setRank(BeanUtils.getProperty(form, "rank4bscUd"));
				//学历
				e.setEducationa(BeanUtils.getProperty(form, "edca4bscUd"));
				//专业名称
				e.setSpecialtyName(BeanUtils.getProperty(form, "sName4bscUd"));
				//公司邮箱
				e.setCompanyMailBox(BeanUtils.getProperty(form, "cmBox4bscUd"));
				//手机号码
				e.setMobileNumber(BeanUtils.getProperty(form, "mNumber4bscUd"));
				//证件类别
				e.setCredentialsType(BeanUtils.getProperty(form, "cType4bscUd"));
				//证件号码
				e.setCredentialsNumber(BeanUtils.getProperty(form, "cNumber4bscUd"));
				//组织单位4
				e.setOrganizationalUnit4(BeanUtils.getProperty(form, "ozUnit44bscUd"));
				//组织单位5
				e.setOrganizationalUnit5(BeanUtils.getProperty(form, "ozUnit54bscUd"));
				//组织单位6
				e.setOrganizationalUnit6(BeanUtils.getProperty(form, "ozUnit64bscUd"));
				//毕业日期
				if(BeanUtils.getProperty(form, "gDate4bscUd")!=null&&BeanUtils.getProperty(form, "gDate4bscUd").trim().length()>0)
				e.setGraduationDate(GmsUtils.gmsFormToDate(BeanUtils.getProperty(form, "gDate4bscUd")));
				//毕业学校/城市
				e.setGraduationSchoolOrCity(BeanUtils.getProperty(form, "gsCity4bscUd"));
				//所属公司
				e.setItsaffiliates(BeanUtils.getProperty(form, "itfs4bscUd"));
				//合同工资
				if(BeanUtils.getProperty(form, "cWages4bscUd")!=null&&BeanUtils.getProperty(form, "cWages4bscUd").trim().length()>0)
				e.setContractWages(Float.parseFloat(BeanUtils.getProperty(form, "cWages4bscUd")));
				//社保
				if(BeanUtils.getProperty(form, "sftn4bscUd")!=null&&BeanUtils.getProperty(form, "sftn4bscUd").trim().length()>0)
				e.setSocietyindemnification(Float.parseFloat(BeanUtils.getProperty(form, "sftn4bscUd")));
				//第一技能
				e.setFirstSkill(BeanUtils.getProperty(form, "fSkill4bscUd"));
				//第二技能
				e.setSecondSkill(BeanUtils.getProperty(form, "sSkill4bscUd"));
				//技能说明
				e.setSkillDescription(BeanUtils.getProperty(form, "sDescription4bscUd"));
				//客户方定义姓名
				e.setChinaBankName(BeanUtils.getProperty(form, "cbName4bscUd"));
				//是否参与工时核查
				e.setBeCheck4Gap(BeanUtils.getProperty(form, "beCheck4Gap4bscUd"));
				employeeService.modifyEmpInfo(e);
				
				//是否参与工时核查状态为“是” 才更新工时详情和工时统计表信息
				if("0".equals(BeanUtils.getProperty(form, "beCheck4Gap4bscUd"))){
					HoursWorkStatisticsInfo tempHwsi = hoursWorkService.queryHoursStatistics(e.getEmployeeId());
					if(tempHwsi==null){
						hoursWorkService.addHoursStatisticsInfo(e);
					}
					
					EmployeeHoursDetailInfo ehditemp = new EmployeeHoursDetailInfo();
					ehditemp.setEmployeeId(e.getEmployeeId());
					List<EmployeeHoursDetailInfo> ehdiListTemp = hoursWorkService.queryGapInfo(ehditemp);
					if(ehdiListTemp==null || ehdiListTemp.size()==0){
						//查询所有标准日期info
						List<HoursWorkStandardInfo> stdHoursList = hoursWorkService.queryStdHourswork(new HoursWorkStandardInfo());
						for(HoursWorkStandardInfo hwsi: stdHoursList){
							EmployeeHoursDetailInfo ehdi = new EmployeeHoursDetailInfo();
							ehdi.setEmployeeId(BeanUtils.getProperty(form, "eId4bscUd"));
							ehdi.setEmployeeName(BeanUtils.getProperty(form, "eName4bscUd"));
							ehdi.setStandardDateType(hwsi.getStandardDateType());
							ehdi.setStandardDate(hwsi.getStandardDate());
							ehdi.setStandardHours(hwsi.getStandardHours());
							ehdi.setStandardWeek(hwsi.getStandardWeek());
							ehdi.setCurrentGapHours((float)hwsi.getStandardHours());
							//初始化该员工工时详情表信息
							hoursWorkService.addHoursDetailInfo(ehdi);
						}
					}
				}
				request.setAttribute("success_msg", "yes");
				return mapping.findForward("success2");
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("error");
			}
	}
	//删除员工基本信息
	public ActionForward delInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			try {
				String eidGrp=request.getParameter("eidGrp");
				String str[]=eidGrp.split(",");
				if(str.length>0){
					for(int i=0;i<str.length;i++){
						employeeService.removeEmpInfo(str[i]);
					}
				}
				return search(mapping, form, request, response);
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("error");
			}
	}
	
	
	
	
	
}