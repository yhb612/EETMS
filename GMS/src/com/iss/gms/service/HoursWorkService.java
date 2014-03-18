package com.iss.gms.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.iss.gms.entity.CusIncomeQueryInfo;
import com.iss.gms.entity.CustomerVerifyHoursInfo;
import com.iss.gms.entity.EmployeeBasicInfo;
import com.iss.gms.entity.EmployeeHoursDetailInfo;
import com.iss.gms.entity.HoursWorkStandardInfo;
import com.iss.gms.entity.HoursWorkStatisticsInfo;
import com.iss.gms.entity.Statistics4EveryGapType;

public interface HoursWorkService {

	//��ʱ��β�ѯ��׼��ʱ
	public List queryStdHourswork(HoursWorkStandardInfo hwsi)throws SQLException;
	//��ʱ��β�ѯ��׼��ʱ��Ϣͳ��
	public HoursWorkStandardInfo queryCountOfEveryType(HoursWorkStandardInfo hwsi)throws SQLException;
	//��ѯԱ������ְ�����ͻ�ȷ�Ϲ�ʱ���һ�ε���Ĺ�ʱͳ��
	public EmployeeBasicInfo queryWtDetail4CheckPart1(EmployeeBasicInfo ebi)throws SQLException;
	public EmployeeHoursDetailInfo queryWtDetail4CheckPart2(HoursWorkStatisticsInfo hwsi)throws SQLException;
	public HoursWorkStatisticsInfo queryWtDetail4CheckPart3(EmployeeBasicInfo ebi)throws SQLException;
	//public Statistics4EveryGapType queryWtDetail4CheckPart4(EmployeeBasicInfo ebi)throws SQLException;
	public List<Statistics4EveryGapType> queryGapHoursList(EmployeeBasicInfo ebi) throws SQLException;
	//�ۺ�������ѯGAP��ʱ��Ϣ
	public List queryGapInfo(EmployeeHoursDetailInfo ehdi) throws SQLException;
	public List queryGapInfoWithCus(EmployeeHoursDetailInfo ehdi)throws SQLException;
	public void addStdHoursworkInfo(HoursWorkStandardInfo hwsi)throws SQLException;
	public void addCstmHoursworkInfo(CustomerVerifyHoursInfo cvhi)throws SQLException;
	public void modifyCstmHoursworkInfo(CustomerVerifyHoursInfo cvhi)throws SQLException;
	public CustomerVerifyHoursInfo queryGradeAndTec(String employeeId)throws SQLException;
	public void modifyGapInfo(EmployeeHoursDetailInfo ehdi)throws SQLException;
	public void modifyLastHours(HoursWorkStatisticsInfo hwsi)throws SQLException;
	public HoursWorkStatisticsInfo queryHoursStatistics(String employeeId)throws SQLException;
	//��ѯĳ�����ڶ�Ӧ����������(�����ա�˫����)
	public String queryDateType(Date date2check)throws SQLException;
	//��ӹ�ʱ�������Ϣ
	public void addHoursDetailInfo(EmployeeHoursDetailInfo ehdi)throws SQLException;
	//ɾ����ʱ�������Ϣ
	public void delHoursDetailInfo(EmployeeHoursDetailInfo ehdi)throws SQLException;
	//��ӹ�ʱͳ�Ʊ���Ϣ
	public void addHoursStatisticsInfo(EmployeeBasicInfo ebi)throws SQLException;
	//ɾ����ʱͳ�Ʊ���Ϣ
	public void delHoursStatisticsInfo(String employeeId)throws SQLException;
	//�������ڲ�ѯ��׼��ʱinfo
	public HoursWorkStandardInfo queryStdInfoByDate(Date date) throws SQLException;
	//���¹�ʱͳ�Ʊ�����
	public void modifyHoursStatistics(HoursWorkStatisticsInfo hwsi)throws SQLException;
	//���¹�ʱ���������(�����ֶ�)(�ͻ�ȷ�Ϲ�ʱ����ʱ����)
	public void modifyHoursDetailInfo(EmployeeHoursDetailInfo ehdi)throws SQLException;
	//���¹�ʱ���������(�����ֶ�)(�ͻ�ȷ�Ϲ�ʱ����ʱ����)(ͬһ��ͬһ���ж�����¼����� �ӵڶ�����¼��ʼ�ô˷���)
	public void modifyHoursDetailInfoRepeat(EmployeeHoursDetailInfo ehdi)throws SQLException;
	//��ѯ��ʱ����
	public EmployeeHoursDetailInfo queryHoursDetail(EmployeeHoursDetailInfo ehdi) throws SQLException;
	//��ѯ�ͻ�ȷ�Ϲ�ʱ��Ϣ
	public List<CustomerVerifyHoursInfo> queryCusHoursInfo(CustomerVerifyHoursInfo cvhi) throws SQLException;
	//ɾ���ͻ�ȷ�Ϲ�ʱ��Ϣ
	public void delCusHoursInfo(CustomerVerifyHoursInfo cvhi)throws SQLException;
	//��ѯ��ְ����-ͳ�ƽ�ֹ����֮�����й���������
	public String queryCusDateCount(EmployeeHoursDetailInfo ehdi) throws SQLException;
	public void delHoursDetailInfo4dim(EmployeeHoursDetailInfo ehdi)throws SQLException;
	public void delStdInfoByDate(Date date)throws SQLException;
	//��ѯ���һ�ε���Ŀͻ�ȷ������
	public Date queryLastDate4cusVer() throws SQLException;
	//��ѯ����ͳ�ƽ�ֹ���ڵ����һ��
	public Date queryLastDate4sta() throws SQLException;
	public List<String> queryWorkPlaces() throws SQLException;
	//��ѯԱ���Ӱ๤ʱ
	public List queryOverTimeHours(EmployeeHoursDetailInfo ehdi)throws SQLException;
	public List<Date> queryAllDates() throws SQLException;
	public List<CustomerVerifyHoursInfo> queryCusHoursInfoBySEdate(CustomerVerifyHoursInfo cvhi) throws SQLException;
	public Float queryGapHoursById(Map<String, Object> map) throws SQLException;
	public void modifyGapByPsa(EmployeeHoursDetailInfo ehdi)throws SQLException;
	public List<CusIncomeQueryInfo> queryHoursByTecAndGrade(CusIncomeQueryInfo ciqi) throws SQLException;
}
