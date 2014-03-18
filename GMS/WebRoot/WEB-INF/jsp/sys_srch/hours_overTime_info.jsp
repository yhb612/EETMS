<%@ page language="java" pageEncoding="utf-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
 <head>
    <title>员工加班工时管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/popup.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
	<script type="text/javascript">
          	function query(){
          		if(val()){
          			document.getElementById("hoursOtForm").submit();
          		}
          	}
          	function val(){
	 		var dateS4g = document.getElementById("dateS4ot").value;
          	var dateE4g = document.getElementById("dateE4ot").value;
	 		if(dateS4g.length==0 || dateE4g.length==0){
	 			alert("开始日期和结束日期不能为空，请重新操作!");
	 			return false;
	 		}
	 		return true;
	 		}
          </script>
  </head>
  
  <body>
  	<c:if test="${err_msg!=null}">
		<script type="text/javascript">
			var msg = '<%=request.getAttribute("err_msg")%>';
			alert(msg);
		</script>
	</c:if>
   <html:form styleId="hoursOtForm" action="/hoursOT.do?method=search" method="post">
	<div >
  	<table cellSpacing=0 cellPadding=0 height="100%">
      <tr 
  style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/bg_header.gif); BACKGROUND-REPEAT: repeat-x; " 
  height=47>
        <td width=10><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hl.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span></td>
        <td><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hl2.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span><span 
      style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hb.gif);
       PADDING-BOTTOM: 10px; COLOR: white; PADDING-TOP: 10px; BACKGROUND-REPEAT: repeat-x; HEIGHT: 47px; TEXT-ALIGN: center; 0px: ">加班可补贴工时查询</span><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hr.gif); WIDTH: 60px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span></td>
        <td 
    style="BACKGROUND-POSITION: 50% bottom; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_rc.gif)" 
    width=10></td>
      </tr>
      <tr>
        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_ls.gif)">&nbsp;</td>
        <td  style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; COLOR: #566984; PADDING-TOP: 10px; BACKGROUND-COLOR: white;width: 10px" vAlign=top align=left>
			 <div style="width:900px">
			 		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >员工姓名/员工编号:</font>
			 		<html:text property="Id4ot" styleClass="gridViewItem" size="12"/>&nbsp;&nbsp;
			 		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >开始时间:</font><span style="color:red;">*</span>
			 		<html:text styleId="dateS4ot" property="dateS4ot" size="12" onclick="SelectDate(this)" readonly="true" styleClass="gridViewItem"/>&nbsp;
			 		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >结束时间:</font><span style="color:red;">*</span>
			 		<html:text styleId="dateE4ot" property="dateE4ot" size="12" onclick="SelectDate(this)" readonly="true" styleClass="gridViewItem"/>&nbsp;
					<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >属地:</font>
					<html:select property="workPlace4ot" >
						<html:option styleClass="gridViewItem" value="" >请选择</html:option>
						<c:if test="${workPlaces!=null}">
							<c:forEach items="${workPlaces}" var="wPlace">
								<c:if test="${wPlace!=null}">
								<html:option value="${wPlace}" styleClass="gridViewItem" >${wPlace}</html:option>
								</c:if>
							</c:forEach>
						</c:if>
					</html:select>
					<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >加班工时:</font>
					<html:select property="flag">
						<html:option value="0" styleClass="gridViewItem">显示所有</html:option>
						<html:option value="1" styleClass="gridViewItem">仅显示大于0</html:option>
					</html:select>
					&nbsp;&nbsp;&nbsp;
            	    <input type="button" value="查询" class="cmdField" onclick="query();">&nbsp;&nbsp;&nbsp;
			 </div>
			 <div style="width: 900px;overflow: auto;height: 85%;">
            <table class=gridView id=ctl00_ContentPlaceHolder2_GridView1 style=" BORDER-COLLAPSE: collapse;" width="100%">
              <tr >
                  <th class=gridViewHeader scope=col>员工编号</th>
                  <th class=gridViewHeader scope=col>员工姓名</th>
                  <th class=gridviewHeader scope=col>属地</th>
                  <th class=gridviewHeader scope=col>时间范围</th>
                  <th class=gridviewHeader scope=col>可补贴加班总工时</th>
                  <th class=gridviewHeader scope=col>加班补贴标准</th>
                  <th class=gridviewHeader scope=col>加班费合计</th>
                </tr>
                <logic:empty name="hoursOtList" scope="request" >
  					 <tr>
  						<td style="color:#364c6d;" colspan="100">暂无数据!</td>
  					</tr>
  				</logic:empty>
  				<logic:notEmpty name="hoursOtList" scope="request">
  					<logic:iterate id="hot" name="hoursOtList">
		  				<tr OnMouseOver="CurrentColor=this.style.backgroundColor;this.style.backgroundColor='#7EAFCB'" OnMouseOut="this.style.backgroundColor=CurrentColor" style="background-color:#eeeeee;">
		                  <td class=gridViewItem><bean:write name="hot" property="employeeId"/></td>
		                  <td class=gridViewItem><bean:write name="hot" property="employeeName"/></td>
		                  <td class=gridViewItem><bean:write name="hot" property="workPlace"/></td>
		                  <td class=gridViewItem>
		                  	<c:out value="${dateS4ot}"/>&nbsp;至&nbsp;<c:out value="${dateE4ot}"/>
		                  </td>
		                  <td class=gridViewItem><bean:write name="hot" property="hoursOtSum"/></td>
		                  <td class=gridViewItem><bean:write name="hot" property="payStandard"/><c:out value="${hot.tgAndTp}"/></td>
		                  <td class=gridViewItem><bean:write name="hot" property="pay4hot"/></td>
		                </tr>
  		    	</logic:iterate>
  			</logic:notEmpty >
            </table>
          </div>
          <div style="width:900px">
       		<c:if test="${resultCount!=null}">
           		<br/><font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >
					共计<c:out value="${resultCount}"/>条
				</font></c:if>
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
<c:if test="${err_msg !=null}">
	<script type="text/javascript">
		var message = <%=request.getAttribute("err_msg")%>;
		alert(message);
	</script>
</c:if>
</body>
</html:html>
