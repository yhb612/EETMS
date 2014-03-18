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

	//按时间段查询标准工时
	public List queryStdHourswork(HoursWorkStandardInfo hwsi)throws SQLException;
	//按时间段查询标准工时信息统计
	public HoursWorkStandardInfo queryCountOfEveryType(HoursWorkStandardInfo hwsi)throws SQLException;
	//查询员工自入职起至客户确认工时最后一次导入的工时统计
	public EmployeeBasicInfo queryWtDetail4CheckPart1(EmployeeBasicInfo ebi)throws SQLException;
	public EmployeeHoursDetailInfo queryWtDetail4CheckPart2(HoursWorkStatisticsInfo hwsi)throws SQLException;
	public HoursWorkStatisticsInfo queryWtDetail4CheckPart3(EmployeeBasicInfo ebi)throws SQLException;
	//public Statistics4EveryGapType queryWtDetail4CheckPart4(EmployeeBasicInfo ebi)throws SQLException;
	public List<Statistics4EveryGapType> queryGapHoursList(EmployeeBasicInfo ebi) throws SQLException;
	//综合条件查询GAP工时信息
	public List queryGapInfo(EmployeeHoursDetailInfo ehdi) throws SQLException;
	public List queryGapInfoWithCus(EmployeeHoursDetailInfo ehdi)throws SQLException;
	public void addStdHoursworkInfo(HoursWorkStandardInfo hwsi)throws SQLException;
	public void addCstmHoursworkInfo(CustomerVerifyHoursInfo cvhi)throws SQLException;
	public void modifyCstmHoursworkInfo(CustomerVerifyHoursInfo cvhi)throws SQLException;
	public CustomerVerifyHoursInfo queryGradeAndTec(String employeeId)throws SQLException;
	public void modifyGapInfo(EmployeeHoursDetailInfo ehdi)throws SQLException;
	public void modifyLastHours(HoursWorkStatisticsInfo hwsi)throws SQLException;
	public HoursWorkStatisticsInfo queryHoursStatistics(String employeeId)throws SQLException;
	//查询某个日期对应的日期类型(工作日、双休日)
	public String queryDateType(Date date2check)throws SQLException;
	//添加工时详情表信息
	public void addHoursDetailInfo(EmployeeHoursDetailInfo ehdi)throws SQLException;
	//删除工时详情表信息
	public void delHoursDetailInfo(EmployeeHoursDetailInfo ehdi)throws SQLException;
	//添加工时统计表信息
	public void addHoursStatisticsInfo(EmployeeBasicInfo ebi)throws SQLException;
	//删除工时统计表信息
	public void delHoursStatisticsInfo(String employeeId)throws SQLException;
	//根据日期查询标准工时info
	public HoursWorkStandardInfo queryStdInfoByDate(Date date) throws SQLException;
	//更新工时统计表数据
	public void modifyHoursStatistics(HoursWorkStatisticsInfo hwsi)throws SQLException;
	//更新工时详情表数据(部分字段)(客户确认工时导入时进行)
	public void modifyHoursDetailInfo(EmployeeHoursDetailInfo ehdi)throws SQLException;
	//更新工时详情表数据(部分字段)(客户确认工时导入时进行)(同一人同一天有多条记录的情况 从第二条记录开始用此方法)
	public void modifyHoursDetailInfoRepeat(EmployeeHoursDetailInfo ehdi)throws SQLException;
	//查询工时详情
	public EmployeeHoursDetailInfo queryHoursDetail(EmployeeHoursDetailInfo ehdi) throws SQLException;
	//查询客户确认工时信息
	public List<CustomerVerifyHoursInfo> queryCusHoursInfo(CustomerVerifyHoursInfo cvhi) throws SQLException;
	//删除客户确认工时信息
	public void delCusHoursInfo(CustomerVerifyHoursInfo cvhi)throws SQLException;
	//查询入职日期-统计截止日期之间所有工作日天数
	public String queryCusDateCount(EmployeeHoursDetailInfo ehdi) throws SQLException;
	public void delHoursDetailInfo4dim(EmployeeHoursDetailInfo ehdi)throws SQLException;
	public void delStdInfoByDate(Date date)throws SQLException;
	//查询最后一次导入的客户确认日期
	public Date queryLastDate4cusVer() throws SQLException;
	//查询所有统计截止日期的最后一天
	public Date queryLastDate4sta() throws SQLException;
	public List<String> queryWorkPlaces() throws SQLException;
	//查询员工加班工时
	public List queryOverTimeHours(EmployeeHoursDetailInfo ehdi)throws SQLException;
	public List<Date> queryAllDates() throws SQLException;
	public List<CustomerVerifyHoursInfo> queryCusHoursInfoBySEdate(CustomerVerifyHoursInfo cvhi) throws SQLException;
	public Float queryGapHoursById(Map<String, Object> map) throws SQLException;
	public void modifyGapByPsa(EmployeeHoursDetailInfo ehdi)throws SQLException;
	public List<CusIncomeQueryInfo> queryHoursByTecAndGrade(CusIncomeQueryInfo ciqi) throws SQLException;
}
