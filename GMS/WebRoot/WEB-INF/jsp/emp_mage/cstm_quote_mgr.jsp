<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %> 
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %> 
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户报价管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/popup.js"></script>
	<script type="text/javascript">
		function query(){
			var customer =document.getElementById("customer");
			if(customer.value.length==0){alert("请选择要查询的客户！");return;}
			document.getElementById("cust").value=customer.value;
			document.getElementById("quoteForm").action="<%=request.getContextPath()%>/cstmPri.do?method=search";
			document.getElementById("quoteForm").submit();
		}
		function updateCus(){
			var customer =document.getElementById("customer");
			if(customer.value.length==0){alert("请先查询要修改的客户！");return;}
			//document.getElementById("cust").value=customer.value;
			document.getElementById("quoteForm").action="<%=request.getContextPath()%>/cstmPri.do?method=update";
			document.getElementById("quoteForm").submit();
		}
		
		function addCus(){
			doOpenDlgScr("新增客户报价","<%=request.getContextPath()%>/cstmPri.do?method=toAdd",850,350);
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
  <c:if test="${err_msg!=null}">
		<script type="text/javascript">
			var msg = '<%=request.getAttribute("err_msg")%>';
			alert(msg);
		</script>
	</c:if>
  <html:form styleId="quoteForm" action="/cstmPri.do?method=update" method="post">
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
      style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hb.gif); PADDING-BOTTOM: 10px; COLOR: white; PADDING-TOP: 10px; BACKGROUND-REPEAT: repeat-x; HEIGHT: 47px; TEXT-ALIGN: center; 0px: ">客户报价管理</span><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hr.gif); WIDTH: 60px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span></td>
        <td 
    style="BACKGROUND-POSITION: 50% bottom; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_rc.gif)" 
    width=10></td>
      </tr>
      <tr>
        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_ls.gif)">&nbsp;</td>
        <td  style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; COLOR: #566984; PADDING-TOP: 10px; BACKGROUND-COLOR: white;width: 10px" vAlign=top align=left>
			<div style="width: 900px;"><span style="color:#364c6d">客户姓名：</span>
				<select id="customer" name="customer" onchange="queryQuote();">
					<option value="" class="slt4dim">请选择</option>
<!--					<option class="slt4dim" value="${china_bank}">${china_bank}</option>-->
					<c:if test="${cusList!=null}">
						<logic:iterate id="cus" name="cusList">
							<option class="slt4dim" value="${cus}" <c:if test="${customerName==cus}">selected</c:if>>
							<c:if test="${cus==china_bank}">${chinabank4jsp}</c:if>
							<c:if test="${cus!=china_bank}">${cus}</c:if>
							</option>
						</logic:iterate>
					</c:if>
				</select>
				<input type="button" value="查询" class="cmdField" onclick="query();">
			</div>
			<br/>
			 <div style="width: 900px;overflow: auto;">
            <table class=gridView id=ctl00_ContentPlaceHolder2_GridView1 style=" BORDER-COLLAPSE: collapse;" width="100%">
	            <tr >
					<th class=gridViewHeader scope=col style="width:20%">客户名称</th>
					<th class=gridViewHeader scope=col style="width:20%">级别</th>
					<th class=gridViewHeader scope=col style="width:20%">技能</th>
					<th class=gridViewHeader scope=col style="width:20%">客户收入（每人天）</th>
					<th class=gridViewHeader scope=col style="width:20%">加班补贴标准（每小时）</th>
	            </tr>
				<tr >
					<td rowspan="6" align="center" valign="middle" class=gridViewItem>
						<c:if test="${customerName==china_bank}">${chinabank4jsp}</c:if>
						<c:if test="${customerName!=china_bank}">${customerName}</c:if>
					</td>
					<td class=gridViewItem>高级</td>
					<td class=gridViewItem>测试</td>
					<td class=gridViewItem><html:text style="color:black;text-align:center" name="cqi" property="price4ST"></html:text></td>
					<td class=gridViewItem><html:text style="color:black;text-align:center" name="cqi" property="overtimePrice4ST"></html:text></td>
				</tr>
				<tr >
					<td class=gridViewItem>中级</td>
					<td class=gridViewItem>测试</td>
					<td class=gridViewItem><html:text style="color:black;text-align:center" name="cqi" property="price4MT"></html:text></td>
					<td class=gridViewItem><html:text style="color:black;text-align:center" name="cqi" property="overtimePrice4MT"></html:text></td>
				</tr>
				<tr >
					<td class=gridViewItem>初级</td>
					<td class=gridViewItem>测试</td>
					<td class=gridViewItem><html:text style="color:black;text-align:center" name="cqi" property="price4JT"></html:text></td>
					<td class=gridViewItem><html:text style="color:black;text-align:center" name="cqi" property="overtimePrice4JT"></html:text></td>
				</tr>
				<tr >
					<td class=gridViewItem>高级</td>
					<td class=gridViewItem>开发</td>
					<td class=gridViewItem><html:text style="color:black;text-align:center" name="cqi" property="price4SD"></html:text></td>
					<td class=gridViewItem><html:text style="color:black;text-align:center" name="cqi" property="overtimePrice4SD"></html:text></td>
				</tr>
				<tr >
					<td class=gridViewItem>中级</td>
					<td class=gridViewItem>开发</td>
					<td class=gridViewItem><html:text style="color:black;text-align:center" name="cqi" property="price4MD"></html:text></td>
					<td class=gridViewItem><html:text style="color:black;text-align:center" name="cqi" property="overtimePrice4MD"></html:text></td>
				</tr>
				<tr >
					<td class=gridViewItem>初级</td>
					<td class=gridViewItem>开发</td>
					<td class=gridViewItem><html:text style="color:black;text-align:center" name="cqi" property="price4JD"></html:text></td>
					<td class=gridViewItem><html:text style="color:black;text-align:center" name="cqi" property="overtimePrice4JD"></html:text></td>
				</tr>
            </table>
          </div><br/>
          <div style="width: 900px;text-align: center;">
          	  <input type="button" value="新增" style="color:black;width:65px" onclick="addCus();">&nbsp;
          	  <input type="button" value="保存" style="color:black;width:65px" onclick="updateCus();">&nbsp;
	          <html:reset style="color:black;width:65px"  value="还原"/>
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
