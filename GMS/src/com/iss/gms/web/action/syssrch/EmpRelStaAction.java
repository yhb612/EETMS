package com.iss.gms.web.action.syssrch;

import java.sql.SQLException;
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

import com.iss.gms.entity.EmpRelStaInfo;
import com.iss.gms.entity.EmployeeBasicInfo;
import com.iss.gms.entity.EmployeeRelegationInfo;
import com.iss.gms.service.IEmployeeService;

public class EmpRelStaAction extends DispatchAction {
	private IEmployeeService employeeService;

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public ActionForward initPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LazyValidatorForm lzform = (LazyValidatorForm)form;
		lzform.set("relStaList", "");
		lzform.set("ersiAll", "");
		return mapping.findForward("success");
	}

	// 查询
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LazyValidatorForm lzForm = (LazyValidatorForm) form;
		String queryRelYear = (String) lzForm.get("queryRelYear");
		String queryRelMonth = (String) lzForm.get("queryRelMonth");
		String queryRelWeek = (String) lzForm.get("queryRelWeek");
		// 查询历史
		if (!queryRelYear.isEmpty() && !queryRelMonth.isEmpty()
				&& !queryRelWeek.isEmpty()) {
			Map<String, String> map4query = new HashMap<String, String>();
			map4query.put("yearAndMonth", queryRelYear + queryRelMonth);
			map4query.put("week", queryRelWeek);
			try {
				List<EmpRelStaInfo> staList = employeeService
						.queryRelStaHis(map4query);
				for(EmpRelStaInfo ersi:staList){
					String bl = ersi.getBusinessLine();
					if ("all".equals(bl)) {
						request.setAttribute("ersiAll", ersi);
						continue;
					}
					if ("0".equals(bl)) {
						ersi.setBusinessLineName("总体部");
					} else if ("1".equals(bl)) {
						ersi.setBusinessLineName("上海分行");
					} else if ("2".equals(bl)) {
						ersi.setBusinessLineName("信息中心");
					} else if ("3".equals(bl)) {
						ersi.setBusinessLineName("山东");
					} else if ("4".equals(bl)) {
						ersi.setBusinessLineName("电子渠道");
					} else if ("5".equals(bl)) {
						ersi.setBusinessLineName("国际结算");
					} else if ("6".equals(bl)) {
						ersi.setBusinessLineName("信息管理");
					} else if ("7".equals(bl)) {
						ersi.setBusinessLineName("投资代理");
					} else if ("8".equals(bl)) {
						ersi.setBusinessLineName("质量管理");
					} else if ("9".equals(bl)) {
						ersi.setBusinessLineName("核心业务");
					} else if ("10".equals(bl)) {
						ersi.setBusinessLineName("上海");
					} else if ("11".equals(bl)) {
						ersi.setBusinessLineName("深圳");
					} else if ("12".equals(bl)) {
						ersi.setBusinessLineName("成都");
					} else if ("13".equals(bl)) {
						ersi.setBusinessLineName("上海银商");
					}else{
						ersi.setBusinessLineName(bl);
					}
				}
				for(int i = 0;i<staList.size();i++){
					String bl = staList.get(i).getBusinessLine();
					if ("all".equals(bl)) {
						staList.remove(i);
						break;
					}
				}
				request.setAttribute("relStaList", staList);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (queryRelYear.isEmpty() && queryRelMonth.isEmpty()&& queryRelWeek.isEmpty()) {//查询当前
			String[] staStatus = { "2", "12", "8", "9", "4", "0", "13", "6","15", "16" };
			EmpRelStaInfo ersiAll = new EmpRelStaInfo();
			try {
				List<EmpRelStaInfo> lt = employeeService.queryEmpCountWithBl();
				for (EmpRelStaInfo ersi : lt) {
					String bl = ersi.getBusinessLine();
					EmpRelStaInfo aaa = employeeService.queryLastRelStaHisByBl(bl);
					Date tempDate=null;
					if(aaa!=null)
						tempDate= aaa.getCreateTime();
					Map<String, Object> map4queryRec = new HashMap<String, Object>();
					map4queryRec.put("updateTime", tempDate);
					map4queryRec.put("businessLine", bl);
					// 查询该业务线是否有变动记录
					List<EmpRelStaInfo> recList = employeeService
							.queryRecordsByBl(map4queryRec);
					if (recList != null && recList.size() > 0) {//有变动信息
						// 入职(测试)
						int count0t = 0;
						// 调入(测试)
						int count1t = 0;
						// 离职(测试)
						int count2t = 0;
						// 调出(测试)
						int count3t = 0;
						// 入职(开发)
						int count0d = 0;
						// 调入(开发)
						int count1d = 0;
						// 离职(开发)
						int count2d = 0;
						// 调出(开发)
						int count3d = 0;

						EmpRelStaInfo eee = employeeService.queryLastRelStaHisByBl(bl);
						for (EmpRelStaInfo rec : recList) {
							if ("0".equals(rec.getTechnology())) {// 测试
								if (rec.getOperate() != null) {
									if ("0".equals(rec.getOperate())) {// 入职
										count0t++;
										continue;
									} else if ("1".equals(rec.getOperate())) {// 调入
										count1t++;
										continue;
									} else if ("2".equals(rec.getOperate())) {// 离职
										count2t++;
										continue;
									} else if ("3".equals(rec.getOperate())) {// 调出
										count3t++;
										continue;
									}
								}
							} else {
								if (rec.getOperate() != null) {
									if ("0".equals(rec.getOperate())) {// 入职
										count0d++;
										continue;
									} else if ("1".equals(rec.getOperate())) {// 调入
										count1d++;
										continue;
									} else if ("2".equals(rec.getOperate())) {// 离职
										count2d++;
										continue;
									} else if ("3".equals(rec.getOperate())) {// 调出
										count3d++;
										continue;
									}
								}
							}
						}
						ersi.setEntryNum4t(count0t);
						ersi.setEntryNum4d(count0d);
						ersi.setCallInNum4t(count1t);
						ersi.setCallInNum4d(count1d);
						ersi.setResignationNum4t(count2t);
						ersi.setResignationNum4d(count2d);
						ersi.setCallOutNum4t(count3t);
						ersi.setCallOutNum4d(count3d);
						
						if(eee!=null){//生成过历史信息
							ersi.setEmpCountLastWeek4t(eee.getEmpCountEnd4t());
							ersi.setEmpCountLastWeek4d(eee.getEmpCountEnd4d());
							ersi.setEmpCountEnd4t(eee.getEmpCountEnd4t()+count0t+count1t-count2t-count3t);
							ersi.setEmpCountEnd4d(eee.getEmpCountEnd4d()+count0d+count1d-count2d-count3d);
						}else{//从未生成过历史信息
							List<EmployeeRelegationInfo> eris = employeeService.queryEmpByBl(bl);
							// 期初总人数
							int tCount = 0;
							int dCount = ersi.getEmpcount();
							for (EmployeeRelegationInfo eri : eris) {
								if (eri != null) {
									if ("测试".equals(eri.getSkill()) || "Test".equals(eri.getSkill())) {
										tCount++;
										dCount--;
									}
								}
							}
							ersi.setEmpCountLastWeek4t(tCount);
							ersi.setEmpCountLastWeek4d(dCount);
							ersi.setEmpCountEnd4t(tCount+count0t+count1t-count2t-count3t);
							ersi.setEmpCountEnd4d(dCount+count0d+count1d-count2d-count3d);
						}
					} else {//没有变动信息
						EmpRelStaInfo eee = employeeService.queryLastRelStaHisByBl(bl);
						if(eee!=null){//生成过
							ersi.setEmpCountLastWeek4t(eee.getEmpCountEnd4t());
							ersi.setEmpCountLastWeek4d(eee.getEmpCountEnd4d());
							ersi.setEmpCountEnd4t(eee.getEmpCountEnd4t());
							ersi.setEmpCountEnd4d(eee.getEmpCountEnd4d());
						}else{//从未生成过
							List<EmployeeRelegationInfo> eris = employeeService.queryEmpByBl(bl);
							// 期初总人数
							int tCount = 0;
							int dCount = ersi.getEmpcount();
							for (EmployeeRelegationInfo eri : eris) {
								if (eri != null) {
									if ("测试".equals(eri.getSkill()) || "Test".equals(eri.getSkill())) {
										tCount++;
										dCount--;
									}
								}
							}
							ersi.setEmpCountLastWeek4t(tCount);
							ersi.setEmpCountLastWeek4d(dCount);
							ersi.setEmpCountEnd4t(tCount);
							ersi.setEmpCountEnd4d(dCount);
						}

					}
					if ("0".equals(bl)) {
						ersi.setBusinessLineName("总体部");
					} else if ("1".equals(bl)) {
						ersi.setBusinessLineName("上海分行");
					} else if ("2".equals(bl)) {
						ersi.setBusinessLineName("信息中心");
					} else if ("3".equals(bl)) {
						ersi.setBusinessLineName("山东");
					} else if ("4".equals(bl)) {
						ersi.setBusinessLineName("电子渠道");
					} else if ("5".equals(bl)) {
						ersi.setBusinessLineName("国际结算");
					} else if ("6".equals(bl)) {
						ersi.setBusinessLineName("信息管理");
					} else if ("7".equals(bl)) {
						ersi.setBusinessLineName("投资代理");
					} else if ("8".equals(bl)) {
						ersi.setBusinessLineName("质量管理");
					} else if ("9".equals(bl)) {
						ersi.setBusinessLineName("核心业务");
					} else if ("10".equals(bl)) {
						ersi.setBusinessLineName("上海");
					} else if ("11".equals(bl)) {
						ersi.setBusinessLineName("深圳");
					} else if ("12".equals(bl)) {
						ersi.setBusinessLineName("成都");
					} else if ("13".equals(bl)) {
						ersi.setBusinessLineName("上海银商");
					} else{
						ersi.setBusinessLineName(bl);
						
					}

					// 各状态分别有多少人
					for (String status : staStatus) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("status", status);
						map.put("business", bl);
						List<EmployeeRelegationInfo> eris = employeeService
								.queryEmpsByBlAndStatus(map);
						int count2t = 0;
						int count2d = eris != null && eris.size() > 0 ? eris.size() : 0;
						for (EmployeeRelegationInfo eri : eris) {
							if (eri!= null) {
								if ("测试".equals(eri.getSkill()) || "Test".equals(eri.getSkill())) {
									count2t++;
									count2d--;
								}
							}
						}
						if (status.equals("2")) {
							ersi.setChargingNum4t(count2t);
							ersi.setChargingNum4d(count2d);
						} else if (status.equals("12")) {
							ersi.setSubcontractorNum4t(count2t);
							ersi.setSubcontractorNum4d(count2d);
						} else if (status.equals("8")) {
							ersi.setCusReservesNum4t(count2t);
							ersi.setCusReservesNum4d(count2d);
						} else if (status.equals("9")) {
							ersi.setComReservesNum4t(count2t);
							ersi.setComReservesNum4d(count2d);
						} else if (status.equals("4")) {
							ersi.setUnUsedNum4t(count2t);
							ersi.setUnUsedNum4d(count2d);
						} else if (status.equals("0")) {
							ersi.setVacNum4t(count2t);
							ersi.setVacNum4d(count2d);
						} else if (status.equals("13")) {
							ersi.setMgrNum4t(count2t);
							ersi.setMgrNum4d(count2d);
						} else if (status.equals("6")) {
							ersi.setLendingOutNum4t(count2t);
							ersi.setLendingOutNum4d(count2d);
						} else if (status.equals("15")) {
							ersi.setPreSalesNum4t(count2t);
							ersi.setPreSalesNum4d(count2d);
						} else if (status.equals("16")) {
							ersi.setTxNum4t(count2t);
							ersi.setTxNum4d(count2d);
						}
					}
					// 合计：
					// 期初人数
					ersiAll.setEmpCountLastWeek4d(ersiAll
							.getEmpCountLastWeek4d()
							+ ersi.getEmpCountLastWeek4d()
							+ ersi.getEmpCountLastWeek4t());
					// 入职人数
					ersiAll.setEntryNum4d(ersiAll.getEntryNum4d()
							+ ersi.getEntryNum4d() + ersi.getEntryNum4t());
					// 调入人数
					ersiAll.setCallInNum4d(ersiAll.getCallInNum4d()
							+ ersi.getCallInNum4d() + ersi.getCallInNum4t());
					// 离职人数
					ersiAll.setResignationNum4d(ersiAll.getResignationNum4d()
							+ ersi.getResignationNum4d()
							+ ersi.getResignationNum4t());
					// 调出人数
					ersiAll.setCallOutNum4d(ersiAll.getCallOutNum4d()
							+ ersi.getCallOutNum4d() + ersi.getCallOutNum4t());
					// 期末人数
					ersiAll
							.setEmpCountEnd4d(ersiAll.getEmpCountEnd4d()
									+ ersi.getEmpCountEnd4d()
									+ ersi.getEmpCountEnd4t());
					// 计费
					ersiAll
							.setChargingNum4d(ersiAll.getChargingNum4d()
									+ ersi.getChargingNum4d()
									+ ersi.getChargingNum4t());
					// 现场储备
					ersiAll.setCusReservesNum4d(ersiAll.getCusReservesNum4d()
							+ ersi.getCusReservesNum4d()
							+ ersi.getCusReservesNum4t());
					// 公司储备
					ersiAll.setComReservesNum4d(ersiAll.getComReservesNum4d()
							+ ersi.getComReservesNum4d()
							+ ersi.getComReservesNum4t());
					// 闲置
					ersiAll.setUnUsedNum4d(ersiAll.getUnUsedNum4d()
							+ ersi.getUnUsedNum4d() + ersi.getUnUsedNum4t());
					// 休假
					ersiAll.setVacNum4d(ersiAll.getVacNum4d()
							+ ersi.getVacNum4d() + ersi.getVacNum4t());
					// 管理
					ersiAll.setMgrNum4d(ersiAll.getMgrNum4d()
							+ ersi.getMgrNum4d() + ersi.getMgrNum4t());
					// 借出外部
					ersiAll.setLendingOutNum4d(ersiAll.getLendingOutNum4d()
							+ ersi.getLendingOutNum4d()
							+ ersi.getLendingOutNum4t());
					// 售前
					ersiAll
							.setPreSalesNum4d(ersiAll.getPreSalesNum4d()
									+ ersi.getPreSalesNum4d()
									+ ersi.getPreSalesNum4t());
					// 调休
					ersiAll.setTxNum4d(ersiAll.getTxNum4d() + ersi.getTxNum4d()
							+ ersi.getTxNum4t());
					// 下包商
					ersiAll.setSubcontractorNum4d(ersiAll
							.getSubcontractorNum4d()
							+ ersi.getSubcontractorNum4d()
							+ ersi.getSubcontractorNum4t());
				}
				request.setAttribute("relStaList", lt);
				request.setAttribute("ersiAll", ersiAll);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			lzForm.set("relStaList", "");
			lzForm.set("ersiAll", "");
		}
		return mapping.findForward("success");
	}

	public ActionForward creatHis(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LazyValidatorForm lzForm = (LazyValidatorForm) form;
		String HisYear = (String) lzForm.get("creatRelStaYear");
		String HisMonth = (String) lzForm.get("creatRelStaMonth");
		String HisWeek = (String) lzForm.get("creatRelStaWeek");
		if(HisYear.isEmpty() ||HisMonth.isEmpty()||HisWeek.isEmpty()){
			return initPage(mapping,form,request, response);
		}
		
		Map<String, String> map4query = new HashMap<String, String>();
		map4query.put("yearAndMonth", HisYear + HisMonth);
		map4query.put("week", HisWeek);
		try {
			List<EmpRelStaInfo> staList = employeeService
					.queryRelStaHis(map4query);
			if(staList!=null && staList.size()>0){
				//清除原数据
				employeeService.delRelStaHis(map4query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		String[] staStatus = { "2", "12", "8", "9", "4", "0", "13", "6", "15",
				"16" };
		EmpRelStaInfo ersiAll = new EmpRelStaInfo();
		try {
			List<EmpRelStaInfo> lt = employeeService.queryEmpCountWithBl();
			for (EmpRelStaInfo ersi : lt) {
				ersi.setCreateTime(new Date());
				ersi.setStaYearMonth(HisYear+HisMonth);
				ersi.setStaWeek(HisWeek);
				String bl = ersi.getBusinessLine();
				// 查询该业务线是否有变动记录
				EmpRelStaInfo aaa = employeeService.queryLastRelStaHisByBl(bl);
				Date tempDate=null;
				if(aaa!=null)
				tempDate = aaa.getCreateTime();
				Map<String, Object> map4queryRec = new HashMap<String, Object>();
				map4queryRec.put("updateTime", tempDate);
				map4queryRec.put("businessLine", bl);
				// 查询该业务线是否有变动记录
				List<EmpRelStaInfo> recList = employeeService
						.queryRecordsByBl(map4queryRec);
				if (recList != null && recList.size() > 0) {//有变动信息
					// 入职(测试)
					int count0t = 0;
					// 调入(测试)
					int count1t = 0;
					// 离职(测试)
					int count2t = 0;
					// 调出(测试)
					int count3t = 0;
					// 入职(开发)
					int count0d = 0;
					// 调入(开发)
					int count1d = 0;
					// 离职(开发)
					int count2d = 0;
					// 调出(开发)
					int count3d = 0;
					EmpRelStaInfo eee = employeeService.queryLastRelStaHisByBl(bl);
					for (EmpRelStaInfo rec : recList) {
						if ("0".equals(rec.getTechnology())) {// 测试
							if (rec.getOperate() != null) {
								if ("0".equals(rec.getOperate())) {// 入职
									count0t++;
									continue;
								} else if ("1".equals(rec.getOperate())) {// 调入
									count1t++;
									continue;
								} else if ("2".equals(rec.getOperate())) {// 离职
									count2t++;
									continue;
								} else if ("3".equals(rec.getOperate())) {// 调出
									count3t++;
									continue;
								}
							}
						} else {
							if (rec.getOperate() != null) {
								if ("0".equals(rec.getOperate())) {// 入职
									count0d++;
									continue;
								} else if ("1".equals(rec.getOperate())) {// 调入
									count1d++;
									continue;
								} else if ("2".equals(rec.getOperate())) {// 离职
									count2d++;
									continue;
								} else if ("3".equals(rec.getOperate())) {// 调出
									count3d++;
									continue;
								}
							}
						}
					}
					ersi.setEntryNum4t(count0t);
					ersi.setEntryNum4d(count0d);
					ersi.setCallInNum4t(count1t);
					ersi.setCallInNum4d(count1d);
					ersi.setResignationNum4t(count2t);
					ersi.setResignationNum4d(count2d);
					ersi.setCallOutNum4t(count3t);
					ersi.setCallOutNum4d(count3d);
					if(eee!=null){//生成过
						ersi.setEmpCountLastWeek4t(eee.getEmpCountEnd4t());
						ersi.setEmpCountLastWeek4d(eee.getEmpCountEnd4d());
						ersi.setEmpCountEnd4t(eee.getEmpCountEnd4t()+count0t+count1t-count2t-count3t);
						ersi.setEmpCountEnd4d(eee.getEmpCountEnd4d()+count0d+count1d-count2d-count3d);
					}else{//从未生成过
						List<EmployeeRelegationInfo> eris = employeeService.queryEmpByBl(bl);
						// 期初总人数
						int tCount = 0;
						int dCount = ersi.getEmpcount();
						for (EmployeeRelegationInfo eri : eris) {
							if (eri != null) {
								if ("测试".equals(eri.getSkill()) || "Test".equals(eri.getSkill())) {
									tCount++;
									dCount--;
								}
							}
						}
						ersi.setEmpCountLastWeek4t(tCount);
						ersi.setEmpCountLastWeek4d(dCount);
						ersi.setEmpCountEnd4t(tCount+count0t+count1t-count2t-count3t);
						ersi.setEmpCountEnd4d(dCount+count0d+count1d-count2d-count3d);
					}
				} else {//没有变动信息
					EmpRelStaInfo eee = employeeService.queryLastRelStaHisByBl(bl);
					if(eee!=null){
						ersi.setEmpCountLastWeek4t(eee.getEmpCountEnd4t());
						ersi.setEmpCountLastWeek4d(eee.getEmpCountEnd4d());
						ersi.setEmpCountEnd4t(eee.getEmpCountEnd4t());
						ersi.setEmpCountEnd4d(eee.getEmpCountEnd4d());
					}else{
						List<EmployeeRelegationInfo> eris = employeeService.queryEmpByBl(bl);
						// 期初总人数
						int tCount = 0;
						int dCount = ersi.getEmpcount();
						for (EmployeeRelegationInfo eri : eris) {
							if (eri != null) {
								if ("测试".equals(eri.getSkill()) || "Test".equals(eri.getSkill())) {
									tCount++;
									dCount--;
								}
							}
						}
						ersi.setEmpCountLastWeek4t(tCount);
						ersi.setEmpCountLastWeek4d(dCount);
						ersi.setEmpCountEnd4t(tCount);
						ersi.setEmpCountEnd4d(dCount);
					}
				}

				// 各状态分别有多少人
				for (String status : staStatus) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("status", status);
					map.put("business", bl);
					List<EmployeeRelegationInfo> eris = employeeService
					.queryEmpsByBlAndStatus(map);
					int count2t = 0;
					int count2d = eris != null && eris.size() > 0 ? eris.size() : 0;
					for (EmployeeRelegationInfo eri : eris) {
						if (eri!= null) {
							if ("测试".equals(eri.getSkill()) || "Test".equals(eri.getSkill())) {
								count2t++;
								count2d--;
							}
						}
					}
					if (status.equals("2")) {
						ersi.setChargingNum4t(count2t);
						ersi.setChargingNum4d(count2d);
					} else if (status.equals("12")) {
						ersi.setSubcontractorNum4t(count2t);
						ersi.setSubcontractorNum4d(count2d);
					} else if (status.equals("8")) {
						ersi.setCusReservesNum4t(count2t);
						ersi.setCusReservesNum4d(count2d);
					} else if (status.equals("9")) {
						ersi.setComReservesNum4t(count2t);
						ersi.setComReservesNum4d(count2d);
					} else if (status.equals("4")) {
						ersi.setUnUsedNum4t(count2t);
						ersi.setUnUsedNum4d(count2d);
					} else if (status.equals("0")) {
						ersi.setVacNum4t(count2t);
						ersi.setVacNum4d(count2d);
					} else if (status.equals("13")) {
						ersi.setMgrNum4t(count2t);
						ersi.setMgrNum4d(count2d);
					} else if (status.equals("6")) {
						ersi.setLendingOutNum4t(count2t);
						ersi.setLendingOutNum4d(count2d);
					} else if (status.equals("15")) {
						ersi.setPreSalesNum4t(count2t);
						ersi.setPreSalesNum4d(count2d);
					} else if (status.equals("16")) {
						ersi.setTxNum4t(count2t);
						ersi.setTxNum4d(count2d);
					}
				}
				// 合计：
				// 期初人数
				ersiAll.setEmpCountLastWeek4d(ersiAll.getEmpCountLastWeek4d()
						+ ersi.getEmpCountLastWeek4d()
						+ ersi.getEmpCountLastWeek4t());
				// 入职人数
				ersiAll.setEntryNum4d(ersiAll.getEntryNum4d()
						+ ersi.getEntryNum4d() + ersi.getEntryNum4t());
				// 调入人数
				ersiAll.setCallInNum4d(ersiAll.getCallInNum4d()
						+ ersi.getCallInNum4d() + ersi.getCallInNum4t());
				// 离职人数
				ersiAll.setResignationNum4d(ersiAll.getResignationNum4d()
						+ ersi.getResignationNum4d()
						+ ersi.getResignationNum4t());
				// 调出人数
				ersiAll.setCallOutNum4d(ersiAll.getCallOutNum4d()
						+ ersi.getCallOutNum4d() + ersi.getCallOutNum4t());
				// 期末人数
				ersiAll.setEmpCountEnd4d(ersiAll.getEmpCountEnd4d()
						+ ersi.getEmpCountEnd4d() + ersi.getEmpCountEnd4t());
				// 计费
				ersiAll.setChargingNum4d(ersiAll.getChargingNum4d()
						+ ersi.getChargingNum4d() + ersi.getChargingNum4t());
				// 现场储备
				ersiAll.setCusReservesNum4d(ersiAll.getCusReservesNum4d()
						+ ersi.getCusReservesNum4d()
						+ ersi.getCusReservesNum4t());
				// 公司储备
				ersiAll.setComReservesNum4d(ersiAll.getComReservesNum4d()
						+ ersi.getComReservesNum4d()
						+ ersi.getComReservesNum4t());
				// 闲置
				ersiAll.setUnUsedNum4d(ersiAll.getUnUsedNum4d()
						+ ersi.getUnUsedNum4d() + ersi.getUnUsedNum4t());
				// 休假
				ersiAll.setVacNum4d(ersiAll.getVacNum4d() + ersi.getVacNum4d()
						+ ersi.getVacNum4t());
				// 管理
				ersiAll.setMgrNum4d(ersiAll.getMgrNum4d() + ersi.getMgrNum4d()
						+ ersi.getMgrNum4t());
				// 借出外部
				ersiAll
						.setLendingOutNum4d(ersiAll.getLendingOutNum4d()
								+ ersi.getLendingOutNum4d()
								+ ersi.getLendingOutNum4t());
				// 售前
				ersiAll.setPreSalesNum4d(ersiAll.getPreSalesNum4d()
						+ ersi.getPreSalesNum4d() + ersi.getPreSalesNum4t());
				// 调休
				ersiAll.setTxNum4d(ersiAll.getTxNum4d() + ersi.getTxNum4d()
						+ ersi.getTxNum4t());
				// 下包商
				ersiAll.setSubcontractorNum4d(ersiAll.getSubcontractorNum4d()
						+ ersi.getSubcontractorNum4d()
						+ ersi.getSubcontractorNum4t());
				employeeService.addRelStaHis(ersi);
			}
			ersiAll.setBusinessLine("all");
			ersiAll.setCreateTime(new Date());
			ersiAll.setStaYearMonth(HisYear+HisMonth);
			ersiAll.setStaWeek(HisWeek);
			employeeService.addRelStaHis(ersiAll);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("cre_suc", "生成成功！");
		return mapping.findForward("success");
	}
}
