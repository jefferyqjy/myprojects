<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<jsp:useBean id="array" scope="page" class="bean.AllBean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList patient = array.getPatientSymptom();
ArrayList drug = array.getDrug();
%>
<form id="form_prescribe" method="post">
  <table cellpadding="5">
    <tr>
      <td align="right">����������</td>
      <td><select id="cc" class="easyui-combobox" name="patient" editable="false" required="true">
      	  <%
      	  for(int i = 0;i < patient.size();i++){
      		  ArrayList alRow = (ArrayList)patient.get(i);
      	  %>
          <option value="<%=alRow.get(0) %>"><%=alRow.get(2) %></option>
          <%} %>
        </select></td>
    </tr>
    <tr>
      <td align="right">ҩƷ���ƣ�</td>
      <td><select id="cc" class="easyui-combobox" name="drug" editable="false" required="true">
      	  <%
      	  for(int i = 0;i < drug.size();i++){
      		  ArrayList alRow = (ArrayList)drug.get(i);
      	  %>
          <option value="<%=alRow.get(0) %>"><%=alRow.get(1) %></option>
          <%} %>
        </select></td>
    </tr>
    <tr>
      <td align="right">��ҩ������</td>
      <td><input class="easyui-numberbox" type="text" name="sums" required="true" missingmessage="��������ֻ����������" customize="Customize" /></td>
    </tr>
  </table>
</form>
