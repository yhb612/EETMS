package com.iss.gms.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.iss.gms.dao.hourswork.HoursWorkDao;
import com.iss.gms.entity.CusIncomeQueryInfo;
import com.iss.gms.entity.CustomerVerifyHoursInfo;
import com.iss.gms.entity.EmployeeBasicInfo;
import com.iss.gms.entity.EmployeeHoursDetailInfo;
import com.iss.gms.entity.HoursWorkStandardInfo;
import com.iss.gms.entity.HoursWorkStatisticsInfo;
import com.iss.gms.entity.Statistics4EveryGapType;

public class HoursWorkServiceImpl implements HoursWorkService {

	private HoursWorkDao hoursWorkDao;
	
	public void setHoursWorkDao(HoursWorkDao hoursWorkDao) {
		this.hoursWorkDao = hoursWorkDao;
	}

	public List queryStdHourswork(HoursWorkStandardInfo hwsi)throws SQLException{
		return hoursWorkDao.queryStdHourswork(hwsi);
	}
	//按时间段查询标准工时信息统计
	public HoursWorkStandardInfo queryCountOfEveryType(HoursWorkStandardInfo hwsi)throws SQLException{
		return hoursWorkDao.queryCountOfEveryType(hwsi);
	}
	
	//查询员工自入职起至客户确认工时最后一次导入的工时统计
	public EmployeeBasicInfo queryWtDetail4CheckPart1(EmployeeBasicInfo ebi)
			throws SQLException { 
		return hoursWorkDao.queryWtDetail4CheckPart1(ebi);
	}

	public EmployeeHoursDetailInfo queryWtDetail4CheckPart2(
			HoursWorkStatisticsInfo hwsi) throws SQLException {
		return hoursWorkDao.queryWtDetail4CheckPart2(hwsi);
	}

	public HoursWorkStatisticsInfo queryWtDetail4CheckPart3(EmployeeBasicInfo ebi)
			throws SQLException {
		return hoursWorkDao.queryWtDetail4CheckPart3(ebi);
	}

	public Statistics4EveryGapType queryWtDetail4CheckPart4(
			EmployeeBasicInfo ebi) throws SQLException {
		return hoursWorkDao.queryWtDetail4CheckPart4(ebi);
	}
	public List<Statistics4EveryGapType> queryGapHoursList(
			EmployeeBasicInfo ebi) throws SQLException {
		return hoursWorkDao.queryGapHoursList(ebi);
	}

	public List queryGapInfo(EmployeeHoursDetailInfo ehdi)
			throws SQLException {
		return hoursWorkDao.queryGapInfo(ehdi);
	}

	public void addStdHoursworkInfo(HoursWorkStandardInfo hwsi)
			throws SQLException {
		hoursWorkDao.addStdHoursworkInfo(hwsi);
	}

	public void addCstmHoursworkInfo(CustomerVerifyHoursInfo cvhi)
			throws SQLException {
		hoursWorkDao.addCstmHoursworkInfo(cvhi);
	}

	public void modifyGapInfo(EmployeeHoursDetailInfo ehdi)
			throws SQLException {
		hoursWorkDao.modifyGapInfo(ehdi);
	}

	public void modifyLastHours(HoursWorkStatisticsInfo hwsi)
			throws SQLException {
		hoursWorkDao.modifyLastHours(hwsi);
	}

	public HoursWorkStatisticsInfo queryHoursStatistics(String employeeId)
			throws SQLException {
		return hoursWorkDao.queryHoursStatistics(employeeId);
	}

	public String queryDateType(Date date2check) throws SQLException {
		return hoursWorkDao.queryDateType(date2check);
	}

	public CustomerVerifyHoursInfo queryGradeAndTec(String employeeId)
			throws SQLException {
		return hoursWorkDao.queryGradeAndTec(employeeId);
	}

	public void addHoursDetailInfo(EmployeeHoursDetailInfo ehdi)
			throws SQLException {
		hoursWorkDao.addHoursDetailInfo(ehdi);
	}

	public void addHoursStatisticsInfo(EmployeeBasicInfo ebi)
			throws SQLException {
		hoursWorkDao.addHoursStatisticsInfo(ebi);
	}

	public HoursWorkStandardInfo queryStdInfoByDate(Date date)
			throws SQLException {
		return hoursWorkDao.queryStdInfoByDate(date);
	}

	public void modifyHoursStatistics(HoursWorkStatisticsInfo hwsi)
			throws SQLException {
		hoursWorkDao.modifyHoursStatistics(hwsi);
	}

	public EmployeeHoursDetailInfo queryHoursDetail(EmployeeHoursDetailInfo ehdi)
			throws SQLException {
		return hoursWorkDao.queryHoursDetail(ehdi);
	}

	public void delCusHoursInfo(CustomerVerifyHoursInfo cvhi)
			throws SQLException {
		hoursWorkDao.delCusHoursInfo(cvhi);
	}

	public List<CustomerVerifyHoursInfo> queryCusHoursInfo(
			CustomerVerifyHoursInfo cvhi) throws SQLException {
		return hoursWorkDao.queryCusHoursInfo(cvhi);
	}

	public void delHoursDetailInfo(EmployeeHoursDetailInfo ehdi)
			throws SQLException {
		hoursWorkDao.delHoursDetailInfo(ehdi);
	}

	public void delHoursStatisticsInfo(String employeeId) throws SQLException {
		hoursWorkDao.delHoursStatisticsInfo(employeeId);
	}

	public void modifyHoursDetailInfo(EmployeeHoursDetailInfo ehdi)
			throws SQLException {
		hoursWorkDao.modifyHoursDetailInfo(ehdi);
	}
	//更新工时详情表数据(部分字段)(客户确认工时导入时进行)(同一人同一天有多条记录的情况 从第二条记录开始用此方法)
	public void modifyHoursDetailInfoRepeat(EmployeeHoursDetailInfo ehdi)
			throws SQLException {
		hoursWorkDao.modifyHoursDetailInfoRepeat(ehdi);
	}

	public String queryCusDateCount(EmployeeHoursDetailInfo ehdi)
			throws SQLException {
		return hoursWorkDao.queryCusDateCount(ehdi);
	}

	public void delHoursDetailInfo4dim(EmployeeHoursDetailInfo ehdi)
			throws SQLException {
		hoursWorkDao.delHoursDetailInfo4dim(ehdi);
	}

	public void delStdInfoByDate(Date date) throws SQLException {
		hoursWorkDao.delStdInfoByDate(date);
	}

	public Date queryLastDate4cusVer() throws SQLException {
		return hoursWorkDao.queryLastDate4cusVer();
	}

	public Date queryLastDate4sta() throws SQLException {
		return hoursWorkDao.queryLastDate4sta();
	}

	public List<String> queryWorkPlaces() throws SQLException {
		return hoursWorkDao.queryWorkPlaces();
	}

	public List queryGapInfoWithCus(EmployeeHoursDetailInfo ehdi)
			throws SQLException {
		return hoursWorkDao.queryGapInfoWithCus(ehdi);
	}

	public List queryOverTimeHours(EmployeeHoursDetailInfo ehdi)
			throws SQLException {
		return hoursWorkDao.queryOverTimeHours(ehdi);
	}

	public List<Date> queryAllDates() throws SQLException {
		return hoursWorkDao.queryAllDates();
	}

	public void modifyCstmHoursworkInfo(CustomerVerifyHoursInfo cvhi)
			throws SQLException {
		hoursWorkDao.modifyCstmHoursworkInfo(cvhi);
	}

	public List<CustomerVerifyHoursInfo> queryCusHoursInfoBySEdate(
			CustomerVerifyHoursInfo cvhi) throws SQLException {
		return hoursWorkDao.queryCusHoursInfoBySEdate(cvhi);
	}

	public Float queryGapHoursById(Map<String, Object> map) throws SQLException {
		return hoursWorkDao.queryGapHoursById(map);
	}

	public void modifyGapByPsa(EmployeeHoursDetailInfo ehdi)
			throws SQLException {
		hoursWorkDao.modifyGapByPsa(ehdi);
	}

	public List<CusIncomeQueryInfo> queryHoursByTecAndGrade(CusIncomeQueryInfo ciqi)
			throws SQLException {
		return hoursWorkDao.queryHoursByTecAndGrade(ciqi);
	}
}
