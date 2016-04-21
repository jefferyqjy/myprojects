<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<jsp:useBean id="array" scope="page" class="bean.AllBean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList patient = array.getPatientAssign();
%>
<style>
.table-padding td {padding:5px;}
</style>
<form id="form_assign" method="post">
  <table class="table-padding">
    <tr>
      <td align="right">¹ÒºÅµ¥±àºÅ£º</td>
      <td><select id="cc" class="easyui-combobox" name="patient" editable="false" required="true">
      	  <%
      	  for(int i = 0;i < patient.size();i++){
      		  ArrayList alRow = (ArrayList)patient.get(i);
      	  %>
          <option value="<%=alRow.get(0) %>"><%=alRow.get(0) %></option>
          <%} %>
        </select></td>
    </tr>
  </table>
</form>
