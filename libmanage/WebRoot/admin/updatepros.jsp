<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.common.CommonDAO"%>
<%@page import="com.cz.entity.Syspros"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/common.css" /> 
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/style.css" />
</head>
  <%
  CommonDAO dao = Info.getDao(request);
  Syspros data = (Syspros)dao.load(request.getParameter("id"),"Syspros");
   %>
  <body>
  <form name="f1" method="post" action="control!updatepros?id=<%=request.getParameter("id")%>"  >
  	<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="mytab" id="table1">
        
         <tr align="center"  style="display:none">
        </tr>
        
         <tr align="center">
          <td height="25" align="center"><span class="">
          <strong>请输入名称</strong> : <br />
            <input name="proname" id="proname" value="<%=data.getProname() %>"  type="text" size="40" />
          </span> </td>
        </tr>
       
        <tr align="center">
          <td height="30" align="center">
            <label>
              <input type="submit" name="button" id="button" value="提交信息">
            </label> &nbsp;&nbsp;
            <input  type="reset" name="button2" id="button2" value="重新填写">                   </td>
        </tr>
      </table>
  </form> 
</body>
</html>
<script type="text/javascript" src="/libmanage/admin/commfiles/js/ajax.js"></script>

<%=Info.sucinfo(request,false)%>