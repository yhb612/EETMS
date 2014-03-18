package com.iss.gms.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.iss.gms.entity.CustomerVerifyHoursInfo;
import com.iss.gms.entity.EmployeeHoursDetailInfo;

public class GmsUtils {

	  //格式化日期类型
		public static Date gmsFormToDate(String str)throws Exception{
			SimpleDateFormat a=new  SimpleDateFormat("yyyy-MM-dd");
			Date mydate=a.parse(str);
			return mydate;
		}
		
		//格式化日期类型
		public static String gmsFormToString(Date d)throws Exception{
			SimpleDateFormat a=new  SimpleDateFormat("yyyy-MM-dd");
			String str=a.format(d);
			return str;
		}
		
		//计算(司龄、工龄)
		public static String calculate(String inputDate,String arg)throws ParseException{
			//当前时间
			Date n = new Date(); 
			//输入时间
			Date m = new Date(); 
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			m=df.parse(inputDate);
			long a = n.getTime(); 
			long b = m.getTime();
			long s= (a-b)/(1000); 
			int hh=(int)s/3600; //共计小时数 
			int dd=(int)hh/24; //共计天数
			String p=null;
			if("m".equals(arg)){
				//"司龄(月)
				int mt=(int)dd/30;
				p=String.valueOf(mt);
			}else if("y".equals(arg)){
				//工龄(年)
				int mt=(int)dd/365;
				p=String.valueOf(mt);
			}
			return p;
		}
		
	  //读取excel ;参数:firstRowNum4Read:需要读取的第一条数据，0为起始;   creat By li,dongming
	  public static List<String []> readExcel(int firstRowNum4Read,InputStream input,String version) throws Exception {
		  List<String []> rowList = new ArrayList<String []>();
		  //office2003以前版本,文件后缀.xls
		  if(version.equals("2003")){
			  HSSFWorkbook wb = new HSSFWorkbook(input);
			  HSSFSheet sheet = wb.getSheetAt(0);
			  int rowCount = sheet.getPhysicalNumberOfRows();//获取行数
			  int colCount = sheet.getRow(firstRowNum4Read-1).getPhysicalNumberOfCells();//获取（非空的）列总数，firstRowNum4Read-1默认为获取抬头行
			  for(int i=firstRowNum4Read;i<rowCount;i++){
				  HSSFRow row = sheet.getRow(i);
				  String[] colArr = new String[colCount];
				  for(int j=0;j<colCount;j++){
					//根据cell里的数据类型进行相应处理
						if(row.getCell(j)==null) continue;
						switch (row.getCell(j).getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								if (DateUtil.isCellDateFormatted(row.getCell(j))) {
									Date theDate = row.getCell(j).getDateCellValue();
									colArr[j] = gmsFormToString(theDate);
								} else {
									colArr[j] = Double.toString(row.getCell(j)
											.getNumericCellValue());
								}
								break;
							case Cell.CELL_TYPE_BLANK:
								colArr[j] = "";
								break;
							case Cell.CELL_TYPE_BOOLEAN:
								colArr[j] = row.getCell(j).getBooleanCellValue() + "";
								break;
							case Cell.CELL_TYPE_ERROR:
								colArr[j] = row.getCell(j).getErrorCellValue() + "";
								break;
							case Cell.CELL_TYPE_FORMULA:
								colArr[j] = row.getCell(j).getCellFormula();
								break;
							default:
								colArr[j] = row.getCell(j).getStringCellValue();
						}
				  }
				  rowList.add(colArr);
			  }
		  }else if(version.equals("2007")){//office2007版本,文件后缀.xlsx
			  XSSFWorkbook wb = new XSSFWorkbook(input);//获得excel工作薄
			  XSSFSheet sheet = wb.getSheetAt(0); //只导入第一个sheet里的内容
			  int rowCount = sheet.getPhysicalNumberOfRows();//获取行数
			  int colCount = sheet.getRow(firstRowNum4Read-1).getPhysicalNumberOfCells();//获取列数，默认第一行为抬头行
			  //从指定的行数(firstRowNum4Read)开始解析
			  for(int i=firstRowNum4Read;i<rowCount;i++){
				  XSSFRow row = sheet.getRow(i);
				  //空行--跳过
				  if(row==null ) continue;
				  String[] colArr = new String[colCount];
					  for(int j=0;j<colCount;j++){
						  //根据cell里的数据类型进行相应处理
						if(row.getCell(j)==null) continue;
						switch (row.getCell(j).getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								if (DateUtil.isCellDateFormatted(row.getCell(j))) {
									Date theDate = row.getCell(j).getDateCellValue();
									colArr[j] = gmsFormToString(theDate);
								} else {
									colArr[j] = Double.toString(row.getCell(j)
											.getNumericCellValue());
								}
								break;
							case Cell.CELL_TYPE_BLANK:
								colArr[j] = "";
								break;
							case Cell.CELL_TYPE_BOOLEAN:
								colArr[j] = row.getCell(j).getBooleanCellValue() + "";
								break;
							case Cell.CELL_TYPE_ERROR:
								colArr[j] = row.getCell(j).getErrorCellValue() + "";
								break;
							case Cell.CELL_TYPE_FORMULA:
								colArr[j] = row.getCell(j).getCellFormula();
								break;
							default:
								colArr[j] = row.getCell(j).getStringCellValue();
						}
					  }
					  rowList.add(colArr);
			  }
		  }
		  //返回一个list<String []> 其中的每一个String[]内是该行所有字段的值
		  input.close();
		  return rowList;
	  }
	  
	  
	  //导出“GAP工时核查信息”至excel
	  public static HSSFWorkbook  exportExcel(List<EmployeeHoursDetailInfo> ehdiList) throws Exception{
				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFSheet sheet = wb.createSheet("工时核查");
				
				//设置样式
				HSSFCellStyle cellStyle1 = wb.createCellStyle();//红底蓝字
				 Font font1 =  wb.createFont();//蓝字
				 font1.setColor(HSSFColor.BLUE.index);
				 font1.setBoldweight(Font.BOLDWEIGHT_BOLD); 
				cellStyle1.setFont(font1);
				cellStyle1.setFillForegroundColor((short)10);
				cellStyle1.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cellStyle1.setRightBorderColor(HSSFColor.LIGHT_GREEN.index);
				cellStyle1.setBottomBorderColor(HSSFColor.LIGHT_GREEN.index);
				cellStyle1.setBorderRight((short)1);
				cellStyle1.setBorderBottom((short)1);
				
				HSSFCellStyle cellStyle2 = wb.createCellStyle();//黄底蓝字
				cellStyle2.setFont(font1);
				cellStyle2.setFillForegroundColor((short)5);
				cellStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cellStyle2.setRightBorderColor(HSSFColor.LIGHT_GREEN.index);
				cellStyle2.setBottomBorderColor(HSSFColor.LIGHT_GREEN.index);
				cellStyle2.setBorderRight((short)1);
				cellStyle2.setBorderBottom((short)1);
				
				HSSFCellStyle cellStyle3 = wb.createCellStyle();//浅蓝底黑字
				Font font2 = wb.createFont();
				font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
				cellStyle3.setFont(font2);
				cellStyle3.setFillForegroundColor((short)12);
				cellStyle3.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cellStyle3.setRightBorderColor(HSSFColor.LIGHT_GREEN.index);
				cellStyle3.setBottomBorderColor(HSSFColor.LIGHT_GREEN.index);
				cellStyle3.setBorderRight((short)1);
				cellStyle3.setBorderBottom((short)1);
				
				//设置sheet抬头样式
				HSSFCellStyle cellStyle = wb.createCellStyle();//墨绿底蓝字
				 Font font =  wb.createFont();
				 font.setColor(HSSFColor.BLUE.index);//蓝字
				 font.setBoldweight(Font.BOLDWEIGHT_BOLD); 
				cellStyle.setFont(font);
				cellStyle.setFillForegroundColor((short)50);
				cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cellStyle.setBorderRight((short)1);
				cellStyle.setBorderBottom((short)1);
				
				//特殊数据标红
				HSSFCellStyle cellStyle4Red = wb.createCellStyle();
				 Font font4red =  wb.createFont();
				 font4red.setColor(HSSFColor.RED.index);
				 cellStyle4Red.setFont(font4red);
				 
				//生成EXCEL下拉列表(开始)
					String[] textlist={
							 "0-迟到早退","1-现场储备","2-回公司开会","3-离场","4-公司储备","5-打卡异常","6-闲置","7-正常倒休","8-重复工时追加倒休"
							};
					String[] textlist1={"工作登记或加班单未审批","转项目","系统问题","其他问题"};
					
					CellRangeAddressList regions = new CellRangeAddressList(1,ehdiList.size(),27,27);
					CellRangeAddressList regions1 = new CellRangeAddressList(1,ehdiList.size(),30,30);
					
					//生成下拉框内容   
					DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);
					DVConstraint constraint1 = DVConstraint.createExplicitListConstraint(textlist1);
					//绑定下拉框和作用区域   
					HSSFDataValidation data_validation = new HSSFDataValidation(regions,constraint);     
					HSSFDataValidation data_validation1 = new HSSFDataValidation(regions1,constraint1);     
					//对sheet页生效   
					sheet.addValidationData(data_validation);
					sheet.addValidationData(data_validation1);
					
					//生成EXCEL下拉列表(结束)
				 
				
				HSSFRow  row = sheet.createRow(0);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue("员工编号");cell.setCellStyle(cellStyle);
				cell = row.createCell(1);
				cell.setCellValue("员工姓名");cell.setCellStyle(cellStyle);
				cell = row.createCell(2);
				cell.setCellValue("工作日期");cell.setCellStyle(cellStyle);
				cell = row.createCell(3);
				cell.setCellValue("星期");cell.setCellStyle(cellStyle);
				cell = row.createCell(4);
				cell.setCellValue("日期类型");cell.setCellStyle(cellStyle);
				cell = row.createCell(5);
				cell.setCellValue("应出勤工时");cell.setCellStyle(cellStyle);
				cell = row.createCell(6);
				cell.setCellValue("PSA请假工时");cell.setCellStyle(cellStyle);
				cell = row.createCell(7);
				cell.setCellValue("PSA请假类型");cell.setCellStyle(cellStyle);
				cell = row.createCell(8);
				cell.setCellValue("新增可倒休工时");cell.setCellStyle(cellStyle);
				cell = row.createCell(9);
				cell.setCellValue("新增可补贴工时");cell.setCellStyle(cellStyle);
				cell = row.createCell(10);
				cell.setCellValue("差异工时");cell.setCellStyle(cellStyle);
				cell = row.createCell(11);
				cell.setCellValue("资源类型");cell.setCellStyle(cellStyle1);
				cell = row.createCell(12);
				cell.setCellValue("项目名称");cell.setCellStyle(cellStyle1);
				cell = row.createCell(13);
				cell.setCellValue("项目编号");cell.setCellStyle(cellStyle1);
				cell = row.createCell(14);
				cell.setCellValue("项目所属部门");cell.setCellStyle(cellStyle1);
				cell = row.createCell(15);
				cell.setCellValue("产品名称");cell.setCellStyle(cellStyle1);
				cell = row.createCell(16);
				cell.setCellValue("服务产品所属团队");cell.setCellStyle(cellStyle1);
				cell = row.createCell(17);
				cell.setCellValue("服务产品归属部门");cell.setCellStyle(cellStyle1);
				cell = row.createCell(18);
				cell.setCellValue("供应商");cell.setCellStyle(cellStyle1);
				cell = row.createCell(19);
				cell.setCellValue("技术平台");cell.setCellStyle(cellStyle1);
				cell = row.createCell(20);
				cell.setCellValue("在场类型");cell.setCellStyle(cellStyle1);
				cell = row.createCell(21);cell.setCellStyle(cellStyle1);
				cell.setCellValue("工作地点");cell.setCellStyle(cellStyle1);
				cell = row.createCell(22);
				cell.setCellValue("姓名");cell.setCellStyle(cellStyle1);
				cell = row.createCell(23);
				cell.setCellValue("技术等级");cell.setCellStyle(cellStyle1);
				cell = row.createCell(24);
				cell.setCellValue("工作日期");cell.setCellStyle(cellStyle1);
				cell = row.createCell(25);
				cell.setCellValue("系统中正常工时");cell.setCellStyle(cellStyle1);
				cell = row.createCell(26);
				cell.setCellValue("系统中加班工时");cell.setCellStyle(cellStyle1);
				cell = row.createCell(27);
				cell.setCellValue("差异类型");cell.setCellStyle(cellStyle);
				cell = row.createCell(28);
				cell.setCellValue("厂商反馈正常工时差异");cell.setCellStyle(cellStyle2);
				cell = row.createCell(29);
				cell.setCellValue("厂商反馈加班工时差异");cell.setCellStyle(cellStyle2);
				cell = row.createCell(30);
				cell.setCellValue("问题原因");cell.setCellStyle(cellStyle2);
				cell = row.createCell(31);
				cell.setCellValue("厂商反馈的差异说明");cell.setCellStyle(cellStyle2);
				cell = row.createCell(32);
				cell.setCellValue("是否为新增工时明细");cell.setCellStyle(cellStyle2);
				cell = row.createCell(33);
				cell.setCellValue("其他错误问题");cell.setCellStyle(cellStyle3);
				cell = row.createCell(34);
				cell.setCellValue("正确值");cell.setCellStyle(cellStyle3);
				cell = row.createCell(35);
				cell.setCellValue("客户方负责人");cell.setCellStyle(cellStyle);
				cell = row.createCell(36);
				cell.setCellValue("公司项目经理");cell.setCellStyle(cellStyle);
				cell = row.createCell(37);
				cell.setCellValue("剩余倒休工时(小时)");cell.setCellStyle(cellStyle);
				
				for(int i=0 ; i<ehdiList.size() ; i++){
					EmployeeHoursDetailInfo  ehdi = ehdiList.get(i) ;
					row = sheet.createRow(i+1);
					cell = row.createCell(0);
					cell.setCellValue(ehdi.getEmployeeId());  //员工编号
					cell = row.createCell(1);
					cell.setCellValue(ehdi.getEmployeeName());  //员工姓名
					cell = row.createCell(2);
					cell.setCellValue(ehdi.getStandardDate()==null ? "":gmsFormToString(ehdi.getStandardDate()));  //日期
					cell = row.createCell(3);
					cell.setCellValue(ehdi.getStandardWeek() );  //星期
					cell = row.createCell(4);
					if("0".equals(ehdi.getStandardDateType())){
						cell.setCellValue("工作日");  //日期类型
					}else if("1".equals(ehdi.getStandardDateType())){
						cell.setCellValue("双休日");  //日期类型
					}else if("2".equals(ehdi.getStandardDateType())){
						cell.setCellValue("节假日");  //日期类型
					}
					cell = row.createCell(5);
					cell.setCellValue(ehdi.getStandardHours() );    //应出勤工时
					cell = row.createCell(6);
					cell.setCellValue(ehdi.getPsaLeaveHours()==null?"":ehdi.getPsaLeaveHours().toString() );   //PSA请假工时
					cell = row.createCell(7);
					cell.setCellValue(ehdi.getPsaLeaveHoursType() );  //PSA请假类型
					cell = row.createCell(8);
					cell.setCellValue(ehdi.getCurrentAhughHours()==null?"":ehdi.getCurrentAhughHours().toString() );  //新增可倒休工时
					cell = row.createCell(9);
					cell.setCellValue(ehdi.getCurrentSubsidiesHours()==null?"":ehdi.getCurrentSubsidiesHours().toString() );  //新增可补贴工时
					cell = row.createCell(10);
					cell.setCellValue(ehdi.getCurrentGapHours()==null?"":ehdi.getCurrentGapHours().toString() );  //差异工时
					if(ehdi.getCurrentGapHours()!=null&&ehdi.getCurrentGapHours()!=0) cell.setCellStyle(cellStyle4Red);
					cell = row.createCell(11);
					cell.setCellValue(ehdi.getResourcesType()==null ? "":ehdi.getResourcesType());//资源类型
					cell = row.createCell(12);
					cell.setCellValue(ehdi.getProjectName()==null ? "":ehdi.getProjectName());//项目名称
					cell = row.createCell(13);
					cell.setCellValue(ehdi.getProjectId()==null ? "":ehdi.getProjectId());//项目编号
					cell = row.createCell(14);
					cell.setCellValue(ehdi.getProjectOfDepartment()==null ? "":ehdi.getProjectOfDepartment());//项目所属部门
					cell = row.createCell(15);
					cell.setCellValue(ehdi.getProductName()==null ? "":ehdi.getProductName());//产品名称
					cell = row.createCell(16);
					cell.setCellValue(ehdi.getServiceProductsAttributiveTeam()==null ? "":ehdi.getServiceProductsAttributiveTeam());//服务团队
					cell = row.createCell(17);
					cell.setCellValue(ehdi.getServiceProductsDepartment()==null ? "":ehdi.getServiceProductsDepartment());//服务部门
					cell = row.createCell(18);
					cell.setCellValue(ehdi.getProviders()==null ? "":ehdi.getProviders());//供应商
					cell = row.createCell(19);
					cell.setCellValue(ehdi.getTechnologyPlatform()==null ? "":ehdi.getTechnologyPlatform());//技术平台
					cell = row.createCell(20);
					cell.setCellValue(ehdi.getPresenceOfType()==null ? "":ehdi.getPresenceOfType());//在场类型
					cell = row.createCell(21);
					cell.setCellValue(ehdi.getWorkPlace()==null ? "":ehdi.getWorkPlace());//工作地点
					cell = row.createCell(22);
					cell.setCellValue(ehdi.getEmpName4Cus()==null ? "":ehdi.getEmpName4Cus());//客户方名称
					cell = row.createCell(23);
					cell.setCellValue(ehdi.getTechnologyGrade()==null ? "":ehdi.getTechnologyGrade());//技术等级
					cell = row.createCell(24);
					cell.setCellValue(ehdi.getStandardDate()==null ? "":gmsFormToString(ehdi.getStandardDate()));//工作日期
					cell = row.createCell(25);
					cell.setCellValue(ehdi.getNormalWorkingHours()==null ? "":ehdi.getNormalWorkingHours().toString());//正常工时
					cell = row.createCell(26);
					cell.setCellValue(ehdi.getOverTimeHours()==null ? "":ehdi.getOverTimeHours().toString());//加班工时
					cell = row.createCell(27); //差异类型
					if(ehdi.getGapType()!=null&&ehdi.getGapType().trim().length()==1){
						for(String tem:textlist){
							if(tem.startsWith(ehdi.getGapType())){
								cell.setCellValue(tem);
							}
						}
					}
					cell = row.createCell(30);
					cell.setCellValue(ehdi.getEmployeeGapReason()==null ? "":ehdi.getEmployeeGapReason());//问题原因
					cell = row.createCell(35);
					cell.setCellValue(ehdi.getCustomerResponsiblePerson()==null ? "":ehdi.getCustomerResponsiblePerson());//客户方负责人
					cell = row.createCell(36);
					cell.setCellValue(ehdi.getCompanyProjectManager()==null ? "":ehdi.getCompanyProjectManager());//公司项目经理
					cell = row.createCell(37);
					cell.setCellValue(ehdi.getHours4VacTotal()==null ? "":ehdi.getHours4VacTotal().toString());//剩余倒休
					
				}
//				FileOutputStream fos = new FileOutputStream(excelpath);
//				wb.write(fos);
//				fos.close();
				return wb;
		}
	  
	  
	  
	  //获取两个日期之间的所有日期
	  public static List<Date> getBetweenDates(Date sdate,Date edate){
		  List<Date> list = new ArrayList<Date>();
	          int s = (int)((edate.getTime() - sdate.getTime())/ (24 * 60 * 60 * 1000)); 
	          if(s>0){ 
	            for(int i = 0;i<=s;i++){ 
	              long todayDate = sdate.getTime() + i * 24 * 60 * 60 * 1000; 
	              Date tmDate = new Date(todayDate); 
	              list.add(tmDate); 
	            } 
	          }
		  return list;
	  }
	  
	  //url传值转码
	  public static String fix2utf(String str) throws Exception{
		  String newStr =new String (str.getBytes("iso-8859-1"),"utf-8");
		  return newStr;
	  }
	  
	  //获取指定月份的最后一天
	  public static Date getLastDate(int year,int month){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cld = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();  
			// 不加下面2行，就是取当前时间前一个月的第一天及最后一天
			cal.set(Calendar.YEAR,year);
			cal.set(Calendar.MONTH, month);
			//获取最后一天
			cal.set(Calendar.DAY_OF_MONTH, 1);  
			cal.add(Calendar.DAY_OF_MONTH, -1); 
			Date lastDate = cal.getTime(); 
			//获取第一天
//			cal.set(Calendar.DAY_OF_MONTH, 1);
//			Date firstDate = cal.getTime();
			return lastDate ;
		}
	  
	  //判断是否是数字
	  public static boolean isNum(String str){return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");	}
	  
	//比较一个日期是否在两个日期之间 ，在日期之间返回true，否则返回false，和两个日期中的一个相同也返回true，
		public static boolean compareDateInner(Date compDate , Date startDate , Date endDate) throws Exception {
			boolean result = false ;
			try{
				Calendar calComp = Calendar.getInstance() ;
				Calendar calStart = Calendar.getInstance();
				Calendar calEnd = Calendar.getInstance() ;
				if(compDate!=null && startDate!=null && endDate!=null){
					calComp.setTime(compDate);
					calStart.setTime(startDate) ;
					calEnd.setTime(endDate);
//					System.out.println((calComp.compareTo(calStart)>=0) + "/"+(calComp.compareTo(calEnd)<=0) );
//					System.out.println((calComp.compareTo(calStart)>=0) && (calComp.compareTo(calEnd)<=0));
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
		//保留两位小数 返回String
		public static  String formatDouble(Double d){
			DecimalFormat df = new DecimalFormat("#.00");
			String f=df.format(d);
			return f;
		}
}
