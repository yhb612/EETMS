<%@ page language="java" import="java.util.*,com.iss.gms.entity.EmployeeBasicInfo;" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>success.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		
	</script>
  </head>
 
  <body>
  <table border="1">
  		<tr>
  			<td>员工编号</td>
  			<td>姓名</td>
  			<td>中行姓名</td>
  			<td>性别</td>
  			<td>入职日期</td>
  			<td>通用职位</td>
  			<td>成本中心</td>
  		</tr>
  		<logic:empty name="lt" scope="request" >
  					 <tr>
  						<td colspan="7">暂无数据!</td>
  					</tr>
  		</logic:empty>
    <logic:notEmpty name="lt" scope="request">
  			<logic:iterate id="Obcr" name="lt">
  				<tr  OnMouseOver="CurrentColor=this.style.backgroundColor;this.style.backgroundColor='#7EAFCB'" OnMouseOut="this.style.backgroundColor=CurrentColor" style="background-color:#eeeeee;">
  					<td>${Obcr.employeeId}</td>
  					<td>${Obcr.employeeName}</td>
  					<td>${Obcr.chinaBankName}</td>
  					<td>${Obcr.employeeGender}</td>
  					<td>${Obcr.entryDate}</td>
  					<td>${Obcr.commonPosition}</td>
  					<td>${Obcr.costCenter}</td>
  				</tr>
  		    </logic:iterate>
  		</logic:notEmpty >
  	</table>
  </body>
</html:html>
