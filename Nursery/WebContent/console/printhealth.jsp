<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="java.util.List,java.util.Map" %>
    <%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>启蒙幼儿园管理系统</title>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
-->
</style>

</head>

<body>
<%
List list = (List)request.getAttribute("HEALTHLIST");

%>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">

    <%
  if (list != null && list.size() > 0) {

	  %>
   <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
      <tr>

        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">姓名</span></div></td>
        <td width="16%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">身份证</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">性别</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">年龄</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">身高</span></div></td>
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">体重</span></div></td>
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">左眼视力</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">右眼视力</span></div></td>
      </tr>
	<%
		for(int i = 0; i < list.size(); i++) {
			Map map = (Map)list.get(i);
			%>
			<tr>
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><%=map.get("name") %></span></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=map.get("bodycard") %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=map.get("sex") %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=map.get("age") %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=map.get("height") %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=map.get("weight") %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=map.get("left_sight") %></div></td>
        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21"> <%=map.get("right_sight") %></div></td>
      </tr>
			<%
		}
	%>

    </table></td>
  </tr>

	  <%
  }
  %>


  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong> ${control.totalnum}</strong> 条记录，当前第<strong> ${control.curpage}</strong> 页，共 <strong>${control.totalpage}</strong> 页</span></div></td>
        <td width="67%"><table width="312" border="0" align="right" cellpadding="0" cellspacing="0">
          <tr>
            <td width="49"><div align="center"><a href="/Nursery/printhealth?curpage=1"><img src="/Nursery/console/images/main_54.gif" width="40" height="15" /></a></div></td>
            <td width="49"><div align="center"><a href="/Nursery/printhealth?curpage=${control.prepage}"><img src="/Nursery/console/images/main_56.gif" width="45" height="15" /></a></div></td>
            <td width="49"><div align="center"><a href="/Nursery/printhealth?curpage=${control.nextpage}"><img src="/Nursery/console/images/main_58.gif" width="45" height="15" /></a></div></td>
            <td width="49"><div align="center"><a href="/Nursery/printhealth?curpage=${control.totalpage}"><img src="/Nursery/console/images/main_60.gif" width="40" height="15" /></a></div></td>
            <td width="37" class="STYLE22"><div align="center">转到</div></td>
            <td width="22"><div align="center">
              <input type="text" name="textfield" id="textfield"  style="width:20px; height:12px; font-size:12px; border:solid 1px #7aaebd;"/>
            </div></td>
            <td width="22" class="STYLE22"><div align="center">页</div></td>
            <td width="35"><img src="/Nursery/console/images/main_62.gif" width="26" height="15" /></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
