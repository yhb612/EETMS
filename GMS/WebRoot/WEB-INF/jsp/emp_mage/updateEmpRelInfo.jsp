<%@ page language="java" pageEncoding="utf-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />
	<title>addEmpRelInfo.jsp</title>
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
		.text{width:80px}
</style>
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
 <script type="text/javascript">
 	function sub(){
 		if(val()){
 			document.getElementById("relUpdateForm").submit();
 		}
 	}
 	function val(){
 		var eid = document.getElementById("eId4u");
 		var pro = document.getElementById("tsPro");
 		var sta = document.getElementById("status4relUd");
 		if(eid.value.length==0){
 			alert("请输入员工编号!");
 			eid.focus();
 			return false;
 		}
 		if(pro.value.length==0){
 			alert("请选择所属TS项目!");
 			pro.focus();
 			return false;
 		}
 		if(sta.value.length==0){
 			alert("请选择员工状态!");
 			pro.focus();
 			return false;
 		}
 		return true;
 	}
 </script>
</head>
<body>
	<c:if test="${change_fail!=null}">
		<script type="text/javascript">
			var msg = '<%=request.getAttribute("change_fail")%>';
			alert(msg);
		</script>
	</c:if>
	<c:if test="${err_msg!=null}">
		<script type="text/javascript">
			var msg = '<%=request.getAttribute("err_msg")%>';
			alert(msg);
			parent.g_close_pop();
		</script>
		</c:if>
	<c:if test="${'yes'==success_msg}">
		<script type="text/javascript">
			parent.g_close_pop();
		</script>
	</c:if>
	<div align="center">
		<font size="+3">修改员工归属信息</font>
	</div>
	<br />
	<br />
	<html:form styleId="relUpdateForm" action="empri.do?method=updateRelInfo" enctype="multipart/form-data">
		<html:hidden property="oldProName" value="${eri.belongedTSproject}"/>
		<html:hidden property="oldStatus" value="${eri.workstaus}"/>
		<html:hidden property="oldEmpId" value="${eri.employeeId}"/>
		<table border="1" align="center" style="background-color: #eeeeee">
			<tr>
			 <td>员工编号： </td><td><html:text styleId="eId4u" property="eId4relUd" styleClass="text" value="${eri.employeeId}" /></td>
			 <td>姓名：</td><td><html:text property="eName4relUd" styleClass="text" value="${eri.employeeName}"/></td>
			 <td>状态：<span style="color:red;">*</span>
			 </td>
				<td><html:select styleId="status4relUd" property="status4relUd" value="${eri.workstaus}">
				 	<html:option value="">请选择</html:option>
				 	<html:option value="2">计费</html:option>
				 	<html:option value="0">长期休假</html:option>
				 	<html:option value="1">未到岗</html:option>
				 	<html:option value="3">结束</html:option>
				 	<html:option value="4">闲置</html:option>
				 	<html:option value="5">离职</html:option>
				 	<html:option value="6">借调外部</html:option>
				 	<html:option value="7">借入</html:option>
				 	<html:option value="8">现场储备</html:option>
				 	<html:option value="9">公司储备</html:option>
				 	<html:option value="10">调离</html:option>
				 	<html:option value="11">借调</html:option>
				 	<html:option value="12">下包商</html:option>
				 	<html:option value="13">管理</html:option>
				 	<html:option value="14">销售</html:option>
				 	<html:option value="15">售前</html:option>
				 	<html:option value="16">调休</html:option>
				 </html:select></td>
			</tr>
			<tr>
			 <td >项目编号：</td><td><html:text styleId="proNum" property="proNum4relUd" readonly="true" style="width:100px" value="${eri.belongedTSprojectId}"></html:text></td>
			 <td>所属TS项目：<span style="color:red;">*</span>
			 </td>
			 <td><html:select styleId="tsPro" property="tsPro4relUd" onchange="javascript:document.getElementById('proNum').value=this.value;" value="${eri.belongedTSprojectId}">
		 			<html:option value="">请选择</html:option>
			 		<logic:notEmpty name="proList" scope="request">
			 			<logic:iterate id="pro" name="proList" >
			 				<html:option value="${pro.proId}" ><bean:write name="pro" property="proName"/></html:option>
			 			</logic:iterate>
			 		</logic:notEmpty>
			 	</html:select>
			 	</td>
			 <td >业务线：
			   </td>
			   <td><html:select property="bline4relUd"  value="${eri.businessLine}">
					 	<html:option value="">请选择</html:option>
					 	<html:option value="0">总体部</html:option>
					 	<html:option value="1">上海分行</html:option>
					 	<html:option value="2">信息中心</html:option>
					 	<html:option value="3">山东</html:option>
					 	<html:option value="4">电子渠道</html:option>
					 	<html:option value="5">国际结算</html:option>
					 	<html:option value="6">信息管理</html:option>
					 	<html:option value="7">投资代理</html:option>
					 	<html:option value="8">质量管理</html:option>
					 	<html:option value="9">核心业务</html:option>
					 	<html:option value="10">上海</html:option>
					 	<html:option value="11">深圳</html:option>
					 	<html:option value="12">成都</html:option>
					 	<html:option value="13">上海银商</html:option>
					 	<html:option value="北京">北京</html:option>
					 </html:select></td>
			</tr>
			<tr>
				
			 <td>项目总监：
			 </td>
			 <td><html:select property="proDrt4relUd" value="${eri.projectDirector}">
			 		<html:option value="">请选择</html:option>
			 		<logic:notEmpty name="proDrtList" scope="request">
			 			<logic:iterate id="drt" name="proDrtList" >
			 				<html:option value="${drt}">${drt}</html:option>
			 			</logic:iterate>
			 		</logic:notEmpty>
			 		<c:if test="${eri.projectDirector!=null}">
				 		<html:option value="${eri.projectDirector}">${eri.projectDirector}</html:option>
			 		</c:if>
			 	</html:select></td>
			 <td>项目经理：
			 </td>
			 <td><html:select property="proMgr4relUd"  value="${eri.projectManager}">
			 		<html:option value="">请选择</html:option>
			 		<logic:notEmpty name="proMgrList" scope="request">
			 			<logic:iterate id="mgr" name="proMgrList" >
			 				<html:option value="${mgr}">${mgr}</html:option>
			 			</logic:iterate>
			 		</logic:notEmpty>
			 		<c:if test="${eri.projectManager!=null}">
				 		<html:option value="${eri.projectManager}">${eri.projectManager}</html:option>
			 		</c:if>
			 	</html:select></td>
			 <td>项目组长：
			 </td>
			 <td><html:select property="proLdr4relUd"  value="${eri.projectLeader}">
			 		<html:option value="">请选择</html:option>
			 		<logic:notEmpty name="proLdrList" scope="request">
			 			<logic:iterate id="ldr" name="proLdrList" >
			 				<html:option value="${ldr}">${ldr}</html:option>
			 			</logic:iterate>
			 		</logic:notEmpty>
			 		<c:if test="${eri.projectLeader!=null}">
				 		<html:option value="${eri.projectLeader}">${eri.projectLeader}</html:option>
			 		</c:if>
			 	</html:select></td>
			</tr>
			<tr>
			 <td >等级：
			 </td>
			 <td><html:select property="grade4relUd"  value="${eri.grade}">
				 	<html:option value="">请选择</html:option>
				 	<html:option value="0">初级</html:option>
				 	<html:option value="1">中级</html:option>
				 	<html:option value="2">高级</html:option>
				 </html:select></td>
			 <td>入场免费时间：</td>
			 <td><html:text property="freeDate4relUd" styleClass="text" size="15px" onclick="SelectDate(this)" readonly="readonly"  value="${admittancefreeDate}"/></td>
			 <td>入场计费时间：</td>
			<td><html:text property="billingDate4relUd" styleClass="text" size="15px" onclick="SelectDate(this)" readonly="readonly"  value="${admittancebillingDate}"/></td>
			</tr>
			<tr>
				<td>员工变动：</td>
				<td>
				 <html:select property="changing4ud">
				 	<html:option value="">请选择</html:option>
				 	<html:option value="0">入职</html:option>
				 	<html:option value="1">调入</html:option>
				 	<html:option value="2">离职</html:option>
				 	<html:option value="3">调出</html:option>
				 </html:select>
				 <c:if test="${eri.empOperate!=null}">
				 	最后一次变动:
				 	<c:if test="${eri.empOperate=='0'}">入职</c:if>
				 	<c:if test="${eri.empOperate=='1'}">调入</c:if>
				 	<c:if test="${eri.empOperate=='2'}">离职</c:if>
				 	<c:if test="${eri.empOperate=='3'}">调出</c:if>
				 </c:if>
				</td>
				<td>预计离场时间：</td>
				<td><html:text property="stimateDate4relUd" size="15px" styleClass="text" onclick="SelectDate(this)" readonly="readonly"  value="${outsceneestimateDate}"/></td>
				<td>实际离场时间：</td>
				<td><html:text property="realityDate4relUd" size="15px" styleClass="text" onclick="SelectDate(this)" readonly="readonly"  value="${outscenerealityDate}"/></td>
				
			</tr>
			<tr>
			 <td>所属公司：
			 </td>
			 <td><html:text property="com4relUd" styleClass="text" value="${eri.company}"/>
			 </td>
			 <td>技能：
			 </td>
			 <td><html:text property="skill4relUd" styleClass="text" value="${eri.skill}"/>
			 <a title="如为测试，可填'测试'或'Test'；归属信息汇总时，非'测试'或'Test',全部默认为'开发'，包括不填!">提示</a>
			 </td>
			 <td>
			 </td>
			 <td></td>
			</tr>
			<tr>
				<td colspan="6" style="text-align: center">
					<input type="button" value="确定" class="btn" onclick="sub();">
					&nbsp;
					<html:reset styleClass="btn"  value="重填"/>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
