package com.iss.gms.dao;

import java.sql.SQLException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iss.gms.entity.EmployeeBasicInfo;
import com.iss.gms.service.IEmployeeService;

public class Test {
	private static ClassPathXmlApplicationContext ctx;
	
	public static void main(String[] args) throws SQLException {
		
		ctx = new ClassPathXmlApplicationContext("spring.xml");
		
		IEmployeeService u=(IEmployeeService)ctx.getBean("employeeService");
		EmployeeBasicInfo e=new EmployeeBasicInfo();
		e.setEmployeeId("36423");
		e.setIsWorkOtimeSubSidies("0");
		EmployeeBasicInfo e1=new EmployeeBasicInfo();
		e1.setEmployeeId("3647534");
		e1.setIsWorkOtimeSubSidies("111");
		u.addEmpInfo(e);
		u.addEmpInfo(e1);
	}
}
