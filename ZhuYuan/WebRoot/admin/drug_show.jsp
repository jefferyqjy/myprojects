<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<form id="form_drug_show" method="post">
  <table cellpadding="5">
    <tr>
      <td align="right">药品名称：</td>
      <td><input class="easyui-validatebox" type="text" name="name" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">规格：</td>
      <td><input class="easyui-validatebox" type="text" name="norms" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">批准文号：</td>
      <td><input class="easyui-validatebox" type="text" name="approval" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">生产厂家：</td>
      <td><input class="easyui-validatebox" type="text" name="factory" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">主要成份：</td>
      <td><textarea class="easyui-validatebox" name="ingredient" required="true" rows="4" cols="40"></textarea></td>
    </tr>
    <tr>
      <td align="right">适应症状：</td>
      <td><textarea class="easyui-validatebox" name="treat" required="true" rows="4" cols="40"></textarea></td>
    </tr>
    <tr>
      <td align="right">用法用量：</td>
      <td><textarea class="easyui-validatebox" name="dosage" required="true" rows="4" cols="40"></textarea></td>
    </tr>
    <tr>
      <td align="right">售价：</td>
      <td><input class="easyui-numberbox" type="text" name="price" required="true" missingMessage="该输入项只能输入数字" customize="Customize" /></td>
    </tr>
  </table>
</form>
