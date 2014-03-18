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
	
	
	//按时间段查询标准工时信息
	public List queryStdHourswork(HoursWorkStandardInfo hwsi)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryStdHourswork",hwsi);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//按时间段查询标准工时信息统计
	public HoursWorkStandardInfo queryCountOfEveryType(HoursWorkStandardInfo hwsi)throws SQLException{
		try {
			return (HoursWorkStandardInfo)getSqlMapClientTemplate().queryForObject("queryCountOfEveryType", hwsi);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//查询员工对账单-工时统计
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
	//综合条件查询GAP工时信息(所有人)
	public List queryGapInfo(EmployeeHoursDetailInfo ehdi)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryGapInfo",ehdi);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//综合条件查询GAP工时信息(只与'确认工时信息'相关的人)
	public List queryGapInfoWithCus(EmployeeHoursDetailInfo ehdi)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryGapInfoWithCus",ehdi);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//查询员工加班工时
	public List queryOverTimeHours(EmployeeHoursDetailInfo ehdi)throws SQLException{
		try {
			return getSqlMapClientTemplate().queryForList("queryOverTimeHours",ehdi);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//添加标准工时
	public void addStdHoursworkInfo(HoursWorkStandardInfo hwsi)
	throws SQLException {
		try {
			 getSqlMapClientTemplate().insert("addStdHoursworkInfo", hwsi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//添加客户确认工时
	public void addCstmHoursworkInfo(CustomerVerifyHoursInfo cvhi)
	throws SQLException {
		try {
			 getSqlMapClientTemplate().insert("addCstmHoursworkInfo", cvhi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//查询员工在客户方的技术等级和技术平台byId
	public CustomerVerifyHoursInfo queryGradeAndTec(String employeeId)throws SQLException{
		try {
			return (CustomerVerifyHoursInfo) getSqlMapClientTemplate().queryForObject("queryGradeAndTec",employeeId);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//更新工时详情表数据(GAP导入或保存修改)
	public void modifyGapInfo(EmployeeHoursDetailInfo ehdi)throws SQLException{
		try {
			 getSqlMapClientTemplate().update("modifyGapInfo",ehdi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//冲销剩余倒休总工时
	public void modifyLastHours(HoursWorkStatisticsInfo hwsi)throws SQLException{
		try {
			 getSqlMapClientTemplate().update("modifyLastHours",hwsi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//查询员工统计工时信息byId
	public HoursWorkStatisticsInfo queryHoursStatistics(String employeeId)throws SQLException{
		try {
			return (HoursWorkStatisticsInfo) getSqlMapClientTemplate().queryForObject("queryHoursStatistics",employeeId);
			
		} catch (Exception e) {
			
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//查询日期类型byDate
	public String queryDateType(Date date2check) throws SQLException{
		try {
			return (String)getSqlMapClientTemplate().queryForObject("queryDateType",date2check);
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//查询最后一次导入的客户确认日期
	public Date queryLastDate4cusVer() throws SQLException{
		try {
			return (Date)getSqlMapClientTemplate().queryForObject("queryLastDate4cusVer");
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//查询所有统计截止日期的最后一天
	public Date queryLastDate4sta() throws SQLException{
		try {
			return (Date)getSqlMapClientTemplate().queryForObject("queryLastDate4sta");
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//查询标准工时Info byDate
	public HoursWorkStandardInfo queryStdInfoByDate(Date date) throws SQLException{
		try {
			return (HoursWorkStandardInfo)getSqlMapClientTemplate().queryForObject("queryStdInfoByDate",date);
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//添加工时详情表信息
	public void addHoursDetailInfo(EmployeeHoursDetailInfo ehdi)throws SQLException {
		try {
			 getSqlMapClientTemplate().insert("addHoursDetailInfo", ehdi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//添加工时统计表信息
	public void addHoursStatisticsInfo(EmployeeBasicInfo ebi)throws SQLException {
		try {
			getSqlMapClientTemplate().insert("addHoursStatisticsInfo", ebi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	//更新工时统计表数据
	public void modifyHoursStatistics(HoursWorkStatisticsInfo hwsi)throws SQLException{
		try {
			 getSqlMapClientTemplate().update("modifyHoursStatistics",hwsi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//更新工时详情表数据(客户确认工时导入)
	public void modifyHoursDetailInfo(EmployeeHoursDetailInfo ehdi)throws SQLException{
		try {
			getSqlMapClientTemplate().update("modifyHoursDetailInfo",ehdi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//更新工时详情表数据(客户确认工时导入)(同一人同一天有多条记录的情况)
	public void modifyHoursDetailInfoRepeat(EmployeeHoursDetailInfo ehdi)throws SQLException{
		try {
			getSqlMapClientTemplate().update("modifyHoursDetailInfoRepeat",ehdi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//更新客户确认工时表数据(客户确认工时导入)(同一人同一天有多条记录的情况)
	public void modifyCstmHoursworkInfo(CustomerVerifyHoursInfo cvhi)throws SQLException{
		try {
			getSqlMapClientTemplate().update("modifyCstmHoursworkInfo",cvhi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//PSA导入时更新gap工时
	public void modifyGapByPsa(EmployeeHoursDetailInfo ehdi)throws SQLException{
		try {
			getSqlMapClientTemplate().update("modifyGapByPsa",ehdi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	
	//查询工时详情info（单条）
	public EmployeeHoursDetailInfo queryHoursDetail(EmployeeHoursDetailInfo ehdi) throws SQLException{
		try {
			return (EmployeeHoursDetailInfo)getSqlMapClientTemplate().queryForObject("queryHoursDetail",ehdi);
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//查询差异工时by 员工编号、日期
	public Float queryGapHoursById(Map<String, Object> map) throws SQLException{
		try {
			return (Float)getSqlMapClientTemplate().queryForObject("queryGapHoursById",map);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//查询入职日期-统计截止日期之间所有工作日天数
	public String queryCusDateCount(EmployeeHoursDetailInfo ehdi) throws SQLException{
		try {
			return (String)getSqlMapClientTemplate().queryForObject("queryCusDateCount",ehdi);
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//查询客户确认工时信息
	public List<CustomerVerifyHoursInfo> queryCusHoursInfo(CustomerVerifyHoursInfo cvhi) throws SQLException{
		try {
			return (List<CustomerVerifyHoursInfo>) getSqlMapClientTemplate().queryForList("queryCusHoursInfo",cvhi);
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//查询客户确认工时信息(某时间段内所有)
	public List<CustomerVerifyHoursInfo> queryCusHoursInfoBySEdate(CustomerVerifyHoursInfo cvhi) throws SQLException{
		try {
			return (List<CustomerVerifyHoursInfo>) getSqlMapClientTemplate().queryForList("queryCusHoursInfoBySEdate",cvhi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//查询工时详情表里所有的属地
	public List<String> queryWorkPlaces() throws SQLException{
		try {
			return (List<String>) getSqlMapClientTemplate().queryForList("queryWorkPlaces");
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//查询工时详情表里所有的属地
	public List<Date> queryAllDates() throws SQLException{
		try {
			return (List<Date>) getSqlMapClientTemplate().queryForList("queryAllDates");
			
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//查询各技术平台各级别的总工时
	public List<CusIncomeQueryInfo> queryHoursByTecAndGrade(CusIncomeQueryInfo ciqi) throws SQLException{
		try {
			return (List<CusIncomeQueryInfo>)getSqlMapClientTemplate().queryForList("queryHoursByTecAndGrade",ciqi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//删除客户确认工时信息
	public void delCusHoursInfo(CustomerVerifyHoursInfo cvhi)throws SQLException{
		try {
			getSqlMapClientTemplate().delete("delCusHoursInfo",cvhi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//删除员工工时详情信息
	public void delHoursDetailInfo(EmployeeHoursDetailInfo ehdi)throws SQLException{
		try {
			getSqlMapClientTemplate().delete("delHoursDetailInfo",ehdi);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//删除员工工时统计信息
	public void delHoursStatisticsInfo(String employeeId)throws SQLException{
		try {
			getSqlMapClientTemplate().delete("delHoursStatisticsInfo",employeeId);
		} catch (Exception e) {
			log.error("", e);
			throw new SQLException(e.getMessage());
		}
	}
	//员工状态改为'离职'后 删除离职生效日期之后的预置(每人预置360条数据)信息
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
