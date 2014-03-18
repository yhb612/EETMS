package com.iss.gms.dao.emp;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.iss.gms.entity.CustomerQuoteInfo;
import com.iss.gms.entity.EmpRelChangeRecord;
import com.iss.gms.entity.EmpRelStaInfo;
import com.iss.gms.entity.EmployeeBasicInfo;
import com.iss.gms.entity.EmployeeDimissionInfo;
import com.iss.gms.entity.EmployeeRelegationInfo;
import com.iss.gms.entity.EmployeeVacationInfo;


public class EmployeeDao extends SqlMapClientDaoSupport {
	Logger	log	= Logger.getLogger("EmployeeDao.java");
	
	
	
	/**
	 * ����Ա��������ѯ
	 * @return
	 * @param ��Ա�������� ���� ��Ա����š� 
	 * @throws SQLException 
	 */
	public List queryByName(String name)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryByName",name);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ��ѯ���빤ʱ�˲������Ա��
	 * @return
	 * @param 
	 * @throws SQLException 
	 */
	public List<EmployeeBasicInfo> queryAllGapChEmp()throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryAllGapChEmp");
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ��ѯ������Ϣ������ҵ����
	 * @return
	 * @param 
	 * @throws SQLException 
	 */
	public List<String> queryAllBl4Rel()throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryAllBl4Rel");
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * �����ۺ�������ѯԱ��������Ϣ
	 * @return
	 * @param  
	 * @throws SQLException 
	 */
	public  List<EmployeeBasicInfo> queryEmpInfoList(EmployeeBasicInfo ebi)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryEmpInfoList",ebi);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ���ݿͻ�����������ѯ
	 * @return
	 * @param '�ͻ�����������' 
	 * @throws SQLException 
	 */
	public List<EmployeeBasicInfo> queryByCusName(String name)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryByCusName",name);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	
	/**
	 * ���Ա��������Ϣ
	 * @param Ա��������Ϣ
	 * @return 
	 * @throws SQLException 
	 */
	public void addEmpInfo(EmployeeBasicInfo ef)throws SQLException{
		try {
			 getSqlMapClientTemplate().insert("insertEmpInfo",ef);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	/**
	 * �޸�Ա��������Ϣ
	 * @param Ա�����
	 * @return 
	 * @throws SQLException 
	 */
	public void modifyEmpInfo(EmployeeBasicInfo ef)throws SQLException{
		try {
			 getSqlMapClientTemplate().update("modifyEmpInfo",ef);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	/**
	 * ɾ��Ա��������Ϣ
	 * @param Ա�����
	 * @return 
	 * @throws SQLException 
	 */
	public void removeEmpInfo(String eid)throws SQLException{
		try {
			 getSqlMapClientTemplate().delete("removeEmpInfo",eid);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	/**
	 * ��ѯ���в���
	 * @return
	 * @param  
	 * @throws SQLException 
	 */
	public List<String> queryDptmts()throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryDptmts");
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ��ѯ�����Ϣ�е����в���
	 * @return
	 * @param  
	 * @throws SQLException 
	 */
	public List<String> queryDptmtsVac()throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryDptmtsVac");
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ��ѯ��ְ��Ϣ�е����в���
	 * @return
	 * @param  
	 * @throws SQLException 
	 */
	public List<String> queryDptmtsDim()throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryDptmtsDim");
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ��ѯ���е�һ����
	 * @return
	 * @param  
	 * @throws SQLException 
	 */
	public List<String> queryFirstSkills()throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryFirstSkills");
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ��ѯ���еڶ�����
	 * @return
	 * @param  
	 * @throws SQLException 
	 */
	public List<String> querySecondSkills()throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("querySecondSkills");
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ��ѯ������Ա����byRank
	 * @return
	 * @param  
	 * @throws SQLException 
	 */
	public List<String> queryNameListByRank(String rank)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryNameListByRank",rank);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	/**
	 * ����������ѯԱ��������Ϣ
	 * @return 
	 * @throws SQLException 
	 */
	public List queryRelegationInfo(EmployeeRelegationInfo eri)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryRelegationInfo",eri);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ���Ա��������Ϣ(��Ա�����)
	 * @return 
	 * @throws SQLException 
	 */
	public void addRelegationInfo(EmployeeRelegationInfo eri)throws SQLException{
		try {
			getSqlMapClientTemplate().insert("insertEmpRelInfo",eri);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ���Ա��������Ϣ(��Ա�����)
	 * @return 
	 * @throws SQLException 
	 */
	public void addRelegationInfoNoId(EmployeeRelegationInfo eri)throws SQLException{
		try {
			getSqlMapClientTemplate().insert("insertEmpRelInfoNoId",eri);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ����Ա����š���Ŀ��״̬  ��ѯ������Ϣ
	 * @return 
	 * @throws SQLException 
	 */
	public EmployeeRelegationInfo queryEriInfoByEid(EmployeeRelegationInfo eri)throws SQLException{
		try {
			return (EmployeeRelegationInfo)getSqlMapClientTemplate().queryForObject("queryEriInfoByEid", eri);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ����Ա����������Ŀ��״̬  ��ѯ������Ϣ
	 * @return 
	 * @throws SQLException 
	 */
	public List<EmployeeRelegationInfo> queryEriInfoByEname(EmployeeRelegationInfo eri)throws SQLException{
		try {
			return (List<EmployeeRelegationInfo>) getSqlMapClientTemplate().queryForList("queryEriInfoByEname", eri);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * �޸�Ա��������Ϣ
	 * @return 
	 * @throws SQLException 
	 */
	public void modifyEmpRelInfo(EmployeeRelegationInfo eri )throws SQLException{
		try {
			getSqlMapClientTemplate().update("modifyEmpRelInfo",eri);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ɾ��Ա��������Ϣ
	 * @return 
	 * @throws SQLException 
	 */
	public void removeEmpRelInfo(EmployeeRelegationInfo eri )throws SQLException{
		try {
			getSqlMapClientTemplate().delete("removeEmpRelInfo",eri);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	/**
	 * ����������ѯԱ�������Ϣ
	 * @return 
	 * @throws SQLException 
	 */
	public List queryVacationInfo(EmployeeVacationInfo evi)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryVacationInfo",evi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ����������ѯԱ����ְ��Ϣ
	 * @return 
	 * @throws SQLException 
	 */
	public List queryDimissionInfo(EmployeeDimissionInfo edi)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryDimissionInfo",edi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	/**
	 * ɾ��Ա����ְ��Ϣ
	 * @return 
	 * @throws SQLException 
	 */
	public void delDimInfo(EmployeeDimissionInfo edi )throws SQLException{
		try {
			getSqlMapClientTemplate().delete("delDimInfo",edi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ����id and date ��ѯԱ�������Ϣ
	 * @return 
	 * @throws SQLException 
	 */
	public EmployeeVacationInfo queryVacationInfoByIdDate(EmployeeVacationInfo evi)throws SQLException{
		try {
			return (EmployeeVacationInfo)getSqlMapClientTemplate().queryForObject("queryVacationInfoByIdDate",evi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	/**
	 * ���Ա�������Ϣ
	 * @return 
	 * @throws SQLException 
	 */
	public void addVacationInfo(EmployeeVacationInfo evi)throws SQLException{
		try {
			getSqlMapClientTemplate().insert("addVacationInfo",evi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	public void delVacInfo(EmployeeVacationInfo evi) throws SQLException{
		try {
			getSqlMapClientTemplate().delete("delVacInfo",evi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	public void delRelStaHis(Map<String, String> map) throws SQLException{
		try {
			getSqlMapClientTemplate().delete("delRelStaHis",map);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	/**
	 * ���Ա����ְ��Ϣ
	 * @param Ա��������Ϣ
	 * @return 
	 * @throws SQLException 
	 */
	public void addDimissionInfo(EmployeeDimissionInfo edi)throws SQLException{
		try {
			 getSqlMapClientTemplate().insert("addDimissionInfo",edi);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * �޸�Ա����ְ��Ϣ
	 * @param by Ա�����
	 * @return 
	 * @throws SQLException 
	 */
	public void modifyInfoById(EmployeeDimissionInfo edi)throws SQLException{
		try {
			 getSqlMapClientTemplate().update("modifyInfoById",edi);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ��ѯ�ͻ�����
	 * @param 
	 * @return 
	 * @throws SQLException 
	 */
	public CustomerQuoteInfo queryCstmQuoteInfo(String customerName)throws SQLException{
		try {
			return (CustomerQuoteInfo)getSqlMapClientTemplate().queryForObject("queryCstmQuoteInfo",customerName);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ��ѯ�ͻ�����(�ͻ�����ģ��)
	 * @param 
	 * @return 
	 * @throws SQLException 
	 */
	public List<CustomerQuoteInfo> queryCstmNames()throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryCstmNames");
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * �޸Ŀͻ�������Ϣ
	 * @param 
	 * @return 
	 * @throws SQLException 
	 */
	public void modifyCstmQuoteInfo(CustomerQuoteInfo cqi)throws SQLException{
		try {
			getSqlMapClientTemplate().update("modifyCstmQuoteInfo",cqi);
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ��ӿͻ�������Ϣ
	 * @param 
	 * @return 
	 * @throws SQLException 
	 */
	public void addCstmQuoteInfo(CustomerQuoteInfo cqi)throws SQLException{
		try {
			getSqlMapClientTemplate().insert("addCstmQuoteInfo",cqi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	/**
	 * ��ѯĳ��Ŀ�µļƷ�Ա��
	 * @return
	 * @param ����Ŀ���ơ� 
	 * @throws SQLException 
	 */
	public List<EmployeeRelegationInfo> queryEmpsByProName(String proName)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryEmpsByProName",proName);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ��ѯ��ҵ�����µ�������
	 * @return
	 * @param  
	 * @throws SQLException 
	 */
	public List<EmpRelStaInfo> queryEmpCountWithBl()throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryEmpCountWithBl");
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ��ѯĳҵ�����µ�����Ա��
	 * @return
	 * @param  
	 * @throws SQLException 
	 */
	public List<EmployeeRelegationInfo> queryEmpByBl(String businessLine)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryEmpByBl",businessLine);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ���Ա�������¼
	 * @param 
	 * @return 
	 * @throws SQLException 
	 */
	public void addChangeRecord(EmpRelStaInfo ersi)throws SQLException{
		try {
			 getSqlMapClientTemplate().insert("addChangeRecord",ersi);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ���ɹ���������ʷ��Ϣ
	 * @param 
	 * @return 
	 * @throws SQLException 
	 */
	public void addRelStaHis(EmpRelStaInfo ersi)throws SQLException{
		try {
			getSqlMapClientTemplate().insert("addRelStaHis",ersi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ��ѯĳԱ�����һ�α��
	 * @param 
	 * @return 
	 * @throws SQLException 
	 */
	public EmpRelStaInfo queryLastRecord(String employeeId)throws SQLException{
		try {
			return (EmpRelStaInfo)getSqlMapClientTemplate().queryForObject("queryLastRecord",employeeId);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ��ѯ����������ʷ����ĳҵ�������һ����¼
	 * @param 
	 * @return 
	 * @throws SQLException 
	 */
	public EmpRelStaInfo queryLastRelStaHisByBl(String businessLine)throws SQLException{
		try {
			return (EmpRelStaInfo)getSqlMapClientTemplate().queryForObject("queryLastRelStaHisByBl",businessLine);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ��ѯĳҵ�������һ�α��
	 * @param 
	 * @return 
	 * @throws SQLException 
	 */
	public EmpRelStaInfo queryLastRecordByBl(String businessLine)throws SQLException{
		try {
			return (EmpRelStaInfo)getSqlMapClientTemplate().queryForObject("queryLastRecordByBl",businessLine);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ��ѯĳҵ�������б䶯
	 * @param 
	 * @return 
	 * @throws SQLException 
	 */
	public List<EmpRelStaInfo>  queryRecordsByBl(Map<String, Object> map)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryRecordsByBl",map);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	/**
	 * ��ѯ����������ʷ��Ϣ
	 * @param 
	 * @return 
	 * @throws SQLException 
	 */
	public List<EmpRelStaInfo> queryRelStaHis(Map<String, String> map)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryRelStaHis",map);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	/**
	 * ��ѯ��������ĳҵ������ĳ״̬��������
	 * @param 
	 * @return 
	 * @throws SQLException 
	 */
	public List<EmployeeRelegationInfo> queryEmpsByBlAndStatus(Map<String, String> map)throws SQLException{ 
		try {
			return getSqlMapClientTemplate().queryForList("queryEmpsByBlAndStatus",map);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
}
