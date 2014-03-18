package com.iss.gms.dao.hourswork;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.iss.gms.entity.CusIncomeQueryInfo;
import com.iss.gms.entity.CustomerVerifyHoursInfo;
import com.iss.gms.entity.EmployeeBasicInfo;
import com.iss.gms.entity.EmployeeHoursDetailInfo;
import com.iss.gms.entity.HoursWorkStandardInfo;
import com.iss.gms.entity.HoursWorkStatisticsInfo;
import com.iss.gms.entity.Statistics4EveryGapType;


public class HoursWorkDao extends SqlMapClientDaoSupport {

	Logger log = Logger.getLogger(HoursWorkDao.class);
	
	
	//��ʱ��β�ѯ��׼��ʱ��Ϣ
	public List queryStdHourswork(HoursWorkStandardInfo hwsi)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryStdHourswork",hwsi);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ʱ��β�ѯ��׼��ʱ��Ϣͳ��
	public HoursWorkStandardInfo queryCountOfEveryType(HoursWorkStandardInfo hwsi)throws SQLException{
		try {
			return (HoursWorkStandardInfo)getSqlMapClientTemplate().queryForObject("queryCountOfEveryType", hwsi);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ѯԱ�����˵�-��ʱͳ��
	public EmployeeBasicInfo queryWtDetail4CheckPart1(EmployeeBasicInfo ebi)throws SQLException{
		try {
			return (EmployeeBasicInfo)getSqlMapClientTemplate().queryForObject("queryWtDetail4CheckPart1", ebi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	public EmployeeHoursDetailInfo queryWtDetail4CheckPart2(HoursWorkStatisticsInfo hwsi)throws SQLException{
		try {
			return (EmployeeHoursDetailInfo)getSqlMapClientTemplate().queryForObject("queryWtDetail4CheckPart2", hwsi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	public HoursWorkStatisticsInfo queryWtDetail4CheckPart3(EmployeeBasicInfo ebi)throws SQLException{
		try {
			return (HoursWorkStatisticsInfo)getSqlMapClientTemplate().queryForObject("queryWtDetail4CheckPart3", ebi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	public Statistics4EveryGapType queryWtDetail4CheckPart4(EmployeeBasicInfo ebi)throws SQLException{
		try {
			return (Statistics4EveryGapType)getSqlMapClientTemplate().queryForObject("queryWtDetail4CheckPart4", ebi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	public List<Statistics4EveryGapType> queryGapHoursList(EmployeeBasicInfo ebi)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryGapHoursList", ebi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//�ۺ�������ѯGAP��ʱ��Ϣ(������)
	public List queryGapInfo(EmployeeHoursDetailInfo ehdi)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryGapInfo",ehdi);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//�ۺ�������ѯGAP��ʱ��Ϣ(ֻ��'ȷ�Ϲ�ʱ��Ϣ'��ص���)
	public List queryGapInfoWithCus(EmployeeHoursDetailInfo ehdi)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryGapInfoWithCus",ehdi);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ѯԱ���Ӱ๤ʱ
	public List queryOverTimeHours(EmployeeHoursDetailInfo ehdi)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryOverTimeHours",ehdi);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ӱ�׼��ʱ
	public void addStdHoursworkInfo(HoursWorkStandardInfo hwsi)
	throws SQLException {
		try {
			 getSqlMapClientTemplate().insert("addStdHoursworkInfo", hwsi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ӿͻ�ȷ�Ϲ�ʱ
	public void addCstmHoursworkInfo(CustomerVerifyHoursInfo cvhi)
	throws SQLException {
		try {
			 getSqlMapClientTemplate().insert("addCstmHoursworkInfo", cvhi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ѯԱ���ڿͻ����ļ����ȼ��ͼ���ƽ̨byId
	public CustomerVerifyHoursInfo queryGradeAndTec(String employeeId)throws SQLException{
		try {
			return (CustomerVerifyHoursInfo) getSqlMapClientTemplate().queryForObject("queryGradeAndTec",employeeId);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//���¹�ʱ���������(GAP����򱣴��޸�)
	public void modifyGapInfo(EmployeeHoursDetailInfo ehdi)throws SQLException{
		try {
			 getSqlMapClientTemplate().update("modifyGapInfo",ehdi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//����ʣ�൹���ܹ�ʱ
	public void modifyLastHours(HoursWorkStatisticsInfo hwsi)throws SQLException{
		try {
			 getSqlMapClientTemplate().update("modifyLastHours",hwsi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ѯԱ��ͳ�ƹ�ʱ��ϢbyId
	public HoursWorkStatisticsInfo queryHoursStatistics(String employeeId)throws SQLException{
		try {
			return (HoursWorkStatisticsInfo) getSqlMapClientTemplate().queryForObject("queryHoursStatistics",employeeId);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ѯ��������byDate
	public String queryDateType(Date date2check) throws SQLException{
		try {
			return (String)getSqlMapClientTemplate().queryForObject("queryDateType",date2check);
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ѯ���һ�ε���Ŀͻ�ȷ������
	public Date queryLastDate4cusVer() throws SQLException{
		try {
			return (Date)getSqlMapClientTemplate().queryForObject("queryLastDate4cusVer");
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ѯ����ͳ�ƽ�ֹ���ڵ����һ��
	public Date queryLastDate4sta() throws SQLException{
		try {
			return (Date)getSqlMapClientTemplate().queryForObject("queryLastDate4sta");
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ѯ��׼��ʱInfo byDate
	public HoursWorkStandardInfo queryStdInfoByDate(Date date) throws SQLException{
		try {
			return (HoursWorkStandardInfo)getSqlMapClientTemplate().queryForObject("queryStdInfoByDate",date);
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ӹ�ʱ�������Ϣ
	public void addHoursDetailInfo(EmployeeHoursDetailInfo ehdi)throws SQLException {
		try {
			 getSqlMapClientTemplate().insert("addHoursDetailInfo", ehdi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ӹ�ʱͳ�Ʊ���Ϣ
	public void addHoursStatisticsInfo(EmployeeBasicInfo ebi)throws SQLException {
		try {
			getSqlMapClientTemplate().insert("addHoursStatisticsInfo", ebi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	//���¹�ʱͳ�Ʊ�����
	public void modifyHoursStatistics(HoursWorkStatisticsInfo hwsi)throws SQLException{
		try {
			 getSqlMapClientTemplate().update("modifyHoursStatistics",hwsi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//���¹�ʱ���������(�ͻ�ȷ�Ϲ�ʱ����)
	public void modifyHoursDetailInfo(EmployeeHoursDetailInfo ehdi)throws SQLException{
		try {
			getSqlMapClientTemplate().update("modifyHoursDetailInfo",ehdi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//���¹�ʱ���������(�ͻ�ȷ�Ϲ�ʱ����)(ͬһ��ͬһ���ж�����¼�����)
	public void modifyHoursDetailInfoRepeat(EmployeeHoursDetailInfo ehdi)throws SQLException{
		try {
			getSqlMapClientTemplate().update("modifyHoursDetailInfoRepeat",ehdi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//���¿ͻ�ȷ�Ϲ�ʱ������(�ͻ�ȷ�Ϲ�ʱ����)(ͬһ��ͬһ���ж�����¼�����)
	public void modifyCstmHoursworkInfo(CustomerVerifyHoursInfo cvhi)throws SQLException{
		try {
			getSqlMapClientTemplate().update("modifyCstmHoursworkInfo",cvhi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//PSA����ʱ����gap��ʱ
	public void modifyGapByPsa(EmployeeHoursDetailInfo ehdi)throws SQLException{
		try {
			getSqlMapClientTemplate().update("modifyGapByPsa",ehdi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	//��ѯ��ʱ����info��������
	public EmployeeHoursDetailInfo queryHoursDetail(EmployeeHoursDetailInfo ehdi) throws SQLException{
		try {
			return (EmployeeHoursDetailInfo)getSqlMapClientTemplate().queryForObject("queryHoursDetail",ehdi);
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ѯ���칤ʱby Ա����š�����
	public Float queryGapHoursById(Map<String, Object> map) throws SQLException{
		try {
			return (Float)getSqlMapClientTemplate().queryForObject("queryGapHoursById",map);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ѯ��ְ����-ͳ�ƽ�ֹ����֮�����й���������
	public String queryCusDateCount(EmployeeHoursDetailInfo ehdi) throws SQLException{
		try {
			return (String)getSqlMapClientTemplate().queryForObject("queryCusDateCount",ehdi);
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ѯ�ͻ�ȷ�Ϲ�ʱ��Ϣ
	public List<CustomerVerifyHoursInfo> queryCusHoursInfo(CustomerVerifyHoursInfo cvhi) throws SQLException{
		try {
			return (List<CustomerVerifyHoursInfo>) getSqlMapClientTemplate().queryForList("queryCusHoursInfo",cvhi);
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ѯ�ͻ�ȷ�Ϲ�ʱ��Ϣ(ĳʱ���������)
	public List<CustomerVerifyHoursInfo> queryCusHoursInfoBySEdate(CustomerVerifyHoursInfo cvhi) throws SQLException{
		try {
			return (List<CustomerVerifyHoursInfo>) getSqlMapClientTemplate().queryForList("queryCusHoursInfoBySEdate",cvhi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ѯ��ʱ����������е�����
	public List<String> queryWorkPlaces() throws SQLException{
		try {
			return (List<String>) getSqlMapClientTemplate().queryForList("queryWorkPlaces");
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ѯ��ʱ����������е�����
	public List<Date> queryAllDates() throws SQLException{
		try {
			return (List<Date>) getSqlMapClientTemplate().queryForList("queryAllDates");
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//��ѯ������ƽ̨��������ܹ�ʱ
	public List<CusIncomeQueryInfo> queryHoursByTecAndGrade(CusIncomeQueryInfo ciqi) throws SQLException{
		try {
			return (List<CusIncomeQueryInfo>)getSqlMapClientTemplate().queryForList("queryHoursByTecAndGrade",ciqi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//ɾ���ͻ�ȷ�Ϲ�ʱ��Ϣ
	public void delCusHoursInfo(CustomerVerifyHoursInfo cvhi)throws SQLException{
		try {
			getSqlMapClientTemplate().delete("delCusHoursInfo",cvhi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//ɾ��Ա����ʱ������Ϣ
	public void delHoursDetailInfo(EmployeeHoursDetailInfo ehdi)throws SQLException{
		try {
			getSqlMapClientTemplate().delete("delHoursDetailInfo",ehdi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//ɾ��Ա����ʱͳ����Ϣ
	public void delHoursStatisticsInfo(String employeeId)throws SQLException{
		try {
			getSqlMapClientTemplate().delete("delHoursStatisticsInfo",employeeId);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//Ա��״̬��Ϊ'��ְ'�� ɾ����ְ��Ч����֮���Ԥ��(ÿ��Ԥ��360������)��Ϣ
	public void delHoursDetailInfo4dim(EmployeeHoursDetailInfo ehdi)throws SQLException{
		try {
			getSqlMapClientTemplate().delete("delHoursDetailInfo4dim",ehdi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	public void delStdInfoByDate(Date date)throws SQLException{
		try {
			getSqlMapClientTemplate().delete("delStdInfoByDate",date);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
}
