<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<form id="form_patient" method="post">
  <table cellpadding="5">
    <tr>
      <td align="right">患者姓名：</td>
      <td><input class="easyui-validatebox" type="text" name="truename" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">性别：</td>
      <td><input name="sex" type="radio" value="男" checked><span>男</span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="女"><span>女</span></td>
    </tr>
    <tr>
      <td align="right">年龄：</td>
      <td><input class="easyui-numberbox" type="text" name="age" required="true" missingMessage="该输入项只能输入数字" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">出生日期：</td>
      <td><input class="easyui-datebox" type="text" name="birthday" required="true" panelWidth="190" editable="false" /></td>
    </tr>
    <tr>
      <td align="right">民族：</td>
      <td><input class="easyui-validatebox" type="text" name="nation" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">身份证号码：</td>
      <td><input class="easyui-validatebox" type="text" name="idcard" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">联系电话：</td>
      <td><input class="easyui-numberbox" type="text" name="phone" required="true" missingMessage="该输入项只能输入数字" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">家庭住址：</td>
      <td><input class="easyui-validatebox" type="text" name="address" required="true" customize="Customize" /></td>
    </tr>
  </table>
</form>
