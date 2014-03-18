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
 * @author Administrator 收入成本预测
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
			// 根据年月获取该月的第一天和最后一天
			Date lastDateOfMonth = GmsUtils.getLastDate(Integer.parseInt(year),
					Integer.parseInt(month));
			Date firstDateOfMonth = GmsUtils.gmsFormToDate(year + "-" + month
					+ "-1");
			// 查询该月内的项目
			Map<String, Date> fldates = new HashMap<String, Date>();
			fldates.put("lastDateOfMonth", lastDateOfMonth);
			fldates.put("firstDateOfMonth", firstDateOfMonth);
			List<ProjectInfo> proList = projectService.queryProByMonth(fldates);
			// 查询客户报价
			CustomerQuoteInfo cqi = employeeService
					.queryCstmQuoteInfo(china_bank);

			List<CostAndIncomeInfo> ciList = new ArrayList<CostAndIncomeInfo>();
			// 构建 “收入成本预测”页面显示所用实体
			if (proList != null && proList.size() > 0) {
				CostAndIncomeInfo caiiAll = new CostAndIncomeInfo();
				for (ProjectInfo tem : proList) {
					CostAndIncomeInfo caii = new CostAndIncomeInfo();
					// 置入项目信息
					caii.setYearAndMonth(year + "-" + month);
					caii.setProId(tem.getProId());
					caii.setProjectName(tem.getProName());
					caii.setProjectManager(tem.getProjectManager());
					caii.setDepartment(tem.getImplementDepartment());

					// 所有该项目计费员工
					List<EmployeeRelegationInfo> eriList = employeeService
							.queryEmpsByProName(tem.getProName());
					// 计费人数
					int chargingEmpCount = 0;

					// 预计收入
					float preIncome = 0f;
					// 预计成本
					float preCost = 0f;
					

					// 计费天数
					int chargingDays = 22;
					HoursWorkStandardInfo hwsi4queryAll = new HoursWorkStandardInfo();
					hwsi4queryAll.setStartDate(firstDateOfMonth);
					hwsi4queryAll.setEndDate(lastDateOfMonth);
					HoursWorkStandardInfo hwsiResultAll = hoursWorkService.queryCountOfEveryType(hwsi4queryAll);
					if(hwsiResultAll!=null&&hwsiResultAll.getWorkDays()>0)
						chargingDays=hwsiResultAll.getWorkDays();
					// 计算项目有效计费天数(项目开始/结束日期在本月)
					if (tem.getBeginDate().after(firstDateOfMonth)
							|| tem.getBeginDate().equals(firstDateOfMonth)) {// 开始日期在本月
						HoursWorkStandardInfo hwsi4query = new HoursWorkStandardInfo();
						hwsi4query.setStartDate(tem.getBeginDate());
						if (tem.getEndDate().before(lastDateOfMonth)
								|| tem.getEndDate().equals(lastDateOfMonth)) {// 结束日期在本月
							hwsi4query.setEndDate(tem.getEndDate());
						} else {
							hwsi4query.setEndDate(lastDateOfMonth);
						}
						HoursWorkStandardInfo hwsiResult = hoursWorkService
								.queryCountOfEveryType(hwsi4query);
						if (hwsiResult != null)
							chargingDays = hwsiResult.getWorkDays();

					} else if (tem.getEndDate().before(lastDateOfMonth)) {// 开始日期不在本月,结束日期在本月
						HoursWorkStandardInfo hwsi4query = new HoursWorkStandardInfo();
						hwsi4query.setStartDate(firstDateOfMonth);
						hwsi4query.setEndDate(tem.getEndDate());
						HoursWorkStandardInfo hwsiResult = hoursWorkService
								.queryCountOfEveryType(hwsi4query);
						if (hwsiResult != null)
							chargingDays = hwsiResult.getWorkDays();
					}
					//员工入职日期在本月、员工在该项目的入场计费时间在本月
					if (eriList != null && eriList.size() > 0) {
						for (EmployeeRelegationInfo eri : eriList) {
							//判断员工入职日期是否在本月	
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
							//判断入场计费日期是否在本月
							if(eri.getAdmittancebillingDate()!=null&&GmsUtils.compareDateInner(eri.getAdmittancebillingDate(), firstDateOfMonth, lastDateOfMonth)){
								HoursWorkStandardInfo hwsi4query = new HoursWorkStandardInfo();
								hwsi4query.setStartDate(eri.getAdmittancebillingDate());
								hwsi4query.setEndDate(lastDateOfMonth);
								HoursWorkStandardInfo hwsiResult = hoursWorkService
								.queryCountOfEveryType(hwsi4query);
								if (hwsiResult != null) chargingDays = hwsiResult.getWorkDays();
							}else if(eri.getAdmittancebillingDate()!=null&&eri.getAdmittancebillingDate().after(lastDateOfMonth)) continue;
							
							//判断预计/实际离场日期是否在本月
							if(eri.getOutscenerealityDate()!=null){//实际离场日期存在
								if(GmsUtils.compareDateInner(eri.getOutscenerealityDate(), firstDateOfMonth, lastDateOfMonth)){
									HoursWorkStandardInfo hwsi4query = new HoursWorkStandardInfo();
									hwsi4query.setStartDate(eri.getOutscenerealityDate());
									hwsi4query.setEndDate(lastDateOfMonth);
									HoursWorkStandardInfo hwsiResult = hoursWorkService.queryCountOfEveryType(hwsi4query);
									if (hwsiResult != null) chargingDays = chargingDays - hwsiResult.getWorkDays();
								}else if(eri.getOutscenerealityDate().before(firstDateOfMonth)) continue;
							}else{//实际日期不存在，就判断预计日期
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
								// 计算收入()
								if (cqi == null)
									cqi = new CustomerQuoteInfo();
								if ("测试".equals(cvhi.getTechnologyPlatform())) {
									if ("高级".equals(cvhi.getTechnologyGrade())) {
										preIncome += cqi.getPrice4ST()
												* chargingDays;
									} else if ("中级".equals(cvhi
											.getTechnologyGrade())) {
										preIncome += cqi.getPrice4MT()
												* chargingDays;
									} else if ("初级".equals(cvhi
											.getTechnologyGrade())) {
										preIncome += cqi.getPrice4JT()
												* chargingDays;
									}
								} else {
									if ("高级".equals(cvhi.getTechnologyGrade())) {
										preIncome += cqi.getPrice4SD()
												* chargingDays;
									} else if ("中级".equals(cvhi
											.getTechnologyGrade())) {
										preIncome += cqi.getPrice4MD()
												* chargingDays;
									} else if ("初级".equals(cvhi
											.getTechnologyGrade())) {
										preIncome += cqi.getPrice4JD()
												* chargingDays;
									}
								}
							}
							// 计算成本
							List<EmployeeBasicInfo> ems = employeeService.queryByName(eri.getEmployeeId());
							if (ems.size() == 1) {
								EmployeeBasicInfo ebi = ems.get(0);
								preCost += ebi.getContractWages()
										+ ebi.getSocietyindemnification();
							}
							chargingEmpCount++;
						}
					}
					// 计费人数
					caii.setBillingNumbers(chargingEmpCount);
					caii.setPredictionCost(preCost);
					caii.setPredictionIncome(preIncome);
					// 计算预计报销
					float preExpenses = 0f;
					if (expenses != null && expenses.trim().length() > 0)
						preExpenses = chargingEmpCount*Float.parseFloat(expenses);
					caii.setPredictionExpenses(preExpenses);
					ciList.add(caii);
					//合计
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
		// 根据年月获取该月的第一天和最后一天
		try {
			Date lastDateOfMonth = GmsUtils.getLastDate(Integer.parseInt(year),
					Integer.parseInt(month));
			Date firstDateOfMonth = GmsUtils.gmsFormToDate(year + "-" + month
					+ "-1");
			List<CostAndIncomeDetailInfo> detailList = new ArrayList<CostAndIncomeDetailInfo>();
			String proName;

			proName = GmsUtils.fix2utf(request.getParameter("proName"));
			// 查询项目计费人员
			List<EmployeeRelegationInfo> eriList = employeeService
					.queryEmpsByProName(proName);
			// 查询客户报价
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
					
					//判断入职日期是否在本月
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
					//判断入场计费日期是否在本月
					if(eri.getAdmittancebillingDate()!=null&&GmsUtils.compareDateInner(eri.getAdmittancebillingDate(), firstDateOfMonth, lastDateOfMonth)){
						HoursWorkStandardInfo hwsi4query = new HoursWorkStandardInfo();
						hwsi4query.setStartDate(eri.getAdmittancebillingDate());
						hwsi4query.setEndDate(lastDateOfMonth);
						HoursWorkStandardInfo hwsiResult = hoursWorkService
						.queryCountOfEveryType(hwsi4query);
						if (hwsiResult != null) cadi.setDays(String.valueOf(hwsiResult.getWorkDays()));
					}else if(eri.getAdmittancebillingDate()!=null&&eri.getAdmittancebillingDate().after(lastDateOfMonth)) continue;//计费日期在本月之后

					//判断预计/实际离场日期是否在本月
					if(eri.getOutscenerealityDate()!=null){//实际离场日期存在
						if(GmsUtils.compareDateInner(eri.getOutscenerealityDate(), firstDateOfMonth, lastDateOfMonth)){
							HoursWorkStandardInfo hwsi4query = new HoursWorkStandardInfo();
							hwsi4query.setStartDate(eri.getOutscenerealityDate());
							hwsi4query.setEndDate(lastDateOfMonth);
							HoursWorkStandardInfo hwsiResult = hoursWorkService.queryCountOfEveryType(hwsi4query);
							if (hwsiResult != null)  cadi.setDays(String.valueOf(Integer.parseInt(cadi.getDays()) - hwsiResult.getWorkDays()));
						}else if(eri.getOutscenerealityDate().before(firstDateOfMonth)) continue;
					}else{//实际日期不存在，就判断预计日期
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
						if ("测试".equals(cvhi.getTechnologyPlatform())) {
							if ("高级".equals(cvhi.getTechnologyGrade())) {
								cadi.setCusGrade("高级");
								cadi.setIncome(cqi.getPrice4ST());
							} else if ("中级".equals(cvhi.getTechnologyGrade())) {
								cadi.setCusGrade("中级");
								cadi.setIncome(cqi.getPrice4MT());
							} else if ("初级".equals(cvhi.getTechnologyGrade())) {
								cadi.setCusGrade("初级");
								cadi.setIncome(cqi.getPrice4JT());
							}
						} else {
							if ("高级".equals(cvhi.getTechnologyGrade())) {
								cadi.setCusGrade("高级");
								cadi.setIncome(cqi.getPrice4SD());
							} else if ("中级".equals(cvhi.getTechnologyGrade())) {
								cadi.setCusGrade("中级");
								cadi.setIncome(cqi.getPrice4MD());
							} else if ("初级".equals(cvhi.getTechnologyGrade())) {
								cadi.setCusGrade("初级");
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
