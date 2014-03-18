package com.iss.gms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.iss.gms.entity.CustomerQuoteInfo;
import com.iss.gms.entity.EmpRelChangeRecord;
import com.iss.gms.entity.EmpRelStaInfo;
import com.iss.gms.entity.EmployeeBasicInfo;
import com.iss.gms.entity.EmployeeDimissionInfo;
import com.iss.gms.entity.EmployeeRelegationInfo;
import com.iss.gms.entity.EmployeeVacationInfo;

public interface IEmployeeService {
	//根据姓名查询员工基本信息
	public List queryByName(String name)throws SQLException;
	public  List<EmployeeBasicInfo> queryEmpInfoList(EmployeeBasicInfo ebi)throws SQLException;
	public List<EmployeeBasicInfo> queryAllGapChEmp()throws SQLException;
	//根据客户定义姓名查询员工基本信息
	public List queryByCusName(String name)throws SQLException;
	//添加员工基本信息
	public void addEmpInfo(EmployeeBasicInfo ef)throws SQLException;
	//修改员工基本信息
	public void modifyEmpInfo(EmployeeBasicInfo ef)throws SQLException;
	//删除员工基本信息
	public void removeEmpInfo(String eid)throws SQLException;
	//查询所有部门
	public List<String> queryDptmts()throws SQLException;
	public List<String> queryFirstSkills()throws SQLException;
	public List<String> querySecondSkills()throws SQLException;
	//查询员工list by rank
	public List<String> queryNameListByRank(String rank)throws SQLException;
	//查询员工归属信息
	public List queryRelegationInfo(EmployeeRelegationInfo eri)throws SQLException;
	//添加员工归属信息
	public void addRelegationInfo(EmployeeRelegationInfo eri)throws SQLException;
	public void addRelegationInfoNoId(EmployeeRelegationInfo eri)throws SQLException;
	//根据id/项目名称/状态  查询员工归属信息
	public EmployeeRelegationInfo queryEriInfoByEid(EmployeeRelegationInfo eri)throws SQLException;
	//根据姓名/项目名称/状态  查询员工归属信息
	public List<EmployeeRelegationInfo> queryEriInfoByEname(EmployeeRelegationInfo eri)throws SQLException;
	//修改员工归属信息
	public void modifyEmpRelInfo(EmployeeRelegationInfo eri )throws SQLException;
	//删除员工归属信息
	public void removeEmpRelInfo(EmployeeRelegationInfo eri  )throws SQLException;
	//根据综合条件查询员工请假信息
	public List queryVacationInfo(EmployeeVacationInfo evi)throws SQLException;
	//添加员工请假信息
	public void addVacationInfo(EmployeeVacationInfo evi)throws SQLException;
	public void delVacInfo(EmployeeVacationInfo evi) throws SQLException;
	//根据综合条件查询员工离职信息
	public List queryDimissionInfo(EmployeeDimissionInfo edi)throws SQLException;
	//添加离职信息
	public void addDimissionInfo(EmployeeDimissionInfo edi)throws SQLException;
	//修改员工离职信息by id
	public void modifyInfoById(EmployeeDimissionInfo edi)throws SQLException;
	//查询客户报价
	public CustomerQuoteInfo queryCstmQuoteInfo(String customerName)throws SQLException;
	//查询客户报价-客户名称
	public List<CustomerQuoteInfo> queryCstmNames()throws SQLException;
	//修改客户报价信息
	public void modifyCstmQuoteInfo(CustomerQuoteInfo cqi)throws SQLException;
	//添加客户报价信息(只有首次进入才需添加信息)
	public void addCstmQuoteInfo(CustomerQuoteInfo cqi)throws SQLException;
	//查询某项目下的计费员工
	public List<EmployeeRelegationInfo> queryEmpsByProName(String proName)throws SQLException;
	
	public EmployeeVacationInfo queryVacationInfoByIdDate(EmployeeVacationInfo evi)throws SQLException;
	public void delDimInfo(EmployeeDimissionInfo edi )throws SQLException;
	public void addChangeRecord(EmpRelStaInfo ersi)throws SQLException;
	public EmpRelStaInfo queryLastRecord(String employeeId)throws SQLException;
	public List<EmpRelStaInfo> queryEmpCountWithBl()throws SQLException;
	public List<EmployeeRelegationInfo> queryEmpByBl(String businessLine)throws SQLException;
	public List<EmployeeRelegationInfo> queryEmpsByBlAndStatus(Map<String, String> map)throws SQLException;
	public EmpRelStaInfo queryLastRecordByBl(String businessLine)throws SQLException;
	public List<String> queryDptmtsVac()throws SQLException;
	public List<EmpRelStaInfo>  queryRecordsByBl(Map<String, Object> map)throws SQLException;
	public List<String> queryDptmtsDim()throws SQLException;
	public List<String> queryAllBl4Rel()throws SQLException;
	public List<EmpRelStaInfo> queryRelStaHis(Map<String, String> map)throws SQLException;
	public void addRelStaHis(EmpRelStaInfo ersi)throws SQLException;
	public void delRelStaHis(Map<String, String> map) throws SQLException;
	public EmpRelStaInfo queryLastRelStaHisByBl(String businessLine)throws SQLException;
}
