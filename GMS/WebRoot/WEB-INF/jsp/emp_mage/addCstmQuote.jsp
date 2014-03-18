<%@ page language="java" pageEncoding="utf-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>addCstmQuote.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/content.css">
	<script type="text/javascript">
		function sub(){
			var cusName4add = document.getElementById("cusName4add").value;
			if(cusName4add.length==0){
				alert("客户名称不能为空，请重新输入！");
				return;
			}
			document.getElementById("addQuoForm").submit();
		}
	
	</script>
</head>

<body>
	<c:if test="${'yes'==success_msg}">
		<script type="text/javascript">
			parent.g_close_pop();
		</script>
	</c:if>
	<c:if test="${error_msg!=null}">
		<script type="text/javascript">
			alert("此员工已经存在，请重新操作！");
		</script>
	</c:if>
	<div align="center">
		<font size="+3">添加客户报价</font>
	</div>
	<html:form styleId="addQuoForm" action="/cstmPri.do?method=add"
		enctype="multipart/form-data">
		<div style="width:100%;" align="center">
			<span style="color: #364c6d;width: 50%">客户姓名:
			<html:text styleId="cusName4add" property="cusName4add" ></html:text>
			</span>

			<table class=gridView id=ctl00_ContentPlaceHolder2_GridView1
				style="BORDER-COLLAPSE: collapse;width:100%;" align="center" >
				<tr>
					<th class=gridViewHeader scope=col style="width: 25%">
						级别
					</th>
					<th class=gridViewHeader scope=col style="width: 25%">
						技能
					</th>
					<th class=gridViewHeader scope=col style="width: 25%">
						客户收入（每人天）
					</th>
					<th class=gridViewHeader scope=col style="width: 25%">
						加班补贴标准（每小时）
					</th>
				</tr>
				<tr>
					<td class=gridViewItem>
						高级
					</td>
					<td class=gridViewItem>
						测试
					</td>
					<td class=gridViewItem>
						<html:text style="color:black;text-align:center"
							property="addprice4ST"></html:text>
					</td>
					<td class=gridViewItem>
						<html:text style="color:black;text-align:center"
							property="addovertimePrice4ST"></html:text>
					</td>
				</tr>
				<tr>
					<td class=gridViewItem>
						中级
					</td>
					<td class=gridViewItem>
						测试
					</td>
					<td class=gridViewItem>
						<html:text style="color:black;text-align:center"
							property="addprice4MT"></html:text>
					</td>
					<td class=gridViewItem>
						<html:text style="color:black;text-align:center"
							property="addovertimePrice4MT"></html:text>
					</td>
				</tr>
				<tr>
					<td class=gridViewItem>
						初级
					</td>
					<td class=gridViewItem>
						测试
					</td>
					<td class=gridViewItem>
						<html:text style="color:black;text-align:center"
							property="addprice4JT"></html:text>
					</td>
					<td class=gridViewItem>
						<html:text style="color:black;text-align:center"
							property="addovertimePrice4JT"></html:text>
					</td>
				</tr>
				<tr>
					<td class=gridViewItem>
						高级
					</td>
					<td class=gridViewItem>
						开发
					</td>
					<td class=gridViewItem>
						<html:text style="color:black;text-align:center"
							property="addprice4SD"></html:text>
					</td>
					<td class=gridViewItem>
						<html:text style="color:black;text-align:center"
							property="addovertimePrice4SD"></html:text>
					</td>
				</tr>
				<tr>
					<td class=gridViewItem>
						中级
					</td>
					<td class=gridViewItem>
						开发
					</td>
					<td class=gridViewItem>
						<html:text style="color:black;text-align:center"
							property="addprice4MD"></html:text>
					</td>
					<td class=gridViewItem>
						<html:text style="color:black;text-align:center"
							property="addovertimePrice4MD"></html:text>
					</td>
				</tr>
				<tr>
					<td class=gridViewItem>
						初级
					</td>
					<td class=gridViewItem>
						开发
					</td>
					<td class=gridViewItem>
						<html:text style="color:black;text-align:center"
							property="addprice4JD"></html:text>
					</td>
					<td class=gridViewItem>
						<html:text style="color:black;text-align:center"
							property="addovertimePrice4JD"></html:text>
					</td>
				</tr>
			</table>
		</div>
		<br />
		<div style="width: 100%; text-align: center;">
			<input type="button" value ="提交" style="color:black;width:65px" onclick="sub();">
			&nbsp;&nbsp;&nbsp;
			<html:reset style="color:black;width:65px" value="重置" />
		</div>
	</html:form>
</body>
</html:html>
