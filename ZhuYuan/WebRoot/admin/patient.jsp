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
	if(adminlogin.get(3).equals("����Ա")){
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
<div region="west" split="false" border="false" title="�����˵�" style="width:180px;" id="west">
  <div id="left-menus" border="false" fit="true">
    <jsp:include page="/admin/left.jsp"></jsp:include>
  </div>
</div>
<div id="mainPanle" region="center" border="true" style="background:#f7f7f7; padding:5px;">
  <form id="form_goods" method="post" action="<%=path %>/admin/patient.jsp" onSubmit="return $(this).form('validate')">
    <table align="center" cellpadding="5" border="1">
      <tr>
        <td>�Һŵ����</td>
        <td><input class="easyui-validatebox" type="text" name="keywords" required="true" customize="Customize" value="<%=keywords != null && !keywords.equals("")?v.getUnicode(keywords):"" %>" /></td>
        <td><input type="submit" value="����" /></td>
      </tr>
    </table>
  </form><br />
  <table width="100%">
    <thead>
      <tr>
        <td colspan="11" align="center" style="padding:5px;">�ҺŹ���</td>
      </tr>
      <tr>
        <td colspan="11" style="padding:5px;"><a href="javascript:void(0)" id="patient_add">�Һ�</a></td>
      </tr>
      <tr class="thead">
        <td align="center">���</td>
        <td align="center">�Һŵ����</td>
        <td align="center">��������</td>
        <td align="center">�Ա�</td>
        <td align="center">����</td>
        <td align="center">��������</td>
        <td align="center">����</td>
        <td align="center">���֤����</td>
        <td align="center">��ϵ�绰</td>
        <td align="center">��ͥסַ</td>
        <td align="center">����</td>
      </tr>
    </thead>
    <tbody class="tbody">
      <%
    if(patient != null && patient.size() != 0){
	    for(int i = 0;i < patient.size();i++){
	    	ArrayList alRow = (ArrayList)patient.get(i);
	    	ArrayList al = new ArrayList();
	    	for(int n = 0;n < alRow.size();n++){
	    		al.add("'"+alRow.get(n)+"'");
	    	}
    %>
      <tr>
        <td align="center"><%=i+1 %></td>
        <td align="center"><%=alRow.get(0) %></td>
        <td align="center"><%=alRow.get(2) %></td>
        <td align="center"><%=alRow.get(3) %></td>
        <td align="center"><%=alRow.get(4) %></td>
        <td align="center"><%=alRow.get(5) %></td>
        <td align="center"><%=alRow.get(6) %></td>
        <td align="center"><%=alRow.get(7) %></td>
        <td align="center"><%=alRow.get(8) %></td>
        <td align="center"><%=alRow.get(9) %></td>
        <td align="center"><a href="<%=path %>/DelServlet?patient=<%=alRow.get(0) %>">ɾ��</a></td>
      </tr>
      <%}} %>
    </tbody>
  </table>
</div>
</body>
</html>
