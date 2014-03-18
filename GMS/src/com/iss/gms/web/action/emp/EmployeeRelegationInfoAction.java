package com.iss.gms.web.action.emp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.iss.gms.entity.EmpRelChangeRecord;
import com.iss.gms.entity.EmpRelStaInfo;
import com.iss.gms.entity.EmployeeBasicInfo;
import com.iss.gms.entity.EmployeeDimissionInfo;
import com.iss.gms.entity.EmployeeHoursDetailInfo;
import com.iss.gms.entity.EmployeeRelegationInfo;
import com.iss.gms.entity.ProjectInfo;
import com.iss.gms.service.HoursWorkService;
import com.iss.gms.service.IEmployeeService;
import com.iss.gms.service.IProjectService;
import com.iss.gms.utils.GmsUtils;

public class EmployeeRelegationInfoAction extends DispatchAction{
	private IEmployeeService employeeService;
	private IProjectService projectService;
	private HoursWorkService hoursWorkService;
	
	public void setHoursWorkService(HoursWorkService hoursWorkService) {
		this.hoursWorkService = hoursWorkService;
	}
	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	private static final Log log = LogFactory.getLog(EmployeeRelegationInfoAction.class);
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	

	//初始化
	public ActionForward initPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			List<String> proMgrList = employeeService.queryNameListByRank("3");
			List<String> blList4Rel = employeeService.queryAllBl4Rel();
			request.setAttribute("blList4Rel", blList4Rel);
			request.setAttribute("proMgrList",proMgrList);
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
			String name = (String)lzform.get("ename4rel");
			String dateS = (String)lzform.get("estimateLDateS4rel"); 
			String dateE = (String)lzform.get("estimateLDateE4rel"); 
			String ProMgr = (String)lzform.get("ProMgr4rel"); 
			String status = (String)lzform.get("status4rel");
			String bl4Rel = (String)lzform.get("bl4Rel");
			EmployeeRelegationInfo eri = new EmployeeRelegationInfo();
			eri.setEmployeeName(name);
			if(dateE!=null&&dateE.trim().length()>0){
				Date estimateLDateE = fmt.parse(dateE);
				eri.setEstimateLDateE(estimateLDateE);
			}
			if(dateS!=null&&dateS.trim().length()>0){
				Date estimateLDateS = fmt.parse(dateS);
				eri.setEstimateLDateS(estimateLDateS);
			}
			eri.setProjectManager(ProMgr);
			eri.setWorkstaus(status);
			eri.setBusinessLine(bl4Rel);
			List lt = employeeService.queryRelegationInfo(eri);
			List<String> proMgrList = employeeService.queryNameListByRank("3");
			List<String> blList4Rel = employeeService.queryAllBl4Rel();
			request.setAttribute("blList4Rel", blList4Rel);
			request.setAttribute("proMgrList",proMgrList);
			if(lt!=null){
				request.setAttribute("eriList", lt);
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
	//跳转到添加页面
	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			try {
				LazyValidatorForm lzform = (LazyValidatorForm)form;
				lzform.set("eId4addRel", "");
				lzform.set("eName4addRel", "");
				lzform.set("proDrt4addRel", "");
				lzform.set("proMgr4addRel", "");
				lzform.set("proLdr4addRel", "");
				lzform.set("grade4addRel", "");
				lzform.set("bline4addRel", "");
				lzform.set("proNum4addRel", "");
				lzform.set("tsPro4addRel", "");
				lzform.set("status4addRel", "");
				lzform.set("freeDate4addRel", "");
				lzform.set("billingDate4addRel", "");
				lzform.set("stimateDate4addRel", "");
				lzform.set("realityDate4addRel", "");
				lzform.set("changing4addRel", "");
				lzform.set("com4addRel", "");
				lzform.set("skill4addRel", "");
				List<ProjectInfo> list = projectService.queryProInfoByName("");
				request.setAttribute("proList", list);
				//项目总监
				List<String> proDrtList = employeeService.queryNameListByRank("1");
				//项目经理
				List<String> proMgrList = employeeService.queryNameListByRank("3");
				//项目组长
				List<String> proLdrList = employeeService.queryNameListByRank("4");
				request.setAttribute("proDrtList",proDrtList);
				request.setAttribute("proMgrList",proMgrList);
				request.setAttribute("proLdrList",proLdrList);
				return mapping.findForward("success1");
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("error");
			}
	}
	
	//添加信息
	public ActionForward addRelInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			
			try {
				EmployeeRelegationInfo eri = new EmployeeRelegationInfo();
				//员工编号
				eri.setEmployeeId(BeanUtils.getProperty(form, "eId4addRel"));
				eri.setEmployeeName(BeanUtils.getProperty(form, "eName4addRel"));
				eri.setProjectDirector(BeanUtils.getProperty(form, "proDrt4addRel"));
				eri.setProjectManager(BeanUtils.getProperty(form, "proMgr4addRel"));
				eri.setProjectLeader(BeanUtils.getProperty(form, "proLdr4addRel"));
				eri.setGrade(BeanUtils.getProperty(form, "grade4addRel"));
				eri.setBusinessLine(BeanUtils.getProperty(form, "bline4addRel"));
				eri.setBelongedTSprojectId(BeanUtils.getProperty(form, "proNum4addRel"));
				ProjectInfo pi = projectService.queryProInfoById(BeanUtils.getProperty(form, "tsPro4addRel"));
				if(pi!=null)
				eri.setBelongedTSproject(pi.getProName());
				eri.setWorkstaus(BeanUtils.getProperty(form, "status4addRel"));
				if(BeanUtils.getProperty(form, "freeDate4addRel").trim().length()>0&&BeanUtils.getProperty(form, "freeDate4addRel")!=null){
					eri.setAdmittancefreeDate(fmt.parse(BeanUtils.getProperty(form, "freeDate4addRel")));
				}
				if(BeanUtils.getProperty(form, "billingDate4addRel").trim().length()>0&&BeanUtils.getProperty(form, "billingDate4addRel")!=null){
					eri.setAdmittancebillingDate(fmt.parse(BeanUtils.getProperty(form, "billingDate4addRel")));
				}
				if(BeanUtils.getProperty(form, "stimateDate4addRel").trim().length()>0&&BeanUtils.getProperty(form, "stimateDate4addRel")!=null){
					eri.setOutsceneestimateDate(fmt.parse(BeanUtils.getProperty(form, "stimateDate4addRel")));
				}
				if(BeanUtils.getProperty(form, "realityDate4addRel").trim().length()>0&&BeanUtils.getProperty(form, "realityDate4addRel")!=null){
					eri.setOutscenerealityDate(fmt.parse(BeanUtils.getProperty(form, "realityDate4addRel")));
				}
				eri.setEmpOperate(BeanUtils.getProperty(form, "changing4addRel"));
				eri.setCompany(BeanUtils.getProperty(form, "com4addRel"));
				eri.setSkill(BeanUtils.getProperty(form, "skill4addRel"));
				if(eri.getEmployeeId()!=null&&eri.getEmployeeId().length()>0){
					EmployeeRelegationInfo erri = employeeService.queryEriInfoByEid(eri);
					if(erri!=null){
						request.setAttribute("err_msg","此信息已存在,请核实后重新操作!");
						return toAdd(mapping, form, request, response);
					}
					employeeService.addRelegationInfo(eri);
				}else{
					employeeService.addRelegationInfoNoId(eri);
				}
				
				//员工变动（如：入职）
				String changeFlag = BeanUtils.getProperty(form, "changing4addRel");
				if(changeFlag!=null&&!changeFlag.isEmpty()){
					EmpRelStaInfo ercr4check = employeeService.queryLastRecord(BeanUtils.getProperty(form, "eId4addRel"));
					if(ercr4check==null || !changeFlag.equals(ercr4check.getOperate())){
						//构造插入实体
								EmpRelStaInfo ersi = new EmpRelStaInfo();
								ersi.setEmployeeId(BeanUtils.getProperty(form, "eId4addRel"));
								ersi.setEmployeeName(BeanUtils.getProperty(form, "eName4addRel"));
								ersi.setUpdateTime(new Date());
								ersi.setOperate(changeFlag);
								ersi.setBusinessLine(eri.getBusinessLine());
								if(eri.getSkill()!=null&&eri.getSkill().trim().length()>0){
									if("测试".equals(eri.getSkill()) || "Test".equals(eri.getSkill())){
										ersi.setTechnology("0");
									}else{
										ersi.setTechnology("1");
									}
								}else{
									ersi.setTechnology("1");
								}
							employeeService.addChangeRecord(ersi);
					}
				}
				request.setAttribute("success_msg", "yes");
				return mapping.findForward("success1");
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("error");
			}
	}
	//跳转到修改页面
	public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			LazyValidatorForm lzform = (LazyValidatorForm)form;
			lzform.set("changing4ud", "");
			try {
				String str = GmsUtils.fix2utf(request.getParameter("str"));
				String tem [] = str.split(",");
				EmployeeRelegationInfo eri4query = new EmployeeRelegationInfo();
				eri4query.setEmployeeId(tem[0]);
				eri4query.setBelongedTSproject(tem[1]);
				if(tem.length==3){
					eri4query.setWorkstaus(tem[2]);
				}else{
					eri4query.setWorkstaus(" ");
					
				}
				EmployeeRelegationInfo eri = employeeService.queryEriInfoByEid(eri4query);
				if(eri==null){
					request.setAttribute("err_msg", "员工不存在,或信息已更新,请刷新后重试!");
					return mapping.findForward("success2");
				}
				DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
				if(eri.getAdmittancefreeDate()!= null){
				String admittancefreeDate = date.format(eri.getAdmittancefreeDate());
				request.setAttribute("admittancefreeDate", admittancefreeDate);}
				if(eri.getAdmittancebillingDate()!= null){
				String admittancebillingDate = date.format(eri.getAdmittancebillingDate());
				request.setAttribute("admittancebillingDate", admittancebillingDate);}
				if(eri.getOutsceneestimateDate()!= null){
				String outsceneestimateDate = date.format(eri.getOutsceneestimateDate());
				request.setAttribute("outsceneestimateDate", outsceneestimateDate);}
				if(eri.getOutscenerealityDate()!= null){
				String outscenerealityDate = date.format(eri.getOutscenerealityDate());
				request.setAttribute("outscenerealityDate", outscenerealityDate);}
	//根据项目名称获取项目信息
				List<ProjectInfo> list = projectService.queryProInfoByName("");
				request.setAttribute("proList", list);
				
				//项目总监
				List<String> proDrtList = employeeService.queryNameListByRank("1");
				if(eri.getProjectDirector()!=null&&proDrtList.contains(eri.getProjectDirector())) proDrtList.remove(eri.getProjectDirector());
				//项目经理
				List<String> proMgrList = employeeService.queryNameListByRank("3");
				if(eri.getProjectManager()!=null&&proMgrList.contains(eri.getProjectManager())) proMgrList.remove(eri.getProjectManager());
				//项目组长
				List<String> proLdrList = employeeService.queryNameListByRank("4");
				if(eri.getProjectLeader()!=null&&proLdrList.contains(eri.getProjectLeader())) proLdrList.remove(eri.getProjectLeader());
				request.setAttribute("eri", eri);
				request.setAttribute("proDrtList",proDrtList);
				request.setAttribute("proMgrList",proMgrList);
				request.setAttribute("proLdrList",proLdrList);
				
				return mapping.findForward("success2");
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("error");
			}
	}
	
	//修改页面
	public ActionForward updateRelInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			
			try {
				EmployeeRelegationInfo eri = new EmployeeRelegationInfo();
				//员工编号
				eri.setEmployeeIdNew(BeanUtils.getProperty(form, "eId4relUd"));
				eri.setEmployeeId(BeanUtils.getProperty(form, "oldEmpId"));
				eri.setEmployeeName(BeanUtils.getProperty(form, "eName4relUd"));
				eri.setProjectDirector(BeanUtils.getProperty(form, "proDrt4relUd"));
				eri.setProjectManager(BeanUtils.getProperty(form, "proMgr4relUd"));
				eri.setProjectLeader(BeanUtils.getProperty(form, "proLdr4relUd"));
				eri.setGrade(BeanUtils.getProperty(form, "grade4relUd"));
				eri.setBusinessLine(BeanUtils.getProperty(form, "bline4relUd"));
				eri.setBelongedTSprojectId(BeanUtils.getProperty(form, "proNum4relUd"));
				List<ProjectInfo> tem = projectService.queryProInfoByName(BeanUtils.getProperty(form, "tsPro4relUd"));
				if(tem.size()==1) eri.setNewProName(tem.get(0).getProName());
				eri.setNewWorkstatus(BeanUtils.getProperty(form, "status4relUd"));
				if(BeanUtils.getProperty(form, "freeDate4relUd").trim().length()>0&&BeanUtils.getProperty(form, "freeDate4relUd")!=null){
					eri.setAdmittancefreeDate(fmt.parse(BeanUtils.getProperty(form, "freeDate4relUd")));
				}
				if(BeanUtils.getProperty(form, "billingDate4relUd").trim().length()>0&&BeanUtils.getProperty(form, "billingDate4relUd")!=null){
					eri.setAdmittancebillingDate(fmt.parse(BeanUtils.getProperty(form, "billingDate4relUd")));
				}
				if(BeanUtils.getProperty(form, "stimateDate4relUd").trim().length()>0&&BeanUtils.getProperty(form, "stimateDate4relUd")!=null){
					eri.setOutsceneestimateDate(fmt.parse(BeanUtils.getProperty(form, "stimateDate4relUd")));
				}
				if(BeanUtils.getProperty(form, "realityDate4relUd").trim().length()>0&&BeanUtils.getProperty(form, "realityDate4relUd")!=null){
					eri.setOutscenerealityDate(fmt.parse(BeanUtils.getProperty(form, "realityDate4relUd")));
				}
				eri.setWorkstaus(BeanUtils.getProperty(form, "oldStatus"));
				eri.setBelongedTSproject(BeanUtils.getProperty(form, "oldProName"));
				eri.setEmpOperate(BeanUtils.getProperty(form, "changing4ud"));
				eri.setCompany(BeanUtils.getProperty(form, "com4relUd"));
				eri.setSkill(BeanUtils.getProperty(form, "skill4relUd"));
				try{
					employeeService.modifyEmpRelInfo(eri);
				}catch (Exception e) {
					request.setAttribute("err_msg","此信息已存在,请核实后重新操作!");
					return mapping.findForward("success2");
				}
				//状态修改为‘离职’ 删除离职日期后的预置信息(工时详情表中在员工基本信息导入时 会每人预置365条数据) 
//				if(eri.getNewWorkstatus()!=null&&eri.getNewWorkstatus().equals("5")){
//					EmployeeHoursDetailInfo ehdi =  new EmployeeHoursDetailInfo();
//					ehdi.setEmployeeId(eri.getEmployeeId());
//					
//					EmployeeDimissionInfo edi = new EmployeeDimissionInfo();
//					edi.setEmployeeId(eri.getEmployeeId());
//					List<EmployeeDimissionInfo> ls = employeeService.queryDimissionInfo(edi);
//					if(ls!=null&&ls.size()==1&&ls.get(0).getResignationDate()!=null){
//						ehdi.setStandardDate(ls.get(0).getResignationDate());
//					}else {
//						ehdi.setStandardDate(new Date());
//					}
//					hoursWorkService.delHoursDetailInfo4dim(ehdi);
//				}
				//员工变动（如：入职）
				String changeFlag = BeanUtils.getProperty(form, "changing4ud");
				if(changeFlag!=null&&!changeFlag.isEmpty()){
					EmpRelStaInfo ercr4check = employeeService.queryLastRecord(BeanUtils.getProperty(form, "eId4relUd"));
					if(ercr4check==null || !changeFlag.equals(ercr4check.getOperate())){
						
							EmpRelStaInfo ersi = new EmpRelStaInfo();
							ersi.setEmployeeId(BeanUtils.getProperty(form, "eId4relUd"));
							ersi.setEmployeeName(BeanUtils.getProperty(form, "eName4relUd"));
							ersi.setUpdateTime(new Date());
							ersi.setOperate(changeFlag);
							String bl = eri.getBusinessLine();
							ersi.setBusinessLine(bl);
							if(eri.getSkill()!=null&&eri.getSkill().trim().length()>0){
								if("测试".equals(eri.getSkill()) || "Test".equals(eri.getSkill())){
									ersi.setTechnology("0");
								}else{
									ersi.setTechnology("1");
								}
							}else{
								ersi.setTechnology("1");
							}
							employeeService.addChangeRecord(ersi);
					}
				}
				request.setAttribute("success_msg", "yes");
				return mapping.findForward("success2");
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("error");
			}
	}
	
	//删除归属信息
	public ActionForward deleteRelInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			try {
				LazyValidatorForm lzform = (LazyValidatorForm)form;
				String msg=(String)lzform.get("msg4del");
				String msgs [] = msg.split("_-_");
				for(String tem :msgs){
					EmployeeRelegationInfo eri = new EmployeeRelegationInfo();
					String a[] = tem.split(",");
					eri.setEmployeeId(a[0]);
					eri.setBelongedTSproject(a[1]);
					eri.setWorkstaus(a[2]);
					employeeService.removeEmpRelInfo(eri);
				}
				return search(mapping, lzform, request, response);
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("error");
			}
	}
	//跳转到导入页面
	public ActionForward toImp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			try {
				return mapping.findForward("success3");
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("error");
			}
	}
	
}
