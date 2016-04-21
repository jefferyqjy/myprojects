<%@ page language="java" import="java.util.*,util.*" pageEncoding="gb2312"%>
<jsp:useBean id="array" scope="page" class="bean.AllBean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList adminlogin = (ArrayList)session.getAttribute("adminlogin");
boolean closed = false;
ArrayList ward = null;
String type = "";
if(adminlogin != null && adminlogin.size() != 0){
	closed = true;
	ward = array.getWard();
	if(adminlogin.get(3).equals("管理员")){
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
	$("#ward_add").click(function(){
		wardadd('<%=path %>');
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
  <table width="100%">
    <thead>
      <tr>
        <td colspan="9" align="center" style="padding:5px;">病房病床管理</td>
      </tr>
      <tr>
        <td colspan="9" style="padding:5px;"><a href="javascript:void(0)" id="ward_add">添加病房</a></td>
      </tr>
      <tr class="thead">
        <td align="center">序号</td>
        <td align="center">病房编号</td>
        <td align="center">所属科室</td>
        <td align="center">病房档次</td>
        <td align="center">病房价格</td>
        <td align="center">床位数量</td>
        <td align="center">床位情况</td>
        <td align="center">添加床位</td>
        <td align="center">操作</td>
      </tr>
    </thead>
    <tbody class="tbody">
      <%
    if(ward != null && ward.size() != 0){
	    for(int i = 0;i < ward.size();i++){
	    	ArrayList alRow = (ArrayList)ward.get(i);
	    	ArrayList sickbed = array.getSickbedWard(alRow.get(0).toString());
	    	int flag = 0;
    %>
      <tr>
        <td align="center"><%=i+1 %></td>
        <td align="center"><%=alRow.get(1) %></td>
        <td align="center"><%=alRow.get(2) %></td>
        <td align="center"><%=alRow.get(3) %></td>
        <td align="center"><%=alRow.get(4) %>元</td>
        <td align="center"><%=sickbed.size() %>张</td>
        <td align="center"><table width="100%" cellpadding="5">
            <tr>
              <td align="center">床位编号</td>
              <td align="center">使用情况</td>
              <td align="center">操作</td>
            </tr>
            <%
            for(int n = 0;n < sickbed.size();n++){
            	ArrayList alRowSic = (ArrayList)sickbed.get(n);
            	ArrayList patient = array.getPatientWard(alRowSic.get(0).toString());
            	if(alRowSic.get(3).equals("1") || (patient != null && patient.size() != 0)){
            		flag = 1;
            	}
            %>
            <tr>
              <td align="center"><%=alRowSic.get(1) %></td>
              <td align="center"><%=alRowSic.get(3).equals("1")?"使用中":"未使用" %></td>
              <td align="center"><%=alRowSic.get(3).equals("1") || (patient != null && patient.size() != 0)?"":"<a href="+path+"/DelServlet?sickbed="+alRowSic.get(0)+">删除</a>" %></td>
            </tr>
            <%} %>
        </table></td>
        <td align="center"><a href="javascript:void(0)" onClick="sickbedadd('<%=path %>','<%=alRow.get(0) %>')">添加病床</a></td>
        <td align="center"><%=flag == 1?"":"<a href="+path+"/DelServlet?ward="+alRow.get(0)+">删除</a>" %></td>
      </tr>
      <%}} %>
    </tbody>
  </table>
</div>
</body>
</html>
