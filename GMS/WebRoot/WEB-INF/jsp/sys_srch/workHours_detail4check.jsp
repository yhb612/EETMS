<%@ page language="java" pageEncoding="utf-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>员工工时对账单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/content.css">
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/popup.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/calendar.js"></script>
</head>

<body>
	<c:if test="${err_msg!=null}">
		<script type="text/javascript">
			var msg = '<%=request.getAttribute("err_msg")%>';
			alert(msg);
		</script>
	</c:if>
	<html:form action="/wt4chk.do?method=search" method="post">
		<div>
			<table cellSpacing=0 cellPadding=0 height="100%">
				<tr style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/bg_header.gif); BACKGROUND-REPEAT: repeat-x; " height=47>
					<td width=10>
						<span
							style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hl.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span>
					</td>
					<td>
						<span
							style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hl2.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span><span
							style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hb.gif); PADDING-BOTTOM: 10px; COLOR: white; PADDING-TOP: 10px; BACKGROUND-REPEAT: repeat-x; HEIGHT: 47px; TEXT-ALIGN: center; 0px: ">员工工时对账单</span><span
							style="FLOAT: left; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_hr.gif); WIDTH: 60px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></span>
					</td>
					<td
						style="BACKGROUND-POSITION: 50% bottom; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_rc.gif)"
						width=10></td>
				</tr>
				<tr>
					<td
						style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_ls.gif)">
						&nbsp;
					</td>
					<td
						style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; COLOR: #566984; PADDING-TOP: 10px; BACKGROUND-COLOR: white; width: 10px"
						vAlign=top align=left>
						<div style="width: 900px">
							<font
								style="color: #364c6d; line-height: 12px; border-bottom: #bad6ec 1px solid; text-align: left">员工编号/员工姓名:</font>
							<html:text property="employeeId4s" styleClass="gridViewItem"
								size="12" />
							<html:submit value="查询" styleClass="cmdField" />
						</div><br/>
						<div style="width: 900px;">
							<table class=gridView id=ctl00_ContentPlaceHolder2_GridView1
								style="BORDER-COLLAPSE: collapse;" >
								<logic:empty name="ebii" scope="request">
									<tr>
										<td style="color: #364c6d;" colspan="100">
											暂无数据!
										</td>
									</tr>
								</logic:empty>
								<logic:notEmpty name="ebii" scope="request">
									<tr>
										<td class=gridViewHeader  style="width:16%;">
											员工编号:
										</td>
										<td class=gridViewItem  style="width:16%;">
											<bean:write name="ebii" property="employeeId"/>
										</td>
										<td class=gridViewHeader  style="width:16%;">
											姓名:
										</td>
										<td class=gridViewItem  style="width:16%;">
											<bean:write name="ebii" property="employeeName"/>
										</td>
										<td class=gridViewHeader  style="width:16%;">
											入职日期:
										</td>
										<td class=gridViewItem  style="width:16%;">
											<c:if test="${ebii.entryDate!=null}">
												<bean:write name="ebii" property="entryDate" format="yyyy-MM-dd"/>
											</c:if>
										</td>
									</tr>
									<tr>
										<td class=gridViewHeader>
											工作日应出勤总工时:
										</td>
										<td class=gridViewItem>
											<bean:write name="edi" property="standardHoursAll"/>
										</td>
										<td class=gridViewHeader>
											中行确认正常总工时：
										</td>
										<td class=gridViewItem>
											<bean:write name="hwsi" property="customerSureTotalHours"/>
										</td>
										<td class=gridViewHeader>
											统计截止日期：
										</td>
										<td class=gridViewItem>
											<c:if test="${hwsi.byTheStatisticalDate!=null}">
												<bean:write name="hwsi" property="byTheStatisticalDate" format="yyyy-MM-dd"/>
											</c:if>
										</td>
									</tr>
									<tr>
										<td class=gridViewHeader>
											加班可倒休工时:
										</td>
										<td class=gridViewItem>
											<bean:write name="hwsi" property="workOtimeAhughTotalHours"/>
										</td>
										<td class=gridViewHeader>
											加班可补贴工时：
										</td>
										<td class=gridViewItem>
											<bean:write name="hwsi" property="workOtimeSubsidiesTotalHours"/>
										</td>
										<td class=gridViewHeader>
											剩余倒休工时：
										</td>
										<td class=gridViewItem>
											<bean:write name="hwsi" property="lastSwoppedTotalHours"/>
										</td>
									</tr>
									<tr>
										<td class=gridViewHeader>
											PSA上申请福利假工时：
										</td>
										<td class=gridViewItem>
											<bean:write name="hwsi" property="psaWelfareHoliaysTotalHours"/>
										</td>
										<td class=gridViewHeader>
											PSA上申请非福利假工时：
										</td>
										<td class=gridViewItem>
											<bean:write name="hwsi" property="psaNotWelfareHoliaysTotalHours"/>
										</td>
										<td class=gridViewHeader>
											迟到早退工时
										</td>
										<td class=gridViewItem>
											<bean:write name="s4eg" property="status0Hours"/>
										</td>
									</tr>
									<tr>
										<td class=gridViewHeader>
											现场储备工时：
										</td>
										<td class=gridViewItem>
											<bean:write name="s4eg" property="status1Hours"/>
										</td>
										<td class=gridViewHeader>
											回公司开会（培训）工时：
										</td>
										<td class=gridViewItem>
											<bean:write name="s4eg" property="status2Hours"/>
										</td>
										<td class=gridViewHeader>
											离场工时：
										</td>
										<td class=gridViewItem>
											<bean:write name="s4eg" property="status3Hours"/>
										</td>
									</tr>
									<tr>
										<td class=gridViewHeader>
											公司储备工时：
										</td>
										<td class=gridViewItem>
											<bean:write name="s4eg" property="status4Hours"/>
										</td>
										<td class=gridViewHeader>
											员工打卡异常冲倒休工时：
										</td>
										<td class=gridViewItem>
											<bean:write name="s4eg" property="status5Hours"/>
										</td>
										<td class=gridViewHeader>
											闲置工时：
										</td>
										<td class=gridViewItem>
											<bean:write name="s4eg" property="status6Hours"/>
										</td>
									</tr>
									<tr>
										<td class=gridViewHeader>
											正常倒休工时：
										</td>
										<td class=gridViewItem>
											<bean:write name="s4eg" property="status7Hours"/>
										</td>
										<td class=gridViewHeader>
											工作饱和度（%）：
										</td>
										<td class=gridViewItem>
											<c:out value="${saturation}"/>%
										</td>
										<td class=gridViewHeader>
											出勤率（%）：
										</td>
										<td class=gridViewItem>
											<c:out value="${attendance}"/>%
										</td>
									</tr>
								</logic:notEmpty>
							</table>
						</div>
					</td>
					<td
						style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_rs.gif)"></td>
				</tr>

				<tr
					style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_fs.gif); BACKGROUND-REPEAT: repeat-x"
					height=10>
					<td
						style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_lf.gif)"></td>
					<td
						style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_fs.gif)"></td>
					<td
						style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/main_rf.gif)"></td>
				</TR>
			</table>
		</div>
	</html:form>
</body>
</html:html>
