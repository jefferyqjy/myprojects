<%@ page language="java" import="java.util.*,util.*" pageEncoding="gb2312"%>
<jsp:useBean id="array" scope="page" class="bean.AllBean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList adminlogin = (ArrayList)session.getAttribute("adminlogin");
boolean closed = false;
ArrayList stock = null;
String type = "";
ArrayList users = null;
if(adminlogin != null && adminlogin.size() != 0){
	closed = true;
	users = array.getUsers(adminlogin.get(0).toString());
	for(int i = 0;i < users.size();i++){
		users.set(i,"'"+users.get(i)+"'");
	}
	stock = array.getStock();
	if(adminlogin.get(3).equals("����Ա")){
		type = "admin";
	}else{
		type = "users";
	}
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
	$("#stock_add").click(function(){
		stockadd('<%=path %>');
	});
})
</script>
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no" resizable="false">
<div id="north" region="north" split="false" border="false">
  <jsp:include page="/admin/head.jsp"></jsp:include>
</div>
<div region="west" split="false" border="false" title="�����˵�" style="width:180px;" id="west">
  <div id="left-menus" border="false" fit="true">
    <jsp:include page="/admin/left.jsp"></jsp:include>
  </div>
</div>
<div id="mainPanle" region="center" border="true" style="background:#f7f7f7; padding:5px;">
  <table width="100%">
    <thead>
      <tr>
        <td colspan="8" align="center" style="padding:5px;">ҩƷ������</td>
      </tr>
      <tr>
        <td colspan="8" style="padding:5px;"><a href="javascript:void(0)" id="stock_add">ҩƷ���</a></td>
      </tr>
      <tr class="thead">
        <td align="center">���</td>
        <td align="center">ҩƷ����</td>
        <td align="center">���κ�</td>
        <td align="center">�������</td>
        <td align="center">�������</td>
        <td align="center">��Ч����</td>
        <td align="center">�Ƿ����</td>
        <td align="center">����</td>
      </tr>
    </thead>
    <tbody class="tbody">
      <%
    if(stock != null && stock.size() != 0){
	    for(int i = 0;i < stock.size();i++){
	    	ArrayList alRow = (ArrayList)stock.get(i);
	    	ArrayList drug = array.getDrug(alRow.get(0).toString());
	    	ArrayList al = new ArrayList();
	    	for(int n = 0;n < drug.size();n++){
	    		al.add("'"+drug.get(n)+"'");
	    	}
    %>
      <tr>
        <td align="center"><%=i+1 %></td>
        <td align="center"><a href="javascript:void(0)" onClick="show_drug('<%=path %>','<%=alRow.get(1) %>',<%=al %>)"><%=drug.get(1) %></a></td>
        <td align="center"><%=alRow.get(2) %></td>
        <td align="center"><%=alRow.get(3) %></td>
        <td align="center"><%=alRow.get(4).toString().substring(0,10) %></td>
        <td align="center"><%=alRow.get(5).toString().substring(0,10) %></td>
        <td align="center"><%=Integer.parseInt(alRow.get(6).toString()) > 0?"�ѹ���":"δ����" %></td>
        <td align="center"><a href="<%=path %>/DelServlet?stock=<%=alRow.get(0) %>">ɾ��</a></td>
      </tr>
      <%}} %>
    </tbody>
  </table>
</div>
</body>
</html>
