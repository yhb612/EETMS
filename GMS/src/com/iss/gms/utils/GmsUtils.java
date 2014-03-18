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

	  //��ʽ����������
		public static Date gmsFormToDate(String str)throws Exception{
			SimpleDateFormat a=new  SimpleDateFormat("yyyy-MM-dd");
			Date mydate=a.parse(str);
			return mydate;
		}
		
		//��ʽ����������
		public static String gmsFormToString(Date d)throws Exception{
			SimpleDateFormat a=new  SimpleDateFormat("yyyy-MM-dd");
			String str=a.format(d);
			return str;
		}
		
		//����(˾�䡢����)
		public static String calculate(String inputDate,String arg)throws ParseException{
			//��ǰʱ��
			Date n = new Date(); 
			//����ʱ��
			Date m = new Date(); 
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			m=df.parse(inputDate);
			long a = n.getTime(); 
			long b = m.getTime();
			long s= (a-b)/(1000); 
			int hh=(int)s/3600; //����Сʱ�� 
			int dd=(int)hh/24; //��������
			String p=null;
			if("m".equals(arg)){
				//"˾��(��)
				int mt=(int)dd/30;
				p=String.valueOf(mt);
			}else if("y".equals(arg)){
				//����(��)
				int mt=(int)dd/365;
				p=String.valueOf(mt);
			}
			return p;
		}
		
	  //��ȡexcel ;����:firstRowNum4Read:��Ҫ��ȡ�ĵ�һ�����ݣ�0Ϊ��ʼ;   creat By li,dongming
	  public static List<String []> readExcel(int firstRowNum4Read,InputStream input,String version) throws Exception {
		  List<String []> rowList = new ArrayList<String []>();
		  //office2003��ǰ�汾,�ļ���׺.xls
		  if(version.equals("2003")){
			  HSSFWorkbook wb = new HSSFWorkbook(input);
			  HSSFSheet sheet = wb.getSheetAt(0);
			  int rowCount = sheet.getPhysicalNumberOfRows();//��ȡ����
			  int colCount = sheet.getRow(firstRowNum4Read-1).getPhysicalNumberOfCells();//��ȡ���ǿյģ���������firstRowNum4Read-1Ĭ��Ϊ��ȡ̧ͷ��
			  for(int i=firstRowNum4Read;i<rowCount;i++){
				  HSSFRow row = sheet.getRow(i);
				  String[] colArr = new String[colCount];
				  for(int j=0;j<colCount;j++){
					//����cell����������ͽ�����Ӧ����
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
		  }else if(version.equals("2007")){//office2007�汾,�ļ���׺.xlsx
			  XSSFWorkbook wb = new XSSFWorkbook(input);//���excel������
			  XSSFSheet sheet = wb.getSheetAt(0); //ֻ�����һ��sheet�������
			  int rowCount = sheet.getPhysicalNumberOfRows();//��ȡ����
			  int colCount = sheet.getRow(firstRowNum4Read-1).getPhysicalNumberOfCells();//��ȡ������Ĭ�ϵ�һ��Ϊ̧ͷ��
			  //��ָ��������(firstRowNum4Read)��ʼ����
			  for(int i=firstRowNum4Read;i<rowCount;i++){
				  XSSFRow row = sheet.getRow(i);
				  //����--����
				  if(row==null ) continue;
				  String[] colArr = new String[colCount];
					  for(int j=0;j<colCount;j++){
						  //����cell����������ͽ�����Ӧ����
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
		  //����һ��list<String []> ���е�ÿһ��String[]���Ǹ��������ֶε�ֵ
		  input.close();
		  return rowList;
	  }
	  
	  
	  //������GAP��ʱ�˲���Ϣ����excel
	  public static HSSFWorkbook  exportExcel(List<EmployeeHoursDetailInfo> ehdiList) throws Exception{
				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFSheet sheet = wb.createSheet("��ʱ�˲�");
				
				//������ʽ
				HSSFCellStyle cellStyle1 = wb.createCellStyle();//�������
				 Font font1 =  wb.createFont();//����
				 font1.setColor(HSSFColor.BLUE.index);
				 font1.setBoldweight(Font.BOLDWEIGHT_BOLD); 
				cellStyle1.setFont(font1);
				cellStyle1.setFillForegroundColor((short)10);
				cellStyle1.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cellStyle1.setRightBorderColor(HSSFColor.LIGHT_GREEN.index);
				cellStyle1.setBottomBorderColor(HSSFColor.LIGHT_GREEN.index);
				cellStyle1.setBorderRight((short)1);
				cellStyle1.setBorderBottom((short)1);
				
				HSSFCellStyle cellStyle2 = wb.createCellStyle();//�Ƶ�����
				cellStyle2.setFont(font1);
				cellStyle2.setFillForegroundColor((short)5);
				cellStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cellStyle2.setRightBorderColor(HSSFColor.LIGHT_GREEN.index);
				cellStyle2.setBottomBorderColor(HSSFColor.LIGHT_GREEN.index);
				cellStyle2.setBorderRight((short)1);
				cellStyle2.setBorderBottom((short)1);
				
				HSSFCellStyle cellStyle3 = wb.createCellStyle();//ǳ���׺���
				Font font2 = wb.createFont();
				font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
				cellStyle3.setFont(font2);
				cellStyle3.setFillForegroundColor((short)12);
				cellStyle3.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cellStyle3.setRightBorderColor(HSSFColor.LIGHT_GREEN.index);
				cellStyle3.setBottomBorderColor(HSSFColor.LIGHT_GREEN.index);
				cellStyle3.setBorderRight((short)1);
				cellStyle3.setBorderBottom((short)1);
				
				//����sheeţͷ��ʽ
				HSSFCellStyle cellStyle = wb.createCellStyle();//ī�̵�����
				 Font font =  wb.createFont();
				 font.setColor(HSSFColor.BLUE.index);//����
				 font.setBoldweight(Font.BOLDWEIGHT_BOLD); 
				cellStyle.setFont(font);
				cellStyle.setFillForegroundColor((short)50);
				cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cellStyle.setBorderRight((short)1);
				cellStyle.setBorderBottom((short)1);
				
				//�������ݱ��
				HSSFCellStyle cellStyle4Red = wb.createCellStyle();
				 Font font4red =  wb.createFont();
				 font4red.setColor(HSSFColor.RED.index);
				 cellStyle4Red.setFont(font4red);
				 
				//����EXCEL�����б�(��ʼ)
					String[] textlist={
							 "0-�ٵ�����","1-�ֳ�����","2-�ع�˾����","3-�볡","4-��˾����","5-���쳣","6-����","7-��������","8-�ظ���ʱ׷�ӵ���"
							};
					String[] textlist1={"�����Ǽǻ�Ӱ൥δ����","ת��Ŀ","ϵͳ����","��������"};
					
					CellRangeAddressList regions = new CellRangeAddressList(1,ehdiList.size(),27,27);
					CellRangeAddressList regions1 = new CellRangeAddressList(1,ehdiList.size(),30,30);
					
					//��������������   
					DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);
					DVConstraint constraint1 = DVConstraint.createExplicitListConstraint(textlist1);
					//�����������������   
					HSSFDataValidation data_validation = new HSSFDataValidation(regions,constraint);     
					HSSFDataValidation data_validation1 = new HSSFDataValidation(regions1,constraint1);     
					//��sheetҳ��Ч   
					sheet.addValidationData(data_validation);
					sheet.addValidationData(data_validation1);
					
					//����EXCEL�����б�(����)
				 
				
				HSSFRow  row = sheet.createRow(0);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue("Ա�����");cell.setCellStyle(cellStyle);
				cell = row.createCell(1);
				cell.setCellValue("Ա������");cell.setCellStyle(cellStyle);
				cell = row.createCell(2);
				cell.setCellValue("��������");cell.setCellStyle(cellStyle);
				cell = row.createCell(3);
				cell.setCellValue("����");cell.setCellStyle(cellStyle);
				cell = row.createCell(4);
				cell.setCellValue("��������");cell.setCellStyle(cellStyle);
				cell = row.createCell(5);
				cell.setCellValue("Ӧ���ڹ�ʱ");cell.setCellStyle(cellStyle);
				cell = row.createCell(6);
				cell.setCellValue("PSA��ٹ�ʱ");cell.setCellStyle(cellStyle);
				cell = row.createCell(7);
				cell.setCellValue("PSA�������");cell.setCellStyle(cellStyle);
				cell = row.createCell(8);
				cell.setCellValue("�����ɵ��ݹ�ʱ");cell.setCellStyle(cellStyle);
				cell = row.createCell(9);
				cell.setCellValue("�����ɲ�����ʱ");cell.setCellStyle(cellStyle);
				cell = row.createCell(10);
				cell.setCellValue("���칤ʱ");cell.setCellStyle(cellStyle);
				cell = row.createCell(11);
				cell.setCellValue("��Դ����");cell.setCellStyle(cellStyle1);
				cell = row.createCell(12);
				cell.setCellValue("��Ŀ����");cell.setCellStyle(cellStyle1);
				cell = row.createCell(13);
				cell.setCellValue("��Ŀ���");cell.setCellStyle(cellStyle1);
				cell = row.createCell(14);
				cell.setCellValue("��Ŀ��������");cell.setCellStyle(cellStyle1);
				cell = row.createCell(15);
				cell.setCellValue("��Ʒ����");cell.setCellStyle(cellStyle1);
				cell = row.createCell(16);
				cell.setCellValue("�����Ʒ�����Ŷ�");cell.setCellStyle(cellStyle1);
				cell = row.createCell(17);
				cell.setCellValue("�����Ʒ��������");cell.setCellStyle(cellStyle1);
				cell = row.createCell(18);
				cell.setCellValue("��Ӧ��");cell.setCellStyle(cellStyle1);
				cell = row.createCell(19);
				cell.setCellValue("����ƽ̨");cell.setCellStyle(cellStyle1);
				cell = row.createCell(20);
				cell.setCellValue("�ڳ�����");cell.setCellStyle(cellStyle1);
				cell = row.createCell(21);cell.setCellStyle(cellStyle1);
				cell.setCellValue("�����ص�");cell.setCellStyle(cellStyle1);
				cell = row.createCell(22);
				cell.setCellValue("����");cell.setCellStyle(cellStyle1);
				cell = row.createCell(23);
				cell.setCellValue("�����ȼ�");cell.setCellStyle(cellStyle1);
				cell = row.createCell(24);
				cell.setCellValue("��������");cell.setCellStyle(cellStyle1);
				cell = row.createCell(25);
				cell.setCellValue("ϵͳ��������ʱ");cell.setCellStyle(cellStyle1);
				cell = row.createCell(26);
				cell.setCellValue("ϵͳ�мӰ๤ʱ");cell.setCellStyle(cellStyle1);
				cell = row.createCell(27);
				cell.setCellValue("��������");cell.setCellStyle(cellStyle);
				cell = row.createCell(28);
				cell.setCellValue("���̷���������ʱ����");cell.setCellStyle(cellStyle2);
				cell = row.createCell(29);
				cell.setCellValue("���̷����Ӱ๤ʱ����");cell.setCellStyle(cellStyle2);
				cell = row.createCell(30);
				cell.setCellValue("����ԭ��");cell.setCellStyle(cellStyle2);
				cell = row.createCell(31);
				cell.setCellValue("���̷����Ĳ���˵��");cell.setCellStyle(cellStyle2);
				cell = row.createCell(32);
				cell.setCellValue("�Ƿ�Ϊ������ʱ��ϸ");cell.setCellStyle(cellStyle2);
				cell = row.createCell(33);
				cell.setCellValue("������������");cell.setCellStyle(cellStyle3);
				cell = row.createCell(34);
				cell.setCellValue("��ȷֵ");cell.setCellStyle(cellStyle3);
				cell = row.createCell(35);
				cell.setCellValue("�ͻ���������");cell.setCellStyle(cellStyle);
				cell = row.createCell(36);
				cell.setCellValue("��˾��Ŀ����");cell.setCellStyle(cellStyle);
				cell = row.createCell(37);
				cell.setCellValue("ʣ�൹�ݹ�ʱ(Сʱ)");cell.setCellStyle(cellStyle);
				
				for(int i=0 ; i<ehdiList.size() ; i++){
					EmployeeHoursDetailInfo  ehdi = ehdiList.get(i) ;
					row = sheet.createRow(i+1);
					cell = row.createCell(0);
					cell.setCellValue(ehdi.getEmployeeId());  //Ա�����
					cell = row.createCell(1);
					cell.setCellValue(ehdi.getEmployeeName());  //Ա������
					cell = row.createCell(2);
					cell.setCellValue(ehdi.getStandardDate()==null ? "":gmsFormToString(ehdi.getStandardDate()));  //����
					cell = row.createCell(3);
					cell.setCellValue(ehdi.getStandardWeek() );  //����
					cell = row.createCell(4);
					if("0".equals(ehdi.getStandardDateType())){
						cell.setCellValue("������");  //��������
					}else if("1".equals(ehdi.getStandardDateType())){
						cell.setCellValue("˫����");  //��������
					}else if("2".equals(ehdi.getStandardDateType())){
						cell.setCellValue("�ڼ���");  //��������
					}
					cell = row.createCell(5);
					cell.setCellValue(ehdi.getStandardHours() );    //Ӧ���ڹ�ʱ
					cell = row.createCell(6);
					cell.setCellValue(ehdi.getPsaLeaveHours()==null?"":ehdi.getPsaLeaveHours().toString() );   //PSA��ٹ�ʱ
					cell = row.createCell(7);
					cell.setCellValue(ehdi.getPsaLeaveHoursType() );  //PSA�������
					cell = row.createCell(8);
					cell.setCellValue(ehdi.getCurrentAhughHours()==null?"":ehdi.getCurrentAhughHours().toString() );  //�����ɵ��ݹ�ʱ
					cell = row.createCell(9);
					cell.setCellValue(ehdi.getCurrentSubsidiesHours()==null?"":ehdi.getCurrentSubsidiesHours().toString() );  //�����ɲ�����ʱ
					cell = row.createCell(10);
					cell.setCellValue(ehdi.getCurrentGapHours()==null?"":ehdi.getCurrentGapHours().toString() );  //���칤ʱ
					if(ehdi.getCurrentGapHours()!=null&&ehdi.getCurrentGapHours()!=0) cell.setCellStyle(cellStyle4Red);
					cell = row.createCell(11);
					cell.setCellValue(ehdi.getResourcesType()==null ? "":ehdi.getResourcesType());//��Դ����
					cell = row.createCell(12);
					cell.setCellValue(ehdi.getProjectName()==null ? "":ehdi.getProjectName());//��Ŀ����
					cell = row.createCell(13);
					cell.setCellValue(ehdi.getProjectId()==null ? "":ehdi.getProjectId());//��Ŀ���
					cell = row.createCell(14);
					cell.setCellValue(ehdi.getProjectOfDepartment()==null ? "":ehdi.getProjectOfDepartment());//��Ŀ��������
					cell = row.createCell(15);
					cell.setCellValue(ehdi.getProductName()==null ? "":ehdi.getProductName());//��Ʒ����
					cell = row.createCell(16);
					cell.setCellValue(ehdi.getServiceProductsAttributiveTeam()==null ? "":ehdi.getServiceProductsAttributiveTeam());//�����Ŷ�
					cell = row.createCell(17);
					cell.setCellValue(ehdi.getServiceProductsDepartment()==null ? "":ehdi.getServiceProductsDepartment());//������
					cell = row.createCell(18);
					cell.setCellValue(ehdi.getProviders()==null ? "":ehdi.getProviders());//��Ӧ��
					cell = row.createCell(19);
					cell.setCellValue(ehdi.getTechnologyPlatform()==null ? "":ehdi.getTechnologyPlatform());//����ƽ̨
					cell = row.createCell(20);
					cell.setCellValue(ehdi.getPresenceOfType()==null ? "":ehdi.getPresenceOfType());//�ڳ�����
					cell = row.createCell(21);
					cell.setCellValue(ehdi.getWorkPlace()==null ? "":ehdi.getWorkPlace());//�����ص�
					cell = row.createCell(22);
					cell.setCellValue(ehdi.getEmpName4Cus()==null ? "":ehdi.getEmpName4Cus());//�ͻ�������
					cell = row.createCell(23);
					cell.setCellValue(ehdi.getTechnologyGrade()==null ? "":ehdi.getTechnologyGrade());//�����ȼ�
					cell = row.createCell(24);
					cell.setCellValue(ehdi.getStandardDate()==null ? "":gmsFormToString(ehdi.getStandardDate()));//��������
					cell = row.createCell(25);
					cell.setCellValue(ehdi.getNormalWorkingHours()==null ? "":ehdi.getNormalWorkingHours().toString());//������ʱ
					cell = row.createCell(26);
					cell.setCellValue(ehdi.getOverTimeHours()==null ? "":ehdi.getOverTimeHours().toString());//�Ӱ๤ʱ
					cell = row.createCell(27); //��������
					if(ehdi.getGapType()!=null&&ehdi.getGapType().trim().length()==1){
						for(String tem:textlist){
							if(tem.startsWith(ehdi.getGapType())){
								cell.setCellValue(tem);
							}
						}
					}
					cell = row.createCell(30);
					cell.setCellValue(ehdi.getEmployeeGapReason()==null ? "":ehdi.getEmployeeGapReason());//����ԭ��
					cell = row.createCell(35);
					cell.setCellValue(ehdi.getCustomerResponsiblePerson()==null ? "":ehdi.getCustomerResponsiblePerson());//�ͻ���������
					cell = row.createCell(36);
					cell.setCellValue(ehdi.getCompanyProjectManager()==null ? "":ehdi.getCompanyProjectManager());//��˾��Ŀ����
					cell = row.createCell(37);
					cell.setCellValue(ehdi.getHours4VacTotal()==null ? "":ehdi.getHours4VacTotal().toString());//ʣ�൹��
					
				}
//				FileOutputStream fos = new FileOutputStream(excelpath);
//				wb.write(fos);
//				fos.close();
				return wb;
		}
	  
	  
	  
	  //��ȡ��������֮�����������
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
	  
	  //url��ֵת��
	  public static String fix2utf(String str) throws Exception{
		  String newStr =new String (str.getBytes("iso-8859-1"),"utf-8");
		  return newStr;
	  }
	  
	  //��ȡָ���·ݵ����һ��
	  public static Date getLastDate(int year,int month){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cld = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();  
			// ��������2�У�����ȡ��ǰʱ��ǰһ���µĵ�һ�켰���һ��
			cal.set(Calendar.YEAR,year);
			cal.set(Calendar.MONTH, month);
			//��ȡ���һ��
			cal.set(Calendar.DAY_OF_MONTH, 1);  
			cal.add(Calendar.DAY_OF_MONTH, -1); 
			Date lastDate = cal.getTime(); 
			//��ȡ��һ��
//			cal.set(Calendar.DAY_OF_MONTH, 1);
//			Date firstDate = cal.getTime();
			return lastDate ;
		}
	  
	  //�ж��Ƿ�������
	  public static boolean isNum(String str){return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");	}
	  
	//�Ƚ�һ�������Ƿ�����������֮�� ��������֮�䷵��true�����򷵻�false�������������е�һ����ͬҲ����true��
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
		//������λС�� ����String
		public static  String formatDouble(Double d){
			DecimalFormat df = new DecimalFormat("#.00");
			String f=df.format(d);
			return f;
		}
}
