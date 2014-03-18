<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dtree.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dtree.css">
</head>
<body style="BACKGROUND-POSITION-Y: -120px; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/bg.gif); BACKGROUND-REPEAT: repeat-x">
<table height="77%" cellSpacing=0 cellPadding=0 width="19%">
  <tbody>
    <tr>
      <td width=10 height=29><IMG src="<%=request.getContextPath()%>/images/bg_left_tl.gif"></td>
      <td style="FONT-SIZE: 18px; BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/bg_left_tc.gif); COLOR: white; FONT-FAMILY: system">
      	
      </td>
      <td width=10><img src="<%=request.getContextPath()%>/images/bg_left_tr.gif"></td>
    </tr>
    <tr>
      <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/bg_left_ls.gif)"></td>
      <td  id = menu style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; HEIGHT: 100%; BACKGROUND-COLOR: WHITE" vAlign=top>
       
          <script type="text/javascript">
            <!--   
            
            menuTree = new dTree('menuTree');
            menuTree.add(Number(1),-1,'企业差旅管理系统');
            <logic:iterate id="menuList" name="sysMenus">
            
            <c:if test="${menuList.islinked=='0'}">
		      menuTree.add(Number(${menuList.sysMenuNo}),Number(${menuList.parent}),"${menuList.sysMenuName}");       		
		    </c:if>
		    <c:if test="${menuList.islinked=='1'}">
              menuTree.add(Number(${menuList.sysMenuNo}),Number(${menuList.parent}),"${menuList.sysMenuName}",'<%=request.getContextPath()%>/${menuList.linkAddress}');                   
            </c:if>

		    </logic:iterate>
		    
            document.write(menuTree);
            //-->   
           
         </script>
       
      </td>
      <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/bg_left_rs.gif)"></td>
    </tr>
    <tr>
      <td width=10><img src="<%=request.getContextPath()%>/images/bg_left_bl.gif"></td>
      <td style="BACKGROUND-IMAGE: url(<%=request.getContextPath()%>/images/bg_left_bc.gif)"></td>
      <td width=10><img src="<%=request.getContextPath()%>/images/bg_left_br.gif"></td>
    </tr>
   
  </tbody>
</table>
</body>
</html>