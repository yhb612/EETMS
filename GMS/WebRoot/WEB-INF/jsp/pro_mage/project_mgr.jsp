<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %> 
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %> 
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>项目信息管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/popup.js"></script>
	<script type="text/javascript">
			//添加项目信息
          	function insertInfo(){
          		doOpenDlgScr("新增项目信息","<%=request.getContextPath()%>/proj.do?method=toAdd",850,330);
          	}
          	
          	//修改项目信息
          	function updateInfo(){
	          	 //得到所有下拉框数组
	          	 var c = document.getElementsByName("chk");
	             //得到选中的信息标识
	             var pid="";
	             //得到选中的总数
	             var count=0;
		         for(var i = 0;i<c.length;i++){
		             if(c[i].checked==true){
		                count++;
		                pid+=c[i].value;
		              }
		         }
		         if(count==1){
		         	doOpenDlgScr("修改项目信息","<%=request.getContextPath()%>/proj.do?method=toUpdate&pid="+pid,850,330);
		         }else{
		         		alert("请选择一条记录进行操作!");
		         }    
          	}
          	
          	//删除项目信息
          	function delInfo(){
          		 //得到所有下拉框数组
	          	 var c = document.getElementsByName("chk");
	             //得到选中的信息标识
	             var pidGrp="";
	             //得到选中的总数
	             var count=0;
		         for(var i = 0;i<c.length;i++){
		             if(c[i].checked==true){
		                count++;
		                pidGrp+=c[i].value+",";
		              }
		         }
		         if(count>0){
            		window.location="proj.do?method=delInfo&pidGrp="+pidGrp;
		         }else{
		         	alert("请选择记录!");
		         }
          	}
          	
          	//导入项目信息
          	function impInfo(){
          		doOpenDlgScr("导入项目信息","<%=request.getContextPath()%>/imp.do?method=toImp&status=proj",400,200);
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
  
  <body class="bod">
  <html:form action="/proj.do?method=search" method="post">
	<div >
  	<table cellSpacing=0 cellPadding=0 height="100%" >
      <tr 
  style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/bg_header.gif); BACKGROUND-REPEAT: repeat-x; " 
  height=47>
        <td width=10><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hl.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span></td>
        <td><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hl2.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span><span 
      style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hb.gif); PADDING-BOTTOM: 10px; COLOR: white; PADDING-TOP: 10px; BACKGROUND-REPEAT: repeat-x; HEIGHT: 47px; TEXT-ALIGN: center; 0px: ">项目信息管理</span><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hr.gif); WIDTH: 60px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span></td>
        <td 
    style="BACKGROUND-POSITION: 50% bottom; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_rc.gif)" 
    width=10></td>
      </tr>
      <tr>
        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_ls.gif)">&nbsp;</td>
        <td  style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; COLOR: #566984; PADDING-TOP: 10px; BACKGROUND-COLOR: white;width: 10px" vAlign=top align=left>
			 <div style="width:900px">
			 		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >项目名称/项目编号:</font>
			 		<html:text property="pname" styleClass="gridViewItem"/>;&nbsp;&nbsp;
			 		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >项目经理:</font>
			 		<html:select property="proMgr4pro"  styleClass="slt4dim">
				 		<html:option value=""  styleClass="slt4dim">请选择</html:option>
				 		<logic:notEmpty name="proMgrs4pro" scope="request">
				 			<logic:iterate id="mgr4pro" name="proMgrs4pro" >
				 				<html:option  styleClass="slt4dim" value="${mgr4pro}">${mgr4pro}</html:option>
				 			</logic:iterate>
				 		</logic:notEmpty>
				 	</html:select>&nbsp;&nbsp;
				 	<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >实施部:</font>
			 		<html:select property="dpt4pro" styleClass="slt4dim">
			 			<html:option value="" styleClass="slt4dim">请选择</html:option>
			 			<logic:notEmpty name="dpts4pro" scope="request">
				 			<logic:iterate id="dptp" name="dpts4pro">
				 				<html:option value="${dptp}" styleClass="slt4dim">${dptp}</html:option>
				 			</logic:iterate>
			 			</logic:notEmpty>
			 		</html:select>&nbsp;&nbsp;
            		<html:submit value="查询" styleClass="cmdField"/>&nbsp;&nbsp;&nbsp;
            		<c:if test="${resultCount!=null}">
            		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >
						共计<c:out value="${resultCount}"/>条
					</font></c:if>
			 </div>
			 <div style="width: 900px;overflow: auto;height: 85%;">
            <table class=gridView id=ctl00_ContentPlaceHolder2_GridView1 style=" BORDER-COLLAPSE: collapse;" width="100%">
              <tr >
                  <th class=gridViewHeader scope=col><input type="checkbox" id="chkall" onclick="selectAll();" /></th>
                  <th class=gridViewHeader scope=col>项目编号</th>
                  <th class=gridViewHeader scope=col>项目名称</th>
                  <th class=gridViewHeader scope=col>事业部</th>
                  <th class=gridViewHeader scope=col>实施部</th>
                  <th class=gridViewHeader scope=col>项目经理</th>
                  <th class=gridviewHeader scope=col>客户名称</th>
                  <th class=gridviewHeader scope=col>开始日期</th>
                  <th class=gridviewHeader scope=col>结束日期</th>
                </tr>
                <logic:empty name="lt" scope="request" >
  					 <tr>
  						<td style="color:#364c6d;" colspan="100">暂无数据!</td>
  					</tr>
  				</logic:empty>
  				<logic:notEmpty name="lt" scope="request">
  					<logic:iterate id="proj" name="lt">
		  				<tr OnMouseOver="this.style.backgroundColor='#7EAFCB'" OnMouseOut="this.style.backgroundColor='#eeeeee'" style="background-color:#eeeeee;">
		  				 
		                  <td class=gridViewItem>
		                  	<input type="checkbox" name="chk" value="<bean:write name="proj" property="proId"/>"/>
		                  </td>
		                  <td class=gridViewItem><bean:write name="proj" property="proId"/></td>
		                  <td class=gridViewItem><bean:write name="proj" property="proName"/></td>
		                  <td class=gridViewItem><bean:write name="proj" property="careerdepartment"/></td>
		                  <td class=gridViewItem><bean:write name="proj" property="implementDepartment" /></td>
		                  <td class=gridViewItem><bean:write name="proj" property="projectManager"/></td>
		                  <td class=gridViewItem><bean:write name="proj" property="customerName"/></td>
		                  <td class=gridViewItem><bean:write name="proj" property="beginDate" format="yyyy-MM-dd"/></td>
		                  <td class=gridViewItem><bean:write name="proj" property="endDate" format="yyyy-MM-dd"/></td>
  		    	</logic:iterate>
  			</logic:notEmpty >
            </table>
          </div>
          <div style="width: 900px">
	          <html:button property="aaa"  styleClass="btn4do" value="新增项目信息" onclick="insertInfo();"/>&nbsp;
	          <html:button property="aaa"  styleClass="btn4do" value="修改项目信息" onclick="updateInfo();"/>&nbsp;
	          <html:button property="aaa"  styleClass="btn4do" value="删除项目信息" onclick="delInfo();"/>&nbsp;
	          <html:button property="aaa"  styleClass="btn4do" value="导入项目信息" onclick="impInfo();"/>&nbsp;
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
