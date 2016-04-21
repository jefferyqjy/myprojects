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
	if(adminlogin.get(3).equals("����Ա")){
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
<div region="west" split="false" border="false" title="�����˵�" style="width:180px;" id="west">
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
            <td>�Һű�ţ�<%=patient.get(0) %>&nbsp;&nbsp;&nbsp;&nbsp;����������<%=patient.get(2) %></td>
            <td></td>
          </tr>
        </table>
        <table border="1" cellpadding="5" width="100%">
          <tr>
            <td colspan="6" align="center">��λ����</td>
          </tr>
          <tr>
            <td align="center">��������</td>
            <td align="center">�۸�</td>
            <td align="center">��Ժ����</td>
            <td align="center"><%=patient.get(12) == null || patient.get(12).equals("")?"��ǰ����":"��Ժ����" %></td>
            <td align="center">����</td>
            <td align="center">���úϼ�</td>
          </tr>
          <tr>
            <td align="center"><%=ward.get(3) %></td>
            <td align="center"><%=ward.get(4) %>Ԫ</td>
            <td align="center"><%=patient.get(10).toString().substring(0,10) %></td>
            <td align="center"><%=patient.get(12) == null || patient.get(12).equals("")?v.getSystemDate().substring(0,10):patient.get(12).toString().substring(0,10) %></td>
            <td align="center"><%=days.get(0) %></td>
            <td align="center"><%=Integer.parseInt(ward.get(4).toString())*Integer.parseInt(days.get(0).toString()) %>Ԫ</td>
          </tr>
        </table>
        <table border="1" cellpadding="5" width="100%">
          <tr>
            <td colspan="5" align="center">ҩ��</td>
          </tr>
          <tr>
            <td align="center">ҩƷ����</td>
            <td align="center">�۸�</td>
            <td align="center">��ҩ����</td>
            <td align="center">��ҩ����</td>
            <td align="center">����</td>
          </tr>
          <%
          int m = 0;
          for(int i = 0;i < prescribe.size();i++){
        	  ArrayList alRow = (ArrayList)prescribe.get(i);
        	  m += Integer.parseInt(alRow.get(6).toString());
          %>
          <tr>
            <td align="center"><%=alRow.get(3) %></td>
            <td align="center"><%=alRow.get(5) %>Ԫ</td>
            <td align="center"><%=alRow.get(4) %></td>
            <td align="center"><%=alRow.get(7).toString().substring(0,10) %></td>
            <td align="center"><%=alRow.get(6) %>Ԫ</td>
          </tr>
          <%} %>
          <tr>
            <td colspan="5" align="center">ҩ�Ѻϼƣ�<%=m %>Ԫ</td>
          </tr>
        </table>
        <table border="1" cellpadding="5" width="100%">
          <tr>
            <td colspan="4" align="center">����</td>
          </tr>
          <tr>
            <td align="center">�����Ŀ</td>
            <td align="center">�۸�</td>
            <td align="center">����</td>
            <td align="center">����</td>
          </tr>
          <%
          int n = 0;
          for(int i = 0;i < inspect.size();i++){
        	  ArrayList alRow = (ArrayList)inspect.get(i);
        	  n += Integer.parseInt(alRow.get(3).toString());
          %>
          <tr>
            <td align="center"><%=alRow.get(2) %></td>
            <td align="center"><%=alRow.get(3) %>Ԫ</td>
            <td align="center"><%=alRow.get(4).toString().substring(0,10) %></td>
            <td align="center"><%=alRow.get(3) %>Ԫ</td>
          </tr>
          <%} %>
          <tr>
            <td colspan="4" align="center">���Ѻϼƣ�<%=n %>Ԫ</td>
          </tr>
        </table>
        <table border="1" cellpadding="5" width="100%">
          <tr>
            <td>���з����ܼƣ�<%=Integer.parseInt(ward.get(4).toString())*Integer.parseInt(days.get(0).toString())+m+n %>Ԫ</td>
          </tr>
        </table></td>
    </tr>
  </table>
  <%}} %>
</div>
</body>
</html>
