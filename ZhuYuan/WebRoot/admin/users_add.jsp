<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<form id="form_reg" method="post">
  <table cellpadding="5">
    <tr>
      <td align="right">用户名：</td>
      <td><input class="easyui-validatebox" type="text" name="name" required="true" validType="pattern" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">密码：</td>
      <td><input class="easyui-validatebox" type="password" name="pwd" required="true" validType="pattern" id="repeat_pwd" customize="Customize" /></td>
    </tr>
    <tr class="tr_border_bottom">
      <td align="right">重复密码：</td>
      <td><input class="easyui-validatebox" type="password" name="repeatpwd" required="true" validType="repeat'#repeat_pwd'" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">真实姓名：</td>
      <td><input class="easyui-validatebox" type="text" name="truename" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">性别：</td>
      <td><input name="sex" type="radio" value="男" checked><span>男</span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="女"><span>女</span></td>
    </tr>
    <tr>
      <td align="right">出生日期：</td>
      <td><input class="easyui-datebox" type="text" name="birthday" required="true" panelWidth="190" editable="false" /></td>
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
      <td align="right">联系地址：</td>
      <td><input class="easyui-validatebox" type="text" name="address" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">邮政编码：</td>
      <td><input class="easyui-numberbox" type="text" name="postalcode" required="true" missingMessage="该输入项只能输入数字" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">电子邮箱：</td>
      <td><input class="easyui-validatebox" type="text" name="email" required="true" validType="email" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">身份：</td>
      <td><input name="status" type="radio" value="挂号处" checked><span>挂号处</span>&nbsp;
      	  <input type="radio" name="status" value="护士"><span>护士</span>&nbsp;
      	  <input type="radio" name="status" value="医生"><span>医生</span>&nbsp;
      	  <input type="radio" name="status" value="收费处"><span>收费处</span>&nbsp;
      	  <input type="radio" name="status" value="药品管理员"><span>药品管理员</span>&nbsp;</td>
    </tr>
  </table>
</form>
