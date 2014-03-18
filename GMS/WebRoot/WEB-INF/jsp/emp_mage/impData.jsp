<%@ page language="java" pageEncoding="utf-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <html:base />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  	
  <body>
  		<c:if test="${imp_msg!=null}">
		<script type="text/javascript">
			var msg = '<%=request.getAttribute("imp_msg")%>';
			alert(msg);
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
  		<html:form action="imp.do?method=impInfo" method="post" enctype="multipart/form-data">
  		<br/>
  		<br/>
  			<div style="text-align: center">
  				<html:file property="file" /><html:submit property="ttt" styleClass="btn" value="导入数据"/>
  				<html:hidden property="flag" value="${status}"/>
  			</div>
  			<c:if test="${null!=err_msg4relimp}">
				<div>
  					<span style="color:red;">
  					以下员工导入失败：<br/>${err_msg4relimp}<br/><br/>
  					可能引起异常的原因：<br/>
  					员工基本信息中存在多条姓名为该员工的信息,无法获取唯一的员工编号！<br/>
  					解决办法：可在导入的归属信息中为导入失败的员工指定员工编号，重新导入！<br/>
  					</span>
  				</div>
			</c:if>
  			
  		</html:form>
  </body>
</html>
