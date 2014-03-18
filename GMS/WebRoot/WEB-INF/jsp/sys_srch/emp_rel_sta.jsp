<%@ page language="java" pageEncoding="utf-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
 <head>
    <title>员工归属汇总表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/content.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/popup.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
	<script type="text/javascript">
          	function creatHis(){
          		var year =document.getElementById("creatRelStaYear").value;
          		var month =document.getElementById("creatRelStaMonth").value;
          		var week =document.getElementById("creatRelStaWeek").value;
          		if(year.length==0 || month.length==0 || week.length==0){
          			alert("请输入完整的生成时间！");
          			return;
          		}
          		document.getElementById("empRelStaForm").action="<%=request.getContextPath()%>/empRelSta.do?method=creatHis";;
          		document.getElementById("empRelStaForm").submit();
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
  	<c:if test="${cre_suc!=null}">
		<script type="text/javascript">
			var msg = '<%=request.getAttribute("cre_suc")%>';
			alert(msg);
		</script>
	</c:if>
   <html:form styleId="empRelStaForm" action="/empRelSta.do?method=search" method="post">
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
       PADDING-BOTTOM: 10px; COLOR: white; PADDING-TOP: 10px; BACKGROUND-REPEAT: repeat-x; HEIGHT: 47px; TEXT-ALIGN: center; 0px: ">员工归属汇总表</span><span 
      style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hr.gif); WIDTH: 60px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span></td>
        <td 
    style="BACKGROUND-POSITION: 50% bottom; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_rc.gif)" 
    width=10></td>
      </tr>
      <tr>
        <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_ls.gif)">&nbsp;</td>
        <td  style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; COLOR: #566984; PADDING-TOP: 10px; BACKGROUND-COLOR: white;width: 10px" vAlign=top align=left>
			 <div style="width:950px">
			 		<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >查询时间:
			 		<html:select property="queryRelYear" >
			 			<html:option value="" styleClass="slt4dim">请选择</html:option>
			 			<html:option value="2013" styleClass="slt4dim">2013</html:option>
			 			<html:option value="2014" styleClass="slt4dim">2014</html:option>
			 			<html:option value="2015" styleClass="slt4dim">2015</html:option>
			 			<html:option value="2016" styleClass="slt4dim">2016</html:option>
			 			<html:option value="2017" styleClass="slt4dim">2017</html:option>
			 			<html:option value="2018" styleClass="slt4dim">2018</html:option>
			 			<html:option value="2019" styleClass="slt4dim">2019</html:option>
			 			<html:option value="2020" styleClass="slt4dim">2020</html:option>
			 		</html:select>年
			 		<html:select property="queryRelMonth" styleClass="slt4dim">
			 			<html:option value="" styleClass="slt4dim">请选择</html:option>
			 			<html:option value="01" styleClass="slt4dim">01</html:option>
			 			<html:option value="02" styleClass="slt4dim">02</html:option>
			 			<html:option value="03" styleClass="slt4dim">03</html:option>
			 			<html:option value="04" styleClass="slt4dim">04</html:option>
			 			<html:option value="05" styleClass="slt4dim">05</html:option>
			 			<html:option value="06" styleClass="slt4dim">06</html:option>
			 			<html:option value="07" styleClass="slt4dim">07</html:option>
			 			<html:option value="08" styleClass="slt4dim">08</html:option>
			 			<html:option value="09" styleClass="slt4dim">09</html:option>
			 			<html:option value="10" styleClass="slt4dim">10</html:option>
			 			<html:option value="11" styleClass="slt4dim">11</html:option>
			 			<html:option value="12" styleClass="slt4dim">12</html:option>
			 		</html:select>月
			 		
			 		<html:select property="queryRelWeek" styleClass="slt4dim">
			 			<html:option value="" styleClass="slt4dim">请选择</html:option>
			 			<html:option value="1" styleClass="slt4dim">第一</html:option>
			 			<html:option value="2" styleClass="slt4dim">第二</html:option>
			 			<html:option value="3" styleClass="slt4dim">第三</html:option>
			 			<html:option value="4" styleClass="slt4dim">第四</html:option>
			 		</html:select>周
			 		&nbsp;&nbsp;&nbsp;</font>
            		<html:submit value="查询" styleClass="cmdField" style="width:70px"/>&nbsp;&nbsp;&nbsp;
            		<span style="text-align: right;width: 550px">
            			<font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >生成时间：
            			<html:select property="creatRelStaYear" styleId="creatRelStaYear">
			 			<html:option value="" styleClass="slt4dim">请选择</html:option>
			 			<html:option value="2013" styleClass="slt4dim">2013</html:option>
			 			<html:option value="2014" styleClass="slt4dim">2014</html:option>
			 			<html:option value="2015" styleClass="slt4dim">2015</html:option>
			 			<html:option value="2016" styleClass="slt4dim">2016</html:option>
			 			<html:option value="2017" styleClass="slt4dim">2017</html:option>
			 			<html:option value="2018" styleClass="slt4dim">2018</html:option>
			 			<html:option value="2019" styleClass="slt4dim">2019</html:option>
			 			<html:option value="2020" styleClass="slt4dim">2020</html:option>
			 		</html:select>年
			 		<html:select property="creatRelStaMonth" styleClass="slt4dim"  styleId="creatRelStaMonth">
			 			<html:option value="" styleClass="slt4dim">请选择</html:option>
			 			<html:option value="01" styleClass="slt4dim">01</html:option>
			 			<html:option value="02" styleClass="slt4dim">02</html:option>
			 			<html:option value="03" styleClass="slt4dim">03</html:option>
			 			<html:option value="04" styleClass="slt4dim">04</html:option>
			 			<html:option value="05" styleClass="slt4dim">05</html:option>
			 			<html:option value="06" styleClass="slt4dim">06</html:option>
			 			<html:option value="07" styleClass="slt4dim">07</html:option>
			 			<html:option value="08" styleClass="slt4dim">08</html:option>
			 			<html:option value="09" styleClass="slt4dim">09</html:option>
			 			<html:option value="10" styleClass="slt4dim">10</html:option>
			 			<html:option value="11" styleClass="slt4dim">11</html:option>
			 			<html:option value="12" styleClass="slt4dim">12</html:option>
			 		</html:select>月
			 		
			 		<html:select property="creatRelStaWeek" styleClass="slt4dim" styleId="creatRelStaWeek">
			 			<html:option value="" styleClass="slt4dim">请选择</html:option>
			 			<html:option value="1" styleClass="slt4dim">第一</html:option>
			 			<html:option value="2" styleClass="slt4dim">第二</html:option>
			 			<html:option value="3" styleClass="slt4dim">第三</html:option>
			 			<html:option value="4" styleClass="slt4dim">第四</html:option>
			 		</html:select>周
			 		&nbsp;&nbsp;&nbsp;</font>
            		<input type="button" value="生成" class="cmdField" style="width:70px" onclick="creatHis();"/>
            		
            		</span>
			 </div>
			 <div style="width: 950px;overflow: auto;height: 95%;">
            <table class=gridView id=ctl00_ContentPlaceHolder2_GridView1 style=" BORDER-COLLAPSE: collapse;" width="100%">
              <tr >
                  <th class=gridViewHeader scope=col>行业</th>
                  <th class=gridViewHeader scope=col>业务条线</th>
                  <th class=gridviewHeader scope=col>技能</th>
                  <th class=gridviewHeader scope=col>期初人数</th>
                  <th class=gridviewHeader scope=col>入职</th>
                  <th class=gridviewHeader scope=col>调入</th>
                  <th class=gridviewHeader scope=col>离职</th>
                  <th class=gridviewHeader scope=col>调出</th>
                  <th class=gridviewHeader scope=col>期末人数</th>
                  <th class=gridviewHeader scope=col>计费</th>
                  <th class=gridviewHeader scope=col>下包商</th>
                  <th class=gridviewHeader scope=col>现场储备</th>
                  <th class=gridviewHeader scope=col>公司储备</th>
                  <th class=gridviewHeader scope=col>闲置</th>
                  <th class=gridviewHeader scope=col>休假</th>
                  <th class=gridviewHeader scope=col>管理</th>
<!--                  <th class=gridviewHeader scope=col>平台</th>-->
                  <th class=gridviewHeader scope=col>借出外部</th>
                  <th class=gridviewHeader scope=col>售前</th>
                  <th class=gridviewHeader scope=col>调休</th>
                </tr>
                <logic:empty name="relStaList" scope="request" >
  					 <tr>
  						<td style="color:#364c6d;" colspan="100">暂无数据!</td>
  					</tr>
  				</logic:empty>
  				<logic:notEmpty name="relStaList" scope="request">
  					<logic:iterate id="relSta" name="relStaList">
		  				<tr style="background-color:#eeeeee;">
		                  <td class=gridViewItem rowspan="2">中行</td>
		                  <td class=gridViewItem rowspan="2"><c:out value="${relSta.businessLineName}"></c:out></td>
		                  <td class=gridViewItem>测试</td>
		                  <td class=gridViewItem><c:out value="${relSta.empCountLastWeek4t}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.entryNum4t}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.callInNum4t}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.resignationNum4t}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.callOutNum4t}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.empCountEnd4t}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.chargingNum4t}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.subcontractorNum4t}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.cusReservesNum4t}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.comReservesNum4t}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.unUsedNum4t}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.vacNum4t}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.mgrNum4t}"/></td>
<!--		                  <td class=gridViewItem></td>-->
		                  <td class=gridViewItem><c:out value="${relSta.lendingOutNum4t}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.preSalesNum4t}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.txNum4t}"/></td>
		                </tr>
		                <tr style="background-color:#eeeeee;">
		                  <td class=gridViewItem>开发</td>
		                  <td class=gridViewItem><c:out value="${relSta.empCountLastWeek4d}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.entryNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.callInNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.resignationNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.callOutNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.empCountEnd4d}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.chargingNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.subcontractorNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.cusReservesNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.comReservesNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.unUsedNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.vacNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.mgrNum4d}"/></td>
<!--		                  <td class=gridViewItem></td>-->
		                  <td class=gridViewItem><c:out value="${relSta.lendingOutNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.preSalesNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${relSta.txNum4d}"/></td>
		                </tr>
  		    	</logic:iterate>
  			
  					<tr style="background-color:#eeeeee;">
		                  <td class=gridViewItem style="height: 35px;" colspan="3">合计</td>
		                  <td class=gridViewItem><c:out value="${ersiAll.empCountLastWeek4d}"/></td>
		                  <td class=gridViewItem><c:out value="${ersiAll.entryNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${ersiAll.callInNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${ersiAll.resignationNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${ersiAll.callOutNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${ersiAll.empCountEnd4d}"/></td>
		                  <td class=gridViewItem><c:out value="${ersiAll.chargingNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${ersiAll.subcontractorNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${ersiAll.cusReservesNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${ersiAll.comReservesNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${ersiAll.unUsedNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${ersiAll.vacNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${ersiAll.mgrNum4d}"/></td>
<!--		                  <td class=gridViewItem></td>-->
		                  <td class=gridViewItem><c:out value="${ersiAll.lendingOutNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${ersiAll.preSalesNum4d}"/></td>
		                  <td class=gridViewItem><c:out value="${ersiAll.txNum4d}"/></td>
	                </tr>
                </logic:notEmpty >
            </table>
          </div>
<!--          <div style="width:900px">-->
<!--       		<c:if test="${resultCount!=null}">-->
<!--           		<br/><font style="color:#364c6d;  line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left" >-->
<!--					共计<c:out value="${resultCount}"/>条-->
<!--				</font></c:if>-->
<!--		  </div>-->
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
