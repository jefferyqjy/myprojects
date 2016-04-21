<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<jsp:useBean id="array" scope="page" class="bean.AllBean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList drug = array.getDrug();
%>
<form id="form_stock" method="post">
  <table cellpadding="5">
    <tr>
      <td align="right">药品名称：</td><td><select id="cc" class="easyui-combobox" name="drug" editable="false" required="true">
      	  <%
      	  for(int i = 0;i < drug.size();i++){
      		  ArrayList alRow = (ArrayList)drug.get(i);
      	  %>
          <option value="<%=alRow.get(0) %>"><%=alRow.get(1) %></option>
          <%} %>
        </select></td>
    </tr>
    <tr>
      <td align="right">批次号：</td>
      <td><input class="easyui-validatebox" type="text" name="batch" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">入库数量：</td>
      <td><input class="easyui-numberbox" type="text" name="sums" required="true" missingMessage="该输入项只能输入数字" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">有效期至：</td>
      <td><input class="easyui-datebox" type="text" name="validity" required="true" panelWidth="190" editable="false" /></td>
    </tr>
  </table>
</form>
