package com.iss.gms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.iss.gms.dao.emp.EmployeeDao;
import com.iss.gms.entity.CustomerQuoteInfo;
import com.iss.gms.entity.EmpRelChangeRecord;
import com.iss.gms.entity.EmpRelStaInfo;
import com.iss.gms.entity.EmployeeBasicInfo;
import com.iss.gms.entity.EmployeeDimissionInfo;
import com.iss.gms.entity.EmployeeRelegationInfo;
import com.iss.gms.entity.EmployeeVacationInfo;

public class EmployeeServiceImpl implements IEmployeeService {
	private EmployeeDao employeeDao;
	
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	//��ѯԱ��������Ϣ
	public List queryByName(String name) throws SQLException {
		return employeeDao.queryByName(name);
	}
	
	//���Ա��������Ϣ
	public void addEmpInfo(EmployeeBasicInfo ef)throws SQLException{
		this.employeeDao.addEmpInfo(ef);
	}
	
	//�޸�Ա��������Ϣ
	public void modifyEmpInfo(EmployeeBasicInfo ef)throws SQLException{
		this.employeeDao.modifyEmpInfo(ef);
	}
	
	//ɾ��Ա��������Ϣ
	public void removeEmpInfo(String eid)throws SQLException{
		this.employeeDao.removeEmpInfo(eid);
	}
	//��ѯԱ��������Ϣ�б�
	public List queryRelegationInfo(EmployeeRelegationInfo eri)throws SQLException{
		
		return employeeDao.queryRelegationInfo(eri);
	}
	//���Ա��������Ϣ
	public void addRelegationInfo(EmployeeRelegationInfo eri)throws SQLException{
		employeeDao.addRelegationInfo(eri);
	}
	
	public EmployeeRelegationInfo queryEriInfoByEid(EmployeeRelegationInfo eri)throws SQLException{
		return employeeDao.queryEriInfoByEid(eri);
	}
	
	//�޸�Ա��������Ϣ
	public void modifyEmpRelInfo(EmployeeRelegationInfo eri)throws SQLException{
		employeeDao.modifyEmpRelInfo(eri);
	}
	
	//ɾ��Ա��������Ϣ
	public void removeEmpRelInfo(EmployeeRelegationInfo eri  )throws SQLException{
		employeeDao.removeEmpRelInfo(eri);
	}
	//�����ۺ�������ѯԱ�������Ϣ
	public List queryVacationInfo(EmployeeVacationInfo evi)throws SQLException{
		return employeeDao.queryVacationInfo(evi);
	}
	//�����ۺ�������ѯԱ����ְ��Ϣ
	public List queryDimissionInfo(EmployeeDimissionInfo edi)throws SQLException{
		return employeeDao.queryDimissionInfo(edi); 
	}
	//���Ա�������Ϣ
	public void addVacationInfo(EmployeeVacationInfo evi)throws SQLException{
		employeeDao.addVacationInfo(evi);
	}
	//�޸�Ա����ְ��Ϣby id
	public void modifyInfoById(EmployeeDimissionInfo edi)throws SQLException{
		employeeDao.modifyInfoById(edi);
	}
	//��ѯ�ͻ�����
	public CustomerQuoteInfo queryCstmQuoteInfo(String customerName)throws SQLException{
		return employeeDao.queryCstmQuoteInfo(customerName);
	}
	//�޸Ŀͻ�������Ϣ
	public void modifyCstmQuoteInfo(CustomerQuoteInfo cqi)throws SQLException{
		employeeDao.modifyCstmQuoteInfo(cqi);
	}
	//��ӿͻ�������Ϣ
	public void addCstmQuoteInfo(CustomerQuoteInfo cqi)throws SQLException{
		employeeDao.addCstmQuoteInfo(cqi);
	}
	//�����ְ��Ϣ
	public void addDimissionInfo(EmployeeDimissionInfo edi) throws SQLException {
		employeeDao.addDimissionInfo(edi);
	}
	
	public List queryByCusName(String name) throws SQLException {
		return employeeDao.queryByCusName(name);
	}

	public List<String> queryDptmts() throws SQLException {
		return employeeDao.queryDptmts();
	}

	public List<String> queryNameListByRank(String rank) throws SQLException {
		return employeeDao.queryNameListByRank(rank);
	}
	//��ѯ��Ŀ�����мƷ���Ա
	public List<EmployeeRelegationInfo> queryEmpsByProName(String proName) throws SQLException {
		return employeeDao.queryEmpsByProName(proName);
	}

	public EmployeeVacationInfo queryVacationInfoByIdDate(
			EmployeeVacationInfo evi) throws SQLException {
		return employeeDao.queryVacationInfoByIdDate(evi);
	}

	public void delVacInfo(EmployeeVacationInfo evi) throws SQLException {
		employeeDao.delVacInfo(evi);
	}

	public List<CustomerQuoteInfo> queryCstmNames() throws SQLException {
		return employeeDao.queryCstmNames();
	}

	public void delDimInfo(EmployeeDimissionInfo edi) throws SQLException {
		employeeDao.delDimInfo(edi);
	}

	public List<EmployeeBasicInfo> queryEmpInfoList(EmployeeBasicInfo ebi)
			throws SQLException {
		return employeeDao.queryEmpInfoList(ebi);
	}

	public List<String> queryFirstSkills() throws SQLException {
		return employeeDao.queryFirstSkills();
	}

	public List<String> querySecondSkills() throws SQLException {
		return employeeDao.querySecondSkills();
	}

	public void addChangeRecord(EmpRelStaInfo ersi) throws SQLException {
		employeeDao.addChangeRecord(ersi);
	}

	public EmpRelStaInfo queryLastRecord(String employeeId)
			throws SQLException {
		return employeeDao.queryLastRecord(employeeId);
	}

	public List<EmpRelStaInfo> queryEmpCountWithBl() throws SQLException {
		return employeeDao.queryEmpCountWithBl();
	}

	public List<EmployeeRelegationInfo> queryEmpByBl(String businessLine) throws SQLException {
		return employeeDao.queryEmpByBl(businessLine);
	}

	public List<EmployeeRelegationInfo> queryEmpsByBlAndStatus(Map<String, String> map) 
			throws SQLException {
		return employeeDao.queryEmpsByBlAndStatus(map);
	}

	public EmpRelStaInfo queryLastRecordByBl(String businessLine)
			throws SQLException {
		return employeeDao.queryLastRecordByBl(businessLine);
	}

	public List<String> queryDptmtsVac() throws SQLException {
		return employeeDao.queryDptmtsVac();
	}

	public List<EmpRelStaInfo> queryRecordsByBl(Map<String, Object> map)throws SQLException {
		return employeeDao.queryRecordsByBl(map);
	}

	public List<String> queryDptmtsDim() throws SQLException {
		return employeeDao.queryDptmtsDim();
	}

	public List<String> queryAllBl4Rel() throws SQLException {
		return employeeDao.queryAllBl4Rel();
	}

	public List<EmpRelStaInfo> queryRelStaHis(Map<String, String> map)
			throws SQLException {
		return employeeDao.queryRelStaHis(map);
	}

	public void addRelStaHis(EmpRelStaInfo ersi) throws SQLException {
		employeeDao.addRelStaHis(ersi);
	}

	public void delRelStaHis(Map<String, String> map) throws SQLException {
		employeeDao.delRelStaHis(map);
	}

	public EmpRelStaInfo queryLastRelStaHisByBl(String businessLine)
			throws SQLException {
		return employeeDao.queryLastRelStaHisByBl(businessLine);
	}

	public void addRelegationInfoNoId(EmployeeRelegationInfo eri)
			throws SQLException {
		employeeDao.addRelegationInfoNoId(eri);
	}

	public List<EmployeeRelegationInfo> queryEriInfoByEname(
			EmployeeRelegationInfo eri) throws SQLException {
		return employeeDao.queryEriInfoByEname(eri);
	}

	public List<EmployeeBasicInfo> queryAllGapChEmp() throws SQLException {
		return employeeDao.queryAllGapChEmp();
	}
	
}
