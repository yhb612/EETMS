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
	 * 根据员工姓名查询
	 * @return
	 * @param ‘员工姓名’ 或者 ‘员工编号’ 
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
	 * 查询参与工时核查的所有员工
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
	 * 查询归属信息中所有业务线
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
	 * 根据综合条件查询员工基本信息
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
	 * 根据客户定义姓名查询
	 * @return
	 * @param '客户方定义姓名' 
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
	 * 添加员工基本信息
	 * @param 员工基本信息
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
	 * 修改员工基本信息
	 * @param 员工编号
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
	 * 删除员工基本信息
	 * @param 员工编号
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
	 * 查询所有部门
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
	 * 查询请假信息中的所有部门
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
	 * 查询离职信息中的所有部门
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
	 * 查询所有第一技能
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
	 * 查询所有第二技能
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
	 * 查询所有人员姓名byRank
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
	 * 根据条件查询员工归属信息
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
	 * 添加员工归属信息(有员工编号)
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
	 * 添加员工归属信息(无员工编号)
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
	 * 根据员工编号、项目、状态  查询归属信息
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
	 * 根据员工姓名、项目、状态  查询归属信息
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
	 * 修改员工归属信息
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
	 * 删除员工归属信息
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
	 * 根据条件查询员工请假信息
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
	 * 根据条件查询员工离职信息
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
	 * 删除员工离职信息
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
	 * 根据id and date 查询员工请假信息
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
	 * 添加员工请假信息
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
	 * 添加员工离职信息
	 * @param 员工基本信息
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
	 * 修改员工离职信息
	 * @param by 员工编号
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
	 * 查询客户报价
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
	 * 查询客户名称(客户报价模块)
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
	 * 修改客户报价信息
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
	 * 添加客户报价信息
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
	 * 查询某项目下的计费员工
	 * @return
	 * @param ‘项目名称’ 
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
	 * 查询各业务线下的总人数
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
	 * 查询某业务线下的所有员工
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
	 * 添加员工变更记录
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
	 * 生成归属汇总历史信息
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
	 * 查询某员工最后一次变更
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
	 * 查询归属汇总历史表中某业务线最后一条记录
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
	 * 查询某业务线最后一次变更
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
	 * 查询某业务线所有变动
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
	 * 查询归属汇总历史信息
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
	 * 查询归属表中某业务线中某状态的总人数
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
