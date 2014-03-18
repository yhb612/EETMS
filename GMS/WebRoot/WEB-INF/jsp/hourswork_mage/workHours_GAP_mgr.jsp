<%@ page language="java" pageEncoding="utf-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
 <head>
    <title>GAP工时核查</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/popup.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
	<script type="text/javascript">
          	
          	//导入信息
          	function impInfo(){
          		doOpenDlgScr("导入信息","<%=request.getContextPath()%>/imp.do?method=toImp&status=whg",400,200);
          	}
          	//导出信息
          	function eptInfo(){
          		if(val()){
	          		document.getElementById("gapId").action="<%=request.getContextPath()%>/gapch.do?method=exportData";
	          		document.getElementById("gapId").submit();
          		}
          	}
          	//保存
          	function update(){
          		var chks = document.getElementsByName("chk");
          		var msg="";
          		var chkCount=0;
          		for(var i=0;i<chks.length;i++){
          			if(chks[i].checked==true){
          				chkCount++;
          				msg+="_-_"+chks[i].value+"_-_";
          			}
          		}
          		if(chkCount==0){
          			alert('请选择要修改的数据！');
          			return;
          		}
          		document.getElementById("msg4Ud").value=msg;
          		document.getElementById("gapId").action="<%=request.getContextPath()%>/gapch.do?method=update";
          		document.getElementById("gapId").submit();
          	}
          	function query(){
          		if(val()){
          			
          			document.getElementById("gapId").action="<%=request.getContextPath()%>/gapch.do?method=search";
          			document.getElementById("gapId").submit();
          		}
          		
          	}
          	function val(){
	 		var dateS4g = document.getElementById("dateS4g").value;
          	var dateE4g = document.getElementById("dateE4g").value;
	 		if(dateS4g.length==0 || dateE4g.length==0){
	 			alert("开始日期和结束日期不能为空，请重新操作!");
	 			return false;
	 		}
	 		return true;
	 		}
          	//全选
		   	function selectAll(){
				var chked = document.getElementById("chkall").checked;
				var chks = document.getElementsByName("chk");
				for(var i=0;i<chks.length;i++){
					chks[i].checked=chked;
				}
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
  </head>
  
  <body>
   <c:if test="${gap_exp!=null}">
		<script type="text/javascript">
			var msg = '<%=request.getAttribute("gap_exp")%>';
			alert(msg);
		</script>
	</c:if>
	<c:if test="${imp_msg!=null}">
		<script type="text/javascript">
			var msg = '<%=request.getAttribute("imp_msg")%>';
			alert(msg);
		</script>
		</c:if>
   <html:form styleId="gapId" action="/gapch.do?method=search" method="post">
	<div >
	<input type="hidden" id="emid" value="${emid}">
	<html:hidden property="gapChList" value="${gapChList}"/>
	<html:hidden property="msg4Ud" value=""/>
  	<table cellSpacing=0 cellPadding=0 height="100%">
      <tr 
  style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/bg_header.gif); BACKGROUND-REPEAT: repeat-x; " 
  height=47>
        <td width=10><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hl.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span></td>
        <td><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hl2.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span><span 
      style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hb.gif); PADDING-BOTTOM: 10px; COLOR: white; PADDING-TOP: 10px; BACKGROUND-REPEAT: repeat-x; HEIGHT: 47px; TEXT-ALIGN: center; 0px: ">GAP工时核查</span><span 
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
			 		<html:text property="Id4g" styleClass="gridViewItem" size="12"/>&nbsp;&nbsp;
			 		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >开始时间:</font><span style="color:red;">*</span>
			 		<html:text styleId="dateS4g" property="dateS4g" size="12" onclick="SelectDate(this)" readonly="true" styleClass="gridViewItem"/>&nbsp;
			 		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >结束时间:</font><span style="color:red;">*</span>
			 		<html:text styleId="dateE4g" property="dateE4g" size="12" onclick="SelectDate(this)" readonly="true" styleClass="gridViewItem"/>&nbsp;
			 		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >差异种类:</font>
			 		<html:select property="gapType" >
						<html:option styleClass="gridViewItem" value="" >请选择</html:option>
						<html:option value="0" styleClass="gridViewItem">正常工时差异</html:option>
						<html:option value="1" styleClass="gridViewItem">重复工时差异</html:option>
					</html:select>&nbsp;&nbsp;
					<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >属地:</font>
					<html:select property="workPlace" >
						<html:option styleClass="gridViewItem" value="" >请选择</html:option>
						<c:if test="${workPlaces!=null}">
							<c:forEach items="${workPlaces}" var="wPlace">
								<c:if test="${wPlace!=null}">
								<html:option value="${wPlace}" styleClass="gridViewItem" >${wPlace}</html:option>
								</c:if>
							</c:forEach>
						</c:if>
					</html:select>&nbsp;&nbsp;&nbsp;
            	    <input type="button" value="查询" class="cmdField" onclick="query();">
            	    <c:if test="${resultCount!=null}">
            		<br/><font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >
						共计<c:out value="${resultCount}"/>条
					</font></c:if>
			 </div>
			 <div style="width: 900px;overflow: auto;height: 85%;">
            <table class=gridView id=ctl00_ContentPlaceHolder2_GridView1 style=" BORDER-COLLAPSE: collapse;" width="200%">
              <tr >
                  <th class=gridViewHeader scope=col>
                  <input type="checkbox" id="chkall" onclick="selectAll();" />
                  </th>
                  <th class=gridViewHeader scope=col>员工编号</th>
                  <th class=gridViewHeader scope=col>员工姓名</th>
                  <th class=gridViewHeader scope=col>日期</th>
                  <th class=gridViewHeader scope=col>星期</th>
                  <th class=gridViewHeader scope=col>日期类型</th>
                  <th class=gridviewHeader scope=col>应出勤工时</th>
                  <th class=gridviewHeader scope=col>所属项目名称</th>
                  <th class=gridviewHeader scope=col>属地</th>
                  <th class=gridviewHeader scope=col>技术平台</th>
                  <th class=gridviewHeader scope=col>技术等级</th>
                  <th class=gridviewHeader scope=col>在场类型</th>
                  <th class=gridviewHeader scope=col>客户确认正常工时</th>
                  <th class=gridviewHeader scope=col>客户确认加班工时</th>
                  <th class=gridviewHeader scope=col>PSA请假工时</th>
                  <th class=gridviewHeader scope=col>PSA请假类型</th>
                  <th class=gridviewHeader scope=col>新增可倒休工时</th>
                  <th class=gridviewHeader scope=col>新增可补贴工时</th>
                  <th class=gridviewHeader scope=col>差异工时</th>
                  <th class=gridviewHeader scope=col>差异类型</th> 
                  <th class=gridviewHeader scope=col>差异原因</th> 
                  <th class=gridviewHeader scope=col>客户方负责人</th> 
                  <th class=gridviewHeader scope=col>公司项目经理</th>
                  <th class=gridviewHeader scope=col>客户所属项目</th> 
                  <th class=gridviewHeader scope=col>剩余可倒休总工时</th> 
                </tr>
                <logic:empty name="gapChList" scope="request" >
  					 <tr>
  						<td style="color:#364c6d;" colspan="100">暂无数据!</td>
  					</tr>
  				</logic:empty>
  				<logic:notEmpty name="gapChList" scope="request">
  					<logic:iterate id="whg" name="gapChList">
  						<input type="hidden" name="${whg.standardDate}" value="${whg.currentGapHours}">
		  				<tr OnMouseOver="CurrentColor=this.style.backgroundColor;this.style.backgroundColor='#7EAFCB'" OnMouseOut="this.style.backgroundColor=CurrentColor" style="background-color:#eeeeee;">
		                  <td class=gridViewItem>
		                  	<input type="checkbox" name="chk" value="${whg.employeeId},${whg.standardDate}"/>
		                  </td>
		                  <td class=gridViewItem><bean:write name="whg" property="employeeId"/></td>
		                  <td class=gridViewItem><bean:write name="whg" property="employeeName"/></td>
		                  <td class=gridViewItem><bean:write name="whg" property="standardDate" format="yyyy-MM-dd"/></td>
		                  <td class=gridViewItem><bean:write name="whg" property="standardWeek"/></td>
		                  <td class=gridViewItem>
		                  <c:if test="${whg.standardDateType=='0'}">
		                  		工作日
		                  </c:if>
		                  <c:if test="${whg.standardDateType=='1'}">
		                  		双休日
		                  </c:if>
		                  <c:if test="${whg.standardDateType=='2'}">
		                  		节假日
		                  </c:if>
		                  </td>
		                  <td class=gridViewItem><bean:write name="whg" property="standardHours"/></td>
		                  <td class=gridViewItem><bean:write name="whg" property="projectName"/></td>
		                  <td class=gridViewItem><bean:write name="whg" property="workPlace"/></td>
		                  <td class=gridViewItem><bean:write name="whg" property="technologyPlatform"/></td>
		                  <td class=gridViewItem><bean:write name="whg" property="technologyGrade"/></td>
		                  <td class=gridViewItem><bean:write name="whg" property="presenceOfType"/></td>
		                  <td class=gridViewItem><bean:write name="whg" property="customerVerifyHours"/></td>
		                  <td class=gridViewItem><bean:write name="whg" property="customerVerifyOvertimeHours"/></td>
		                  <td class=gridViewItem><bean:write name="whg" property="psaLeaveHours"/></td>
		                  <td class=gridViewItem><bean:write name="whg" property="psaLeaveHoursType"/></td>
		                  <td class=gridViewItem><bean:write name="whg" property="currentAhughHours"/></td>
		                  <td class=gridViewItem><bean:write name="whg" property="currentSubsidiesHours"/></td>
		                  <td class=gridViewItem><bean:write name="whg" property="currentGapHours"/> </td>
		                  <td class=gridViewItem style="text-align: left;">
							<select name="${whg.employeeId},${whg.standardDate}" >
								<option class="gridViewItem" value="" >请选择</option> 
								<option value="2" class="gridViewItem" <c:if test="${whg.gapType=='2'}">selected=true</c:if>>回公司开会</option>
								<option value="1" class="gridViewItem" <c:if test="${whg.gapType=='1'}">selected=true</c:if>>现场储备</option>
								<option value="4" class="gridViewItem" <c:if test="${whg.gapType=='4'}">selected=true</c:if>>公司储备</option>
								<option value="0" class="gridViewItem" <c:if test="${whg.gapType=='0'}">selected=true</c:if>>迟到早退(更新倒休)</option>
								<option value="5" class="gridViewItem" <c:if test="${whg.gapType=='5'}">selected=true</c:if>>打卡异常(更新倒休)</option>
								<option value="3" class="gridViewItem" <c:if test="${whg.gapType=='3'}">selected=true</c:if>>离场(更新倒休)</option>
								<option value="6" class="gridViewItem" <c:if test="${whg.gapType=='6'}">selected=true</c:if>>闲置(更新倒休)</option>
								<option value="7" class="gridViewItem" <c:if test="${whg.gapType=='7'}">selected=true</c:if>>正常倒休(更新倒休)</option>
								<option value="8" class="gridViewItem" <c:if test="${whg.gapType=='8'}">selected=true</c:if>>重复工时追加倒休(更新倒休)</option>
							</select>
		                  </td>
		                  <td class=gridViewItem><input class="gridViewItem" type=text name="${whg.employeeId},${whg.standardDate}" value="${whg.employeeGapReason}"> </td>
		                  <td class=gridViewItem><input class="gridViewItem" type=text name="${whg.employeeId},${whg.standardDate}" value="${whg.customerResponsiblePerson}"> </td>
		                  <td class=gridViewItem><input class="gridViewItem" type=text name="${whg.employeeId},${whg.standardDate}" value="${whg.companyProjectManager}"> </td>
		                  <td class=gridViewItem><input class="gridViewItem" type=text name="${whg.employeeId},${whg.standardDate}" value="${whg.belongedProject}"> </td>
		                  <td class=gridViewItem><c:out value="${whg.hours4VacTotal}"></c:out></td>
		                  
		                </tr>
		                
  		    	</logic:iterate>
  			</logic:notEmpty >
            </table>
          </div>
          <div style="width: 900px">
	          <html:button property="aaa"  styleClass="btn4do" value="保  存" onclick="update();"/>&nbsp;
	          <html:button property="aaa"  styleClass="btn4do" value="导出信息" onclick="eptInfo();"/>&nbsp;
	          <html:button property="aaa"  styleClass="btn4do" value="导入信息" onclick="impInfo();"/>&nbsp;
	          <span style="color:red">注：导入操作时，会同步更新工时信息！</span>
          </div>
           </td>
        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_rs.gif)"></td>
      </tr>
      
      <tr style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_fs.gif); BACKGROUND-REPEAT: repeat-x" height=10>
        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_lf.gif)"></td>
        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_fs.gif)"></td>
        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_rf.gif)">%</td>
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

