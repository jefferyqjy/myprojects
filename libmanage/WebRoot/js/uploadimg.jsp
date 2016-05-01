<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0040)http://2school.wygk.cn/admin/syscome.asp -->
<html xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>欢迎进入系统后台</TITLE>
<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/common.css" /> 
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/style.css" /> 
<script type="text/javascript" src="/libmanage/js/popup.js"></script>
<meta content="MSHTML 6.00.2900.5726" name=GENERATOR></HEAD>
<body>
<%
String filename = (String)request.getAttribute("fujianFileName");
if(filename!=null)
{
 %>
 <script type="text/javascript">
 var txt = parent.document.getElementById("txt");
 var filename = parent.document.getElementById("filename");
 filename.value="<%=filename%>";
 txt.src="../upfile/<%=filename%>";
 popclose();
 </script>
 <%} %>
<form action="${pageContext.request.contextPath }/common?operate=upload" enctype="multipart/form-data" name="f1" method="post">
<table cellSpacing=1 cellPadding=3 width="100%" align=center  
border=0>
   <tr>
    <td align="center" class=forumrow> 
    <div style="height:27px;line-height: 27px"><strong>请选择文件</strong>  </div>
     <div style="height:27px;line-height: 27px"><input name="fujian" type="file"  size="30"></div>
       <div style="height:27px;line-height: 27x"> 
      <input type="submit" name="Submit"  value="提交信息">
      &nbsp;&nbsp;&nbsp;
      <input type="reset" name="Submit" value="重新填写">
      </div>
     </td>
    </tr>
  </table>
</form>
</body>
<script type="text/javascript">
function check()
{
if(f1.olduserpass.value=="")
{
alert("请输入原密码");
return;
}
if(f1.userpass.value=="")
{
alert("请输入新密码");
return;
}
if(f1.copyuserpass.value!=f1.userpass.value)
{
alert("两次密码输入不一致");
return;
}
}
</script>
<script type="text/javascript">
<%
String error = (String)request.getAttribute("error");
if(error!=null)
{
 %>
 alert("原密码不对");
 <%}%>
 <%
String suc = (String)request.getAttribute("suc");
if(suc!=null)
{
 %>
 alert("操作成功");
  parent.location.replace("/libmanage/admin/kcfiles.jsp");
 <%}%>
 </script>
</html>

