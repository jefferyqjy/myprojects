<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.entity.Sysuser"%>
<%@page import="com.cz.entity.Sreader"%>
 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/common.css" /> 
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/style.css" /> 
	
  </head>
  
  <body>
  <%
    Sreader reader = (Sreader)request.getSession().getAttribute("reader"); 
	String pwd = reader.getUpass();
   %>
   <input type="hidden" id="hpwd" name="hpwd" value="<%=pwd %>"  >
  <form name="f1" method="post" action="${pageContext.request.contextPath }/sreader?operate=puppass&id=<%=reader.getId() %>" onSubmit="return check();">
  	<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="mytab" id="table1">
        <tr align="center" style="">
          <td colspan="2"  background="/libmanage/admin/commfiles/images/bg.gif"
				bgcolor="#FFFFFF" class="STYLE3">修改密码</td>
        </tr>
		<tr align="center">
          <td align="center"> 旧密码</td>
          <td align="left"><span class="style1">
          	<input type="password" id="oldpwd" name="oldpwd" size="20" maxlength="20">
          </span> </td>
        </tr>
        <tr align="center">
          <td align="center"> 新密码</td>
          <td align="left"><span class="style1">
            <input name="newpwd" id="newpwd"    type="password" size="20" maxlength="20"  />
          </span> </td>
        </tr>
        <tr align="center">
          <td align="center"> 确认新密码</td>
          <td align="left"><span class="style1">
            <input name="upassword" id="upassword"    type="password" size="20" maxlength="20"  />
          </span> </td>
        </tr>
       
        <tr align="center">
          <td colspan="2" align="center">
            <label>
              <input type="submit" name="button" id="button" value="提交信息">
            </label> &nbsp;&nbsp;
            <input  type="reset" name="button2" id="button2" value="重新填写">
                   </td>
        </tr>
      </table>
      </form> 
</body>
<script type="text/javascript">
function check()
{

if(document.getElementById("oldpwd").value=="")
{
alert("请输入旧密码");
return false;
}if(document.getElementById("newpwd").value=="")
{
alert("请输入新密码");
return false;
}if(document.getElementById("upassword").value=="")
{
alert("请确认新密码");
return false;
}if(document.getElementById("upassword").value!=document.getElementById("newpwd").value)
{
alert("新密码输入不一致");
return false;
}if(document.getElementById("oldpwd").value!=document.getElementById("hpwd").value){
alert("旧密码错误");
return false;
}

}
</script>
<%
if(request.getAttribute("suc")!=null) {
%>
	<script type="text/javascript">
		alert("修改成功");
	</script>
<%
}
%>
</html>