<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <script type="text/javascript">
         function userReg()
         {
                var url="<%=path %>/qiantai/userinfo/userReg.jsp";
                var ret = window.showModalDialog(url,"","dialogWidth:800px; dialogHeight:500px; dialogLeft: status:no; directories:yes;scrollbars:yes;Resizable=no;");
         }
      </script>
  </head>
  
  <body>
       <TABLE border=0 cellSpacing=0 cellPadding=0 width=772  height="120" bgColor=#ffffff align=center style="background-image: url('<%=path %>/img/logo.jpg'); background-repeat : repeat-xy">
		  <TR>
		    <TD height=80 width=772 align=center>
			     &nbsp;
		    </TD>
		  </TR>
		</TABLE>
       <TABLE  border=0 cellSpacing=0 cellPadding=0 width=772 align=center style="background-image: url('<%=path %>/img/banner.jpg'); background-repeat : repeat-xy">
		  <TR>
		    <TD style="height: 30px;font-weight: 900;">
		         &nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=path %>/cheAllQian.action">租房信息</A>
		         &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="userReg()">注册会员</A>
		         &nbsp;&nbsp;&nbsp;&nbsp;<A href="<%= path%>/liuyanAll.action" >系统留言板</A>
		    </TD>
		  </TR>
	   </TABLE>
  </body>
</html>
