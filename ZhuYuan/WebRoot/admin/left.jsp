<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList adminlogin = (ArrayList)session.getAttribute("adminlogin");

%>
<ul>
<%
if(adminlogin != null && adminlogin.size() != 0){
if(adminlogin.get(3).equals("挂号处")){
%>
  <li><a href="<%=path %>/admin/patient.jsp">挂号管理</a></li>
<%}else{ %>
  <li><a href="javascript:void(0)" style="color:#CCC">挂号管理</a></li>
<%} %>
<%if(adminlogin.get(3).equals("护士")){ %>
  <li><a href="<%=path %>/admin/assign.jsp">床位分配管理</a></li>
<%}else{ %>
  <li><a href="javascript:void(0)" style="color:#CCC">床位分配管理</a></li>
<%} %>
<%if(adminlogin.get(3).equals("医生")){ %>
  <li><a href="<%=path %>/admin/symptom.jsp">患者病状管理</a></li>
  <li><a href="<%=path %>/admin/prescribe.jsp">患者开药管理</a></li>
  <li><a href="<%=path %>/admin/inspect.jsp">患者检查管理</a></li>
<%}else{ %>
  <li><a href="javascript:void(0)" style="color:#CCC">患者病状管理</a></li>
  <li><a href="javascript:void(0)" style="color:#CCC">患者开药管理</a></li>
  <li><a href="javascript:void(0)" style="color:#CCC">患者检查管理</a></li>
<%} %>
<%if(adminlogin.get(3).equals("收费处")){ %>
  <li><a href="<%=path %>/admin/leavehospital.jsp">出院管理</a></li>
<%}else{ %>
  <li><a href="javascript:void(0)" style="color:#CCC">出院管理</a></li>
<%} %>
<%if(adminlogin.get(3).equals("患者")){ %>
  <li><a href="<%=path %>/admin/search.jsp">费用查询</a></li>
<%}else{ %>
  <li><a href="javascript:void(0)" style="color:#CCC">费用查询</a></li>
<%} %>
<%if(adminlogin.get(3).equals("药品管理员")){ %>
  <li><a href="<%=path %>/admin/drug.jsp">药品管理</a></li>
  <li><a href="<%=path %>/admin/stock.jsp">药品入库</a></li>
<%}else{ %>
  <li><a href="javascript:void(0)" style="color:#CCC">药品管理</a></li>
  <li><a href="javascript:void(0)" style="color:#CCC">药品入库</a></li>
<%} %>
<%if(adminlogin.get(3).equals("管理员")){ %>
  <li><a href="<%=path %>/admin/systemuser.jsp">系统用户管理</a></li>
  <li><a href="<%=path %>/admin/users.jsp">普通用户管理</a></li>
  <li><a href="<%=path %>/admin/ward.jsp">病房病床管理</a></li>
<%}else{ %>
  <li><a href="javascript:void(0)" style="color:#CCC">系统用户管理</a></li>
  <li><a href="javascript:void(0)" style="color:#CCC">普通用户管理</a></li>
  <li><a href="javascript:void(0)" style="color:#CCC">病房病床管理</a></li>
<%}} %>
</ul>

