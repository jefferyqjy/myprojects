<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.CommonDAO"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.entity.Sysuser"%>
<%@page import="com.cz.common.PageManager"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/common.css" /> 
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/style.css" /> 
	<script type="text/javascript" src="/libmanage/js/popup.js"></script>
  </head>
  <%
  CommonDAO dao = Info.getDao(request);
   String username = request.getParameter("username")==null?"":request.getParameter("username");
  String truename = request.getParameter("truename")==null?"":request.getParameter("truename");
  String did = request.getParameter("did")==null?"":request.getParameter("did");
  
  if(!did.equals(""))
  {
  dao.delete(did,"Sysuser");
  }
  
  String url = "sysusers.jsp?1=1";
  String sql = "from Sysuser  where 1=1 ";
  
  if(!username.equals(""))
  {
  url+="&username="+username;
  sql+=" and uname like '%"+username+"%' ";
  }
  if(!truename.equals("")){
  url+="&truename="+truename;
  sql+=" and tname like '%"+truename+"%' ";
  }
  sql+=" order by id desc";
  System.out.println(sql);
  PageManager pageManager = PageManager.getPage(url, 10, request);
  pageManager.doList(sql);
  PageManager bean = (PageManager) request.getAttribute("page");
  ArrayList<Sysuser> nlist = (ArrayList) bean.getCollection();
   %>
  <body>
  	<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
  	<form name="f1" method="post" action="/libmanage/admin/sysusers.jsp" >
  	<table id="mainbody" border="0" width="100%" cellspacing="1"
					class="tableform">
			<tr>
     				 <td width=10% height="31" align="center" style="font-size: 12px">用户名&nbsp;:</td>
	    <td width=14% style="font-size: 12px" align="left"><input name="username" value="<%=username %>" type="text"></td>
					<td width=10% align="center" style="font-size: 12px">姓名&nbsp;:</td>
					<td width=46% style="font-size: 12px" align="left"><input value="<%=truename %>"  name="truename" type="text"></td>
					<td width=20% style="font-size: 12px" align="right"><input type="submit" class="btn3_mouseup" value="查询">&nbsp;&nbsp;</td>
			</tr>
			</table>
  	
  	
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="mytab" id="table1">
<tr align="center">
          <td colspan="7"  background="/libmanage/admin/commfiles/images/bg.gif"
				bgcolor="#FFFFFF" class="STYLE3">管理员列表</td>
        </tr>
        <tr align="center">
          <td  class="itemtitle" > 用户名</td>
          <td class="itemtitle"> 密码</td>
          <td class="itemtitle"> 姓名</td>
          <td class="itemtitle"> 电话 </td>
          <td class="itemtitle">email</td>
          <td class="itemtitle">操作</td>
  </tr>
        
         <%
 
  for(Sysuser map:nlist)
  {
    %>
        <tr align="center">
          <td align="center"> <%=map.getUname().equals("")?"&nbsp;":map.getUname() %> </td>
          <td align="center"> <%=map.getUpass().equals("")?"&nbsp;":map.getUpass() %>  </td>
          <td align="center">  <%=map.getTname().equals("")?"&nbsp;":map.getTname() %> </td>
          <td align="center">  <%=map.getTel().equals("")?"&nbsp;":map.getTel() %> </td>
          <td align="center"> <%=map.getEmail().equals("")?"&nbsp;":map.getEmail() %> </td>
          <td align="center">
          
          <a href="javascript:update('<%=map.getId()%>')">修改</a>
          <script type="text/javascript">
          function update(no)
          {
          pop('/libmanage/admin/updatesysusers.jsp?id='+no,'修改管理员信息',500,164);
          }
          </script>
          &nbsp;|&nbsp;
          <a href="/libmanage/admin/sysusers.jsp?did=<%=map.getId()%>">删除</a>
          
          
          
          </td>
  </tr>
        <%} %>
        
        <tr align="center">
          <td align="right" colspan="13"><%=bean.getInfo() %></td>
        </tr>
      </table>
      </form>
</body>
</html>
<%
if(request.getAttribute("suc")!=null)
{
 %>
 <script type="text/javascript">
<!--
alert("添加成功")
//-->
</script>
<%}%>