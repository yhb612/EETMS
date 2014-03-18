<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %> 
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %> 
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户收入查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/popup.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
	<script type="text/javascript">
		function query(){
       		if(val()){
       			document.getElementById("cusIcForm").action="<%=request.getContextPath()%>/cusIc.do?method=search";
				document.getElementById("cusIcForm").submit();
        	}
        }
        function val(){
	 		var sDate4ic = document.getElementById("sDate4ic").value;
	        var eDate4ic = document.getElementById("eDate4ic").value;
	 		if(eDate4ic.length==0 || sDate4ic.length==0){
	 			alert("开始日期和结束日期不能为空，请重新操作!");
	 			return false;
	 		}
	 		return true;
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
  <html:form styleId="cusIcForm" action="/cusIc.do?method=search" method="post"> 
  	<html:hidden property="cust" styleId="cust"/>
	<div >
  	<table cellSpacing=0 cellPadding=0 height="100%">
      <tr 
  style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/bg_header.gif); BACKGROUND-REPEAT: repeat-x; " 
  height=47>
        <td width=10><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hl.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span></td>
        <td><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hl2.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span><span 
      style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hb.gif); PADDING-BOTTOM: 10px; COLOR: white; PADDING-TOP: 10px; BACKGROUND-REPEAT: repeat-x; HEIGHT: 47px; TEXT-ALIGN: center; 0px: ">客户收入查询</span><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hr.gif); WIDTH: 60px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span></td>
        <td 
    style="BACKGROUND-POSITION: 50% bottom; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_rc.gif)" 
    width=10></td>
      </tr>
      <tr>
        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_ls.gif)">&nbsp;</td>
        <td  style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; COLOR: #566984; PADDING-TOP: 10px; BACKGROUND-COLOR: white;width: 10px" vAlign=top align=left>
			<div style="width: 900px;">
				<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >查询时间:</font>
			 		<html:text property="sDate4ic" styleId="sDate4ic" styleClass="s4relquery" size="15px" onclick="SelectDate(this)" readonly="readonly"/>
			 		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >-</font>
			 		<html:text property="eDate4ic" styleId="eDate4ic" styleClass="s4relquery" size="15px" onclick="SelectDate(this)" readonly="readonly"/>
				<input type="button" value="查询" class="cmdField" onclick="query();">
			</div>
			<br/>
			 <div style="width: 900px;overflow: auto;height: 90%">
		            <table class=gridView id=ctl00_ContentPlaceHolder2_GridView1 style=" BORDER-COLLAPSE: collapse;" width="100%" >
			            <tr >
						<th class=gridViewHeader scope=col style="width:20%">客户名称</th>
						<th class=gridViewHeader scope=col style="width:20%">级别</th>
						<th class=gridViewHeader scope=col style="width:20%">技能</th>
						<th class=gridViewHeader scope=col style="width:20%">客户收入(元)</th>
						<th class=gridViewHeader scope=col style="width:20%">合计(元)</th>
		            </tr>
					<tr >
						<td rowspan="6" align="center" valign="middle" class=gridViewItem>
							中行软开
						</td>
						<td class=gridViewItem style="height:28px">高级</td>
						<td class=gridViewItem>测试</td>
						<td class=gridViewItem>
						<bean:write name="ciqi" property="ic4st" format="0.00"/></td>
						<td class=gridViewItem rowspan="6">
						<bean:write name="ciqi" property="icAll" format="0.00"/>
						</td>
					</tr>
					<tr >
						<td class=gridViewItem style="height:28px">中级</td>
						<td class=gridViewItem>测试</td>
						<td class=gridViewItem>
						<bean:write name="ciqi" property="ic4mt" format="0.00"/></td>
					</tr>
					<tr >
						<td class=gridViewItem style="height:28px">初级</td>
						<td class=gridViewItem>测试</td>
						<td class=gridViewItem>
						<bean:write name="ciqi" property="ic4jt" format="0.00"/></td>
					</tr>
					<tr >
						<td class=gridViewItem style="height:28px">高级</td>
						<td class=gridViewItem>开发</td>
						<td class=gridViewItem>
						<bean:write name="ciqi" property="ic4sd" format="0.00"/></td>
					</tr>
					<tr >
						<td class=gridViewItem style="height:28px">中级</td>
						<td class=gridViewItem>开发</td>
						<td class=gridViewItem>
						<bean:write name="ciqi" property="ic4md" format="0.00"/></td>
					</tr>
					<tr >
						<td class=gridViewItem style="height:28px">初级</td>
						<td class=gridViewItem>开发</td>
						<td class=gridViewItem>
						<bean:write name="ciqi" property="ic4jd" format="0.00"/></td>
					</tr>
	            </table>
          </div>
           </td>
        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_rs.gif)"></td>
      </tr>
      
      <tr style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_fs.gif); BACKGROUND-REPEAT: repeat-x" height=10>
        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_lf.gif)"></td>
        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_fs.gif)"></td>
        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_rf.gif)"></td>
      </TR>
  </table>
</div>
</html:form>
</body>
</html>
