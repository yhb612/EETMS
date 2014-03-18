<%@ page language="java" pageEncoding="utf-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>预测信息详情</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css">

  </head>

<body>
	<table class=gridView 
		style="BORDER-COLLAPSE: collapse; width: 100%;">
		<tr>
			<th class=gridViewHeader scope=col>
				员工编号
			</th>
			<th class=gridViewHeader scope=col>
				员工姓名
			</th>
			<th class=gridViewHeader scope=col>
				收入(日)
			</th>
			<th class=gridViewHeader scope=col>
				预测人日
			</th>
			<th class=gridViewHeader scope=col>
				技术等级(客户方定级)
			</th>
		</tr>
		<logic:empty name="detailList" scope="request">
			<tr>
				<td style="color: #364c6d;" colspan="100">
					暂无数据!
				</td>
			</tr>
		</logic:empty>
		<logic:notEmpty name="detailList" scope="request">
			<logic:iterate id="dl" name="detailList">
				<tr
					OnMouseOver="CurrentColor=this.style.backgroundColor;this.style.backgroundColor='#7EAFCB'"
					OnMouseOut="this.style.backgroundColor=CurrentColor"
					style="background-color: #eeeeee;">
					<td class=gridViewItem style="line-height: 30px"><c:out value="${dl.empId}"/></td>
					<td class=gridViewItem style="line-height: 30px">
						${dl.empName}</td>
					<td class=gridViewItem style="line-height: 30px">
						￥${dl.income}</td>
					<td class=gridViewItem style="line-height: 30px">
						${dl.days}天</td>
					<td class=gridViewItem style="line-height: 30px">
						${dl.cusGrade}</td>
				</tr>
			</logic:iterate>
		</logic:notEmpty>
	</table>
</body>
</html:html>
