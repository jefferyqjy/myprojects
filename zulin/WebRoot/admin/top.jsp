<%@page import="com.model.TAdmin"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<STYLE type=text/css>
		BODY {PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-TOP: 0px; BACKGROUND-COLOR: #2a8dc8}
		BODY {FONT-SIZE: 12px; COLOR: #003366; FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif}
		TD {FONT-SIZE: 12px; COLOR: #003366; FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif}
		DIV {FONT-SIZE: 12px; COLOR: #003366; FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif}
		P {FONT-SIZE: 12px; COLOR: #003366; FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif}
	</STYLE>
	
  </head>
  
<body>
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
<% 
TAdmin admin=(TAdmin)request.getSession().getAttribute("admin");
String name=admin.getUserName();
String userType=admin.getUserType();
%>
  <TR>
    <TD width=10></TD>
    <TD background=><FONT style="font-size: 30px;"><B>房屋租赁管理系统</B></FONT> </TD>
    <TD>
	      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
	        <TR><TD align=right height=35></TD></TR>
	        <TR><TD height=35>&nbsp;你好，<%="1".equals(userType)?"房东":"管理员" %>，<%=name %> </TD></TR>
	      </TABLE>
    </TD>
    <TD width=10></TD>
  </TR>
</TABLE>
</body>
</html>
