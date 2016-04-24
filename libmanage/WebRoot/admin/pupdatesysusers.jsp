<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.dao.SysuserDAO"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.entity.Sysuser"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/common.css" /> 
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/style.css" /> 
	
	<script type="text/javascript" src="/libmanage/admin/commfiles/js/ajax.js"></script>
	<%
	boolean success = (Boolean)request.getAttribute("suc");
	if(success) {
	%>
		<script>type="text/javascript">
		alert("修改成功");
		</script>
	<%} else {%>
		<script>type="text/javascript">
		alert("修改失败");
		</script>
	<%}%>
  </head>
  
<body>
	<%
		String id = request.getParameter("id");
  		Sysuser adminmap = (Sysuser) session.getAttribute("admin");
  		if(StringUtils.isEmpty(id)) {
  			id = adminmap.getId() + "";
  		}
  		SysuserDAO dao = new SysuserDAO();
  		Sysuser map = dao.findById(Integer.valueOf(id.trim()));
	%>
  <form name="f1" method="post" action="${pageContext.request.contextPath }/sysuser?id=<%=id %>&operate=updatesysuser"  >
  	<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="mytab" id="table1">
        <tr align="center" >
          <td colspan="2"  background="/libmanage/admin/commfiles/images/bg.gif" bgcolor="#FFFFFF" class="STYLE3">修改个人信息</td>
        </tr>
      <tr align="center">
          <td width="20%" align="center"> 用户名</td>
        <td width="80%" align="left"><span class="style1">
      <input name="uname" id="uname" disabled="disabled" value="<%=map.getUname()%>" type="text" size="20"  maxlength="20" />
      </span></td>
      </tr>
        
       
        <tr align="center">
          <td align="center"> 登录密码</td>
          <td align="left"><span class="style1">
            <input name="upass" id="upass" value="<%=map.getUpass() %>"  type="text" size="20" maxlength="20"  />
          </span> </td>
        </tr>
        <tr align="center">
          <td align="center"> 姓名</td>
          <td align="left"><span class="style1">
            <input name="tname" id="tname"  type="text"  value="<%=map.getTname() %>" size="20" maxlength="20"  />
          </span> </td>
        </tr>
         <tr align="center">
          <td align="center"> 联系电话</td>
          <td align="left"><span class="style1">
            <input name="tel" id="tel"   value="<%=map.getTel() %>" type="text" size="40" />
          </span> </td>
        </tr>
         <tr align="center">
          <td align="center"> email</td>
          <td align="left"><span class="style1">
            <input name="email" id="email" value="<%=map.getEmail() %>"   type="text" size="40" />
          </span> </td>
        </tr>
        <tr align="center">
          <td colspan="2" align="center">
            <label>
              <input type="submit" name="button" id="button" value="提交信息">
            </label> &nbsp;&nbsp;
            <input type="reset" name="button2" id="button2" value="重新填写">
           </td>
        </tr>
      </table>
      </form> 
</body>
</html>