<%@ page language="java" import="java.util.*,util.*" pageEncoding="gb2312"%>
<jsp:useBean id="array" scope="page" class="bean.AllBean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList adminlogin = (ArrayList)session.getAttribute("adminlogin");
boolean closed = false;
ArrayList patient = null;
String type = "";
ArrayList users = null;
if(adminlogin != null && adminlogin.size() != 0){
	closed = true;
	users = array.getUsers(adminlogin.get(0).toString());
	for(int i = 0;i < users.size();i++){
		users.set(i,"'"+users.get(i)+"'");
	}
	patient = array.getPatient();
	if(adminlogin.get(3).equals("管理员")){
		type = "admin";
	}else{
		type = "users";
	}
}
String keywords = request.getParameter("keywords");
Validate v = new Validate();
if(keywords != null && !keywords.equals("")){
	patient = array.getPatientSearch(v.getUnicode(keywords));
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
	$("#patient_add").click(function(){
		patientadd('<%=path %>');
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
  <form id="form_goods" method="post" action="<%=path %>/admin/leavehospital.jsp" onSubmit="return $(this).form('validate')">
    <table align="center" cellpadding="5" border="1">
      <tr>
        <td>挂号单编号</td>
        <td><input class="easyui-validatebox" type="text" name="keywords" required="true" customize="Customize" value="<%=keywords != null && !keywords.equals("")?v.getUnicode(keywords):"" %>" /></td>
        <td><input type="submit" value="搜索" /></td>
      </tr>
    </table>
  </form><br />
  <table width="100%">
    <thead>
      <tr>
        <td colspan="8" align="center" style="padding:5px;">挂号管理</td>
      </tr>
      <tr class="thead">
        <td align="center">序号</td>
        <td align="center">挂号单编号</td>
        <td align="center">患者姓名</td>
        <td align="center">入院日期</td>
        <td align="center">床位号</td>
        <td align="center">是否出院</td>
        <td align="center">出院日期</td>
        <td align="center">操作</td>
      </tr>
    </thead>
    <tbody class="tbody">
      <%
    if(patient != null && patient.size() != 0){
	    for(int i = 0;i < patient.size();i++){
	    	ArrayList alRow = (ArrayList)patient.get(i);
	    	ArrayList sickbed = array.getSickbed(alRow.get(11).toString());
	    	ArrayList al = new ArrayList();
	    	for(int n = 0;n < alRow.size();n++){
	    		al.add("'"+alRow.get(n)+"'");
	    	}
    %>
      <tr>
        <td align="center"><%=i+1 %></td>
        <td align="center"><%=alRow.get(0) %></td>
        <td align="center"><%=alRow.get(2) %></td>
        <td align="center"><%=alRow.get(10).toString().substring(0,10) %></td>
        <td align="center"><%=sickbed.get(1) %></td>
        <td align="center"><%=alRow.get(12) == null || alRow.get(12).equals("")?"未出院":"已出院" %></td>
        <td align="center"><%=alRow.get(12) == null || alRow.get(12).equals("")?"未出院":alRow.get(12).toString().substring(0,10) %></td>
        <td align="center"><%=alRow.get(12) == null || alRow.get(12).equals("")?"<a href=\""+path+"/LeavehospitalServlet?id="+alRow.get(0)+"\">出院</a>&nbsp;&nbsp;":"" %><a href="<%=path %>/admin/leavehospital_feiyong.jsp?id=<%=alRow.get(0) %>">费用查询</a></td>
      </tr>
      <%}} %>
    </tbody>
  </table>
</div>
</body>
</html>
