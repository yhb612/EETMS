package com.iss.gms.web.action.cost;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.LazyValidatorForm;

import com.iss.gms.entity.CostAndIncomeDetailInfo;
import com.iss.gms.entity.CostAndIncomeInfo;
import com.iss.gms.entity.CustomerQuoteInfo;
import com.iss.gms.entity.CustomerVerifyHoursInfo;
import com.iss.gms.entity.EmployeeBasicInfo;
import com.iss.gms.entity.EmployeeRelegationInfo;
import com.iss.gms.entity.HoursWorkStandardInfo;
import com.iss.gms.entity.ProjectInfo;
import com.iss.gms.service.HoursWorkService;
import com.iss.gms.service.IEmployeeService;
import com.iss.gms.service.IProjectService;
import com.iss.gms.utils.GmsUtils;

/**
 * @author Administrator ����ɱ�Ԥ��
 */
public class Prediction4CostAction extends DispatchAction {

	private String expenses;
	private HoursWorkService hoursWorkService;
	private IProjectService projectService;
	private IEmployeeService employeeService;
	private String china_bank;

	public void setChina_bank(String chinaBank) {
		china_bank = chinaBank;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public void setHoursWorkService(HoursWorkService hoursWorkService) {
		this.hoursWorkService = hoursWorkService;
	}

	public void setExpenses(String expenses) {
		this.expenses = expenses;
	}

	public ActionForward initPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LazyValidatorForm lzform = (LazyValidatorForm)form;
		lzform.set("preYear", "");
		lzform.set("preMonth", "");
		return mapping.findForward("success");
	}

	public ActionForward predict(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		LazyValidatorForm lzform = (LazyValidatorForm) form;
		String year = (String) lzform.get("preYear");
		String month = (String) lzform.get("preMonth");
		try {
			// �������»�ȡ���µĵ�һ������һ��
			Date lastDateOfMonth = GmsUtils.getLastDate(Integer.parseInt(year),
					Integer.parseInt(month));
			Date firstDateOfMonth = GmsUtils.gmsFormToDate(year + "-" + month
					+ "-1");
			// ��ѯ�����ڵ���Ŀ
			Map<String, Date> fldates = new HashMap<String, Date>();
			fldates.put("lastDateOfMonth", lastDateOfMonth);
			fldates.put("firstDateOfMonth", firstDateOfMonth);
			List<ProjectInfo> proList = projectService.queryProByMonth(fldates);
			// ��ѯ�ͻ�����
			CustomerQuoteInfo cqi = employeeService
					.queryCstmQuoteInfo(china_bank);

			List<CostAndIncomeInfo> ciList = new ArrayList<CostAndIncomeInfo>();
			// ���� ������ɱ�Ԥ�⡱ҳ����ʾ����ʵ��
			if (proList != null && proList.size() > 0) {
				CostAndIncomeInfo caiiAll = new CostAndIncomeInfo();
				for (ProjectInfo tem : proList) {
					CostAndIncomeInfo caii = new CostAndIncomeInfo();
					// ������Ŀ��Ϣ
					caii.setYearAndMonth(year + "-" + month);
					caii.setProId(tem.getProId());
					caii.setProjectName(tem.getProName());
					caii.setProjectManager(tem.getProjectManager());
					caii.setDepartment(tem.getImplementDepartment());

					// ���и���Ŀ�Ʒ�Ա��
					List<EmployeeRelegationInfo> eriList = employeeService
							.queryEmpsByProName(tem.getProName());
					// �Ʒ�����
					int chargingEmpCount = 0;

					// Ԥ������
					float preIncome = 0f;
					// Ԥ�Ƴɱ�
					float preCost = 0f;
					

					// �Ʒ�����
					int chargingDays = 22;
					HoursWorkStandardInfo hwsi4queryAll = new HoursWorkStandardInfo();
					hwsi4queryAll.setStartDate(firstDateOfMonth);
					hwsi4queryAll.setEndDate(lastDateOfMonth);
					HoursWorkStandardInfo hwsiResultAll = hoursWorkService.queryCountOfEveryType(hwsi4queryAll);
					if(hwsiResultAll!=null&&hwsiResultAll.getWorkDays()>0)
						chargingDays=hwsiResultAll.getWorkDays();
					// ������Ŀ��Ч�Ʒ�����(��Ŀ��ʼ/���������ڱ���)
					if (tem.getBeginDate().after(firstDateOfMonth)
							|| tem.getBeginDate().equals(firstDateOfMonth)) {// ��ʼ�����ڱ���
						HoursWorkStandardInfo hwsi4query = new HoursWorkStandardInfo();
						hwsi4query.setStartDate(tem.getBeginDate());
						if (tem.getEndDate().before(lastDateOfMonth)
								|| tem.getEndDate().equals(lastDateOfMonth)) {// ���������ڱ���
							hwsi4query.setEndDate(tem.getEndDate());
						} else {
							hwsi4query.setEndDate(lastDateOfMonth);
						}
						HoursWorkStandardInfo hwsiResult = hoursWorkService
								.queryCountOfEveryType(hwsi4query);
						if (hwsiResult != null)
							chargingDays = hwsiResult.getWorkDays();

					} else if (tem.getEndDate().before(lastDateOfMonth)) {// ��ʼ���ڲ��ڱ���,���������ڱ���
						HoursWorkStandardInfo hwsi4query = new HoursWorkStandardInfo();
						hwsi4query.setStartDate(firstDateOfMonth);
						hwsi4query.setEndDate(tem.getEndDate());
						HoursWorkStandardInfo hwsiResult = hoursWorkService
								.queryCountOfEveryType(hwsi4query);
						if (hwsiResult != null)
							chargingDays = hwsiResult.getWorkDays();
					}
					//Ա����ְ�����ڱ��¡�Ա���ڸ���Ŀ���볡�Ʒ�ʱ���ڱ���
					if (eriList != null && eriList.size() > 0) {
						for (EmployeeRelegationInfo eri : eriList) {
							//�ж�Ա����ְ�����Ƿ��ڱ���	
//							List<EmployeeBasicInfo> ebiListTm = employeeService.queryByName(eri.getEmployeeId());
//							if (ebiListTm != null && ebiListTm.size() == 1) {
//								if (ebiListTm.get(0).getEntryDate() != null) {
//									if (ebiListTm.get(0).getEntryDate().after(
//											firstDateOfMonth)
//											&& ebiListTm.get(0).getEntryDate()
//													.before(lastDateOfMonth)) {
//										HoursWorkStandardInfo hwsi4query = new HoursWorkStandardInfo();
//										hwsi4query.setStartDate(ebiListTm
//												.get(0).getEntryDate());
//										if (tem.getEndDate().before(
//												lastDateOfMonth)) {
//											hwsi4query.setEndDate(tem
//													.getEndDate());
//										} else {
//											hwsi4query
//													.setEndDate(lastDateOfMonth);
//										}
//										HoursWorkStandardInfo hwsiResult = hoursWorkService
//												.queryCountOfEveryType(hwsi4query);
//										if (hwsiResult != null)
//											chargingDays = hwsiResult
//													.getWorkDays();
//									}
//								}
//							}
							//�ж��볡�Ʒ������Ƿ��ڱ���
							if(eri.getAdmittancebillingDate()!=null&&GmsUtils.compareDateInner(eri.getAdmittancebillingDate(), firstDateOfMonth, lastDateOfMonth)){
								HoursWorkStandardInfo hwsi4query = new HoursWorkStandardInfo();
								hwsi4query.setStartDate(eri.getAdmittancebillingDate());
								hwsi4query.setEndDate(lastDateOfMonth);
								HoursWorkStandardInfo hwsiResult = hoursWorkService
								.queryCountOfEveryType(hwsi4query);
								if (hwsiResult != null) chargingDays = hwsiResult.getWorkDays();
							}else if(eri.getAdmittancebillingDate()!=null&&eri.getAdmittancebillingDate().after(lastDateOfMonth)) continue;
							
							//�ж�Ԥ��/ʵ���볡�����Ƿ��ڱ���
							if(eri.getOutscenerealityDate()!=null){//ʵ���볡���ڴ���
								if(GmsUtils.compareDateInner(eri.getOutscenerealityDate(), firstDateOfMonth, lastDateOfMonth)){
									HoursWorkStandardInfo hwsi4query = new HoursWorkStandardInfo();
									hwsi4query.setStartDate(eri.getOutscenerealityDate());
									hwsi4query.setEndDate(lastDateOfMonth);
									HoursWorkStandardInfo hwsiResult = hoursWorkService.queryCountOfEveryType(hwsi4query);
									if (hwsiResult != null) chargingDays = chargingDays - hwsiResult.getWorkDays();
								}else if(eri.getOutscenerealityDate().before(firstDateOfMonth)) continue;
							}else{//ʵ�����ڲ����ڣ����ж�Ԥ������
								if(eri.getOutsceneestimateDate()!=null&&GmsUtils.compareDateInner(eri.getOutsceneestimateDate(),
									firstDateOfMonth, lastDateOfMonth)){
									HoursWorkStandardInfo hwsi4query = new HoursWorkStandardInfo();
									hwsi4query.setStartDate(eri.getOutsceneestimateDate());
									hwsi4query.setEndDate(lastDateOfMonth);
									HoursWorkStandardInfo hwsiResult = hoursWorkService
									.queryCountOfEveryType(hwsi4query);
									if (hwsiResult != null) chargingDays = chargingDays - hwsiResult.getWorkDays();
								}else if(eri.getOutsceneestimateDate()!=null&&eri.getOutsceneestimateDate().before(firstDateOfMonth)) continue;
							}
							

							CustomerVerifyHoursInfo cvhi = hoursWorkService
									.queryGradeAndTec(eri.getEmployeeId());
							if (cvhi != null) {
								// ��������()
								if (cqi == null)
									cqi = new CustomerQuoteInfo();
								if ("����".equals(cvhi.getTechnologyPlatform())) {
									if ("�߼�".equals(cvhi.getTechnologyGrade())) {
										preIncome += cqi.getPrice4ST()
												* chargingDays;
									} else if ("�м�".equals(cvhi
											.getTechnologyGrade())) {
										preIncome += cqi.getPrice4MT()
												* chargingDays;
									} else if ("����".equals(cvhi
											.getTechnologyGrade())) {
										preIncome += cqi.getPrice4JT()
												* chargingDays;
									}
								} else {
									if ("�߼�".equals(cvhi.getTechnologyGrade())) {
										preIncome += cqi.getPrice4SD()
												* chargingDays;
									} else if ("�м�".equals(cvhi
											.getTechnologyGrade())) {
										preIncome += cqi.getPrice4MD()
												* chargingDays;
									} else if ("����".equals(cvhi
											.getTechnologyGrade())) {
										preIncome += cqi.getPrice4JD()
												* chargingDays;
									}
								}
							}
							// ����ɱ�
							List<EmployeeBasicInfo> ems = employeeService.queryByName(eri.getEmployeeId());
							if (ems.size() == 1) {
								EmployeeBasicInfo ebi = ems.get(0);
								preCost += ebi.getContractWages()
										+ ebi.getSocietyindemnification();
							}
							chargingEmpCount++;
						}
					}
					// �Ʒ�����
					caii.setBillingNumbers(chargingEmpCount);
					caii.setPredictionCost(preCost);
					caii.setPredictionIncome(preIncome);
					// ����Ԥ�Ʊ���
					float preExpenses = 0f;
					if (expenses != null && expenses.trim().length() > 0)
						preExpenses = chargingEmpCount*Float.parseFloat(expenses);
					caii.setPredictionExpenses(preExpenses);
					ciList.add(caii);
					//�ϼ�
					caiiAll.setBillingNumbers(caiiAll.getBillingNumbers()+caii.getBillingNumbers());
					caiiAll.setPredictionCost(caiiAll.getPredictionCost()+caii.getPredictionCost());
					caiiAll.setPredictionIncome(caiiAll.getPredictionIncome()+caii.getPredictionIncome());
					caiiAll.setPredictionExpenses(caiiAll.getPredictionExpenses()+caii.getPredictionExpenses());
				}
				request.setAttribute("caiiAll", caiiAll);
			}
			request.setAttribute("ciList", ciList);
			if(ciList!=null) request.setAttribute("resultCount", ciList.size());
			return mapping.findForward("success");
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("error");
		}
	}

	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LazyValidatorForm lzform = (LazyValidatorForm) form;
		String year = (String) lzform.get("preYear");
		String month = (String) lzform.get("preMonth");
		// �������»�ȡ���µĵ�һ������һ��
		try {
			Date lastDateOfMonth = GmsUtils.getLastDate(Integer.parseInt(year),
					Integer.parseInt(month));
			Date firstDateOfMonth = GmsUtils.gmsFormToDate(year + "-" + month
					+ "-1");
			List<CostAndIncomeDetailInfo> detailList = new ArrayList<CostAndIncomeDetailInfo>();
			String proName;

			proName = GmsUtils.fix2utf(request.getParameter("proName"));
			// ��ѯ��Ŀ�Ʒ���Ա
			List<EmployeeRelegationInfo> eriList = employeeService
					.queryEmpsByProName(proName);
			// ��ѯ�ͻ�����
			CustomerQuoteInfo cqi = employeeService
					.queryCstmQuoteInfo(china_bank);

			if (eriList != null && eriList.size() > 0) {
				for (EmployeeRelegationInfo eri : eriList) {
					CostAndIncomeDetailInfo cadi = new CostAndIncomeDetailInfo();
					cadi.setEmpId(eri.getEmployeeId());
					cadi.setEmpName(eri.getEmployeeName());
					cadi.setDays("22");
					HoursWorkStandardInfo hwsi4queryAll = new HoursWorkStandardInfo();
					hwsi4queryAll.setStartDate(firstDateOfMonth);
					hwsi4queryAll.setEndDate(lastDateOfMonth);
					HoursWorkStandardInfo hwsiResultAll = hoursWorkService.queryCountOfEveryType(hwsi4queryAll);
					if(hwsiResultAll!=null&&hwsiResultAll.getWorkDays()>0)
						cadi.setDays(String.valueOf(hwsiResultAll.getWorkDays()));
					
					//�ж���ְ�����Ƿ��ڱ���
//					List<EmployeeBasicInfo> ebiListTm = employeeService.queryByName(eri.getEmployeeId());
//					if (ebiListTm != null && ebiListTm.size() == 1) {
//						if (ebiListTm.get(0).getEntryDate() != null) {
//							if (ebiListTm.get(0).getEntryDate().after(
//									firstDateOfMonth)
//									&& ebiListTm.get(0).getEntryDate().before(
//											lastDateOfMonth)) {
//								HoursWorkStandardInfo hwsi4query = new HoursWorkStandardInfo();
//								hwsi4query.setStartDate(ebiListTm.get(0)
//										.getEntryDate());
//								hwsi4query.setEndDate(lastDateOfMonth);
//								HoursWorkStandardInfo hwsiResult = hoursWorkService
//										.queryCountOfEveryType(hwsi4query);
//								if (hwsiResult != null)
//									cadi.setDays(String.valueOf(hwsiResult
//											.getWorkDays()));
//								;
//							}
//						}
//					}
					//�ж��볡�Ʒ������Ƿ��ڱ���
					if(eri.getAdmittancebillingDate()!=null&&GmsUtils.compareDateInner(eri.getAdmittancebillingDate(), firstDateOfMonth, lastDateOfMonth)){
						HoursWorkStandardInfo hwsi4query = new HoursWorkStandardInfo();
						hwsi4query.setStartDate(eri.getAdmittancebillingDate());
						hwsi4query.setEndDate(lastDateOfMonth);
						HoursWorkStandardInfo hwsiResult = hoursWorkService
						.queryCountOfEveryType(hwsi4query);
						if (hwsiResult != null) cadi.setDays(String.valueOf(hwsiResult.getWorkDays()));
					}else if(eri.getAdmittancebillingDate()!=null&&eri.getAdmittancebillingDate().after(lastDateOfMonth)) continue;//�Ʒ������ڱ���֮��

					//�ж�Ԥ��/ʵ���볡�����Ƿ��ڱ���
					if(eri.getOutscenerealityDate()!=null){//ʵ���볡���ڴ���
						if(GmsUtils.compareDateInner(eri.getOutscenerealityDate(), firstDateOfMonth, lastDateOfMonth)){
							HoursWorkStandardInfo hwsi4query = new HoursWorkStandardInfo();
							hwsi4query.setStartDate(eri.getOutscenerealityDate());
							hwsi4query.setEndDate(lastDateOfMonth);
							HoursWorkStandardInfo hwsiResult = hoursWorkService.queryCountOfEveryType(hwsi4query);
							if (hwsiResult != null)  cadi.setDays(String.valueOf(Integer.parseInt(cadi.getDays()) - hwsiResult.getWorkDays()));
						}else if(eri.getOutscenerealityDate().before(firstDateOfMonth)) continue;
					}else{//ʵ�����ڲ����ڣ����ж�Ԥ������
						if(eri.getOutsceneestimateDate()!=null&&GmsUtils.compareDateInner(eri.getOutsceneestimateDate(),
							firstDateOfMonth, lastDateOfMonth)){
							HoursWorkStandardInfo hwsi4query = new HoursWorkStandardInfo();
							hwsi4query.setStartDate(eri.getOutsceneestimateDate());
							hwsi4query.setEndDate(lastDateOfMonth);
							HoursWorkStandardInfo hwsiResult = hoursWorkService
							.queryCountOfEveryType(hwsi4query);
							if (hwsiResult != null)  cadi.setDays(String.valueOf(Integer.parseInt(cadi.getDays()) - hwsiResult.getWorkDays()));
						}else if(eri.getOutsceneestimateDate()!=null&&eri.getOutsceneestimateDate().before(firstDateOfMonth)) continue;
					}
					
					CustomerVerifyHoursInfo cvhi = hoursWorkService
							.queryGradeAndTec(eri.getEmployeeId());
					if (cvhi != null) {
						if ("����".equals(cvhi.getTechnologyPlatform())) {
							if ("�߼�".equals(cvhi.getTechnologyGrade())) {
								cadi.setCusGrade("�߼�");
								cadi.setIncome(cqi.getPrice4ST());
							} else if ("�м�".equals(cvhi.getTechnologyGrade())) {
								cadi.setCusGrade("�м�");
								cadi.setIncome(cqi.getPrice4MT());
							} else if ("����".equals(cvhi.getTechnologyGrade())) {
								cadi.setCusGrade("����");
								cadi.setIncome(cqi.getPrice4JT());
							}
						} else {
							if ("�߼�".equals(cvhi.getTechnologyGrade())) {
								cadi.setCusGrade("�߼�");
								cadi.setIncome(cqi.getPrice4SD());
							} else if ("�м�".equals(cvhi.getTechnologyGrade())) {
								cadi.setCusGrade("�м�");
								cadi.setIncome(cqi.getPrice4MD());
							} else if ("����".equals(cvhi.getTechnologyGrade())) {
								cadi.setCusGrade("����");
								cadi.setIncome(cqi.getPrice4JD());
							}
						}
					}
					detailList.add(cadi);
				}
			}
			request.setAttribute("detailList", detailList);
			return mapping.findForward("detail");
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("error");
		}
	}
}
