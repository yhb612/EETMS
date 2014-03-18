<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %> 
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>标准工时管理</title>
   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/popup.js"></script>
	<script type="text/javascript">
			//验证必输项
			function vali(){
			}
			
          	//导入标准工时信息
          	function impInfo(){
          		doOpenDlgScr("导入标准工时","<%=request.getContextPath()%>/imp.do?method=toImp&status=hws",400,200);
          	}
          	
          	 //弹出详细信息
    		var g_pop=null;
	
		  	function doOpenDlgScr(sTitle,sUrl,iWidth,iHeight) {
			  g_pop = null;
			  g_pop = new Popup({ contentType:1,scrollType:"yes",isReloadOnClose:false });
			  g_pop.config.width = iWidth;
			  g_pop.config.height = iHeight;
			  g_pop.config.isSupportDraging= true;
			  g_pop.setContent("title",sTitle);
			  g_pop.setContent("contentUrl",sUrl);
			  g_pop.build();
			  g_pop.show();
			}
	
		    //关闭详细信息
			function g_close_pop(){
			  g_pop.close();
			}
			
          </script>
     <script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
  </head>
  
  <body class="bod">
  <html:form action="/hoursworkStd.do?method=search">
	<div >
  	<table cellSpacing=0 cellPadding=0 height="100%">
      <tr 
  style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/bg_header.gif); BACKGROUND-REPEAT: repeat-x; " 
  height=47>
        <td width=10><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hl.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span></td>
        <td><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hl2.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span><span 
      style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hb.gif); PADDING-BOTTOM: 10px; COLOR: white; PADDING-TOP: 10px; BACKGROUND-REPEAT: repeat-x; HEIGHT: 47px; TEXT-ALIGN: center; 0px: ">标准工时管理</span><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hr.gif); WIDTH: 60px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span></td>
        <td 
    style="BACKGROUND-POSITION: 50% bottom; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_rc.gif)" 
    width=10></td>
      </tr>
      <tr>
        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_ls.gif)">&nbsp;</td>
        <td  style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; COLOR: #566984; PADDING-TOP: 10px; BACKGROUND-COLOR: white;width: 10px" vAlign=top align=left>
			 <div style="width:900px">
			 		
			 		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >开始日期:</font>
			 		<html:text property="stdDateS" styleClass="s4relquery" size="15px" onclick="SelectDate(this)" readonly="readonly"/>
			 		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >-结束日期:</font>
			 		<html:text property="stdDateE" styleClass="s4relquery" size="15px" onclick="SelectDate(this)" readonly="readonly"/>
			 		&nbsp;&nbsp;&nbsp;
            		<html:submit value="查询" styleClass="cmdField"/>&nbsp;&nbsp;&nbsp;
            		<c:if test="${resultCount!=null}">
            		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >
						共计<c:out value="${resultCount}"/>条
					</font></c:if>
			 </div>
			<div style="width:900px; overflow: auto; height: 85%;">
           		 <table class=gridView id=ctl00_ContentPlaceHolder2_GridView1 style=" BORDER-COLLAPSE: collapse;width:100%" >
             		 <tr >

		                  <th class=gridviewHeader scope=col>日期</th>
		                  <th class=gridviewHeader scope=col>星期</th>
		                  <th class=gridviewHeader scope=col>应出勤工时</th>
		                  <th class=gridviewHeader scope=col>日期类型</th>
		             </tr>
	                <logic:empty name="hwsiList" scope="request" >
	  					 <tr> 
	  						<td style="color:#364c6d;" colspan="100">暂无数据!</td>
	  					</tr>
	  				</logic:empty>
	  				<logic:notEmpty name="hwsiList" scope="request">
	  					<logic:iterate id="hwstd" name="hwsiList">
			  				<tr OnMouseOver="CurrentColor=this.style.backgroundColor;this.style.backgroundColor='#7EAFCB'" OnMouseOut="this.style.backgroundColor=CurrentColor" style="background-color:#eeeeee;">
			                  <td class=gridViewItem><bean:write name="hwstd" property="standardDate" format="yyyy-MM-dd"/></td>
			                  <td class=gridViewItem><bean:write name="hwstd" property="standardWeek"/></td>
			                  <td class=gridViewItem>${hwstd.standardHours }</td>
			                  <td class=gridViewItem>
			                  <c:if test="${hwstd.standardDateType=='0'}">工作日</c:if>
			                  <c:if test="${hwstd.standardDateType=='1'}">双休日</c:if>
			                  <c:if test="${hwstd.standardDateType=='2'}">节假日</c:if>
			                  </td>
			                 </tr>
		  		    	</logic:iterate>
			  			<tr>
			  			  <td class=gridViewItem>合计：</td>
		                  <td class=gridViewItem>工作日：${hwis4count.workDays }&nbsp;天</td>
		                  <td class=gridViewItem>双休日：${hwis4count.weekEndDays }&nbsp;天</td>
		                  <td class=gridViewItem>节假日：${hwis4count.redLetterDays }&nbsp;天</td>
			  			</tr>
		  			</logic:notEmpty >
            </table>
          </div>
          <div style="width: 900px">
	          <html:button property="aaa"  styleClass="btn4do" value="导入标准工时信息" onclick="impInfo();"/>&nbsp;
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
