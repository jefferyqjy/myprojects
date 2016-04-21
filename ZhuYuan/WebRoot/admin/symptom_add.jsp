<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<jsp:useBean id="array" scope="page" class="bean.AllBean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList patient = array.getPatientSymptom();
%>
<form id="form_symptom" method="post">
  <table cellpadding="5">
    <tr>
      <td align="right">患者姓名：</td>
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
      <td align="right">有效期至：</td>
      <td><textarea class="easyui-validatebox" name="symptom" required="true" rows="8" cols="40"></textarea></td>
    </tr>
  </table>
</form>
