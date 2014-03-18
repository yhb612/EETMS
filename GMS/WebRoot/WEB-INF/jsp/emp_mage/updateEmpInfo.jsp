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
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css">
		td { 
			color:#364c6d; border-right: #bad6ec 1px solid; border-top: #bad6ec 1px solid; border-left: #bad6ec 1px solid; 
			line-height: 12px; border-bottom: #bad6ec 1px solid;white-space: nowrap; font-size:13px; padding-top:0.05cm;
			}
		.btn { 
			    height:30px; width:80px;
		}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
<script type="text/javascript">

</script>
  </head>
  
  <body>	
	<c:if test="${'yes'==success_msg}">
		<script type="text/javascript">
			parent.g_close_pop();
		</script>
	</c:if>
	<c:if test="${err_msg!=null}">
		<script type="text/javascript">
			var msg = '<%=request.getAttribute("err_msg")%>';
			alert(msg);
			parent.g_close_pop();
		</script>
	</c:if>
  		<div align="center">
		<font size="+3">修改员工信息</font>
	</div>
	<br />
	<br />
	<html:form action="empbi.do?method=modifyInfo">
		<table border="1" align="center" style="background-color:#eeeeee">
			<tr>
				<td>
					员工编号：
				</td>
				<td><html:text property="eId4bscUd" size="15px"/></td>
				<td>
					员工姓名：
				</td>
				<td><html:text property="eName4bscUd" size="15px"/></td>
				<td>
					性别：
				</td>
				<td><html:select property="eGender4bscUd">
						<html:option value="0">男</html:option>
						<html:option value="1">女</html:option>
					</html:select></td>
				<td>
					所属公司：
				</td>
				<td><html:text property="itfs4bscUd" size="15px"/></td>
			</tr>
			<tr>
				<td>
					通用职位：
				</td>
				<td><html:text property="cPosition4bscUd" size="15px"/></td>
				<td>
					成本中心：
				</td>
				<td><html:text property="cCenter4bscUd" size="15px"/></td>
				<td>
					客户方定义姓名：
				</td>
				<td><html:text property="cbName4bscUd" size="15px"/></td>
				
				<td >
					入职日期：
				</td>
				<td><html:text property="eDate4bscUd" size="15px"  onclick="SelectDate(this)" readonly="true"/></td>
				
			</tr>
			<tr>
				<td>
					工作地文本：
				</td>
				<td><html:text property="wpText4bscUd" size="15px"/></td>
				<td>
					人事范围：
				</td>
				<td><html:text property="pRange4bscUd" size="15px"/></td>
				<td>
					人事子范围：
				</td>
				<td><html:text property="pSRange4bscUd" size="15px"/></td>
				<td>
					组织单位6：
				</td>
				<td><html:text property="ozUnit64bscUd" size="15px"/></td>
				
				
				
			</tr>
			<tr>
				<td>
					员工组：
				</td>
				<td><html:select property="eGroup4bscUd">
						
						<html:option value="">请选择</html:option>
						<html:option value="0">正式员工</html:option>
						<html:option value="1">试用员工</html:option>
						<html:option value="1">实习生</html:option>
					</html:select></td>
				<td>
					员工子组：
				</td>
				<td><html:select property="esGroup4bscUd">
						<html:option value="">请选择</html:option>
						<html:option value="0">实施</html:option>
						<html:option value="1">销售</html:option>
					</html:select></td>
				<td>
					职级：
				</td>
				<td><html:select property="rank4bscUd">
						<html:option value="">请选择</html:option>
						<html:option value="0">BU head</html:option>
						<html:option value="1">部门总监</html:option>
						<html:option value="2">客户总监</html:option>
						<html:option value="3">项目经理</html:option>
						<html:option value="4">项目组长</html:option>
						<html:option value="5">客户经理</html:option>
					</html:select></td>
				<td>
					组织单位3：
				</td>
				<td><html:text property="ozUnit34bscUd" size="15px"/></td>
				
				
			</tr>
			<tr>
				
				<td>
					公司邮箱：
				</td>
				<td><html:text property="cmBox4bscUd" size="15px"/></td>
				<td>
					手机号码：
				</td>
				<td><html:text property="mNumber4bscUd" size="15px"/></td>
				<td>
					合同工资：
				</td>
				<td><html:text property="cWages4bscUd" size="15px"/></td>
				<td>
					社保：
				</td>
				<td><html:text property="sftn4bscUd" size="15px"/></td>
				
			</tr>
			<tr>
				<td>
				
					证件类别：
				</td>
				<td><html:select property="cType4bscUd">
						<html:option value="">请选择</html:option>
						<html:option value="0">身份证</html:option>
						<html:option value="1">护照</html:option>
						<html:option value="2">军官证</html:option>
					</html:select></td>
				<td>
					证件号码：
				</td>
				<td><html:text property="cNumber4bscUd" size="15px"/></td>
				<td>
					组织单位4：
				</td>
				<td><html:text property="ozUnit44bscUd" size="15px"/></td>
				<td>
					组织单位5：
				</td>
				<td><html:text property="ozUnit54bscUd" size="15px"/></td>
				
			</tr>
			<tr>
				<td>
					毕业学校/城市：
				</td>
				<td><html:text property="gsCity4bscUd" size="15px"/></td>
				<td>
					毕业日期：
				</td>
				<td><html:text property="gDate4bscUd" size="15px" onclick="SelectDate(this)"  readonly="true"/></td>
				<td>
					学历：
				</td>
				<td><html:text property="edca4bscUd" size="15px"/></td>
				<td>
					专业名称：
				</td>
				<td><html:text property="sName4bscUd" size="15px"/></td>
				
			</tr>
			<tr>
				<td>
					技能说明：
				</td>
				<td><html:text property="sDescription4bscUd" size="15px"/></td>
				<td>
					第一技能：
				</td>
				<td><html:text property="fSkill4bscUd" size="15px"/></td>
				<td>
					第二技能：
				</td>
				<td><html:text property="sSkill4bscUd" size="15px"/></td>
				<td> 是否参与工时核查 </td>
				<td>
					 <html:select property="beCheck4Gap4bscUd" >
						<html:option value="0">是</html:option>
						<html:option value="1">否</html:option>
					 </html:select> 
				</td>
			</tr>
			<tr>
				<td colspan="8" style="text-align: center">
					<html:submit styleClass="btn" value="确定"/>
					&nbsp;
					<html:reset styleClass="btn"  value="重填"/>
				</td>
			</tr>
		</table>
	</html:form>
  </body>
</html:html>