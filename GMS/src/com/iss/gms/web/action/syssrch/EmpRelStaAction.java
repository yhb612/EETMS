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

	// ��ѯ
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LazyValidatorForm lzForm = (LazyValidatorForm) form;
		String queryRelYear = (String) lzForm.get("queryRelYear");
		String queryRelMonth = (String) lzForm.get("queryRelMonth");
		String queryRelWeek = (String) lzForm.get("queryRelWeek");
		// ��ѯ��ʷ
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
						ersi.setBusinessLineName("���岿");
					} else if ("1".equals(bl)) {
						ersi.setBusinessLineName("�Ϻ�����");
					} else if ("2".equals(bl)) {
						ersi.setBusinessLineName("��Ϣ����");
					} else if ("3".equals(bl)) {
						ersi.setBusinessLineName("ɽ��");
					} else if ("4".equals(bl)) {
						ersi.setBusinessLineName("��������");
					} else if ("5".equals(bl)) {
						ersi.setBusinessLineName("���ʽ���");
					} else if ("6".equals(bl)) {
						ersi.setBusinessLineName("��Ϣ����");
					} else if ("7".equals(bl)) {
						ersi.setBusinessLineName("Ͷ�ʴ���");
					} else if ("8".equals(bl)) {
						ersi.setBusinessLineName("��������");
					} else if ("9".equals(bl)) {
						ersi.setBusinessLineName("����ҵ��");
					} else if ("10".equals(bl)) {
						ersi.setBusinessLineName("�Ϻ�");
					} else if ("11".equals(bl)) {
						ersi.setBusinessLineName("����");
					} else if ("12".equals(bl)) {
						ersi.setBusinessLineName("�ɶ�");
					} else if ("13".equals(bl)) {
						ersi.setBusinessLineName("�Ϻ�����");
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
		} else if (queryRelYear.isEmpty() && queryRelMonth.isEmpty()&& queryRelWeek.isEmpty()) {//��ѯ��ǰ
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
					// ��ѯ��ҵ�����Ƿ��б䶯��¼
					List<EmpRelStaInfo> recList = employeeService
							.queryRecordsByBl(map4queryRec);
					if (recList != null && recList.size() > 0) {//�б䶯��Ϣ
						// ��ְ(����)
						int count0t = 0;
						// ����(����)
						int count1t = 0;
						// ��ְ(����)
						int count2t = 0;
						// ����(����)
						int count3t = 0;
						// ��ְ(����)
						int count0d = 0;
						// ����(����)
						int count1d = 0;
						// ��ְ(����)
						int count2d = 0;
						// ����(����)
						int count3d = 0;

						EmpRelStaInfo eee = employeeService.queryLastRelStaHisByBl(bl);
						for (EmpRelStaInfo rec : recList) {
							if ("0".equals(rec.getTechnology())) {// ����
								if (rec.getOperate() != null) {
									if ("0".equals(rec.getOperate())) {// ��ְ
										count0t++;
										continue;
									} else if ("1".equals(rec.getOperate())) {// ����
										count1t++;
										continue;
									} else if ("2".equals(rec.getOperate())) {// ��ְ
										count2t++;
										continue;
									} else if ("3".equals(rec.getOperate())) {// ����
										count3t++;
										continue;
									}
								}
							} else {
								if (rec.getOperate() != null) {
									if ("0".equals(rec.getOperate())) {// ��ְ
										count0d++;
										continue;
									} else if ("1".equals(rec.getOperate())) {// ����
										count1d++;
										continue;
									} else if ("2".equals(rec.getOperate())) {// ��ְ
										count2d++;
										continue;
									} else if ("3".equals(rec.getOperate())) {// ����
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
						
						if(eee!=null){//���ɹ���ʷ��Ϣ
							ersi.setEmpCountLastWeek4t(eee.getEmpCountEnd4t());
							ersi.setEmpCountLastWeek4d(eee.getEmpCountEnd4d());
							ersi.setEmpCountEnd4t(eee.getEmpCountEnd4t()+count0t+count1t-count2t-count3t);
							ersi.setEmpCountEnd4d(eee.getEmpCountEnd4d()+count0d+count1d-count2d-count3d);
						}else{//��δ���ɹ���ʷ��Ϣ
							List<EmployeeRelegationInfo> eris = employeeService.queryEmpByBl(bl);
							// �ڳ�������
							int tCount = 0;
							int dCount = ersi.getEmpcount();
							for (EmployeeRelegationInfo eri : eris) {
								if (eri != null) {
									if ("����".equals(eri.getSkill()) || "Test".equals(eri.getSkill())) {
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
					} else {//û�б䶯��Ϣ
						EmpRelStaInfo eee = employeeService.queryLastRelStaHisByBl(bl);
						if(eee!=null){//���ɹ�
							ersi.setEmpCountLastWeek4t(eee.getEmpCountEnd4t());
							ersi.setEmpCountLastWeek4d(eee.getEmpCountEnd4d());
							ersi.setEmpCountEnd4t(eee.getEmpCountEnd4t());
							ersi.setEmpCountEnd4d(eee.getEmpCountEnd4d());
						}else{//��δ���ɹ�
							List<EmployeeRelegationInfo> eris = employeeService.queryEmpByBl(bl);
							// �ڳ�������
							int tCount = 0;
							int dCount = ersi.getEmpcount();
							for (EmployeeRelegationInfo eri : eris) {
								if (eri != null) {
									if ("����".equals(eri.getSkill()) || "Test".equals(eri.getSkill())) {
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
						ersi.setBusinessLineName("���岿");
					} else if ("1".equals(bl)) {
						ersi.setBusinessLineName("�Ϻ�����");
					} else if ("2".equals(bl)) {
						ersi.setBusinessLineName("��Ϣ����");
					} else if ("3".equals(bl)) {
						ersi.setBusinessLineName("ɽ��");
					} else if ("4".equals(bl)) {
						ersi.setBusinessLineName("��������");
					} else if ("5".equals(bl)) {
						ersi.setBusinessLineName("���ʽ���");
					} else if ("6".equals(bl)) {
						ersi.setBusinessLineName("��Ϣ����");
					} else if ("7".equals(bl)) {
						ersi.setBusinessLineName("Ͷ�ʴ���");
					} else if ("8".equals(bl)) {
						ersi.setBusinessLineName("��������");
					} else if ("9".equals(bl)) {
						ersi.setBusinessLineName("����ҵ��");
					} else if ("10".equals(bl)) {
						ersi.setBusinessLineName("�Ϻ�");
					} else if ("11".equals(bl)) {
						ersi.setBusinessLineName("����");
					} else if ("12".equals(bl)) {
						ersi.setBusinessLineName("�ɶ�");
					} else if ("13".equals(bl)) {
						ersi.setBusinessLineName("�Ϻ�����");
					} else{
						ersi.setBusinessLineName(bl);
						
					}

					// ��״̬�ֱ��ж�����
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
								if ("����".equals(eri.getSkill()) || "Test".equals(eri.getSkill())) {
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
					// �ϼƣ�
					// �ڳ�����
					ersiAll.setEmpCountLastWeek4d(ersiAll
							.getEmpCountLastWeek4d()
							+ ersi.getEmpCountLastWeek4d()
							+ ersi.getEmpCountLastWeek4t());
					// ��ְ����
					ersiAll.setEntryNum4d(ersiAll.getEntryNum4d()
							+ ersi.getEntryNum4d() + ersi.getEntryNum4t());
					// ��������
					ersiAll.setCallInNum4d(ersiAll.getCallInNum4d()
							+ ersi.getCallInNum4d() + ersi.getCallInNum4t());
					// ��ְ����
					ersiAll.setResignationNum4d(ersiAll.getResignationNum4d()
							+ ersi.getResignationNum4d()
							+ ersi.getResignationNum4t());
					// ��������
					ersiAll.setCallOutNum4d(ersiAll.getCallOutNum4d()
							+ ersi.getCallOutNum4d() + ersi.getCallOutNum4t());
					// ��ĩ����
					ersiAll
							.setEmpCountEnd4d(ersiAll.getEmpCountEnd4d()
									+ ersi.getEmpCountEnd4d()
									+ ersi.getEmpCountEnd4t());
					// �Ʒ�
					ersiAll
							.setChargingNum4d(ersiAll.getChargingNum4d()
									+ ersi.getChargingNum4d()
									+ ersi.getChargingNum4t());
					// �ֳ�����
					ersiAll.setCusReservesNum4d(ersiAll.getCusReservesNum4d()
							+ ersi.getCusReservesNum4d()
							+ ersi.getCusReservesNum4t());
					// ��˾����
					ersiAll.setComReservesNum4d(ersiAll.getComReservesNum4d()
							+ ersi.getComReservesNum4d()
							+ ersi.getComReservesNum4t());
					// ����
					ersiAll.setUnUsedNum4d(ersiAll.getUnUsedNum4d()
							+ ersi.getUnUsedNum4d() + ersi.getUnUsedNum4t());
					// �ݼ�
					ersiAll.setVacNum4d(ersiAll.getVacNum4d()
							+ ersi.getVacNum4d() + ersi.getVacNum4t());
					// ����
					ersiAll.setMgrNum4d(ersiAll.getMgrNum4d()
							+ ersi.getMgrNum4d() + ersi.getMgrNum4t());
					// ����ⲿ
					ersiAll.setLendingOutNum4d(ersiAll.getLendingOutNum4d()
							+ ersi.getLendingOutNum4d()
							+ ersi.getLendingOutNum4t());
					// ��ǰ
					ersiAll
							.setPreSalesNum4d(ersiAll.getPreSalesNum4d()
									+ ersi.getPreSalesNum4d()
									+ ersi.getPreSalesNum4t());
					// ����
					ersiAll.setTxNum4d(ersiAll.getTxNum4d() + ersi.getTxNum4d()
							+ ersi.getTxNum4t());
					// �°���
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
				//���ԭ����
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
				// ��ѯ��ҵ�����Ƿ��б䶯��¼
				EmpRelStaInfo aaa = employeeService.queryLastRelStaHisByBl(bl);
				Date tempDate=null;
				if(aaa!=null)
				tempDate = aaa.getCreateTime();
				Map<String, Object> map4queryRec = new HashMap<String, Object>();
				map4queryRec.put("updateTime", tempDate);
				map4queryRec.put("businessLine", bl);
				// ��ѯ��ҵ�����Ƿ��б䶯��¼
				List<EmpRelStaInfo> recList = employeeService
						.queryRecordsByBl(map4queryRec);
				if (recList != null && recList.size() > 0) {//�б䶯��Ϣ
					// ��ְ(����)
					int count0t = 0;
					// ����(����)
					int count1t = 0;
					// ��ְ(����)
					int count2t = 0;
					// ����(����)
					int count3t = 0;
					// ��ְ(����)
					int count0d = 0;
					// ����(����)
					int count1d = 0;
					// ��ְ(����)
					int count2d = 0;
					// ����(����)
					int count3d = 0;
					EmpRelStaInfo eee = employeeService.queryLastRelStaHisByBl(bl);
					for (EmpRelStaInfo rec : recList) {
						if ("0".equals(rec.getTechnology())) {// ����
							if (rec.getOperate() != null) {
								if ("0".equals(rec.getOperate())) {// ��ְ
									count0t++;
									continue;
								} else if ("1".equals(rec.getOperate())) {// ����
									count1t++;
									continue;
								} else if ("2".equals(rec.getOperate())) {// ��ְ
									count2t++;
									continue;
								} else if ("3".equals(rec.getOperate())) {// ����
									count3t++;
									continue;
								}
							}
						} else {
							if (rec.getOperate() != null) {
								if ("0".equals(rec.getOperate())) {// ��ְ
									count0d++;
									continue;
								} else if ("1".equals(rec.getOperate())) {// ����
									count1d++;
									continue;
								} else if ("2".equals(rec.getOperate())) {// ��ְ
									count2d++;
									continue;
								} else if ("3".equals(rec.getOperate())) {// ����
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
					if(eee!=null){//���ɹ�
						ersi.setEmpCountLastWeek4t(eee.getEmpCountEnd4t());
						ersi.setEmpCountLastWeek4d(eee.getEmpCountEnd4d());
						ersi.setEmpCountEnd4t(eee.getEmpCountEnd4t()+count0t+count1t-count2t-count3t);
						ersi.setEmpCountEnd4d(eee.getEmpCountEnd4d()+count0d+count1d-count2d-count3d);
					}else{//��δ���ɹ�
						List<EmployeeRelegationInfo> eris = employeeService.queryEmpByBl(bl);
						// �ڳ�������
						int tCount = 0;
						int dCount = ersi.getEmpcount();
						for (EmployeeRelegationInfo eri : eris) {
							if (eri != null) {
								if ("����".equals(eri.getSkill()) || "Test".equals(eri.getSkill())) {
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
				} else {//û�б䶯��Ϣ
					EmpRelStaInfo eee = employeeService.queryLastRelStaHisByBl(bl);
					if(eee!=null){
						ersi.setEmpCountLastWeek4t(eee.getEmpCountEnd4t());
						ersi.setEmpCountLastWeek4d(eee.getEmpCountEnd4d());
						ersi.setEmpCountEnd4t(eee.getEmpCountEnd4t());
						ersi.setEmpCountEnd4d(eee.getEmpCountEnd4d());
					}else{
						List<EmployeeRelegationInfo> eris = employeeService.queryEmpByBl(bl);
						// �ڳ�������
						int tCount = 0;
						int dCount = ersi.getEmpcount();
						for (EmployeeRelegationInfo eri : eris) {
							if (eri != null) {
								if ("����".equals(eri.getSkill()) || "Test".equals(eri.getSkill())) {
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

				// ��״̬�ֱ��ж�����
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
							if ("����".equals(eri.getSkill()) || "Test".equals(eri.getSkill())) {
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
				// �ϼƣ�
				// �ڳ�����
				ersiAll.setEmpCountLastWeek4d(ersiAll.getEmpCountLastWeek4d()
						+ ersi.getEmpCountLastWeek4d()
						+ ersi.getEmpCountLastWeek4t());
				// ��ְ����
				ersiAll.setEntryNum4d(ersiAll.getEntryNum4d()
						+ ersi.getEntryNum4d() + ersi.getEntryNum4t());
				// ��������
				ersiAll.setCallInNum4d(ersiAll.getCallInNum4d()
						+ ersi.getCallInNum4d() + ersi.getCallInNum4t());
				// ��ְ����
				ersiAll.setResignationNum4d(ersiAll.getResignationNum4d()
						+ ersi.getResignationNum4d()
						+ ersi.getResignationNum4t());
				// ��������
				ersiAll.setCallOutNum4d(ersiAll.getCallOutNum4d()
						+ ersi.getCallOutNum4d() + ersi.getCallOutNum4t());
				// ��ĩ����
				ersiAll.setEmpCountEnd4d(ersiAll.getEmpCountEnd4d()
						+ ersi.getEmpCountEnd4d() + ersi.getEmpCountEnd4t());
				// �Ʒ�
				ersiAll.setChargingNum4d(ersiAll.getChargingNum4d()
						+ ersi.getChargingNum4d() + ersi.getChargingNum4t());
				// �ֳ�����
				ersiAll.setCusReservesNum4d(ersiAll.getCusReservesNum4d()
						+ ersi.getCusReservesNum4d()
						+ ersi.getCusReservesNum4t());
				// ��˾����
				ersiAll.setComReservesNum4d(ersiAll.getComReservesNum4d()
						+ ersi.getComReservesNum4d()
						+ ersi.getComReservesNum4t());
				// ����
				ersiAll.setUnUsedNum4d(ersiAll.getUnUsedNum4d()
						+ ersi.getUnUsedNum4d() + ersi.getUnUsedNum4t());
				// �ݼ�
				ersiAll.setVacNum4d(ersiAll.getVacNum4d() + ersi.getVacNum4d()
						+ ersi.getVacNum4t());
				// ����
				ersiAll.setMgrNum4d(ersiAll.getMgrNum4d() + ersi.getMgrNum4d()
						+ ersi.getMgrNum4t());
				// ����ⲿ
				ersiAll
						.setLendingOutNum4d(ersiAll.getLendingOutNum4d()
								+ ersi.getLendingOutNum4d()
								+ ersi.getLendingOutNum4t());
				// ��ǰ
				ersiAll.setPreSalesNum4d(ersiAll.getPreSalesNum4d()
						+ ersi.getPreSalesNum4d() + ersi.getPreSalesNum4t());
				// ����
				ersiAll.setTxNum4d(ersiAll.getTxNum4d() + ersi.getTxNum4d()
						+ ersi.getTxNum4t());
				// �°���
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
		request.setAttribute("cre_suc", "���ɳɹ���");
		return mapping.findForward("success");
	}
}
