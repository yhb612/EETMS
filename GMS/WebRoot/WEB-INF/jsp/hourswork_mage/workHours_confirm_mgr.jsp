<%@ page language="java" pageEncoding="utf-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>客户确认工时管理</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css">
	<script type="text/javascript">
		function imp(){
			var filePath = document.getElementById("filePath");
			if(filePath.value.length==0){
				alert('请选择导入文件！');
				return;
			}
			var chk = document.getElementById("toSta");
			if(chk.checked){
				if(confirm("本次导入会同步更新员工工时统计信息（如：员工剩余倒休工时等），本操作不可逆，确定导入吗？")){
					document.getElementById("cusVForm").action ="<%=request.getContextPath()%>/imp.do?method=impInfo&staFlag=sta";
					document.getElementById("cusVForm").submit();
				}
			}
			if(!chk.checked){
				document.getElementById("cusVForm").action ="<%=request.getContextPath()%>/imp.do?method=impInfo";
				document.getElementById("cusVForm").submit();
			}
		
		}
	</script>
  </head>
<body class="bod">
<c:if test="${err_msg!=null}">
		<script type="text/javascript">
			var msg = '<%=request.getAttribute("err_msg")%>';
			alert(msg);
		</script>
	</c:if>
<c:if test="${imp_msg!=null}">
		<script type="text/javascript">
			var msg = '<%=request.getAttribute("imp_msg")%>';
			alert(msg);
		</script>
	</c:if>
<html:form styleId="cusVForm" action="/imp.do?method=impInfo" method="post" enctype="multipart/form-data"> 
	<div>
	  	<table cellSpacing=0 cellPadding=0 height="100%" >
	      <tr 
	  			style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/bg_header.gif); BACKGROUND-REPEAT: repeat-x; " 
	  			height=47>
	        <td width=10><span 
	     		 style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hl.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span></td>
	        <td><span 
	      		style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hl2.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span><span 
	      		style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hb.gif); PADDING-BOTTOM: 10px; COLOR: white; PADDING-TOP: 10px; BACKGROUND-REPEAT: repeat-x; HEIGHT: 47px; TEXT-ALIGN: center; 0px: ">客户确认工时管理</span><span 
	      		style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hr.gif); WIDTH: 60px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span></td>
	        <td 
	   		 style="BACKGROUND-POSITION: 50% bottom; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_rc.gif)" 
	    		width=10></td>
	      </tr>
	      <tr>
	        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_ls.gif)">&nbsp;</td>
	        <td  style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; COLOR: #566984; PADDING-TOP: 10px; BACKGROUND-COLOR: white;width: 10px" vAlign=top align=left>
				 <div style="width:900px">
				 		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >客户确认工时数据 :</font>
	  				<html:file styleId="filePath" property="file" style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left"/>&nbsp;&nbsp;&nbsp;
	  				<input type="button" value="导入" class="cmdField" onclick="imp();">
	  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  				<input id="toSta" type="checkbox"><span style="color:#364c6d ">是否更新工时统计（客户确认工时<b><span style="color: red">最终版</span></b>导入时勾选）</span>
				 </div>
				 <div style="color: black;width:900px;">
				 	<c:if test="${clDate!=null}">
				 		最后一次导入客户确认数据的截止日期：<c:out value="${clDate}"/>
				 		<c:if test="${dataStatus=='last'}">
				 			(最终版)
				 		</c:if>
				 		<c:if test="${dataStatus=='notLast'}">
				 			(非最终版)
				 		</c:if>
				 	</c:if>
				 	<c:if test="${impCount!=null}">
				 		本次导入成功<c:out value="${impCount}"/>条数据<br/><br/>
				 	</c:if>
				 	<c:if test="${errorNames!=null}">
				 		<span style="color:red;">以下员工导入异常，请确认员工姓名与员工基本信息中的客户定义姓名保持一致！</span><br/>
				 		<c:out value="${errorNames}"/>
				 	</c:if>
				 </div>
	        </td>
	        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_rs.gif)"></td>
	      </tr>
	      
	      <tr style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_fs.gif); BACKGROUND-REPEAT: repeat-x" height=10>
	        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_lf.gif)"></td>
	        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_fs.gif)"></td>
	        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_rf.gif)"></td>
	      </TR>
	      <html:hidden property="flag" value="cci"/>
	  </table>
  </div>
  </html:form>
</body>
</html:html>