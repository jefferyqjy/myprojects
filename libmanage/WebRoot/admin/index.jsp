<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.entity.Sreader"%>
<%@page import="com.cz.entity.Sysuser"%>
<%

 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/commfiles/css/common.css" type="text/css" />
<title></title>
</head>

<%
	String admin = "";
	String utype = "";
	if(session.getAttribute("admin")!=null) {
		Sysuser map = (Sysuser)request.getSession().getAttribute("admin"); 
 		admin = map.getUname();
 		utype="管理员";
	}
	if(session.getAttribute("reader")!=null) {
		Sreader map = (Sreader)request.getSession().getAttribute("reader"); 
 		admin = map.getUname();
 		utype="读者";
	}
%>

<frameset rows="87,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src="/libmanage/admin/topframe.jsp" name="topFrame" frameborder="no" scrolling="No" noresize="noresize" id="topFrame" title="" />
		<frameset name="myFrame" cols="200,7,*" frameborder="no" border="0" framespacing="0">
	<frame src="/libmanage/admin/leftframe.jsp" name="leftFrame" frameborder="no" scrolling="No" noresize="noresize" id="leftFrame" title="" />
	<frame src="/libmanage/admin/switchframe.jsp" name="midFrame" frameborder="no" scrolling="No" noresize="noresize" id="midFrame" title="" />
		<frameset rows="59,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src="/libmanage/admin/mainframe.jsp" name="mainFrame" frameborder="no" scrolling="No"  noresize="noresize" id="mainFrame" title="" />
    
    <%if(utype.equals("管理员")) {%>
		<frame src="/libmanage/admin/bookhj.jsp" name="manFrame" frameborder="no" id="manFrame" title="manFrame" />
    <%} else { %>
     	<frame src="/libmanage/admin/rbookhj.jsp" name="manFrame" frameborder="no" id="manFrame" title="manFrame" />
    <%}%>
    
     </frameset>
  </frameset>
</frameset>
<noframes>
<body >


</body>
</noframes>
</html>

