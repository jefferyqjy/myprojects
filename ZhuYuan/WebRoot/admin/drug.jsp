<%@ page language="java" import="java.util.*,util.*" pageEncoding="gb2312"%>
<jsp:useBean id="array" scope="page" class="bean.AllBean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList adminlogin = (ArrayList)session.getAttribute("adminlogin");
boolean closed = false;
ArrayList drug = null;
String type = "";
ArrayList users = null;
if(adminlogin != null && adminlogin.size() != 0){
	closed = true;
	users = array.getUsers(adminlogin.get(0).toString());
	for(int i = 0;i < users.size();i++){
		users.set(i,"'"+users.get(i)+"'");
	}
	drug = array.getDrug();
	if(adminlogin.get(3).equals("管理员")){
		type = "admin";
	}else{
		type = "users";
	}
}
String keywords = request.getParameter("keywords");
Validate v = new Validate();
if(keywords != null && !keywords.equals("")){
	drug = array.getDrugSearch(v.getUnicode(keywords));
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
	$("#editinfor").click(function(){
		fodify_information('<%=path %>','<%=adminlogin != null && adminlogin.size() != 0?adminlogin.get(0):""  %>',<%=users %>,'book.jsp');
	});
	$("#drug_add").click(function(){
		drugadd('<%=path %>');
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
  <form id="form_goods" method="post" action="<%=path %>/admin/drug.jsp" onSubmit="return $(this).form('validate')">
    <table align="center" cellpadding="5" border="1">
      <tr>
        <td>药品名称</td>
        <td><input class="easyui-validatebox" type="text" name="keywords" required="true" customize="Customize" value="<%=keywords != null && !keywords.equals("")?v.getUnicode(keywords):"" %>" /></td>
        <td><input type="submit" value="搜索" /></td>
      </tr>
    </table>
  </form><br />
  <table width="100%">
    <thead>
      <tr>
        <td colspan="8" align="center" style="padding:5px;">药品管理</td>
      </tr>
      <tr>
        <td colspan="8" style="padding:5px;"><a href="javascript:void(0)" id="drug_add">添加新药品</a></td>
      </tr>
      <tr class="thead">
        <td align="center">序号</td>
        <td align="center">药品名称</td>
        <td align="center">规格</td>
        <td align="center">批准文号</td>
        <td align="center">生产厂家</td>
        <td align="center">价格</td>
        <td align="center">库存量</td>
        <td align="center">操作</td>
      </tr>
    </thead>
    <tbody class="tbody">
      <%
    if(drug != null && drug.size() != 0){
	    for(int i = 0;i < drug.size();i++){
	    	ArrayList alRow = (ArrayList)drug.get(i);
	    	ArrayList al = new ArrayList();
	    	for(int n = 0;n < alRow.size();n++){
	    		al.add("'"+alRow.get(n)+"'");
	    	}
    %>
      <tr>
        <td align="center"><%=i+1 %></td>
        <td align="center"><a href="javascript:void(0)" onClick="show_drug('<%=path %>','<%=alRow.get(0) %>',<%=al %>)"><%=alRow.get(1) %></a></td>
        <td align="center"><%=alRow.get(2) %></td>
        <td align="center"><%=alRow.get(3) %></td>
        <td align="center"><%=alRow.get(4) %></td>
        <td align="center"><%=alRow.get(8) %></td>
        <td align="center"><%=alRow.get(9) %></td>
        <td align="center"><a href="javascript:void(0)" onClick="fodify_drug('<%=path %>','<%=alRow.get(0) %>',<%=al %>)">修改</a>&nbsp;&nbsp;<a href="<%=path %>/DelServlet?drug=<%=alRow.get(0) %>">删除</a></td>
      </tr>
      <%}} %>
    </tbody>
  </table>
</div>
</body>
</html>
