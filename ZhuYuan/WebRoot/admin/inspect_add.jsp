<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<jsp:useBean id="array" scope="page" class="bean.AllBean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList patient = array.getPatientSymptom();
%>
<form id="form_inspect" method="post">
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
      <td align="right">检查项目名称：</td>
      <td><input class="easyui-validatebox" type="text" name="item" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">价格：</td>
      <td><input class="easyui-numberbox" type="text" name="price" required="true" missingmessage="该输入项只能输入数字" customize="Customize" /></td>
    </tr>
  </table>
</form>
