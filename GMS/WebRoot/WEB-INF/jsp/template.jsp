<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html lang="true">   
  <head>   
    <html:base />   
 
    <meta http-equiv="pragma" content="no-cache">   
    <meta http-equiv="cache-control" content="no-cache">   
    <meta http-equiv="expires" content="0">       
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
  </head>   
  <div>
  		<tiles:insert attribute='header'/>
  		<div style="margin-left: 10px;margin-top: 15px;">
  			<tiles:insert attribute='menu'/>
  		</div>
  		<div style="position:absolute; left: 22%; top: 22%;background-color:;width: 75%;height: 75%">
  		    <tiles:insert attribute="body"/>
  		</div>
  </div>
</html:html>
