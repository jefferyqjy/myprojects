<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<form id="form_reg" method="post">
  <table cellpadding="5">
    <tr>
      <td align="right">�û�����</td>
      <td><input class="easyui-validatebox" type="text" name="name" required="true" validType="pattern" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">���룺</td>
      <td><input class="easyui-validatebox" type="password" name="pwd" required="true" validType="pattern" id="repeat_pwd" customize="Customize" /></td>
    </tr>
    <tr class="tr_border_bottom">
      <td align="right">�ظ����룺</td>
      <td><input class="easyui-validatebox" type="password" name="repeatpwd" required="true" validType="repeat'#repeat_pwd'" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">��ʵ������</td>
      <td><input class="easyui-validatebox" type="text" name="truename" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">�Ա�</td>
      <td><input name="sex" type="radio" value="��" checked><span>��</span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="Ů"><span>Ů</span></td>
    </tr>
    <tr>
      <td align="right">�������ڣ�</td>
      <td><input class="easyui-datebox" type="text" name="birthday" required="true" panelWidth="190" editable="false" /></td>
    </tr>
    <tr>
      <td align="right">���֤���룺</td>
      <td><input class="easyui-validatebox" type="text" name="idcard" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">��ϵ�绰��</td>
      <td><input class="easyui-numberbox" type="text" name="phone" required="true" missingMessage="��������ֻ����������" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">��ϵ��ַ��</td>
      <td><input class="easyui-validatebox" type="text" name="address" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">�������룺</td>
      <td><input class="easyui-numberbox" type="text" name="postalcode" required="true" missingMessage="��������ֻ����������" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">�������䣺</td>
      <td><input class="easyui-validatebox" type="text" name="email" required="true" validType="email" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">��ݣ�</td>
      <td><input name="status" type="radio" value="�ҺŴ�" checked><span>�ҺŴ�</span>&nbsp;
      	  <input type="radio" name="status" value="��ʿ"><span>��ʿ</span>&nbsp;
      	  <input type="radio" name="status" value="ҽ��"><span>ҽ��</span>&nbsp;
      	  <input type="radio" name="status" value="�շѴ�"><span>�շѴ�</span>&nbsp;
      	  <input type="radio" name="status" value="ҩƷ����Ա"><span>ҩƷ����Ա</span>&nbsp;</td>
    </tr>
  </table>
</form>
