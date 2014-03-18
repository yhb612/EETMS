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
	// Ա��service
	private IEmployeeService employeeService;
	// ��Ŀservice
	private IProjectService projectService;
	// ��ʱservice
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

	// ������ת������ҳ��
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

	// ���õ������ݷ���������flag���ֵ��ô˷�����ҳ�棬�Թ�����Ӧʵ�壩
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
			request.setAttribute("err_msg", "�ļ����ʹ���,�����²���!");
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
		// �ļ�����
		String impPath = path + "\\" + filename;
		log.info("�ϴ����ļ�����·����" + impPath);
		InputStream is4imp = new FileInputStream(impPath);
		// �Ǹ���������
		String[] fflPsaType = { "�¼�", "����" };

		// Ա��������Ϣ ����
		if ("empbi".equals(uploadForm.getFlag())) {
			List<String[]> lt = new ArrayList<String[]>();
			try {
				lt = GmsUtils.readExcel(1, is4imp, excelVersion);// �ӵ�2�п�ʼ��ȡ��0Ϊ��һ�У�
			} catch (OutOfMemoryError e) {
				request.setAttribute("err_msg", "���������������������");
				return mapping.findForward("success");
			}
			log.info("���ε��������Ϣ������" + lt.size() + "��");

			// ��ѯ���б�׼����info
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
				// �缺���� ������� ����
				// List<EmployeeBasicInfo> eee =
				// employeeService.queryByName(String.valueOf((int)
				// Double.parseDouble(str[14].replace(" ", ""))));
				// if(eee!=null&&eee.size()==1) {
				// e.setBeCheck4Gap(eee.get(0).getBeCheck4Gap());//�Ƿ���빤ʱ�˲�
				// e.setChinaBankName(eee.get(0).getChinaBankName());//�ͻ�����������
				// e.setRank(eee.get(0).getRank());//ְ��
				// e.setSkillDescription(eee.get(0).getSkillDescription());//����˵��
				// e.setFirstSkill(eee.get(0).getFirstSkill());//��һ����
				// e.setSecondSkill(eee.get(0).getSecondSkill());//�ڶ�����
				// e.setItsaffiliates(eee.get(0).getItsaffiliates());//������˾
				// employeeService.modifyEmpInfo(e);
				// continue;
				// }
				
					// Ա�����
					e.setEmployeeId(String.valueOf((int) Double.parseDouble(str[14]
							.replace(" ", ""))));
					// Ա������
					e.setEmployeeName(str[15].replace(" ", ""));
					// �ͻ���������
					e.setChinaBankName(str[15].replace(" ", ""));
					// Ա���Ա�
					if ("��".equals(str[18].trim())) {
						e.setEmployeeGender("0");
					} else if ("Ů".equals(str[18].trim())) {
						e.setEmployeeGender("1");
					}
					// ��ְ����
					if (str[47] != null && str[47].trim().length() > 0)
						e.setEntryDate(GmsUtils.gmsFormToDate(str[47].trim()));
					// ͨ��ְλ
					e.setCommonPosition(str[54]);
					// �ɱ�����
					e.setCostCenter(str[34]);
					// ��֯��λ3
					e.setOrganizationalUnit3(str[3]);
					// ���·�Χ
					e.setPersonnelRange(str[40]);
					// �������ı�
					e.setWorkPlaceText(str[31]);
					// �����ӷ�Χ
					e.setPersonnelSubRange(str[42]);
					// ˾��(��)
					e.setDrivingAgeMonth(str[51]);
					// ����(��)
					e.setWorkingAgeYear(str[49]);
					// Ա����
					if ("��ʽԱ��".equals(str[36])) {
						e.setEmployeeGroup("0");
					} else if ("����Ա��".equals(str[36])) {
						e.setEmployeeGroup("1");
					} else if ("ʵϰԱ��".equals(str[36])) {
						e.setEmployeeGroup("2");
					}else if ("��ְ/����Ա��".equals(str[36])) {
						e.setEmployeeGroup("3");
					}
					// Ա������
					if ("ʵʩ".equals(str[38])) {
						e.setEmployeeSubGroup("0");
					} else if ("����".equals(str[38])) {
						e.setEmployeeSubGroup("1");
					} else if ("ƽ̨".equals(str[38])) {
						e.setEmployeeSubGroup("2");
					} else if ("RM".equals(str[38])) {
						e.setEmployeeSubGroup("3");
					}
					// ְ��
//					if (str[14]!=null&&"BUhead".equals(str[14].trim())) {
//						e.setRank("0");
//					} else if ("�����ܼ�".equals(str[14])) {
//						e.setRank("1");
//					} else if ("�ͻ��ܼ�".equals(str[14])) {
//						e.setRank("2");
//					} else if ("��Ŀ����".equals(str[14])) {
//						e.setRank("3");
//					} else if ("��Ŀ�鳤".equals(str[14])) {
//						e.setRank("4");
//					} else if ("�ͻ�����".equals(str[14])) {
//						e.setRank("5");
//					}
					// ѧ��
					e.setEducationa(str[80]);
					// רҵ����
					e.setSpecialtyName(str[77]);
					// ��˾����
					e.setCompanyMailBox(str[19]);
					// �ֻ�����
					e.setMobileNumber(str[23] != null&&str[23].trim().length()>0?
							String.valueOf(BigDecimal.valueOf(Double.parseDouble(str[23].replace(" ", "")))):"");
					// ֤�����
					if ("�������֤".equals(str[83])) {
						e.setCredentialsType("0");
					} else if ("����".equals(str[83])) {
						e.setCredentialsType("1");
					} else if ("����֤".equals(str[83])) {
						e.setCredentialsType("2");
					}
					// ֤������
					e.setCredentialsNumber(str[84]);
					// ��֯��λ4
					e.setOrganizationalUnit4(str[5]);
					// ��֯��λ5
					e.setOrganizationalUnit5(str[7]);
					// ��֯��λ6
					e.setOrganizationalUnit6(str[9]);
					// ��ҵ����
					if (str[78] != null && str[78].trim().length() > 0)
						e.setGraduationDate(GmsUtils.gmsFormToDate(str[78]));
					// ��ҵѧУ/����
					e.setGraduationSchoolOrCity(str[79]);
					//��˾����
					e.setItsaffiliates(str[27]);
					if (eee != null && eee.size() == 1){
						// �缺���� ����һ�� �޸�
						employeeService.modifyEmpInfo(e);
					}else{
						// �����ڣ����Ա��������Ϣ
						employeeService.addEmpInfo(e);
					}
				// ��ӹ�ʱ����
				for (HoursWorkStandardInfo hwsi : stdHoursList) {
					// ֻ��ʼ�������ְ����֮�����Ϣ
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
					// ��ӹ�ʱͳ�Ʊ���Ա�������Ϣ
					HoursWorkStatisticsInfo hwsi = hoursWorkService
							.queryHoursStatistics(String.valueOf((int) Double
									.parseDouble(str[14].replace(" ", ""))));
					if (hwsi == null) {
						hoursWorkService.addHoursStatisticsInfo(e);
					}
					// �缺���� �������� ����
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
		// ������Ϣ����
		else if ("eri".equals(uploadForm.getFlag())) {
			List<String[]> lt = new ArrayList<String[]>();
			try {
				lt = GmsUtils.readExcel(3, is4imp, excelVersion);// �ӵ�4�п�ʼ��ȡ��0Ϊ��һ�У�
			} catch (OutOfMemoryError e) {
				request.setAttribute("err_msg", "���������������������");
				return mapping.findForward("success");
			}
			log.info("���ε��������Ϣ������" + lt.size() + "��");
			// ��Ա��������Ϣ�����Ҳ������ҵ�������Ա����Ϣ
			StringBuffer err_names = new StringBuffer();
			String datePatten = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}";
			for (int i = 0; i < lt.size(); i++) {
				String[] str = (String[]) lt.get(i);
				if(str.length<19){
					request.setAttribute("err_msg", "���ݽṹ�쳣���뵼��ģ�岻һ�£���ȷ�Ϻ����²�����");
					break;
				}
				if(str[6]==null || str[7]==null || str[6].trim().length()==0 || str[7].trim().length()==0) continue;
				EmployeeRelegationInfo eri = new EmployeeRelegationInfo();
				if ("�߼�".equals(str[1])) {
					eri.setGrade("2");
				} else if ("�м�".equals(str[1])) {
					eri.setGrade("1");
				} else if ("����".equals(str[1])) {
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

				if ("�����ݼ�".equals(str[7])) {
					eri.setWorkstaus("0");
				} else if ("δ����".equals(str[7])) {
					eri.setWorkstaus("1");
				} else if ("�Ʒ�".equals(str[7])) {
					eri.setWorkstaus("2");
				} else if ("����".equals(str[7])) {
					eri.setWorkstaus("3");
				} else if ("����".equals(str[7])) {
					eri.setWorkstaus("4");
				} else if ("��ְ".equals(str[7])) {
					eri.setWorkstaus("5");
				} else if ("����ⲿ".equals(str[7])) {
					eri.setWorkstaus("6");
				} else if ("����".equals(str[7])) {
					eri.setWorkstaus("7");
				} else if ("�ֳ�����".equals(str[7])) {
					eri.setWorkstaus("8");
				} else if ("��˾����".equals(str[7])) {
					eri.setWorkstaus("9");
				} else if ("����".equals(str[7])) {
					eri.setWorkstaus("10");
				} else if ("���".equals(str[7])) {
					eri.setWorkstaus("11");
				}else if ("�°���".equals(str[7])) {
					eri.setWorkstaus("12");
				}else if ("����".equals(str[7])) {
					eri.setWorkstaus("13");
				}else if ("����".equals(str[7])) {
					eri.setWorkstaus("14");
				}else if ("��ǰ".equals(str[7])) {
					eri.setWorkstaus("15");
				}else if ("����".equals(str[7])) {
					eri.setWorkstaus("16");
				}else {
					eri.setWorkstaus(" ");
				}
				if ("���岿".equals(str[8])) {
					eri.setBusinessLine("0");
				} else if ("�Ϻ�����".equals(str[8])) {
					eri.setBusinessLine("1");
				} else if ("��Ϣ����".equals(str[8])) {
					eri.setBusinessLine("2");
				} else if ("ɽ��".equals(str[8])) {
					eri.setBusinessLine("3");
				} else if ("��������".equals(str[8])) {
					eri.setBusinessLine("4");
				} else if ("���ʽ���".equals(str[8])) {
					eri.setBusinessLine("5");
				} else if ("��Ϣ����".equals(str[8])) {
					eri.setBusinessLine("6");
				} else if ("Ͷ�ʴ���".equals(str[8])) {
					eri.setBusinessLine("7");
				} else if ("��������".equals(str[8])) {
					eri.setBusinessLine("8");
				} else if ("����ҵ��".equals(str[8])) {
					eri.setBusinessLine("9");
				} else if ("�Ϻ�".equals(str[8])) {
					eri.setBusinessLine("10");
				} else if ("����".equals(str[8])) {
					eri.setBusinessLine("11");
				} else if ("�ɶ�".equals(str[8])) {
					eri.setBusinessLine("12");
				} else if ("�Ϻ�����".equals(str[8])) {
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
					if ("��ְ".equals(str[17])) {
						eri.setEmpOperate("0");
					} else if ("����".equals(str[17])) {
						eri.setEmpOperate("1");
					} else if ("��ְ".equals(str[17])) {
						eri.setEmpOperate("2");
					} else if ("����".equals(str[17])) {
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
					//������Ա�䶯��Ϣ
					if (eri.getEmpOperate() != null) {
						
						// �������ʵ��
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
								ersi.setTechnology("0");//����
							} else {
								ersi.setTechnology("1");//����
							}
						}else{
							ersi.setTechnology("1");//û�м���Ĭ�ϡ�������
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
				log.info("���ε���Ա��������Ϣ�쳣��Ա����:" + err_names.toString());
			}
			return mapping.findForward("success");
		}
		// ��Ŀ��Ϣ����
		else if ("proj".equals(uploadForm.getFlag())) {
			List<String[]> lt = new ArrayList<String[]>();
			try {
				lt = GmsUtils.readExcel(1, is4imp, excelVersion);// �ӵ�2�п�ʼ��ȡ��0Ϊ��һ�У�
			} catch (OutOfMemoryError e) {
				request.setAttribute("err_msg", "���������������������");
				return mapping.findForward("success");
			}
			log.info("���ε�����Ŀ��Ϣ������" + lt.size() + "��");
			for (int i = 0; i < lt.size(); i++) {
				String[] str = (String[]) lt.get(i);
				ProjectInfo p = new ProjectInfo();
				// ��Ŀ���
				p.setProId(str[0]);
				// ��Ŀ����
				p.setProName(str[1]);
				// ��ҵ��
				p.setCareerdepartment(str[2]);
				// ʵʩ��
				p.setImplementDepartment(str[3]);
				// ��Ŀ����
				p.setProjectManager(str[4]);
				// �ͻ�����
				p.setCustomerName(str[5]);
				// ��ʼ����
				p.setBeginDate(GmsUtils.gmsFormToDate(str[6]));
				// ��������
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

		// Ա����ְ��Ϣ����
		else if ("edi".equals(uploadForm.getFlag())) {
			List<String[]> lt = new ArrayList<String[]>();
			try {
				lt = GmsUtils.readExcel(1, is4imp, excelVersion);// �ӵ�2�п�ʼ��ȡ��0Ϊ��һ�У�
			} catch (OutOfMemoryError e) {
				request.setAttribute("err_msg", "���������������������");
				return mapping.findForward("success");
			}
			log.info("���ε�����ְ��Ϣ������" + lt.size() + "��");
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
				if ("��".equals(str[20])) {
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
		// Ա��PSA�����Ϣ����
		else if ("evi".equals(uploadForm.getFlag())) {
			List<String[]> lt = new ArrayList<String[]>();
			try {
				lt = GmsUtils.readExcel(1, is4imp, excelVersion);// �ӵ�2�п�ʼ��ȡ��0Ϊ��һ�У�
			} catch (OutOfMemoryError e) {
				request.setAttribute("err_msg", "���������������������");
				return mapping.findForward("success");
			}
			log.info("���ε��������Ϣ������" + lt.size() + "��");
			for (int i = 0; i < lt.size(); i++) {
				String[] str = (String[]) lt.get(i);
				EmployeeVacationInfo evi = new EmployeeVacationInfo();
				// ��ٵĿ�ʼ���ںͽ�������Ϊͬһ��
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
							// �Ǹ�����
							evi.setIsWelfareHoliays("1");
						} else {
							// ������
							evi.setIsWelfareHoliays("0");
						}
					}
					EmployeeVacationInfo eviTemp = employeeService
							.queryVacationInfoByIdDate(evi);
					// ����ԭ����
					if (eviTemp != null)
						employeeService.delVacInfo(eviTemp);
					try {
						employeeService.addVacationInfo(evi);
						if("����ͨ��".equals(str[10])){
							// ��ѯ�����׼��ʱ��Ϣ
							HoursWorkStandardInfo hwsstdi4detail = hoursWorkService.queryStdInfoByDate(GmsUtils.gmsFormToDate(str[5]));
							if(hwsstdi4detail!=null&&hwsstdi4detail.getStandardHours()!=null){
								Float gapTemp = hwsstdi4detail.getStandardHours()-Float.parseFloat(evi.getTotalNumberHours());
								EmployeeHoursDetailInfo ehdi = new EmployeeHoursDetailInfo();
								ehdi.setEmployeeId(evi.getEmployeeId());
								ehdi.setStandardDate(evi.getPsaDate());
								ehdi.setPsaLeaveHours(Float.parseFloat(evi.getTotalNumberHours()));
								ehdi.setPsaLeaveHoursType(evi.getLeaveTypeName());
								ehdi.setCurrentGapHours(gapTemp);
								//���¹�ʱ������и��˸���GAP��ʱ
								hoursWorkService.modifyGapByPsa(ehdi);
							}
						}
						
					} catch (SQLException e) {
						log.error(str[0] + ":" + e.getMessage());
						continue;
					}

				}
				// ��ٿ����
				else if (GmsUtils.gmsFormToDate(str[6]).after(
						GmsUtils.gmsFormToDate(str[5]))) {
					// ��ÿ�ʼ�������������֮ǰ���������ڣ�������ʼ�ͽ������ڣ�
					List<Date> dates = GmsUtils.getBetweenDates(GmsUtils
							.gmsFormToDate(str[5]), GmsUtils
							.gmsFormToDate(str[6]));
					// �ų��ǹ�������
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
								// �Ǹ�����
								evi.setIsWelfareHoliays("1");
							} else {
								// ������
								evi.setIsWelfareHoliays("0");
							}
						}
						try {
							EmployeeVacationInfo eviTemp = employeeService
									.queryVacationInfoByIdDate(evi);
							// ����ԭ����
							if (eviTemp != null)
								employeeService.delVacInfo(eviTemp);
							employeeService.addVacationInfo(evi);
							
							if("����ͨ��".equals(str[10])){
								// ��ѯ�����׼��ʱ��Ϣ
								HoursWorkStandardInfo hwsstdi4detail = hoursWorkService.queryStdInfoByDate(GmsUtils.gmsFormToDate(str[5]));
								if(hwsstdi4detail!=null&&hwsstdi4detail.getStandardHours()!=null){
									Float gapTemp = hwsstdi4detail.getStandardHours()-Float.parseFloat(evi.getTotalNumberHours());
									EmployeeHoursDetailInfo ehdi = new EmployeeHoursDetailInfo();
									ehdi.setEmployeeId(evi.getEmployeeId());
									ehdi.setStandardDate(evi.getPsaDate());
									ehdi.setPsaLeaveHours(Float.parseFloat(evi.getTotalNumberHours()));
									ehdi.setPsaLeaveHoursType(evi.getLeaveTypeName());
									ehdi.setCurrentGapHours(gapTemp);
									//���¹�ʱ������и��˸���GAP��ʱ
									hoursWorkService.modifyGapByPsa(ehdi);
								}
							}
						} catch (SQLException e) {
							log.error(str[0] + ":" + e.getMessage());
							continue;
						}
					}
				} else {
					// ���ݴ��󣬽��������ڿ�ʼʱ��֮ǰ����������������Ϣ
					continue;
				}
			}
		}
		// ��׼��ʱ����
		else if ("hws".equals(uploadForm.getFlag())) {
			List<String[]> lt = new ArrayList<String[]>();
			try {
				lt = GmsUtils.readExcel(1, is4imp, excelVersion);// �ӵ�2�п�ʼ��ȡ��0Ϊ��һ�У�
			} catch (OutOfMemoryError e) {
				request.setAttribute("err_msg", "���������������������");
				return mapping.findForward("success");
			}
			log.info("���ε����׼��ʱ��Ϣ������" + lt.size() + "��");
			// ��ѯ����Ա��
			List<EmployeeBasicInfo> ebiList = employeeService.queryAllGapChEmp();
			int count4ig = 0;
			for (int i = 0; i < lt.size(); i++) {
				String[] str = (String[]) lt.get(i);
				HoursWorkStandardInfo hwsi = new HoursWorkStandardInfo();
				if (str[0] == null || str[0].trim().length() < 8)
					continue;
				hwsi.setStandardDate(GmsUtils.gmsFormToDate(str[0]));
				hwsi.setStandardWeek(str[1]);
				if ("������".equals(str[2])) {
					hwsi.setStandardDateType("0");
				} else if ("˫����".equals(str[2])) {
					hwsi.setStandardDateType("1");
				} else if ("�ڼ���".equals(str[2])) {
					hwsi.setStandardDateType("2");
				}
				hwsi.setStandardHours((int) Double.parseDouble(str[3]));
				try {

					// 5����֤�Ƿ�Ϊ������ݣ��������ݣ��������¹�ʱ�������
					if (count4ig < 5) {
						List<Date> allDate = hoursWorkService.queryAllDates();
						if (allDate.contains(hwsi.getStandardDate()))
							count4ig++;
						// ���¹�ʱ�������Ϣ(ֻ������ݵ�����������¹�ʱ�����)
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
		// �ͻ�ȷ�Ϲ�ʱ����
		else if ("cci".equals(uploadForm.getFlag())) {
			List<String[]> lt = new ArrayList<String[]>();
			try {
				lt = GmsUtils.readExcel(1, is4imp, excelVersion);// �ӵ�2�п�ʼ��ȡ��0Ϊ��һ�У�
			} catch (OutOfMemoryError e) {
				request.setAttribute("err_msg", "���������������������");
				return mapping.findForward("toCstmHw");
			}
			log.info("���ε���ͻ�ȷ�Ϲ�ʱ��Ϣ������" + lt.size() + "��");
			// ����ʱ�����쳣��Ա������
			StringBuffer errorNames = new StringBuffer();
			// ���ε���ɹ�����������
			int impCount = 0;
			// ȷ�Ϲ�ʱ����ʱ��ɾ�����(������¼��ִ�й�ɾ������������(ͬһ��ͬһ��)�����Ȿ�ε�����
			// ͬһ��ͬһ���ж������ݶ�������ɾ�������(����������ݶ����ǰ��յ��������ɾ��))
			StringBuffer delFlag = new StringBuffer();
			// ���¹�ʱ������� ���ֶ�������¼���ε�����(ͬһ��ͬһ����ֶ�����¼�����)��update��������
			// ����������ݰѸ�update������Ϣ��update�������
			StringBuffer detailUpdateFlag = new StringBuffer();
			StringBuffer cusAddFlag = new StringBuffer();
			for (int i = 0; i < lt.size(); i++) {
				String[] str = (String[]) lt.get(i);
				try {
				// ��ȡԱ��������Ϣ����Ա����ӦemployeeId
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
				
				// ��ε���ʱ�������ԭ��ͬһ��ͬһ��ļ�¼���ٲ���
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

				// ���¹�ʱ�������Ϣ
				EmployeeHoursDetailInfo ehdi4query = new EmployeeHoursDetailInfo();
				ehdi4query.setEmployeeId(temp.get(0).getEmployeeId().trim());
				ehdi4query.setStandardDate(cvhi.getWorkDate());
				EmployeeHoursDetailInfo ehdi4check = hoursWorkService
						.queryHoursDetail(ehdi4query);
				if (ehdi4check != null) {
					// ��ѯ�����׼��ʱ��Ϣ
					HoursWorkStandardInfo hwsstdi4detail = hoursWorkService
							.queryStdInfoByDate(GmsUtils.gmsFormToDate(str[13]));
					EmployeeHoursDetailInfo ehdi4update = new EmployeeHoursDetailInfo();
					ehdi4update.setEmployeeId(ehdi4check.getEmployeeId());
					ehdi4update.setStandardDate(ehdi4check.getStandardDate());
					// �ͻ�ȷ��������ʱ
					ehdi4update.setCustomerVerifyHours(Float.parseFloat(str[14]));
					// �Ӱ๤ʱ
					ehdi4update.setCustomerVerifyOvertimeHours(Float.parseFloat(str[15]));
					// ���±�׼��ʱ
					ehdi4update.setStandardHours(hwsstdi4detail.getStandardHours());
					// ������������
					ehdi4update.setStandardDateType(hwsstdi4detail.getStandardDateType());
					// �������GAP��ʱ(PSA�����Ϣ��δ�������)
					float gapHOurs = hwsstdi4detail.getStandardHours()- Float.parseFloat(str[14]);

					// ��ѯ����PSA�����Ϣ
					EmployeeVacationInfo eviTemp = new EmployeeVacationInfo();
					eviTemp.setEmployeeId(temp.get(0).getEmployeeId());
					eviTemp.setPsaDate(GmsUtils.gmsFormToDate(str[13]));
					EmployeeVacationInfo evi4detail = employeeService
							.queryVacationInfoByIdDate(eviTemp);

					if (evi4detail != null&&"����ͨ��".equals(evi4detail.getReceiptStatus())) {
						if (evi4detail.getTotalNumberHours() != null) {
							// PSA��ٹ�ʱ
							ehdi4update.setPsaLeaveHours(Float
									.parseFloat(evi4detail
											.getTotalNumberHours()));
							// ����PSA�����Ϣ ȷ������GAP��ʱ
							gapHOurs = gapHOurs
									- Float.parseFloat(evi4detail
											.getTotalNumberHours());
						}
						// PSA�������
						ehdi4update.setPsaLeaveHoursType(evi4detail
								.getLeaveTypeName());
					}
					ehdi4update.setCurrentGapHours(gapHOurs);

					if (Float.parseFloat(str[15]) != 0f) {
						if ("0".equals(hwsstdi4detail.getStandardDateType())) {
							// ��������ɲ�����ʱ(������)
							ehdi4update.setCurrentSubsidiesHours(Float
									.parseFloat(str[15]));
						} else if ("1".equals(hwsstdi4detail
								.getStandardDateType())) {
							// ��������ɵ��ݹ�ʱ(˫����)
							if (Float.parseFloat(str[15]) > 8) {
								// ˫���ռӰ೬��8Сʱ �������ּ���ɲ���
								ehdi4update.setCurrentAhughHours(8f);
								ehdi4update.setCurrentSubsidiesHours(Float
										.parseFloat(str[15]) - 8f);
							} else {
								ehdi4update.setCurrentAhughHours(Float
										.parseFloat(str[15]));
							}
						} else if ("2".equals(hwsstdi4detail
								.getStandardDateType())) {
							// ��������ɵ��ݹ�ʱ(�ڼ��� 3��)
							if (Float.parseFloat(str[15]) > 8) {
								// �ڼ��ռӰ೬��8Сʱ 8Сʱ������3�� �������ּ���ɲ���(��3��)
								ehdi4update.setCurrentAhughHours(24f);
								ehdi4update.setCurrentSubsidiesHours(Float
										.parseFloat(str[15]) - 8f);
							} else {
								ehdi4update.setCurrentAhughHours(Float
										.parseFloat(str[15]) * 3);
							}
						}
					}
					// ��Ŀ����
					ehdi4update.setProjectName(cvhi.getProjectName());
					// ��������
					ehdi4update.setWorkPlace(cvhi.getWorkPlace());
					// ����ƽ̨
					ehdi4update.setTechnologyPlatform(cvhi.getTechnologyPlatform());
					// �����ȼ�
					ehdi4update.setTechnologyGrade(cvhi.getTechnologyGrade());
					// �ڳ�����
					ehdi4update.setPresenceOfType(cvhi.getPresenceOfType());
					//�ͻ�������ı���˾Ա������
					ehdi4update.setEmpName4Cus(cvhi.getEmployeeName());
					//��Դ����
					ehdi4update.setResourcesType(cvhi.getResourcesType());
					//��Ŀ���
					ehdi4update.setProjectId(cvhi.getProjectId());
					//��Ŀ��������
					ehdi4update.setProjectOfDepartment(cvhi.getProjectOfDepartment());
					//��Ʒ����
					ehdi4update.setProductName(cvhi.getProductName());
					//�����Ʒ�����Ŷ�
					ehdi4update.setServiceProductsAttributiveTeam(cvhi.getServiceProductsAttributiveTeam());
					//�����Ʒ��������
					ehdi4update.setServiceProductsDepartment(cvhi.getServiceProductsDepartment());
					//��Ӧ��
					ehdi4update.setProviders(cvhi.getProviders());
					//������ʱ
					ehdi4update.setNormalWorkingHours(Float.parseFloat(str[14]));
					// �Ӱ๤ʱ
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

				// ���¹�ʱͳ�Ʊ���Ϣ
				String staFlag = request.getParameter("staFlag");
				// �����հ�ͻ�ȷ�Ϲ�ʱ������ʱ �Ÿ��¹�ʱͳ�Ʊ���Ϣ
				if ("sta".equals(staFlag)) {
					HoursWorkStandardInfo hwstdi = hoursWorkService
							.queryStdInfoByDate(GmsUtils.gmsFormToDate(str[13]));
					if (hwstdi == null)
						continue;
					HoursWorkStatisticsInfo tempHwsi = hoursWorkService
							.queryHoursStatistics(temp.get(0).getEmployeeId());
					if (tempHwsi != null) {
						// �������¹�ʱͳ��ʵ��
						HoursWorkStatisticsInfo hwsii = new HoursWorkStatisticsInfo();
						hwsii.setEmployeeId(tempHwsi.getEmployeeId());
						// �ͻ�ȷ���ܹ�ʱ
						float csth = tempHwsi.getCustomerSureTotalHours();
						csth += Float.parseFloat(str[14]);
						hwsii.setCustomerSureTotalHours(csth);
						// ��׼�ܹ�ʱ
						float sth = tempHwsi.getStandardTotalHours();
						sth += hwstdi.getStandardHours();
						hwsii.setStandardTotalHours(sth);
						// PSA����ܹ�ʱ
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
						// �Ӱ�ɵ����ܹ�ʱ
						float woath = tempHwsi.getWorkOtimeAhughTotalHours();
						// �Ӱ�ɲ����ܹ�ʱ
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
						// ʣ�൹���ܹ�ʱ
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
						// ��ֹͳ������
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
			log.warn("����ɹ��������ļ���"+filename+",���ε���ͻ�ȷ�Ϲ�ʱ��Ϣ�ɹ�������" + impCount);
			if (errorNames.toString().trim().length() > 0) {
				request.setAttribute("errorNames", errorNames);
				log.warn(filename+",���ε���ͻ�ȷ�Ϲ�ʱ�쳣����Ա������" + errorNames);
			}
			is4imp.close();
			// ɾ���ϴ�excel�ļ�
			File f = new File(path + "\\" + filename);
			f.delete();
			errorNames=null;
			delFlag =null;
			detailUpdateFlag =null;
			cusAddFlag = null;
			request.setAttribute("impCount", impCount);
			request.setAttribute("imp_msg", "����ɹ���");
			return mapping.findForward("toCstmHw");
		}

		// GAP��ʱ���� ͬʱupdate
		else if ("whg".equals(uploadForm.getFlag())) {
			List<String[]> lt = new ArrayList<String[]>();
			try {
				lt = GmsUtils.readExcel(1, is4imp, excelVersion);// �ӵ�2�п�ʼ��ȡ��0Ϊ��һ�У�
			} catch (OutOfMemoryError e) {
				request.setAttribute("err_msg", "���������������������");
				return mapping.findForward("success");
			}
			log.info("���ε���GAP��ʱ��Ϣ������" + lt.size() + "��");
			String[] gapType2Update = { "0", "3", "5", "6", "7", "8" };// �ᷢ�����������Ĳ�������(��������
																		// 0�ٵ�����1�ֳ�����2�ع�˾����3�볡4��˾����5���쳣6����7��������8�ظ���ʱ׷�ӵ���)
			String[] gapTypeAll = { "0", "1", "2", "3", "4", "5", "6", "7", "8" };// ���в�������
			for (int i = 0; i < lt.size(); i++) {
				String[] str = (String[]) lt.get(i);
				String gapType = "";
				// ���š����ڡ��������� ����Ϊ��
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
						.queryHoursDetail(ehdi);// �����������Ϣ
				if (ehditem == null)
					continue;
				if (gapType.length() == 1) {// ��֤���������Ƿ����Ҫ��
					// ����ʱ��ֻҪ��д�˷��ϱ�׼��GAP���ͣ�GAP��ʱ�Զ�����
					ehdi.setCurrentGapHours(0f);
					// ȡ���ݿ��е�GAP��ʱ������ȡ�����GAP��ʱ�����⵼���GAP��ʱ���޸Ķ�Ӱ��ʣ�൹�ݹ�ʱ���µ���ȷ��
					ehdi.setWriteOffHours(ehditem.getCurrentGapHours());
				} else {
					// ��д�˲����ϱ�׼��GAP���ͣ�GAP��ʱ����
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

				// ��������Ϊָ������ �����Ա��ʣ�൹�ݹ�ʱ
				for (String tem : gapType2Update) {
					if (tem.equals(gapType)) {
						HoursWorkStatisticsInfo hwsi = new HoursWorkStatisticsInfo();
						hwsi.setEmployeeId(str[0]);
						// ȡ���ݿ��е�GAP��ʱ������ȡ�����GAP��ʱ�����⵼���GAP��ʱ���޸Ķ�Ӱ��ʣ�൹�ݹ�ʱ���µ���ȷ��
						hwsi.setHours2udtLast(ehditem.getCurrentGapHours());
						hoursWorkService.modifyLastHours(hwsi);
						break;
					}
				}
			}
		}
		is4imp.close();
		// ɾ���ϴ�excel�ļ�
		File f = new File(path + "\\" + filename);
		f.delete();
		request.setAttribute("imp_msg", "������ɣ�");
		return mapping.findForward("success");
	}
}
