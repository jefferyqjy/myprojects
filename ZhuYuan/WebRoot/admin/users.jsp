<%@ page language="java" import="java.util.*,util.*" pageEncoding="gb2312"%>
<jsp:useBean id="array" scope="page" class="bean.AllBean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList adminlogin = (ArrayList)session.getAttribute("adminlogin");
boolean closed = false;
ArrayList users = null;
String type = "";
if(adminlogin != null && adminlogin.size() != 0){
	closed = true;
	users = array.getUsers();
	if(adminlogin.get(3).equals("管理员")){
		type = "admin";
	}else{
		type = "users";
	}
}
String keywords = request.getParameter("keywords");
Validate v = new Validate();
if(keywords != null && !keywords.equals("")){
	users = array.getUsersSearch(v.getUnicode(keywords));
}
String message = (String)request.getAttribute("message");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/css/admin-css.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/themes/icon.css">
<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/admin.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('tbody tr:even').css({'background':'#ffffff'});
	$('tbody tr:odd').css({'background':'#eeeeff'});
	var message = <%=message %>;
	if(message != null && message != ""){
		alert(message);
	}
	login('<%=path %>',<%=closed %>);
	$("#click_logout").click(function(){
		logout('<%=path %>');
	});
	$("#editpass").click(function(){
		editpass('<%=path %>','<%=type %>');
	});
	$("#users_add").click(function(){
		usersadd('<%=path %>');
	});
})
</script>
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no" resizable="false">
<div id="north" region="north" split="false" border="false">
  <jsp:include page="/admin/head.jsp"></jsp:include>
</div>
<div region="west" split="false" border="false" title="导航菜单" style="width:180px;" id="west">
  <div id="left-menus" border="false" fit="true">
    <jsp:include page="/admin/left.jsp"></jsp:include>
  </div>
</div>
<div id="mainPanle" region="center" border="true" style="background:#f7f7f7; padding:5px;">
  <form id="form_goods" method="post" action="<%=path %>/admin/users.jsp" onSubmit="return $(this).form('validate')">
    <table align="center" cellpadding="5" border="1">
      <tr>
        <td>用户姓名</td>
        <td><input class="easyui-validatebox" type="text" name="keywords" required="true" customize="Customize" value="<%=keywords != null && !keywords.equals("")?v.getUnicode(keywords):"" %>" /></td>
        <td><input type="submit" value="搜索" /></td>
      </tr>
    </table>
  </form><br />
  <table width="100%">
    <thead>
      <tr>
        <td colspan="12" align="center" style="padding:5px;">普通用户管理</td>
      </tr>
      <tr>
        <td colspan="12" style="padding:5px;"><a href="javascript:void(0)" id="users_add">添加普通用户</a></td>
      </tr>
      <tr class="thead">
        <td align="center">序号</td>
        <td align="center">用户名</td>
        <td align="center">姓名</td>
        <td align="center">性别</td>
        <td align="center">出生日期</td>
        <td align="center">身份证号码</td>
        <td align="center">联系电话</td>
        <td align="center">家庭地址</td>
        <td align="center">邮编</td>
        <td align="center">电子邮箱</td>
        <td align="center">身份</td>
        <td align="center">操作</td>
      </tr>
    </thead>
    <tbody class="tbody">
      <%
    if(users != null && users.size() != 0){
	    for(int i = 0;i < users.size();i++){
	    	ArrayList alRow = (ArrayList)users.get(i);
	    	ArrayList al = new ArrayList();
	    	for(int n = 0;n < alRow.size();n++){
	    		al.add("'"+alRow.get(n)+"'");
	    	}
    %>
      <tr>
        <td align="center"><%=i+1 %></td>
        <td align="center"><%=alRow.get(1) %></td>
        <td align="center"><%=alRow.get(3) %></td>
        <td align="center"><%=alRow.get(4) %></td>
        <td align="center"><%=alRow.get(5) %></td>
        <td align="center"><%=alRow.get(6) %></td>
        <td align="center"><%=alRow.get(7) %></td>
        <td align="center"><%=alRow.get(8) %></td>
        <td align="center"><%=alRow.get(9) %></td>
        <td align="center"><%=alRow.get(10) %></td>
        <td align="center"><%=alRow.get(11) %></td>
        <td align="center"><a href="javascript:void(0)" onClick="fodify_information('<%=path %>','<%=alRow.get(0) %>',<%=al %>,'users.jsp')">修改</a>&nbsp;&nbsp;<a href="<%=path %>/DelServlet?users=<%=alRow.get(0) %>">删除</a></td>
      </tr>
      <%}} %>
    </tbody>
  </table>
</div>
</body>
</html>
