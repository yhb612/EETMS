//package com.base.sys.ops;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//
//import com.base.beans.EmpWork;
//import com.base.beans.StatEmpWork;
//import com.base.utils.Utils;
//
//
//
//public class ExcelOp {
//
//	private Logger logger = Logger.getLogger("info");
//	public void addEmpWorks(String file){
////		PropertyConfigurator.configure("./src/log4j.properties");
//		try{
//			if(file==null || "".equals(file)){
//			 logger.error("中行考勤文件为空！");
//			}else{
//				File f = new File(file);
//				FileInputStream fis = new FileInputStream(f) ;
//				HSSFWorkbook wb = new HSSFWorkbook(fis);
//				HSSFSheet sheet = wb.getSheetAt(0) ;
//	            //行数
//				int rowNum = sheet.getLastRowNum();
//				Utils u = new Utils();
//				DBOp dbo = new DBOp();
//				for(int i=4 ; i<=rowNum ; i++){ //遍历中行考勤记录
//					try{
//						EmpWork ea = new EmpWork();
//						HSSFRow row = sheet.getRow(i);
//						//资源类型
//						HSSFCell cell = row.getCell(0);
//						String type = null ;
//						if(cell == null){
//							type="";
//						}else{
//							type = cell.getStringCellValue();
//						}
//						cell = null ;
//						
//						ea.setSourceType(type);
//						//所属项目
//						cell = row.getCell(1);
//						String proj = null ;
//						if(cell == null){
//							proj = "";
//						}else{
//							proj = cell.getStringCellValue();
//						}
//						
//						cell = null ;
//						ea.setProjName(proj);
//						//项目编号
//						cell = row.getCell(2);
//						String projid = null ;
//						if(cell == null){
//							projid = "" ;
//						}else{
//							projid =  cell.getStringCellValue();
//						}
//						cell = null ;
//						ea.setProjId(projid);
//						//项目所属部门
//						cell = row.getCell(3);
//						String projdept = null;
//						if(cell == null ){
//							projdept = "";
//						}else{
//							projdept = cell.getStringCellValue();
//						}
//						cell = null ;
//						ea.setProjDept(projdept);
//						//服务产品
//						cell = row.getCell(4);
//						String prod  = cell.getStringCellValue();
//						cell = null ;
//						ea.setProdName(prod);
//						//产品所属团队
//						cell = row.getCell(5);
//						String prodgroup = cell.getStringCellValue();
//						cell = null ;
//						ea.setProdGroup(prodgroup);
//						//产品所属部门
//						cell = row.getCell(6);
//						String proddept = cell.getStringCellValue();
//						cell = null ;
//						ea.setProdDept(proddept);
//						//供应商
//						cell = row.getCell(7);
//						String supplier = cell.getStringCellValue();
//						cell = null ;
//						ea.setSupplier(supplier);
//						//技术平台
//						cell = row.getCell(8);
//						String platform = cell.getStringCellValue();
//						cell = null ;
//						ea.setTechPlatform(platform);
//						//姓名
//						cell = row.getCell(9);
//						String name = cell.getStringCellValue();
//						cell = null ;
//						ea.setEmpName(name);
//						//等级
//						cell = row.getCell(10);
//						String level = cell.getStringCellValue();
//						cell = null ;
//						ea.setTechLevel(level);
//						//工作日期
//						cell = row.getCell(11);
//						if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
//							ea.setWorkDate(cell.getStringCellValue());
//						}else if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
//							Date workdate = cell.getDateCellValue();
//							ea.setWorkDate(u.parseDateToString(workdate, null));
//						}else{
//							ea.setWorkDate(null);
//						}
//						cell = null ;
//						//正常工时
//						cell = row.getCell(12);
//						double worktime = cell.getNumericCellValue();
//						cell = null ;
//						ea.setWorkTime(worktime);
//						//加班工时
//						cell = row.getCell(13);
//						double overtime = cell.getNumericCellValue();
//						cell = null ;
//						ea.setOverTime(overtime);	
//						//添加记录
////						if(dbo.ifHasEmpWork(ea)){
////							logger.error("录入员工工时信息出错，人员姓名是："+ea.getEmpName()+" - 工时登记日期："+ea.getWorkDate());
////						}else{
//							dbo.addEmpWork(ea);	
////						}
//						
//					}catch(Exception e){
//						logger.error("Excelop-addZhEmpAtt 插入中行考勤信息记录有误，行号："+(i+1),e);
//					}
//					
//				}//遍历中行考勤记录结束
//			}
//			
//		}catch(Exception e){
//			logger.error("Excelop - addZhEmpAtt方法出错",e);
//		}
//	}
//	
//	/**
//	 * 按照人员等级进行分类排序
//	 * @param list
//	 * @param excelpath
//	 * @param sdate
//	 * @param edate
//	 */
//	public void ExportExcel_bak2(List<StatEmpWork> list , String excelpath , String sdate , String edate){
//		try{
//			List<StatEmpWork> tmpList = new ArrayList<StatEmpWork>();  //初级考勤统计
//			List<StatEmpWork> tmpList2 = new ArrayList<StatEmpWork>(); //中级考勤统计
//			List<StatEmpWork> tmpList3 = new ArrayList<StatEmpWork>(); //高级考勤统计
//			List<StatEmpWork> tmpList4 = new ArrayList<StatEmpWork>();  //无加班工时
//			for(int i=0 ; i<list.size(); i++){
//				StatEmpWork stm = list.get(i);
//				if(stm.getPrimOverTimeSubsidy()!=0){
//					tmpList.add(stm);
//				}else if(stm.getSecondOverTimeSubsidy()!=0){
//					tmpList2.add(stm);
//				}else if(stm.getAdvanceOverTimeSubsidy()!=0){
//					tmpList3.add(stm);
//				}else{
//					tmpList4.add(stm);
//				}
//			}
//			list.clear();
//			for(int i=0 ; i<tmpList.size();i++){
//				StatEmpWork stm = tmpList.get(i);
//				list.add(stm);
//			}
//			for(int i=0 ; i<tmpList2.size();i++){
//				StatEmpWork stm = tmpList2.get(i);
//				list.add(stm);
//			}
//			for(int i=0 ; i<tmpList3.size();i++){
//				StatEmpWork stm = tmpList3.get(i);
//				list.add(stm);
//			}
//			for(int i=0 ; i<tmpList4.size();i++){
//				StatEmpWork stm = tmpList4.get(i);
//				list.add(stm);
//			}
//			HSSFWorkbook wb = new HSSFWorkbook();
//			HSSFSheet sheet = wb.createSheet("统计工时");
//			HSSFRow  row = sheet.createRow(0);
//			HSSFCell cell = row.createCell(0);
//			cell.setCellValue(sdate+"至"+edate+"考勤统计");
//			row = sheet.createRow(1);
//			cell = row.createCell(0);
//			cell.setCellValue("序号");
//			cell = row.createCell(1);
//			cell.setCellValue("姓名");
//			cell = row.createCell(2);
//			cell.setCellValue("登记开始日期");
//			cell = row.createCell(3);
//			cell.setCellValue("登记结束日期");
//			cell = row.createCell(4);
//			cell.setCellValue("差异日期集合");
//			cell = row.createCell(5);
//			cell.setCellValue("加班可补贴工时");
//			cell = row.createCell(6);
//			cell.setCellValue("初级加班可补贴工时");
//			cell = row.createCell(7);
//			cell.setCellValue("中级加班可补贴工时");
//			cell = row.createCell(8);
//			cell.setCellValue("高级加班可补贴工时");
//			cell = row.createCell(9);
//			cell.setCellValue("双休日加班可倒休工时");
//			cell = row.createCell(10);
//			cell.setCellValue("法定假日加班可倒休工时");
//			cell = row.createCell(11);
//			cell.setCellValue("应出勤天数");
//			cell = row.createCell(12);
//			cell.setCellValue("工作日出勤天数");
//			cell = row.createCell(13);
//			cell.setCellValue("双休日出勤天数");
//			cell = row.createCell(14);
//			cell.setCellValue("法定假日出勤天数");
//			cell = row.createCell(15);
//			cell.setCellValue("正常工时合计");
//			cell = row.createCell(16);
//			cell.setCellValue("加班工时合计");
//			cell = row.createCell(17);
//			cell.setCellValue("工作日加班工时");
//			cell = row.createCell(18);
//			cell.setCellValue("双休日加班工时");
//			cell = row.createCell(19);
//			cell.setCellValue("法定假日加班工时");
//			cell = row.createCell(20);
//			cell.setCellValue("人员等级");
//			for(int i=0 ; i<list.size() ; i++){
//				StatEmpWork  sew = list.get(i) ;
//				row = sheet.createRow(i+2);
//				cell = row.createCell(0);
//				cell.setCellValue(sew.getNum());  //序号
//				cell = row.createCell(1);
//				cell.setCellValue(sew.getEmpName());  //姓名
//				cell = row.createCell(2);
//				cell.setCellValue(sew.getWorkStartDate() );  //考勤开始日期
//				cell = row.createCell(3);
//				cell.setCellValue(sew.getWorkEndDate() );  //考勤结束日期
//				cell = row.createCell(4);
//				cell.setCellValue(sew.getErrorDates() );  //未出勤日期集合
//				cell = row.createCell(5);
//				cell.setCellValue(sew.getOverTimeSubsidy() );    //加班可补贴工时
//				cell = row.createCell(6);
//				cell.setCellValue(sew.getPrimOverTimeSubsidy() );    //初级加班可补贴工时
//				cell = row.createCell(7);
//				cell.setCellValue(sew.getSecondOverTimeSubsidy() );    //中级加班可补贴工时
//				cell = row.createCell(8);
//				cell.setCellValue(sew.getAdvanceOverTimeSubsidy() );    //高级加班可补贴工时
//				cell = row.createCell(9);
//				cell.setCellValue(sew.getExTimeWeekend() );   //双休日加班可倒休工时
//				cell = row.createCell(10);
//				cell.setCellValue(sew.getExTimeVacation() );  //法定假日加班可倒休工时
//				cell = row.createCell(11);
//				cell.setCellValue(sew.getShouldAttDays() );  //应出勤天数
//				cell = row.createCell(12);
//				cell.setCellValue(sew.getWorkAttDays() );  //工作日出勤天数
//				cell = row.createCell(13);
//				cell.setCellValue(sew.getWeekendAttDays() );  //双休日出勤天数
//				cell = row.createCell(14);
//				cell.setCellValue(sew.getVacationAttDays() );  //法定假日出勤天数
//				cell = row.createCell(15);
//				cell.setCellValue(sew.getWorkTimeAll() );  //正常工时合计
//				cell = row.createCell(16);
//				cell.setCellValue(sew.getOverTimeAll() ); // 加班工时合计
//				cell = row.createCell(17);
//				cell.setCellValue(sew.getOverTimeWorkday() );  //工作日加班工时
//				cell = row.createCell(18);
//				cell.setCellValue(sew.getOverTimeWeekend() );  //双休日加班工时
//				cell = row.createCell(19);
//				cell.setCellValue(sew.getOverTimeVacation() );  //法定假日加班工时
//				cell = row.createCell(20);    //设置等级
//				String level = "";
//				if(sew.getPrimOverTimeSubsidy()!=0){
//					level = "初级";
//				}else if(sew.getSecondOverTimeSubsidy()!=0){
//					level = "中级" ;
//				}else if(sew.getAdvanceOverTimeSubsidy()!=0){
//					level = "高级" ;
//				}else{
//					level = "无加班";
//				}
//				cell.setCellValue(level );  //法定假日加班工时
//			}
//			//File f = new File(excelpath);
//			FileOutputStream fos = new FileOutputStream(excelpath);
//			wb.write(fos);
//			fos.close();
//			System.out.println("导出完成");
//		}catch(Exception e){
//			e.printStackTrace();
//			logger.error("导入excel出错",e);
//		}
//	}
//	//将StatEmpWork导入到excel
//	public void ExportExcel(List<StatEmpWork> list , String excelpath , String sdate , String edate,String grade){
//		try{
//			
//			HSSFWorkbook wb = new HSSFWorkbook();
//			HSSFSheet sheet = wb.createSheet("统计工时");
//			HSSFRow  row = sheet.createRow(0);
//			HSSFCell cell = row.createCell(0);
//			if (grade==null || "".equals(grade)){
//				grade="全部等级";
//			}
//			cell.setCellValue(sdate+"至"+edate+"的 "+grade+" 员工考勤统计");
//			row = sheet.createRow(1);
//			cell = row.createCell(0);
//			cell.setCellValue("序号");
//			cell = row.createCell(1);
//			cell.setCellValue("姓名");
//			cell = row.createCell(2);
//			cell.setCellValue("登记开始日期");
//			cell = row.createCell(3);
//			cell.setCellValue("登记结束日期");
//			cell = row.createCell(4);
//			cell.setCellValue("差异日期集合");
//			cell = row.createCell(5);
//			cell.setCellValue("加班可补贴工时");
//			cell = row.createCell(6);
//			cell.setCellValue("初级加班可补贴工时");
//			cell = row.createCell(7);
//			cell.setCellValue("中级加班可补贴工时");
//			cell = row.createCell(8);
//			cell.setCellValue("高级加班可补贴工时");
//			cell = row.createCell(9);
//			cell.setCellValue("双休日加班可倒休工时");
//			cell = row.createCell(10);
//			cell.setCellValue("法定假日加班可倒休工时");
//			cell = row.createCell(11);
//			cell.setCellValue("应出勤天数");
//			cell = row.createCell(12);
//			cell.setCellValue("工作日出勤天数");
//			cell = row.createCell(13);
//			cell.setCellValue("双休日出勤天数");
//			cell = row.createCell(14);
//			cell.setCellValue("法定假日出勤天数");
//			cell = row.createCell(15);
//			cell.setCellValue("正常工时合计");
//			cell = row.createCell(16);
//			cell.setCellValue("加班工时合计");
//			cell = row.createCell(17);
//			cell.setCellValue("工作日加班工时");
//			cell = row.createCell(18);
//			cell.setCellValue("双休日加班工时");
//			cell = row.createCell(19);
//			cell.setCellValue("法定假日加班工时");
//			for(int i=0 ; i<list.size() ; i++){
//				StatEmpWork  sew = list.get(i) ;
//				row = sheet.createRow(i+2);
//				cell = row.createCell(0);
//				cell.setCellValue(sew.getNum());  //序号
//				cell = row.createCell(1);
//				cell.setCellValue(sew.getEmpName());  //姓名
//				cell = row.createCell(2);
//				cell.setCellValue(sew.getWorkStartDate() );  //考勤开始日期
//				cell = row.createCell(3);
//				cell.setCellValue(sew.getWorkEndDate() );  //考勤结束日期
//				cell = row.createCell(4);
//				cell.setCellValue(sew.getErrorDates() );  //未出勤日期集合
//				cell = row.createCell(5);
//				cell.setCellValue(sew.getOverTimeSubsidy() );    //加班可补贴工时
//				cell = row.createCell(6);
//				cell.setCellValue(sew.getPrimOverTimeSubsidy() );    //初级加班可补贴工时
//				cell = row.createCell(7);
//				cell.setCellValue(sew.getSecondOverTimeSubsidy() );    //中级加班可补贴工时
//				cell = row.createCell(8);
//				cell.setCellValue(sew.getAdvanceOverTimeSubsidy() );    //高级加班可补贴工时
//				cell = row.createCell(9);
//				cell.setCellValue(sew.getExTimeWeekend() );   //双休日加班可倒休工时
//				cell = row.createCell(10);
//				cell.setCellValue(sew.getExTimeVacation() );  //法定假日加班可倒休工时
//				cell = row.createCell(11);
//				cell.setCellValue(sew.getShouldAttDays() );  //应出勤天数
//				cell = row.createCell(12);
//				cell.setCellValue(sew.getWorkAttDays() );  //工作日出勤天数
//				cell = row.createCell(13);
//				cell.setCellValue(sew.getWeekendAttDays() );  //双休日出勤天数
//				cell = row.createCell(14);
//				cell.setCellValue(sew.getVacationAttDays() );  //法定假日出勤天数
//				cell = row.createCell(15);
//				cell.setCellValue(sew.getWorkTimeAll() );  //正常工时合计
//				cell = row.createCell(16);
//				cell.setCellValue(sew.getOverTimeAll() ); // 加班工时合计
//				cell = row.createCell(17);
//				cell.setCellValue(sew.getOverTimeWorkday() );  //工作日加班工时
//				cell = row.createCell(18);
//				cell.setCellValue(sew.getOverTimeWeekend() );  //双休日加班工时
//				cell = row.createCell(19);
//				cell.setCellValue(sew.getOverTimeVacation() );  //法定假日加班工时
//				
//			}
//			//File f = new File(excelpath);
//			FileOutputStream fos = new FileOutputStream(excelpath);
//			wb.write(fos);
//			fos.close();
//			System.out.println("导出完成");
//		}catch(Exception e){
//			e.printStackTrace();
//			logger.error("导入excel出错",e);
//		}
//	}
//	
//	public static void main(String[] args){
//		ExcelOp eo = new ExcelOp();
//		String filePath = "f:/excels/员工工时测试.xls";
//		eo.addEmpWorks(filePath);
//		System.out.println("录入完成");
//	}
//}
