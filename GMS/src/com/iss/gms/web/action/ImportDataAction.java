package com.iss.gms.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.iss.gms.entity.CustomerVerifyHoursInfo;
import com.iss.gms.entity.EmpRelStaInfo;
import com.iss.gms.entity.EmployeeBasicInfo;
import com.iss.gms.entity.EmployeeDimissionInfo;
import com.iss.gms.entity.EmployeeHoursDetailInfo;
import com.iss.gms.entity.EmployeeRelegationInfo;
import com.iss.gms.entity.EmployeeVacationInfo;
import com.iss.gms.entity.HoursWorkStandardInfo;
import com.iss.gms.entity.HoursWorkStatisticsInfo;
import com.iss.gms.entity.ProjectInfo;
import com.iss.gms.form.LoadForm;
import com.iss.gms.service.HoursWorkService;
import com.iss.gms.service.IEmployeeService;
import com.iss.gms.service.IProjectService;
import com.iss.gms.utils.GmsUtils;

public class ImportDataAction extends DispatchAction {
	// 员工service
	private IEmployeeService employeeService;
	// 项目service
	private IProjectService projectService;
	// 工时service
	private HoursWorkService hoursWorkService;
	Logger log = Logger.getLogger(ImportDataAction.class.getName());

	public void setHoursWorkService(HoursWorkService hoursWorkService) {
		this.hoursWorkService = hoursWorkService;
	}

	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// 公用跳转到导入页面
	public ActionForward toImp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("status", request.getParameter("status"));
			return mapping.findForward("success");
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("error");
		}
	}

	// 公用导入数据方法（根据flag区分调用此方法的页面，以构建对应实体）
	public ActionForward impInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LoadForm uploadForm = (LoadForm) form;
		FormFile file = uploadForm.getFile();
		if (file == null) {
			return mapping.findForward("success");
		}

		String filename = file.getFileName();
		String excelVersion = "";
		if (filename.endsWith(".xls"))
			excelVersion = "2003";
		else if (filename.endsWith(".xlsx"))
			excelVersion = "2007";
		else {
			request.setAttribute("err_msg", "文件类型错误,请重新操作!");
			return mapping.findForward("success");
		}
		uploadForm.setFilename(filename);
		String size = Integer.toString(file.getFileSize()) + "bytes";
		uploadForm.setSize(size);

		InputStream is = file.getInputStream();
		String path = request.getRealPath("/excelFilesData");

		OutputStream os = new FileOutputStream(path + "/" + filename);

		int bytes = 0;
		byte[] buffer = new byte[8192];
		while ((bytes = is.read(buffer, 0, 8192)) != -1) {
			os.write(buffer, 0, bytes);
		}
		os.close();
		is.close();
		file.destroy();
		// 文件解析
		String impPath = path + "\\" + filename;
		log.info("上传后文件所在路径：" + impPath);
		InputStream is4imp = new FileInputStream(impPath);
		// 非福利假数组
		String[] fflPsaType = { "事假", "病假" };

		// 员工基本信息 导入
		if ("empbi".equals(uploadForm.getFlag())) {
			List<String[]> lt = new ArrayList<String[]>();
			try {
				lt = GmsUtils.readExcel(1, is4imp, excelVersion);// 从第2行开始读取（0为第一行）
			} catch (OutOfMemoryError e) {
				request.setAttribute("err_msg", "数据量过大，请分批操作！");
				return mapping.findForward("success");
			}
			log.info("本次导入基本信息条数：" + lt.size() + "条");

			// 查询所有标准日期info
			List<HoursWorkStandardInfo> stdHoursList = hoursWorkService
					.queryStdHourswork(new HoursWorkStandardInfo());
			for (int i = 0; i < lt.size(); i++) {
				String[] str = (String[]) lt.get(i);
				if (str[14] == null || str[14].trim().length() == 0)
					continue;

				EmployeeBasicInfo e = new EmployeeBasicInfo();
				List<EmployeeBasicInfo> eee = employeeService
				.queryByName(String.valueOf((int) Double
						.parseDouble(str[14].replace(" ", ""))));
				if (eee != null && eee.size() == 1){
					e=eee.get(0);
				}
				// 如己存在 处理二： 更新
				// List<EmployeeBasicInfo> eee =
				// employeeService.queryByName(String.valueOf((int)
				// Double.parseDouble(str[14].replace(" ", ""))));
				// if(eee!=null&&eee.size()==1) {
				// e.setBeCheck4Gap(eee.get(0).getBeCheck4Gap());//是否参与工时核查
				// e.setChinaBankName(eee.get(0).getChinaBankName());//客户方定义姓名
				// e.setRank(eee.get(0).getRank());//职级
				// e.setSkillDescription(eee.get(0).getSkillDescription());//技能说明
				// e.setFirstSkill(eee.get(0).getFirstSkill());//第一技能
				// e.setSecondSkill(eee.get(0).getSecondSkill());//第二技能
				// e.setItsaffiliates(eee.get(0).getItsaffiliates());//所属公司
				// employeeService.modifyEmpInfo(e);
				// continue;
				// }
				
					// 员工编号
					e.setEmployeeId(String.valueOf((int) Double.parseDouble(str[14]
							.replace(" ", ""))));
					// 员工姓名
					e.setEmployeeName(str[15].replace(" ", ""));
					// 客户定义姓名
					e.setChinaBankName(str[15].replace(" ", ""));
					// 员工性别
					if ("男".equals(str[18].trim())) {
						e.setEmployeeGender("0");
					} else if ("女".equals(str[18].trim())) {
						e.setEmployeeGender("1");
					}
					// 入职日期
					if (str[47] != null && str[47].trim().length() > 0)
						e.setEntryDate(GmsUtils.gmsFormToDate(str[47].trim()));
					// 通用职位
					e.setCommonPosition(str[54]);
					// 成本中心
					e.setCostCenter(str[34]);
					// 组织单位3
					e.setOrganizationalUnit3(str[3]);
					// 人事范围
					e.setPersonnelRange(str[40]);
					// 工作地文本
					e.setWorkPlaceText(str[31]);
					// 人事子范围
					e.setPersonnelSubRange(str[42]);
					// 司龄(月)
					e.setDrivingAgeMonth(str[51]);
					// 工龄(年)
					e.setWorkingAgeYear(str[49]);
					// 员工组
					if ("正式员工".equals(str[36])) {
						e.setEmployeeGroup("0");
					} else if ("试用员工".equals(str[36])) {
						e.setEmployeeGroup("1");
					} else if ("实习员工".equals(str[36])) {
						e.setEmployeeGroup("2");
					}else if ("兼职/劳务员工".equals(str[36])) {
						e.setEmployeeGroup("3");
					}
					// 员工子组
					if ("实施".equals(str[38])) {
						e.setEmployeeSubGroup("0");
					} else if ("销售".equals(str[38])) {
						e.setEmployeeSubGroup("1");
					} else if ("平台".equals(str[38])) {
						e.setEmployeeSubGroup("2");
					} else if ("RM".equals(str[38])) {
						e.setEmployeeSubGroup("3");
					}
					// 职级
//					if (str[14]!=null&&"BUhead".equals(str[14].trim())) {
//						e.setRank("0");
//					} else if ("部门总监".equals(str[14])) {
//						e.setRank("1");
//					} else if ("客户总监".equals(str[14])) {
//						e.setRank("2");
//					} else if ("项目经理".equals(str[14])) {
//						e.setRank("3");
//					} else if ("项目组长".equals(str[14])) {
//						e.setRank("4");
//					} else if ("客户经理".equals(str[14])) {
//						e.setRank("5");
//					}
					// 学历
					e.setEducationa(str[80]);
					// 专业名称
					e.setSpecialtyName(str[77]);
					// 公司邮箱
					e.setCompanyMailBox(str[19]);
					// 手机号码
					e.setMobileNumber(str[23] != null&&str[23].trim().length()>0?
							String.valueOf(BigDecimal.valueOf(Double.parseDouble(str[23].replace(" ", "")))):"");
					// 证件类别
					if ("居民身份证".equals(str[83])) {
						e.setCredentialsType("0");
					} else if ("护照".equals(str[83])) {
						e.setCredentialsType("1");
					} else if ("军官证".equals(str[83])) {
						e.setCredentialsType("2");
					}
					// 证件号码
					e.setCredentialsNumber(str[84]);
					// 组织单位4
					e.setOrganizationalUnit4(str[5]);
					// 组织单位5
					e.setOrganizationalUnit5(str[7]);
					// 组织单位6
					e.setOrganizationalUnit6(str[9]);
					// 毕业日期
					if (str[78] != null && str[78].trim().length() > 0)
						e.setGraduationDate(GmsUtils.gmsFormToDate(str[78]));
					// 毕业学校/城市
					e.setGraduationSchoolOrCity(str[79]);
					//公司名称
					e.setItsaffiliates(str[27]);
					if (eee != null && eee.size() == 1){
						// 如己存在 处理一： 修改
						employeeService.modifyEmpInfo(e);
					}else{
						// 不存在：添加员工基本信息
						employeeService.addEmpInfo(e);
					}
				// 添加工时详情
				for (HoursWorkStandardInfo hwsi : stdHoursList) {
					// 只初始化添加入职日期之后的信息
					if (e.getEntryDate() != null) {
						if (e.getEntryDate().after(hwsi.getStandardDate()))
							continue;
					}
					EmployeeHoursDetailInfo ehdi4query = new EmployeeHoursDetailInfo();
					ehdi4query.setEmployeeId(String.valueOf((int) Double
							.parseDouble(str[14].replace(" ", ""))));
					ehdi4query.setStandardDate(hwsi.getStandardDate());
					EmployeeHoursDetailInfo ehdi4chk = hoursWorkService
							.queryHoursDetail(ehdi4query);
					if (ehdi4chk != null)
						continue;
					EmployeeHoursDetailInfo ehdi = new EmployeeHoursDetailInfo();
					ehdi.setEmployeeId(String.valueOf((int) Double
							.parseDouble(str[14].replace(" ", ""))));
					ehdi.setEmployeeName(str[15].replace(" ", ""));
					ehdi.setStandardDateType(hwsi.getStandardDateType());
					ehdi.setStandardDate(hwsi.getStandardDate());
					ehdi.setStandardHours(hwsi.getStandardHours());
					ehdi.setStandardWeek(hwsi.getStandardWeek());
					ehdi.setCurrentGapHours((float) hwsi.getStandardHours());
					hoursWorkService.addHoursDetailInfo(ehdi);
				}
				try {
					// 添加工时统计表中员工相关信息
					HoursWorkStatisticsInfo hwsi = hoursWorkService
							.queryHoursStatistics(String.valueOf((int) Double
									.parseDouble(str[14].replace(" ", ""))));
					if (hwsi == null) {
						hoursWorkService.addHoursStatisticsInfo(e);
					}
					// 如己存在 处理三： 覆盖
					// List<EmployeeBasicInfo> eee =
					// employeeService.queryByName(String.valueOf((int)
					// Double.parseDouble(str[14].replace(" ", ""))));
					// if(eee!=null&&eee.size()==1)
					// employeeService.removeEmpInfo(String.valueOf((int)
					// Double.parseDouble(str[14].replace(" ", ""))));
					
				} catch (SQLException ex) {
					log.error(str[14] + ":" + ex.getMessage());
					continue;
				}
			}
		}
		// 归属信息导入
		else if ("eri".equals(uploadForm.getFlag())) {
			List<String[]> lt = new ArrayList<String[]>();
			try {
				lt = GmsUtils.readExcel(3, is4imp, excelVersion);// 从第4行开始读取（0为第一行）
			} catch (OutOfMemoryError e) {
				request.setAttribute("err_msg", "数据量过大，请分批操作！");
				return mapping.findForward("success");
			}
			log.info("本次导入归属信息条数：" + lt.size() + "条");
			// 在员工基本信息表里找不到或找到多条该员工信息
			StringBuffer err_names = new StringBuffer();
			String datePatten = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}";
			for (int i = 0; i < lt.size(); i++) {
				String[] str = (String[]) lt.get(i);
				if(str.length<19){
					request.setAttribute("err_msg", "数据结构异常，与导入模板不一致，请确认后重新操作！");
					break;
				}
				if(str[6]==null || str[7]==null || str[6].trim().length()==0 || str[7].trim().length()==0) continue;
				EmployeeRelegationInfo eri = new EmployeeRelegationInfo();
				if ("高级".equals(str[1])) {
					eri.setGrade("2");
				} else if ("中级".equals(str[1])) {
					eri.setGrade("1");
				} else if ("初级".equals(str[1])) {
					eri.setGrade("0");
				} else {
					eri.setGrade("");
				}
				eri.setCompany(str[2]);
				eri.setProjectDirector(str[3]);
				eri.setProjectManager(str[4]);
				eri.setProjectLeader(str[5]);
				eri.setEmployeeName(str[6]);
				if(str[18]==null||str[18].trim().length()==0){
					List<EmployeeBasicInfo> list = employeeService.queryByName(str[6]);
					if (list != null && list.size() == 1) {
						eri.setEmployeeId(list.get(0).getEmployeeId());
					}else if(list != null && list.size() > 1){
						if(!err_names.toString().contains("_"+str[6] + ","))
							err_names.append("_"+str[6] + ",");
						continue;
					}
				}else{
					if(str[18].contains(".")){
						eri.setEmployeeId(String.valueOf((int) Double.parseDouble(str[18].replace(" ", "")))
								);
					}else{
						eri.setEmployeeId(str[18]);
					}
				}

				if ("长期休假".equals(str[7])) {
					eri.setWorkstaus("0");
				} else if ("未到岗".equals(str[7])) {
					eri.setWorkstaus("1");
				} else if ("计费".equals(str[7])) {
					eri.setWorkstaus("2");
				} else if ("结束".equals(str[7])) {
					eri.setWorkstaus("3");
				} else if ("闲置".equals(str[7])) {
					eri.setWorkstaus("4");
				} else if ("离职".equals(str[7])) {
					eri.setWorkstaus("5");
				} else if ("借调外部".equals(str[7])) {
					eri.setWorkstaus("6");
				} else if ("借入".equals(str[7])) {
					eri.setWorkstaus("7");
				} else if ("现场储备".equals(str[7])) {
					eri.setWorkstaus("8");
				} else if ("公司储备".equals(str[7])) {
					eri.setWorkstaus("9");
				} else if ("调离".equals(str[7])) {
					eri.setWorkstaus("10");
				} else if ("借调".equals(str[7])) {
					eri.setWorkstaus("11");
				}else if ("下包商".equals(str[7])) {
					eri.setWorkstaus("12");
				}else if ("管理".equals(str[7])) {
					eri.setWorkstaus("13");
				}else if ("销售".equals(str[7])) {
					eri.setWorkstaus("14");
				}else if ("售前".equals(str[7])) {
					eri.setWorkstaus("15");
				}else if ("调休".equals(str[7])) {
					eri.setWorkstaus("16");
				}else {
					eri.setWorkstaus(" ");
				}
				if ("总体部".equals(str[8])) {
					eri.setBusinessLine("0");
				} else if ("上海分行".equals(str[8])) {
					eri.setBusinessLine("1");
				} else if ("信息中心".equals(str[8])) {
					eri.setBusinessLine("2");
				} else if ("山东".equals(str[8])) {
					eri.setBusinessLine("3");
				} else if ("电子渠道".equals(str[8])) {
					eri.setBusinessLine("4");
				} else if ("国际结算".equals(str[8])) {
					eri.setBusinessLine("5");
				} else if ("信息管理".equals(str[8])) {
					eri.setBusinessLine("6");
				} else if ("投资代理".equals(str[8])) {
					eri.setBusinessLine("7");
				} else if ("质量管理".equals(str[8])) {
					eri.setBusinessLine("8");
				} else if ("核心业务".equals(str[8])) {
					eri.setBusinessLine("9");
				} else if ("上海".equals(str[8])) {
					eri.setBusinessLine("10");
				} else if ("深圳".equals(str[8])) {
					eri.setBusinessLine("11");
				} else if ("成都".equals(str[8])) {
					eri.setBusinessLine("12");
				} else if ("上海银商".equals(str[8])) {
					eri.setBusinessLine("13");
				} else {
					eri.setBusinessLine(str[8]);
				}
				eri.setBelongedTSproject(str[9]!=null&&str[9].trim().length()==0?" ":str[9]);
				eri.setSkill(str[11]);
				eri.setRate(str[12]);
				if (str[13] != null &&str[13].matches(datePatten))
					eri.setAdmittancefreeDate(GmsUtils.gmsFormToDate(str[13]));
				if (str[14] != null && str[14].matches(datePatten))
					eri.setAdmittancebillingDate(GmsUtils
							.gmsFormToDate(str[14]));
				if (str[15] != null && str[15].matches(datePatten))
					eri.setOutsceneestimateDate(GmsUtils
									.gmsFormToDate(str[15]));
				if (str[16] != null && str[16].matches(datePatten))
					eri.setOutscenerealityDate(GmsUtils.gmsFormToDate(str[16]));
				if (str[17] != null) {
					if ("入职".equals(str[17])) {
						eri.setEmpOperate("0");
					} else if ("调入".equals(str[17])) {
						eri.setEmpOperate("1");
					} else if ("离职".equals(str[17])) {
						eri.setEmpOperate("2");
					} else if ("调出".equals(str[17])) {
						eri.setEmpOperate("3");
					}
					
				}
				try {
					
					if(eri.getEmployeeId()!=null&&eri.getEmployeeId().length()>0){
						EmployeeRelegationInfo eritemp = employeeService
						.queryEriInfoByEid(eri);
						if (eritemp != null) {
							employeeService.removeEmpRelInfo(eri);
						}
						employeeService.addRelegationInfo(eri);
					}else{
						List<EmployeeRelegationInfo> eriList = employeeService.queryEriInfoByEname(eri);
						if(eriList!=null&&eriList.size()==1){
							eri.setEmployeeId(eriList.get(0).getEmployeeId());
							employeeService.removeEmpRelInfo(eri);
							employeeService.addRelegationInfo(eri);
						}else{
							employeeService.addRelegationInfoNoId(eri);
						}
					}
					//插入人员变动信息
					if (eri.getEmpOperate() != null) {
						
						// 构造插入实体
						EmpRelStaInfo ersi = new EmpRelStaInfo();
						if(eri.getEmployeeId()==null || eri.getEmployeeId().trim().length()==0){
							List<EmployeeRelegationInfo> eriList = employeeService.queryEriInfoByEname(eri);
							if(eriList!=null&&eriList.size()==1)
								ersi.setEmployeeId(eriList.get(0).getEmployeeId());
						}else{
							ersi.setEmployeeId(eri.getEmployeeId());
						}
						ersi.setEmployeeName(eri.getEmployeeName());
						ersi.setUpdateTime(new Date());
						ersi.setOperate(eri.getEmpOperate());
						ersi.setBusinessLine(eri.getBusinessLine());
						if (eri.getSkill() != null && eri.getSkill().length()>0) {
							if ("Test".equals(eri.getSkill())) {
								ersi.setTechnology("0");//测试
							} else {
								ersi.setTechnology("1");//开发
							}
						}else{
							ersi.setTechnology("1");//没有技能默认“开发”
						}
						
						EmpRelStaInfo ersi4check = employeeService.queryLastRecord(ersi.getEmployeeId());
						if(ersi4check==null || !ersi4check.getOperate().equals(ersi.getOperate()))
						employeeService.addChangeRecord(ersi);
					}
				} catch (SQLException e) {
					log.error(str[0] + ":" + e.getMessage());
					continue;
				}
			}
			if (err_names.toString().trim().length() > 0) {
				request.setAttribute("err_msg4relimp", err_names
						.toString());
				log.info("本次导入员工归属信息异常人员名单:" + err_names.toString());
			}
			return mapping.findForward("success");
		}
		// 项目信息导入
		else if ("proj".equals(uploadForm.getFlag())) {
			List<String[]> lt = new ArrayList<String[]>();
			try {
				lt = GmsUtils.readExcel(1, is4imp, excelVersion);// 从第2行开始读取（0为第一行）
			} catch (OutOfMemoryError e) {
				request.setAttribute("err_msg", "数据量过大，请分批操作！");
				return mapping.findForward("success");
			}
			log.info("本次导入项目信息条数：" + lt.size() + "条");
			for (int i = 0; i < lt.size(); i++) {
				String[] str = (String[]) lt.get(i);
				ProjectInfo p = new ProjectInfo();
				// 项目编号
				p.setProId(str[0]);
				// 项目名称
				p.setProName(str[1]);
				// 事业部
				p.setCareerdepartment(str[2]);
				// 实施部
				p.setImplementDepartment(str[3]);
				// 项目经理
				p.setProjectManager(str[4]);
				// 客户名称
				p.setCustomerName(str[5]);
				// 开始日期
				p.setBeginDate(GmsUtils.gmsFormToDate(str[6]));
				// 结束日期
				p.setEndDate(GmsUtils.gmsFormToDate(str[7]));
				try {
					ProjectInfo pi = projectService.queryProInfoById(p
							.getProId());
					if (pi != null)
						projectService.removeProjectInfo(p.getProId());
					projectService.addProjectInfo(p);
				} catch (SQLException e) {
					log.error(str[0] + ":" + e.getMessage());
					continue;
				}
			}
		}

		// 员工离职信息导入
		else if ("edi".equals(uploadForm.getFlag())) {
			List<String[]> lt = new ArrayList<String[]>();
			try {
				lt = GmsUtils.readExcel(1, is4imp, excelVersion);// 从第2行开始读取（0为第一行）
			} catch (OutOfMemoryError e) {
				request.setAttribute("err_msg", "数据量过大，请分批操作！");
				return mapping.findForward("success");
			}
			log.info("本次导入离职信息条数：" + lt.size() + "条");
			for (int i = 0; i < lt.size(); i++) {
				String[] str = (String[]) lt.get(i);
				EmployeeDimissionInfo edi = new EmployeeDimissionInfo();
				edi.setEmployeeId(String.valueOf((int) Double
						.parseDouble(str[0].replace(" ", ""))));
				edi.setEmployeeName(str[1]);
				edi.setEntryDate(str[2]);
				edi.setDepartment(str[3]);
				edi.setDependency(str[4]);
				edi.setPersonnelSubrangeText(str[5]);
				edi.setEmployeeGroup(str[6]);
				edi.setCommonPosition(str[7]);
				edi.setBusinessNature(str[8]);
				edi.setEnterpriseflock(str[9]);
				edi.setEnterpriseflockCode(str[10]);
				edi.setDepartmentCode(str[11]);
				edi.setDependencyCode(str[12]);
				edi.setPersonnelSubrange(str[13]);
				edi.setWorkPlace(str[14]);
				edi.setTrialPeriod(str[15]);
				edi.setAppraisalDate(str[16]);
				edi.setResignationDate(GmsUtils.gmsFormToDate(str[17]));
				edi.setResignationStatus(str[18]);
				edi.setResignationReasons(str[19]);
				if ("否".equals(str[20])) {
					edi.setInitiativeOrPassive("1");
				} else
					edi.setInitiativeOrPassive("0");
				try {
					List<EmployeeDimissionInfo> ltTemp = employeeService
							.queryDimissionInfo(edi);
					if (ltTemp != null && ltTemp.size() > 0) {
						employeeService.delDimInfo(edi);
					}
					employeeService.addDimissionInfo(edi);
				} catch (SQLException e) {
					log.error(str[0] + ":" + e.getMessage());
					continue;
				}
			}
		}
		// 员工PSA请假信息导入
		else if ("evi".equals(uploadForm.getFlag())) {
			List<String[]> lt = new ArrayList<String[]>();
			try {
				lt = GmsUtils.readExcel(1, is4imp, excelVersion);// 从第2行开始读取（0为第一行）
			} catch (OutOfMemoryError e) {
				request.setAttribute("err_msg", "数据量过大，请分批操作！");
				return mapping.findForward("success");
			}
			log.info("本次导入请假信息条数：" + lt.size() + "条");
			for (int i = 0; i < lt.size(); i++) {
				String[] str = (String[]) lt.get(i);
				EmployeeVacationInfo evi = new EmployeeVacationInfo();
				// 请假的开始日期和结束日期为同一天
				if (GmsUtils.gmsFormToDate(str[5]).equals(
						GmsUtils.gmsFormToDate(str[6]))) {
					evi.setEmployeeId(String.valueOf((int) Double
							.parseDouble(str[0])));
					evi.setEmployeeName(str[1]);
					evi.setDepartmentId(String.valueOf((int) Double
							.parseDouble(str[2])));
					evi.setDepartmentName(str[3]);
					evi.setLeaveTypeName(str[4]);
					evi.setPsaDate(GmsUtils.gmsFormToDate(str[5]));
					evi.setTotalNumberHours(str[9]);
					evi.setReceiptStatus(str[10]);
					evi.setApprovedByDate(str[11]);
					for (int k = 0; k < fflPsaType.length; k++) {
						if (fflPsaType[k].equals(str[4].trim())) {
							// 非福利假
							evi.setIsWelfareHoliays("1");
						} else {
							// 福利假
							evi.setIsWelfareHoliays("0");
						}
					}
					EmployeeVacationInfo eviTemp = employeeService
							.queryVacationInfoByIdDate(evi);
					// 覆盖原数据
					if (eviTemp != null)
						employeeService.delVacInfo(eviTemp);
					try {
						employeeService.addVacationInfo(evi);
						if("审批通过".equals(str[10])){
							// 查询该天标准工时信息
							HoursWorkStandardInfo hwsstdi4detail = hoursWorkService.queryStdInfoByDate(GmsUtils.gmsFormToDate(str[5]));
							if(hwsstdi4detail!=null&&hwsstdi4detail.getStandardHours()!=null){
								Float gapTemp = hwsstdi4detail.getStandardHours()-Float.parseFloat(evi.getTotalNumberHours());
								EmployeeHoursDetailInfo ehdi = new EmployeeHoursDetailInfo();
								ehdi.setEmployeeId(evi.getEmployeeId());
								ehdi.setStandardDate(evi.getPsaDate());
								ehdi.setPsaLeaveHours(Float.parseFloat(evi.getTotalNumberHours()));
								ehdi.setPsaLeaveHoursType(evi.getLeaveTypeName());
								ehdi.setCurrentGapHours(gapTemp);
								//更新工时详情表中该人该天GAP工时
								hoursWorkService.modifyGapByPsa(ehdi);
							}
						}
						
					} catch (SQLException e) {
						log.error(str[0] + ":" + e.getMessage());
						continue;
					}

				}
				// 请假跨多天
				else if (GmsUtils.gmsFormToDate(str[6]).after(
						GmsUtils.gmsFormToDate(str[5]))) {
					// 获得开始日期与结束日期之前的所有日期（包括开始和结束日期）
					List<Date> dates = GmsUtils.getBetweenDates(GmsUtils
							.gmsFormToDate(str[5]), GmsUtils
							.gmsFormToDate(str[6]));
					// 排除非工作日期
					for (int j = dates.size() - 1; j >= 0; j--) {
						String st = hoursWorkService
								.queryDateType(dates.get(j));
						if (!"0".equals(st))
							dates.remove(j);
					}
					for (Date tem : dates) {
						evi.setEmployeeId(String.valueOf((int) Double
								.parseDouble(str[0])));
						evi.setEmployeeName(str[1]);
						evi.setDepartmentId(String.valueOf((int) Double
								.parseDouble(str[2])));
						evi.setDepartmentName(str[3]);
						evi.setLeaveTypeName(str[4]);
						evi.setPsaDate(tem);
						evi.setTotalNumberHours("8");
						evi.setReceiptStatus(str[10]);
						evi.setApprovedByDate(str[11]);
						for (int k = 0; k < fflPsaType.length; k++) {
							if (fflPsaType[k].equals(str[4].trim())) {
								// 非福利假
								evi.setIsWelfareHoliays("1");
							} else {
								// 福利假
								evi.setIsWelfareHoliays("0");
							}
						}
						try {
							EmployeeVacationInfo eviTemp = employeeService
									.queryVacationInfoByIdDate(evi);
							// 覆盖原数据
							if (eviTemp != null)
								employeeService.delVacInfo(eviTemp);
							employeeService.addVacationInfo(evi);
							
							if("审批通过".equals(str[10])){
								// 查询该天标准工时信息
								HoursWorkStandardInfo hwsstdi4detail = hoursWorkService.queryStdInfoByDate(GmsUtils.gmsFormToDate(str[5]));
								if(hwsstdi4detail!=null&&hwsstdi4detail.getStandardHours()!=null){
									Float gapTemp = hwsstdi4detail.getStandardHours()-Float.parseFloat(evi.getTotalNumberHours());
									EmployeeHoursDetailInfo ehdi = new EmployeeHoursDetailInfo();
									ehdi.setEmployeeId(evi.getEmployeeId());
									ehdi.setStandardDate(evi.getPsaDate());
									ehdi.setPsaLeaveHours(Float.parseFloat(evi.getTotalNumberHours()));
									ehdi.setPsaLeaveHoursType(evi.getLeaveTypeName());
									ehdi.setCurrentGapHours(gapTemp);
									//更新工时详情表中该人该天GAP工时
									hoursWorkService.modifyGapByPsa(ehdi);
								}
							}
						} catch (SQLException e) {
							log.error(str[0] + ":" + e.getMessage());
							continue;
						}
					}
				} else {
					// 数据错误，结束日期在开始时间之前，跳过本条错误信息
					continue;
				}
			}
		}
		// 标准工时导入
		else if ("hws".equals(uploadForm.getFlag())) {
			List<String[]> lt = new ArrayList<String[]>();
			try {
				lt = GmsUtils.readExcel(1, is4imp, excelVersion);// 从第2行开始读取（0为第一行）
			} catch (OutOfMemoryError e) {
				request.setAttribute("err_msg", "数据量过大，请分批操作！");
				return mapping.findForward("success");
			}
			log.info("本次导入标准工时信息条数：" + lt.size() + "条");
			// 查询所有员工
			List<EmployeeBasicInfo> ebiList = employeeService.queryAllGapChEmp();
			int count4ig = 0;
			for (int i = 0; i < lt.size(); i++) {
				String[] str = (String[]) lt.get(i);
				HoursWorkStandardInfo hwsi = new HoursWorkStandardInfo();
				if (str[0] == null || str[0].trim().length() < 8)
					continue;
				hwsi.setStandardDate(GmsUtils.gmsFormToDate(str[0]));
				hwsi.setStandardWeek(str[1]);
				if ("工作日".equals(str[2])) {
					hwsi.setStandardDateType("0");
				} else if ("双休日".equals(str[2])) {
					hwsi.setStandardDateType("1");
				} else if ("节假日".equals(str[2])) {
					hwsi.setStandardDateType("2");
				}
				hwsi.setStandardHours((int) Double.parseDouble(str[3]));
				try {

					// 5次验证是否为已有年份，如非新年份，跳过更新工时详情操作
					if (count4ig < 5) {
						List<Date> allDate = hoursWorkService.queryAllDates();
						if (allDate.contains(hwsi.getStandardDate()))
							count4ig++;
						// 更新工时详情表信息(只有新年份的日历才需更新工时详情表)
						if (ebiList != null && ebiList.size() > 0) {
							for (EmployeeBasicInfo ebi : ebiList) {
								if ("1".equals(ebi.getBeCheck4Gap()))
									continue;
								EmployeeHoursDetailInfo ehdi4query = new EmployeeHoursDetailInfo();
								ehdi4query.setEmployeeId(ebi.getEmployeeId());
								ehdi4query.setStandardDate(hwsi
										.getStandardDate());
								EmployeeHoursDetailInfo ehdirs = hoursWorkService
										.queryHoursDetail(ehdi4query);
								if (ehdirs != null)
									continue;
								EmployeeHoursDetailInfo ehdi = new EmployeeHoursDetailInfo();
								ehdi.setEmployeeId(ebi.getEmployeeId());
								ehdi.setEmployeeName(ebi.getEmployeeName());
								ehdi.setStandardDateType(hwsi
										.getStandardDateType());
								ehdi.setStandardDate(hwsi.getStandardDate());
								ehdi.setStandardHours(hwsi.getStandardHours());
								ehdi.setStandardWeek(hwsi.getStandardWeek());
								ehdi.setCurrentGapHours((float) hwsi
										.getStandardHours());
								hoursWorkService.addHoursDetailInfo(ehdi);
							}
						}
					}

					HoursWorkStandardInfo hwstRs = hoursWorkService
							.queryStdInfoByDate(hwsi.getStandardDate());
					if (hwstRs != null)
						hoursWorkService.delStdInfoByDate(hwsi
								.getStandardDate());
					hoursWorkService.addStdHoursworkInfo(hwsi);
				} catch (SQLException e) {
					log.error(str[0] + ":" + e.getMessage());
					continue;
				}
			}
		}
		// 客户确认工时导入
		else if ("cci".equals(uploadForm.getFlag())) {
			List<String[]> lt = new ArrayList<String[]>();
			try {
				lt = GmsUtils.readExcel(1, is4imp, excelVersion);// 从第2行开始读取（0为第一行）
			} catch (OutOfMemoryError e) {
				request.setAttribute("err_msg", "数据量过大，请分批操作！");
				return mapping.findForward("toCstmHw");
			}
			log.info("本次导入客户确认工时信息条数：" + lt.size() + "条");
			// 导入时发生异常的员工姓名
			StringBuffer errorNames = new StringBuffer();
			// 本次导入成功的数据条数
			int impCount = 0;
			// 确认工时导入时的删除标记(用来记录己执行过删除操作的数据(同一人同一天)，避免本次导入中
			// 同一人同一天有多条数据而引起误删除的情况(因导入后面数据而误把前面刚导入的数据删除))
			StringBuffer delFlag = new StringBuffer();
			// 更新工时详情表标记 此字段用来记录本次导入中(同一人同一天出现多条记录的情况)己update过的数据
			// 避免后面数据把刚update过的信息又update掉的情况
			StringBuffer detailUpdateFlag = new StringBuffer();
			StringBuffer cusAddFlag = new StringBuffer();
			for (int i = 0; i < lt.size(); i++) {
				String[] str = (String[]) lt.get(i);
				try {
				// 获取员工基本信息表中员工对应employeeId
				List<EmployeeBasicInfo> temp = employeeService.queryByCusName(str[11].trim());
				if (temp.size() != 1) {
					if (!errorNames.toString().contains("_"+str[11].trim()))
						errorNames.append("_"+str[11] + ",");
					continue;
				}
				CustomerVerifyHoursInfo cvhi = new CustomerVerifyHoursInfo();
				cvhi.setEmployeeId(temp.get(0).getEmployeeId().trim());

				if (str[0] != null && str[0].trim().length() > 0)
					cvhi.setResourcesType(str[0]);
				else
					cvhi.setResourcesType(" ");

				if (str[1] != null && str[1].trim().length() > 0)
					cvhi.setProjectName(str[1]);
				else
					cvhi.setProjectName(" ");

				if (str[2] != null && str[2].trim().length() > 0)
					cvhi.setProjectId(str[2]);
				else
					cvhi.setProjectId(" ");

				if (str[3] != null && str[3].trim().length() > 0)
					cvhi.setProjectOfDepartment(str[3]);
				else
					cvhi.setProjectOfDepartment(" ");

				if (str[4] != null && str[4].trim().length() > 0)
					cvhi.setProductName(str[4]);
				else
					cvhi.setProductName(" ");

				if (str[5] != null && str[5].trim().length() > 0)
					cvhi.setServiceProductsAttributiveTeam(str[5]);
				else
					cvhi.setServiceProductsAttributiveTeam(" ");

				if (str[6] != null && str[6].trim().length() > 0)
					cvhi.setServiceProductsDepartment(str[6]);
				else
					cvhi.setServiceProductsDepartment(" ");

				if (str[7] != null && str[7].trim().length() > 0)
					cvhi.setProviders(str[7]);
				else
					cvhi.setProviders(" ");

				if (str[8] != null && str[8].trim().length() > 0)
					cvhi.setTechnologyPlatform(str[8]);
				else
					cvhi.setTechnologyPlatform(" ");

				if (str[9] != null && str[9].trim().length() > 0)
					cvhi.setPresenceOfType(str[9]);
				else
					cvhi.setPresenceOfType(" ");

				if (str[10] != null && str[10].trim().length() > 0)
					cvhi.setWorkPlace(str[10].trim());
				else
					cvhi.setWorkPlace(" ");

				cvhi.setEmployeeName(str[11].trim());

				if (str[12] != null && str[12].trim().length() > 0)
					cvhi.setTechnologyGrade(str[12]);
				else
					cvhi.setTechnologyGrade(" ");

				cvhi.setWorkDate(GmsUtils.gmsFormToDate(str[13]));
				cvhi.setNormalWorkingHours(Float.parseFloat(str[14]));
				cvhi.setOverTimeHours(Float.parseFloat(str[15]));
				// cvhi.setIsBilling(str[16]);
				
				// 多次导入时，先清掉原来同一人同一天的记录，再插入
				List<CustomerVerifyHoursInfo> cusList = hoursWorkService
						.queryCusHoursInfo(cvhi);
				if (cusList.size() != 0) {
					if (!delFlag.toString().contains(
							"_"+cvhi.getEmployeeId() + str[13] + ",")) {
						hoursWorkService.delCusHoursInfo(cvhi);
					}
				}
				if (!delFlag.toString().contains(
						"_"+cvhi.getEmployeeId() + str[13] + ",")) {
					delFlag.append("_"+cvhi.getEmployeeId() + str[13] + ",");
				}
				if (!cusAddFlag.toString().contains(
						"_"+cvhi.getEmployeeId() + str[13] + ",")) {
					hoursWorkService.addCstmHoursworkInfo(cvhi);
					cusAddFlag.append("_"+cvhi.getEmployeeId() + str[13] + ",");
				} else {
					hoursWorkService.modifyCstmHoursworkInfo(cvhi);
				}

				// 更新工时详情表信息
				EmployeeHoursDetailInfo ehdi4query = new EmployeeHoursDetailInfo();
				ehdi4query.setEmployeeId(temp.get(0).getEmployeeId().trim());
				ehdi4query.setStandardDate(cvhi.getWorkDate());
				EmployeeHoursDetailInfo ehdi4check = hoursWorkService
						.queryHoursDetail(ehdi4query);
				if (ehdi4check != null) {
					// 查询该天标准工时信息
					HoursWorkStandardInfo hwsstdi4detail = hoursWorkService
							.queryStdInfoByDate(GmsUtils.gmsFormToDate(str[13]));
					EmployeeHoursDetailInfo ehdi4update = new EmployeeHoursDetailInfo();
					ehdi4update.setEmployeeId(ehdi4check.getEmployeeId());
					ehdi4update.setStandardDate(ehdi4check.getStandardDate());
					// 客户确认正常工时
					ehdi4update.setCustomerVerifyHours(Float.parseFloat(str[14]));
					// 加班工时
					ehdi4update.setCustomerVerifyOvertimeHours(Float.parseFloat(str[15]));
					// 更新标准工时
					ehdi4update.setStandardHours(hwsstdi4detail.getStandardHours());
					// 更新日期类型
					ehdi4update.setStandardDateType(hwsstdi4detail.getStandardDateType());
					// 当天产生GAP工时(PSA请假信息尚未参与计算)
					float gapHOurs = hwsstdi4detail.getStandardHours()- Float.parseFloat(str[14]);

					// 查询当天PSA请假信息
					EmployeeVacationInfo eviTemp = new EmployeeVacationInfo();
					eviTemp.setEmployeeId(temp.get(0).getEmployeeId());
					eviTemp.setPsaDate(GmsUtils.gmsFormToDate(str[13]));
					EmployeeVacationInfo evi4detail = employeeService
							.queryVacationInfoByIdDate(eviTemp);

					if (evi4detail != null&&"审批通过".equals(evi4detail.getReceiptStatus())) {
						if (evi4detail.getTotalNumberHours() != null) {
							// PSA请假工时
							ehdi4update.setPsaLeaveHours(Float
									.parseFloat(evi4detail
											.getTotalNumberHours()));
							// 根据PSA请假信息 确定最终GAP工时
							gapHOurs = gapHOurs
									- Float.parseFloat(evi4detail
											.getTotalNumberHours());
						}
						// PSA请假类型
						ehdi4update.setPsaLeaveHoursType(evi4detail
								.getLeaveTypeName());
					}
					ehdi4update.setCurrentGapHours(gapHOurs);

					if (Float.parseFloat(str[15]) != 0f) {
						if ("0".equals(hwsstdi4detail.getStandardDateType())) {
							// 当天产生可补贴工时(工作日)
							ehdi4update.setCurrentSubsidiesHours(Float
									.parseFloat(str[15]));
						} else if ("1".equals(hwsstdi4detail
								.getStandardDateType())) {
							// 当天产生可倒休工时(双休日)
							if (Float.parseFloat(str[15]) > 8) {
								// 双休日加班超过8小时 超出部分计入可补贴
								ehdi4update.setCurrentAhughHours(8f);
								ehdi4update.setCurrentSubsidiesHours(Float
										.parseFloat(str[15]) - 8f);
							} else {
								ehdi4update.setCurrentAhughHours(Float
										.parseFloat(str[15]));
							}
						} else if ("2".equals(hwsstdi4detail
								.getStandardDateType())) {
							// 当天产生可倒休工时(节假日 3倍)
							if (Float.parseFloat(str[15]) > 8) {
								// 节假日加班超过8小时 8小时部分算3倍 超出部分计入可补贴(非3倍)
								ehdi4update.setCurrentAhughHours(24f);
								ehdi4update.setCurrentSubsidiesHours(Float
										.parseFloat(str[15]) - 8f);
							} else {
								ehdi4update.setCurrentAhughHours(Float
										.parseFloat(str[15]) * 3);
							}
						}
					}
					// 项目名称
					ehdi4update.setProjectName(cvhi.getProjectName());
					// 工作属地
					ehdi4update.setWorkPlace(cvhi.getWorkPlace());
					// 技术平台
					ehdi4update.setTechnologyPlatform(cvhi.getTechnologyPlatform());
					// 技术等级
					ehdi4update.setTechnologyGrade(cvhi.getTechnologyGrade());
					// 在场类型
					ehdi4update.setPresenceOfType(cvhi.getPresenceOfType());
					//客户方定义的本公司员工姓名
					ehdi4update.setEmpName4Cus(cvhi.getEmployeeName());
					//资源类型
					ehdi4update.setResourcesType(cvhi.getResourcesType());
					//项目编号
					ehdi4update.setProjectId(cvhi.getProjectId());
					//项目所属部门
					ehdi4update.setProjectOfDepartment(cvhi.getProjectOfDepartment());
					//产品名称
					ehdi4update.setProductName(cvhi.getProductName());
					//服务产品所属团队
					ehdi4update.setServiceProductsAttributiveTeam(cvhi.getServiceProductsAttributiveTeam());
					//服务产品归属部门
					ehdi4update.setServiceProductsDepartment(cvhi.getServiceProductsDepartment());
					//供应商
					ehdi4update.setProviders(cvhi.getProviders());
					//正常工时
					ehdi4update.setNormalWorkingHours(Float.parseFloat(str[14]));
					// 加班工时
					ehdi4update.setOverTimeHours(Float.parseFloat(str[15]));

					if (!detailUpdateFlag.toString().contains(
							"_"+cvhi.getEmployeeId() + str[13] + ",")) {
						hoursWorkService.modifyHoursDetailInfo(ehdi4update);
						detailUpdateFlag.append("_"+cvhi.getEmployeeId() + str[13]
								+ ",");
					} else {
						hoursWorkService
								.modifyHoursDetailInfoRepeat(ehdi4update);
					}

				}

				// 更新工时统计表信息
				String staFlag = request.getParameter("staFlag");
				// “最终版客户确认工时”导入时 才更新工时统计表信息
				if ("sta".equals(staFlag)) {
					HoursWorkStandardInfo hwstdi = hoursWorkService
							.queryStdInfoByDate(GmsUtils.gmsFormToDate(str[13]));
					if (hwstdi == null)
						continue;
					HoursWorkStatisticsInfo tempHwsi = hoursWorkService
							.queryHoursStatistics(temp.get(0).getEmployeeId());
					if (tempHwsi != null) {
						// 构建更新工时统计实体
						HoursWorkStatisticsInfo hwsii = new HoursWorkStatisticsInfo();
						hwsii.setEmployeeId(tempHwsi.getEmployeeId());
						// 客户确认总工时
						float csth = tempHwsi.getCustomerSureTotalHours();
						csth += Float.parseFloat(str[14]);
						hwsii.setCustomerSureTotalHours(csth);
						// 标准总工时
						float sth = tempHwsi.getStandardTotalHours();
						sth += hwstdi.getStandardHours();
						hwsii.setStandardTotalHours(sth);
						// PSA请假总工时
						EmployeeVacationInfo eviTem = new EmployeeVacationInfo();
						eviTem.setEmployeeId(temp.get(0).getEmployeeId());
						eviTem.setPsaDate(GmsUtils.gmsFormToDate(str[13]));
						EmployeeVacationInfo evi = employeeService
								.queryVacationInfoByIdDate(eviTem);
						float pwhth = tempHwsi.getPsaWelfareHoliaysTotalHours();
						float pnwhth = tempHwsi
								.getPsaNotWelfareHoliaysTotalHours();
						if (evi != null) {
							if ("0".equals(evi.getIsWelfareHoliays())) {
								pwhth += Float.parseFloat(evi
										.getTotalNumberHours());
							} else if ("1".equals(evi.getIsWelfareHoliays())) {
								pnwhth += Float.parseFloat(evi
										.getTotalNumberHours());
							}
						}
						hwsii.setPsaWelfareHoliaysTotalHours(pwhth);
						hwsii.setPsaNotWelfareHoliaysTotalHours(pnwhth);
						// 加班可倒休总工时
						float woath = tempHwsi.getWorkOtimeAhughTotalHours();
						// 加班可补贴总工时
						float wosth = tempHwsi
								.getWorkOtimeSubsidiesTotalHours();
						if (Float.parseFloat(str[15]) != 0f) {
							if ("0".equals(hwstdi.getStandardDateType())) {
								wosth += Float.parseFloat(str[15]);
							} else if ("1".equals(hwstdi.getStandardDateType())) {
								woath += Float.parseFloat(str[15]);
							} else if ("2".equals(hwstdi.getStandardDateType())) {
								woath += Float.parseFloat(str[15]) * 3;
							}
						}
						hwsii.setWorkOtimeAhughTotalHours(woath);
						hwsii.setWorkOtimeSubsidiesTotalHours(wosth);
						// 剩余倒休总工时
						float lsth = tempHwsi.getLastSwoppedTotalHours();
						if (Float.parseFloat(str[15]) != 0f) {
							if ("1".equals(hwstdi.getStandardDateType())) {
								lsth += Float.parseFloat(str[15]) > 8 ? 8f
										: Float.parseFloat(str[15]);
							} else if ("2".equals(hwstdi.getStandardDateType())) {
								lsth += Float.parseFloat(str[15]) > 8 ? 24f
										: Float.parseFloat(str[15]) * 3;
							}
						}
						hwsii.setLastSwoppedTotalHours(lsth);
						// 截止统计日期
						Date btsd = tempHwsi.getByTheStatisticalDate();
						if (btsd != null) {
							if (btsd.before(GmsUtils.gmsFormToDate(str[13]))) {
								hwsii.setByTheStatisticalDate(GmsUtils
										.gmsFormToDate(str[13]));
							} else {
								hwsii.setByTheStatisticalDate(btsd);
							}
						} else {
							hwsii.setByTheStatisticalDate(GmsUtils
									.gmsFormToDate(str[13]));
						}
						hoursWorkService.modifyHoursStatistics(hwsii);
					}

				}
					
					impCount++;
				} catch (Exception e) {
					log.error(str[0] + ":" + e.getMessage());
					if (!errorNames.toString().contains("_"+str[11].trim()))
					errorNames.append("_"+str[11] + ",");
					continue;
				}
			}
			log.warn("导入成功！导入文件："+filename+",本次导入客户确认工时信息成功条数：" + impCount);
			if (errorNames.toString().trim().length() > 0) {
				request.setAttribute("errorNames", errorNames);
				log.warn(filename+",本次导入客户确认工时异常的人员名单：" + errorNames);
			}
			is4imp.close();
			// 删除上传excel文件
			File f = new File(path + "\\" + filename);
			f.delete();
			errorNames=null;
			delFlag =null;
			detailUpdateFlag =null;
			cusAddFlag = null;
			request.setAttribute("impCount", impCount);
			request.setAttribute("imp_msg", "导入成功！");
			return mapping.findForward("toCstmHw");
		}

		// GAP工时导入 同时update
		else if ("whg".equals(uploadForm.getFlag())) {
			List<String[]> lt = new ArrayList<String[]>();
			try {
				lt = GmsUtils.readExcel(1, is4imp, excelVersion);// 从第2行开始读取（0为第一行）
			} catch (OutOfMemoryError e) {
				request.setAttribute("err_msg", "数据量过大，请分批操作！");
				return mapping.findForward("success");
			}
			log.info("本次导入GAP工时信息条数：" + lt.size() + "条");
			String[] gapType2Update = { "0", "3", "5", "6", "7", "8" };// 会发生冲销操作的差异类型(差异类型
																		// 0迟到早退1现场储备2回公司开会3离场4公司储备5打卡异常6闲置7正常倒休8重复工时追加倒休)
			String[] gapTypeAll = { "0", "1", "2", "3", "4", "5", "6", "7", "8" };// 所有差异类型
			for (int i = 0; i < lt.size(); i++) {
				String[] str = (String[]) lt.get(i);
				String gapType = "";
				// 工号、日期、差异类型 不能为空
				if (str[0] == null || str[0].trim().length() == 0
						|| str[2] == null || str[2].trim().length() == 0
						|| str[27] == null || str[27].trim().length() == 0)
					continue;
				// if(!GmsUtils.isNum(str[14].trim())) continue;
				String tempStr = str[27].substring(0, 1);
				for (String gt : gapTypeAll) {
					if (gt.equals(tempStr)) {
						gapType = tempStr;
						break;
					}
				}
				EmployeeHoursDetailInfo ehdi = new EmployeeHoursDetailInfo();

				ehdi.setEmployeeId(str[0]);
//				ehdi.setEmployeeName(str[1]);
				ehdi.setStandardDate(GmsUtils.gmsFormToDate(str[2]));
				EmployeeHoursDetailInfo ehditem = hoursWorkService
						.queryHoursDetail(ehdi);// 备份详情表信息
				if (ehditem == null)
					continue;
				if (gapType.length() == 1) {// 验证差异类型是否符合要求
					// 导入时，只要填写了符合标准的GAP类型，GAP工时自动清零
					ehdi.setCurrentGapHours(0f);
					// 取数据库中的GAP工时，而不取导入的GAP工时，避免导入的GAP工时被修改而影响剩余倒休工时更新的正确性
					ehdi.setWriteOffHours(ehditem.getCurrentGapHours());
				} else {
					// 填写了不符合标准的GAP类型，GAP工时不变
					ehdi.setCurrentGapHours(ehditem.getCurrentGapHours());
				}
				ehdi.setGapType(gapType);
				ehdi.setEmployeeGapReason(str[30]);
				ehdi.setCustomerResponsiblePerson(str[35]);
				ehdi.setCompanyProjectManager(str[36]);
//				ehdi.setBelongedProject(str[18]);
				try {
					hoursWorkService.modifyGapInfo(ehdi);
				} catch (SQLException e) {
					log.error(str[0] + ":" + e.getMessage());
					continue;
				}

				// 差异类型为指定类型 需更新员工剩余倒休工时
				for (String tem : gapType2Update) {
					if (tem.equals(gapType)) {
						HoursWorkStatisticsInfo hwsi = new HoursWorkStatisticsInfo();
						hwsi.setEmployeeId(str[0]);
						// 取数据库中的GAP工时，而不取导入的GAP工时，避免导入的GAP工时被修改而影响剩余倒休工时更新的正确性
						hwsi.setHours2udtLast(ehditem.getCurrentGapHours());
						hoursWorkService.modifyLastHours(hwsi);
						break;
					}
				}
			}
		}
		is4imp.close();
		// 删除上传excel文件
		File f = new File(path + "\\" + filename);
		f.delete();
		request.setAttribute("imp_msg", "导入完成！");
		return mapping.findForward("success");
	}
}
