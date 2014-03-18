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

	<title>addEmpInfo.jsp</title>

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
	 function sub(){
	 		if(val()){
	 			document.getElementById("bscAddForm").submit();
	 		}
	 	}
	 	function val(){
	 		var eid = document.getElementById("eid4addBsc");
	 		if(eid.value.length==0){
	 			alert("请输入员工编号!");
	 			eid.focus();
	 			return false;
	 		}
	 		return true;
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
			alert("此员工编号已存在，请重新操作！");
		</script>
	</c:if>
	<%response.setHeader("Pragma", "no-cache");%>
 	<%response.setHeader("Cache-Control", "no-cache");%> 
	<div align="center">
		<font size="+3">添加员工信息</font>
	</div>
	<br />
	<html:form  styleId="bscAddForm" action="empbi.do?method=addInfo" enctype="multipart/form-data" >
		<table border="1" align="center" style="background-color:#eeeeee">
			<tr>
				<td>
					员工编号：
				</td>
				<td>
					<html:text  styleId="eid4addBsc" property="eId_add4bsc" size="15px"/><span style="color:red;">*</span>
				</td>
				<td>
					员工姓名：
				</td>
				<td><html:text property="eName_add4bsc" size="15px"/></td>
				<td>
					性别:
				</td>
				<td><html:select property="eGender_add4bsc">
						<html:option value="0">男</html:option>
						<html:option value="1">女</html:option>
					</html:select></td>
				<td>
					所属公司：
				</td>
				<td><html:text property="itfs_add4bsc" size="15px"/></td>
				
			</tr>
			<tr>
				<td>
					通用职位：
					
				</td>
				<td><html:text property="cPosition_add4bsc" size="15px"/></td>
				<td>
					成本中心：
				</td>
				<td><html:text property="cCenter_add4bsc" size="15px"/></td>
				<td>
					客户方定义姓名：
				</td>
				<td><html:text property="cbName_add4bsc" size="15px"/></td>
				
				<td >
					入职日期：
				</td>
				<td><html:text property="eDate_add4bsc" size="15px"  onclick="SelectDate(this)" readonly="readonly"/></td>
				
			</tr>
			<tr>
				<td>
					工作地文本：
					
				</td>
				<td><html:text property="wpText_add4bsc" size="15px"/></td>
				<td>
					人事范围：
				</td>
				<td><html:text property="pRange_add4bsc" size="15px"/></td>
				<td>
					人事子范围：
				</td>
				<td><html:text property="pSRange_add4bsc" size="15px"/></td>
				<td>
					组织单位6：
				</td>
				<td><html:text property="ozUnit6_add4bsc" size="15px"/></td>
				
				
			</tr>
			<tr>
				<td>
					
					员工组：
					
				</td>
				<td><html:select property="eGroup_add4bsc">
						<html:option value="">请选择</html:option>
						<html:option value="0">正式员工</html:option>
						<html:option value="1">试用员工</html:option>
						<html:option value="2">实习生</html:option>
					</html:select></td>
				<td>
					员工子组：
				</td>
				<td><html:select property="esGroup_add4bsc">
						<html:option value="">请选择</html:option>
						<html:option value="0">实施</html:option>
						<html:option value="1">销售</html:option>
					</html:select></td>
				<td>
					职级：
				</td>
				<td><html:select property="rank_add4bsc">
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
				<td><html:text property="ozUnit3_add4bsc" size="15px"/></td>
				
				
			</tr>
			<tr>
				
				<td>
					公司邮箱：
				</td>
				<td><html:text property="cmBox_add4bsc" size="15px"/></td>
				<td>
					手机号码：
				</td>
				<td><html:text property="mNumber_add4bsc" size="15px"/></td>
				
				<td>
					合同工资：
				</td>
				<td><html:text property="cWages_add4bsc" size="15px"/></td>
				<td>
					社保：
					
				</td>
				<td><html:text property="sftn_add4bsc" size="15px"/></td>
			</tr>
			<tr>
				<td>
					证件类别：
				</td>
				<td><html:select property="cType_add4bsc">
						<html:option value="">请选择</html:option>
						<html:option value="0">身份证</html:option>
						<html:option value="1">护照</html:option>
						<html:option value="2">军官证</html:option>
					</html:select></td>
				<td>
					证件号码：
					
				</td>
				<td><html:text property="cNumber_add4bsc" size="15px"/></td>
				<td>
					组织单位4：
				</td>
				<td><html:text property="ozUnit4_add4bsc" size="15px"/></td>
				<td>
					组织单位5：
				</td>
				<td><html:text property="ozUnit5_add4bsc" size="15px"/></td>
				
				
			</tr>
			<tr>
				<td>
					毕业学校/城市：
				</td>
				<td><html:text property="gsCity_add4bsc" size="15px"/></td>
				<td>
					毕业日期：
					
				</td>
				<td><html:text property="gDate_add4bsc" size="15px" onclick="SelectDate(this)" readonly="readonly"/></td>
				<td>
					学历：
				</td>
				<td><html:text property="edca_add4bsc" size="15px"/></td>
				<td>
					专业名称：
					
				</td>
				<td><html:text property="sName_add4bsc" size="15px"/></td>
				
			</tr>
			<tr>
				<td>
					技能说明：
				</td>
				<td><html:text property="sDescription_add4bsc" size="15px"/></td>
				<td>
					第一技能：
				</td>
				<td><html:text property="fSkill_add4bsc" size="15px"/></td>
				<td>
					第二技能：
				</td>
				<td><html:text property="sSkill_add4bsc" size="15px"/></td>
				<td> 是否参与工时核查 </td>
				<td>
					 <html:select property="beCheck4Gap_add4bsc">
						<html:option value="0">是</html:option>
						<html:option value="1">否</html:option>
					 </html:select> 
				</td>
			</tr>
			<tr>
				<td colspan="8" style="text-align: center">
					<input type="button" value="确定" class="btn" onclick="sub();">
					&nbsp;
					<html:reset styleClass="btn"  value="重填"/>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
