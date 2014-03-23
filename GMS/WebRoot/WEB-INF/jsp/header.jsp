<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type=text/css> 
#logo {
	font-size: 11px; 
}
#logo A {
	color: WHITE
}
#welcome {
	font-size: 12px; color: WHITE
}
form {
	margin: 0px
}
</style>

<script src="<%=request.getContextPath()%>/js/Clock.js" type=text/javascript></script>
</head>

<body 
style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/bg.gif); MARGIN: 0px; BACKGROUND-REPEAT: repeat-x">
<form id="form1">
  <div id=logo >
    <div style="PADDING-RIGHT: 50px; BACKGROUND-POSITION: right 50%; DISPLAY: block; PADDING-LEFT: 0px; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/bg_banner_menu.gif); PADDING-BOTTOM: 0px; PADDING-TOP: 3px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 30px; TEXT-ALIGN: right">
    		<img src="<%=request.getContextPath()%>/images/menu_seprator.gif" align=absMiddle> 
		 <a href="<%=request.getContextPath()%>/toIndex.do?method=toIndex">返回首页</a> 
		 	<img src="<%=request.getContextPath()%>/images/menu_seprator.gif" align=absMiddle> 
		 <a href="<%=request.getContextPath()%>">退出系统</a> 
    </div>
    <div style="DISPLAY: block; HEIGHT: 54px; font-size:x-large;margin-left: 70px;color: #AABFFF;letter-spacing: 3px">企业差旅管理系统11111</div>
    <div  style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/bg_nav.gif); BACKGROUND-REPEAT: repeat-x; HEIGHT: 30px">
      <table cellSpacing=0 cellPadding=0 width="100%">
        <tbody>
          <tr>
            <td>
              <div id = welcome><img src="<%=request.getContextPath()%>/images/nav_pre.gif" align=absMiddle> 
              		欢迎 <span id=lblBra>软通动力</span> 
              		<span id=lblDep>中行CBG实施部</span> [ ${requestScope.username} ] 光临 
              </div>
            </td>
            <td align=right width="60%"> 
            	<img src="<%=request.getContextPath()%>/images/menu_seprator.gif" align=absMiddle> 
            	<span id=clock style="font-size: 12px; color: WHITE"></span>
            	
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
<SCRIPT type=text/javascript>
    var clock = new Clock();
    clock.display(document.getElementById("clock"));
</script>
</form>
</body>
</html>