package com.iss.gms.utils;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class Utils {
	private Logger logger = Logger.getLogger("info");
	//获得一个路径下的所有文件的完整路径名的集合
	public void getAllFiles(File file , List list) throws Exception{
		try{
			if(file.isFile()){
				list.add(file.toString());
			}else if(file.isDirectory()){
				File[] files = file.listFiles();
				for(int i=0 ; i<files.length ; i++){
					getAllFiles(files[i] , list);
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	//判断一个字符串是否是以.xls结尾
	public boolean checkIfExcelFile(String fileName) throws Exception {
		boolean result = false ;
		try{
			int n = fileName.lastIndexOf(".");
			String subStr = fileName.substring(n,fileName.length());
			if(".xls".equals(subStr)){
				result = true ;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	//筛选list，将以.xls结尾的字符串保留，其余的删除
	public void screenList(List list) throws Exception {
		try{
			for(int i=0 ; i<list.size() ; i++){
				if(!this.checkIfExcelFile(list.get(i).toString())){
					list.remove(i);
					i--;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	//比较两个日期，开始日期在结束日期之前，返回true,日期相同，返回true
	public boolean compareDatesEq(Date startDate , Date endDate) throws Exception {
		boolean result = false ;
		try{
			if(startDate!=null && endDate!=null){
				Calendar startCal = Calendar.getInstance();
				Calendar endCal = Calendar.getInstance();
				startCal.setTime(startDate);
				endCal.setTime(endDate);
				if(endCal.compareTo(startCal)>=0){
					result = true ;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return result ;
	}
	//比较两个日期是否相同
	public boolean equalDates(Date date1 , Date date2) throws Exception {
		boolean result = false ;
		try{
			if(date1!=null && date2!=null){
				Calendar cal1 = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
				cal1.setTime(date1);
				cal2.setTime(date2);
				if(cal2.compareTo(cal1)==0){
					result = true ;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return result ;
	}
	
	//转换Date为String类型
	public String parseDateToString(Date date , String pattern) {
		String result = null;
		try{
			if("".equals(pattern) || pattern==null){
				pattern = "yyyy-MM-dd" ;
			}
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern(pattern);
			if(date!=null){
				result = sdf.format(date) ;
			}
		}catch(Exception e){
			e.printStackTrace();
//			throw e;
		}
		return result ;
	}
	//转换String类型为Date
	public Date parseStringToDate(String source , String pattern){
		Date date = null ;
		try{
			if("".equals(pattern) || pattern==null){
				pattern = "yyyy-MM-dd" ;
			}
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern(pattern);
			if(!("".equals(source) || source==null)){
				date = sdf.parse(source);
			}
		}catch(Exception e){
			e.printStackTrace();
//			throw e;
		}
		return date ;
	}
	//比较一个日期是否在两个日期之间 ，在日期之间返回true，否则返回false，和两个日期中的一个相同也返回true，
	public boolean compareDateInner(Date compDate , Date startDate , Date endDate) throws Exception {
		boolean result = false ;
		try{
			Calendar calComp = Calendar.getInstance() ;
			Calendar calStart = Calendar.getInstance();
			Calendar calEnd = Calendar.getInstance() ;
			if(compDate!=null && startDate!=null && endDate!=null){
				calComp.setTime(compDate);
				calStart.setTime(startDate) ;
				calEnd.setTime(endDate);
//				System.out.println((calComp.compareTo(calStart)>=0) + "/"+(calComp.compareTo(calEnd)<=0) );
//				System.out.println((calComp.compareTo(calStart)>=0) && (calComp.compareTo(calEnd)<=0));
				if((calComp.compareTo(calStart)>=0) && (calComp.compareTo(calEnd)<=0)){
					result = true ;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		 
		return result ;
	}
	//比较开始时间和结束时间，如果结束之间大于开始时间，返回true
	public boolean compareDate(Date startDate , Date endDate) throws Exception {
		boolean result = false ;
		try{
			Calendar calStart = Calendar.getInstance();
			Calendar calEnd = Calendar.getInstance() ;
			if(startDate!=null && endDate!=null){
				calStart.setTime(startDate);
				calEnd.setTime(endDate);
				if(calEnd.compareTo(calStart)>0){
					result = true ;
				}
			}
			
		}catch(Exception e){
			throw e;
		}
		return result;
	}
	//double 截取小数点后两位
	//将double的数值转换为小数点后两位
	public double getPatternDouble(Double source) throws Exception {
		double result = 0.00 ;
		try{
			String tmp = Double.toString(source) ;
			int n = tmp.indexOf(".") ;
			if(n==-1){
				tmp = tmp+".00" ;
			}
			if(n==0){
				tmp = "0" + tmp ;
			}
			if(n>0){
				String tmpStr = tmp.substring(n,tmp.length());
//				if();
			}
		}catch(Exception e){
			System.out.println("转换出错") ;
			e.printStackTrace() ;
			throw e;
		}
		
		return result ;
	}
	//保留小数点后几位  //如果是3.2  保留4位，则返回值是3.2  ，不会补零，有待改进
	public double getPatternDoublePoint(Double source , int pointLength) throws Exception {
		double result = 0.0 ;
		try{
			StringBuffer sb = new StringBuffer("1");
			if(pointLength>0){
				for(int i=0 ; i<pointLength ; i++){
					sb.append("0");
				}
				int p = Integer.parseInt(sb.toString());
				int tmpDou = (int)(source*p) ;
				result = (double)tmpDou / p ;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return result ;
	}
	//获得截取的字符串
	public String getSubStr(String source , char s ,char e) throws Exception {
		String res = null ;
		try{
			if(source!=null && !("".equals(source))){
				res = source.substring(source.indexOf(s)+1 , source.indexOf(e));
			}
		}catch(Exception me){
			me.printStackTrace();
			throw me;
		}
		return res ;
	}

	//返回一个Calendar是星期几
	public String getWeek(Calendar cal){
		String week = null ;
		try{
			if(cal!=null){
				int n = cal.get(Calendar.DAY_OF_WEEK);
				switch(n){
				case Calendar.MONDAY:
					week = "星期一" ;
					break;
				case Calendar.TUESDAY:
					week = "星期二" ;
					break;
				case Calendar.WEDNESDAY:
					week = "星期三" ;
					break;
				case Calendar.THURSDAY:
					week = "星期四" ;
					break ;
				case Calendar.FRIDAY:
					week = "星期五" ;
					break ;
				case Calendar.SATURDAY:
					week = "星期六" ;
					break ;
				case Calendar.SUNDAY:
					week = "星期日" ;
					break;
				default:
					week="error";
					break;
				}
			}
		}catch(Exception e){
			logger.error("Utils - getWeek 获得日期的星期几出错!",e);
		}
		return week ;
	}
	//返回一个Calendar是星期几
	public String getWeekByDate(Date date){
		String week = null ;
		try{
			Calendar cal = Calendar.getInstance();
			cal.setTime(date) ;
			if(cal!=null){
				int n = cal.get(Calendar.DAY_OF_WEEK);
				switch(n){
				case Calendar.MONDAY:
					week = "星期一" ;
					break;
				case Calendar.TUESDAY:
					week = "星期二" ;
					break;
				case Calendar.WEDNESDAY:
					week = "星期三" ;
					break;
				case Calendar.THURSDAY:
					week = "星期四" ;
					break ;
				case Calendar.FRIDAY:
					week = "星期五" ;
					break ;
				case Calendar.SATURDAY:
					week = "星期六" ;
					break ;
				case Calendar.SUNDAY:
					week = "星期日" ;
					break;
				default:
					week="error";
					break;
				}
			}
		}catch(Exception e){
			logger.error("Utils - getWeek 获得日期的星期几出错!",e);
		}
		return week ;
	}
	
	/**
	 * 检查子字符串是否在元字符串内
	 * @param sourceStr
	 * @param compStr
	 * @return
	 */
	public boolean checkInnerString(String sourceStr , String compStr){
		boolean res = false ;
		if(sourceStr!=null && compStr!=null){
			int n = sourceStr.indexOf(compStr);
			if(n>-1){
				res = true ;
			}
		}
        return res ;
	}
	
	public double formatDouble(double d){
		try{
			return (double)((int)(d*100+0.5))/100;
		}catch(Exception e){
			logger.error("Utils - formatDouble方法出错!"+e);
			return d;
		}
		
	}
	public double formatDouble2(double d){
		try{
			BigDecimal bd = new BigDecimal(d);
			return bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		}catch(Exception e){
			logger.error("Utils - formatDouble2方法出错!"+e);
			return d;
		}
	}
	public static void main(String[] args){
		Utils u = new Utils();
	
		System.out.println(u.formatDouble(12.134000099)) ;
	}
}
