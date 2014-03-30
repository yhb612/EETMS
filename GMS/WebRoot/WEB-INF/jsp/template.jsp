<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %> 


<html>
<HEAD>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</HEAD>
<frameset rows="22%,78%" border="0" framespacing="0" frameBorder=NO>
   <frame src="login.do?method=returnHeader">
   <frameset cols="15%,85%">
    <frame src="login.do?method=returnMenu" name="left">
    <frame src="login.do?method=returnBody" name="right">
   </frameset>
</frameset>


</HTML>

