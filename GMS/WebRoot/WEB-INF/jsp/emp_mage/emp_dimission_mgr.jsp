<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %> 
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>员工离职管理
    </title>
   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/popup.js"></script>
	<script type="text/javascript">
			//验证必输项
			function vali(){
				var name ="";
				name = document.getElementById("ename").value;
				if("".equals(name) || name.length==0){
					alert('员工姓名/编号不能为空！');
				}
			}
			
          	//导入员工请假信息
          	function impInfo(){
          		doOpenDlgScr("导入员工离职信息","<%=request.getContextPath()%>/imp.do?method=toImp&status=edi",400,200);
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
			function save(eid){
				var doms = document.getElementsByName(eid);
				var str="";
				for(i=0;i<doms.length;i++){
					str+=doms[i].value+",";
				}
				window.location = "empdi.do?method=update&str="+str+"&eid="+eid;
				
			}
          </script>
     <script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
  </head>
  
  <body class="bod">
  <html:form action="empdi.do?method=search" method="post">
	<div >
  	<table cellSpacing=0 cellPadding=0 height="100%">
      <tr 
  style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/bg_header.gif); BACKGROUND-REPEAT: repeat-x; " 
  height=47>
        <td width=10><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hl.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span></td>
        <td><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hl2.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span><span 
      style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hb.gif); PADDING-BOTTOM: 10px; COLOR: white; PADDING-TOP: 10px; BACKGROUND-REPEAT: repeat-x; HEIGHT: 47px; TEXT-ALIGN: center; 0px: ">员工离职管理</span><span 
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
			 		<html:text property="ename4dim" styleClass="s4relquery"/>&nbsp;&nbsp;&nbsp;
			 		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >离职日期:</font>
			 		<html:text property="dimDateS" styleClass="s4relquery" size="15px" onclick="SelectDate(this)" readonly="readonly"/><font style="color:black">-</font><html:text property="dimDateE" styleClass="s4relquery" size="15px" onclick="SelectDate(this)" readonly="readonly"/>
			 		&nbsp;&nbsp;&nbsp;
			 		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >部门:</font>
			 		<html:select property="department4dim">
			 			<html:option value="" styleClass="slt4dim">请选择</html:option>
			 			<logic:iterate id="dpt" name="dpts">
			 				<html:option value="${dpt}" styleClass="slt4dim">${dpt}</html:option>
			 			</logic:iterate>
			 		</html:select>&nbsp;&nbsp;&nbsp;
            		<html:submit value="查询" styleClass="cmdField" onclick="vali();"/>&nbsp;&nbsp;&nbsp;
            		<c:if test="${resultCount!=null}">
            		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >
						共计<c:out value="${resultCount}"/>条
					</font></c:if>
			 </div>
			<div style="width:900px; overflow: auto; height: 85%;">
           		 <table class=gridView id=ctl00_ContentPlaceHolder2_GridView1 style=" BORDER-COLLAPSE: collapse;width:180%" >
             		 <tr >
<!--		                  <th class=gridViewHeader scope=col>全选<input type="checkbox" id="checkAll" /></th>-->
		                  <th class=gridViewHeader scope=col>员工编号</th>
		                  <th class=gridViewHeader scope=col>姓名</th>
		                  <th class=gridViewHeader scope=col>入职日期</th>
		                  <th class=gridViewHeader scope=col>部门</th>
		                  <th class=gridViewHeader scope=col>属地</th>
		                  <th class=gridviewHeader scope=col>人事子范围文本</th>
		                  <th class=gridviewHeader scope=col>员工组</th>
		                  <th class=gridviewHeader scope=col>通用职位</th>
		                  <th class=gridviewHeader scope=col>业务性质</th>
		                  <th class=gridviewHeader scope=col>事业群</th>
		                  <th class=gridviewHeader scope=col>事业群代码</th>
		                  <th class=gridviewHeader scope=col>部门代码</th>
		                  <th class=gridviewHeader scope=col>属地代码</th>
		                  <th class=gridviewHeader scope=col>人事子范围</th>
		                  <th class=gridviewHeader scope=col>工作地</th>
		                  <th class=gridviewHeader scope=col>试用期限</th>
		                  <th class=gridviewHeader scope=col>转正日期</th>
		                  <th class=gridviewHeader scope=col>离职日期</th>
		                  <th class=gridviewHeader scope=col>离职时状态</th>
		                  <th class=gridviewHeader scope=col>离职原因</th>
		                  <th class=gridviewHeader scope=col>主/被动</th>
		                  <th class=gridviewHeader scope=col>操作</th>
		             </tr>
	                <logic:empty name="ediList" scope="request" >
	  					 <tr> 
	  						<td style="color:#364c6d;" colspan="100">暂无数据!</td>
	  					</tr>
	  				</logic:empty>
	  				<logic:notEmpty name="ediList" scope="request">
	  					<logic:iterate id="empDim" name="ediList">
			  				<tr OnMouseOver="CurrentColor=this.style.backgroundColor;this.style.backgroundColor='#7EAFCB'" OnMouseOut="this.style.backgroundColor=CurrentColor" style="background-color:#eeeeee;">
<!--			                  <td class=gridViewItem><input name="chk"  type="checkbox" id="<bean:write name="empDim" property="employeeId"/>" /></td>-->
			                  <td class=gridViewItem><bean:write name="empDim" property="employeeId"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="employeeName"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="entryDate"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="department"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="dependency"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="personnelSubrangeText"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="employeeGroup"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="commonPosition"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="businessNature"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="enterpriseflock"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="enterpriseflockCode"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="departmentCode"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="dependencyCode"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="personnelSubrange"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="workPlace"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="trialPeriod"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="appraisalDate"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="resignationDate" format="yyyy-MM-dd"/></td>
			                  <td class=gridViewItem><bean:write name="empDim" property="resignationStatus"/></td>
			                  <td class=gridViewItem><input maxlength="80" type="text"  class="s4relquery" name ="${empDim.employeeId}" value="${empDim.resignationReasons}  "> </td>
			                  <td class=gridViewItem>
			                  <select name ="${empDim.employeeId}" >
				                  <option class="s4relquery"  value="0" <c:if test="${empDim.initiativeOrPassive=='0'}"> selected=true</c:if>>主动</option>
				                  <option class="s4relquery"  value="1" <c:if test="${empDim.initiativeOrPassive=='1'}"> selected=true</c:if>>被动</option> 
			                  </select>
			                  </td>
			                  <td class=gridViewItem><input type="button" class="cmdField"  value="保存" onclick="save(${empDim.employeeId});"> </td>
			                  </tr>
		  		    	</logic:iterate>
		  			</logic:notEmpty >
            </table>
          </div>
          <div style="width: 900px">
	          <html:button property="aaa"  styleClass="btn4do" value="导入员工离职信息" onclick="impInfo();"/>&nbsp;
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
