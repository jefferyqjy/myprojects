<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<jsp:useBean id="array" scope="page" class="bean.AllBean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList adminlogin = (ArrayList)session.getAttribute("adminlogin");
%>
<title>��СҽԺסԺ����ϵͳ</title>
<style>
.table-padding td {padding:5px;}
</style>
<div class="north-left">��СҽԺסԺ����ϵͳ</div>
<div class="north-right">��ӭ��<%=adminlogin != null && adminlogin.size() != 0?adminlogin.get(1):"" %>&nbsp;--&gt;&nbsp;�û���ɫ��<%=adminlogin != null && adminlogin.size() != 0?adminlogin.get(3):"" %><%if(adminlogin != null && adminlogin.size() != 0 && !adminlogin.get(3).equals("����")){ %>&nbsp;--&gt;&nbsp;<a href="javascript:void(0)" id="editpass">�޸�����</a><%} %>&nbsp;--&gt;&nbsp;<a href="javascript:void(0)" id="click_logout">��ȫ�˳�</a></div>