<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList adminlogin = (ArrayList)session.getAttribute("adminlogin");

%>
<ul>
<%
if(adminlogin != null && adminlogin.size() != 0){
if(adminlogin.get(3).equals("�ҺŴ�")){
%>
  <li><a href="<%=path %>/admin/patient.jsp">�ҺŹ���</a></li>
<%}else{ %>
  <li><a href="javascript:void(0)" style="color:#CCC">�ҺŹ���</a></li>
<%} %>
<%if(adminlogin.get(3).equals("��ʿ")){ %>
  <li><a href="<%=path %>/admin/assign.jsp">��λ�������</a></li>
<%}else{ %>
  <li><a href="javascript:void(0)" style="color:#CCC">��λ�������</a></li>
<%} %>
<%if(adminlogin.get(3).equals("ҽ��")){ %>
  <li><a href="<%=path %>/admin/symptom.jsp">���߲�״����</a></li>
  <li><a href="<%=path %>/admin/prescribe.jsp">���߿�ҩ����</a></li>
  <li><a href="<%=path %>/admin/inspect.jsp">���߼�����</a></li>
<%}else{ %>
  <li><a href="javascript:void(0)" style="color:#CCC">���߲�״����</a></li>
  <li><a href="javascript:void(0)" style="color:#CCC">���߿�ҩ����</a></li>
  <li><a href="javascript:void(0)" style="color:#CCC">���߼�����</a></li>
<%} %>
<%if(adminlogin.get(3).equals("�շѴ�")){ %>
  <li><a href="<%=path %>/admin/leavehospital.jsp">��Ժ����</a></li>
<%}else{ %>
  <li><a href="javascript:void(0)" style="color:#CCC">��Ժ����</a></li>
<%} %>
<%if(adminlogin.get(3).equals("����")){ %>
  <li><a href="<%=path %>/admin/search.jsp">���ò�ѯ</a></li>
<%}else{ %>
  <li><a href="javascript:void(0)" style="color:#CCC">���ò�ѯ</a></li>
<%} %>
<%if(adminlogin.get(3).equals("ҩƷ����Ա")){ %>
  <li><a href="<%=path %>/admin/drug.jsp">ҩƷ����</a></li>
  <li><a href="<%=path %>/admin/stock.jsp">ҩƷ���</a></li>
<%}else{ %>
  <li><a href="javascript:void(0)" style="color:#CCC">ҩƷ����</a></li>
  <li><a href="javascript:void(0)" style="color:#CCC">ҩƷ���</a></li>
<%} %>
<%if(adminlogin.get(3).equals("����Ա")){ %>
  <li><a href="<%=path %>/admin/systemuser.jsp">ϵͳ�û�����</a></li>
  <li><a href="<%=path %>/admin/users.jsp">��ͨ�û�����</a></li>
  <li><a href="<%=path %>/admin/ward.jsp">������������</a></li>
<%}else{ %>
  <li><a href="javascript:void(0)" style="color:#CCC">ϵͳ�û�����</a></li>
  <li><a href="javascript:void(0)" style="color:#CCC">��ͨ�û�����</a></li>
  <li><a href="javascript:void(0)" style="color:#CCC">������������</a></li>
<%}} %>
</ul>

