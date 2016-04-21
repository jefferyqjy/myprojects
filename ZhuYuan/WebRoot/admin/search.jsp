<%@ page language="java" import="java.util.*,util.*" pageEncoding="gb2312"%>
<jsp:useBean id="array" scope="page" class="bean.AllBean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList adminlogin = (ArrayList)session.getAttribute("adminlogin");
boolean closed = false;
String type = "";
ArrayList users = null;
String keywords = "";
if(adminlogin != null && adminlogin.size() != 0){
	closed = true;
	users = array.getUsers(adminlogin.get(0).toString());
	for(int i = 0;i < users.size();i++){
		users.set(i,"'"+users.get(i)+"'");
	}
	if(adminlogin.get(3).equals("管理员")){
		type = "admin";
	}else{
		type = "users";
	}
	keywords = adminlogin.get(0).toString();
}
Validate v = new Validate();
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
  <%
  if(keywords != null && !keywords.equals("")){ 
		ArrayList patient = array.getPatient(keywords);
		if(patient != null && patient.size() != 0){
			String dates1 = "'"+patient.get(10).toString()+"'";
			String dates2 = "";
			if(patient.get(12) == null || patient.get(12).equals("")){
				dates2 = "getdate()";
			}else{
				dates2 = "'"+patient.get(12).toString()+"'";
			}
			ArrayList days = array.getDay(dates1,dates2);
			ArrayList ward = array.getWard(array.getSickbed(patient.get(11).toString()).get(2).toString());
			ArrayList prescribe = array.getPrescribeFeiyong(keywords);
			ArrayList inspect = array.getInspectFeiyong(keywords);
  %>
  <table width="80%" border="1" align="center">
    <tr>
      <td><table border="1" cellpadding="5" width="100%">
          <tr>
            <td>挂号编号：<%=patient.get(0) %>&nbsp;&nbsp;&nbsp;&nbsp;患者姓名：<%=patient.get(2) %></td>
            <td></td>
          </tr>
        </table>
        <table border="1" cellpadding="5" width="100%">
          <tr>
            <td colspan="6" align="center">床位费用</td>
          </tr>
          <tr>
            <td align="center">病房档次</td>
            <td align="center">价格</td>
            <td align="center">入院日期</td>
            <td align="center"><%=patient.get(12) == null || patient.get(12).equals("")?"当前日期":"出院日期" %></td>
            <td align="center">天数</td>
            <td align="center">费用合计</td>
          </tr>
          <tr>
            <td align="center"><%=ward.get(3) %></td>
            <td align="center"><%=ward.get(4) %>元</td>
            <td align="center"><%=patient.get(10).toString().substring(0,10) %></td>
            <td align="center"><%=patient.get(12) == null || patient.get(12).equals("")?v.getSystemDate().substring(0,10):patient.get(12).toString().substring(0,10) %></td>
            <td align="center"><%=days.get(0) %></td>
            <td align="center"><%=Integer.parseInt(ward.get(4).toString())*Integer.parseInt(days.get(0).toString()) %>元</td>
          </tr>
        </table>
        <table border="1" cellpadding="5" width="100%">
          <tr>
            <td colspan="5" align="center">药费</td>
          </tr>
          <tr>
            <td align="center">药品名称</td>
            <td align="center">价格</td>
            <td align="center">开药数量</td>
            <td align="center">开药日期</td>
            <td align="center">费用</td>
          </tr>
          <%
          int m = 0;
          for(int i = 0;i < prescribe.size();i++){
        	  ArrayList alRow = (ArrayList)prescribe.get(i);
        	  m += Integer.parseInt(alRow.get(6).toString());
          %>
          <tr>
            <td align="center"><%=alRow.get(3) %></td>
            <td align="center"><%=alRow.get(5) %>元</td>
            <td align="center"><%=alRow.get(4) %></td>
            <td align="center"><%=alRow.get(7).toString().substring(0,10) %></td>
            <td align="center"><%=alRow.get(6) %>元</td>
          </tr>
          <%} %>
          <tr>
            <td colspan="5" align="center">药费合计：<%=m %>元</td>
          </tr>
        </table>
        <table border="1" cellpadding="5" width="100%">
          <tr>
            <td colspan="4" align="center">检查费</td>
          </tr>
          <tr>
            <td align="center">检查项目</td>
            <td align="center">价格</td>
            <td align="center">日期</td>
            <td align="center">费用</td>
          </tr>
          <%
          int n = 0;
          for(int i = 0;i < inspect.size();i++){
        	  ArrayList alRow = (ArrayList)inspect.get(i);
        	  n += Integer.parseInt(alRow.get(3).toString());
          %>
          <tr>
            <td align="center"><%=alRow.get(2) %></td>
            <td align="center"><%=alRow.get(3) %>元</td>
            <td align="center"><%=alRow.get(4).toString().substring(0,10) %></td>
            <td align="center"><%=alRow.get(3) %>元</td>
          </tr>
          <%} %>
          <tr>
            <td colspan="4" align="center">检查费合计：<%=n %>元</td>
          </tr>
        </table>
        <table border="1" cellpadding="5" width="100%">
          <tr>
            <td>所有费用总计：<%=Integer.parseInt(ward.get(4).toString())*Integer.parseInt(days.get(0).toString())+m+n %>元</td>
          </tr>
        </table></td>
    </tr>
  </table>
  <%}} %>
</div>
</body>
</html>
