<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%

 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/common.css" /> 
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/style.css" /> 
  </head>
<%
if(request.getAttribute("suc")!=null)
{
 %>
 <script type="text/javascript">
<!--
alert("添加成功")
//-->
</script>
<%}%>
   
  <body>
  <form action="/libmanage/sys.do?action=addapp" method="post" enctype="multipart/form-data" onsubmit="return ck();">
  	<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="mytab" id="table1">
        <tr align="center">
          <td colspan="2"  background="/libmanage/admin/commfiles/images/bg.gif"
				bgcolor="#FFFFFF" class="STYLE3">客户端版本发布</td>
        </tr>
        <tr align="center">
          <td width="23%" align="center"> 版本号 </td>
          <td width="77%" align="left"><span class="style1">
            <input type="text" name="versionCode" id="versionCode" style="width:200px;" />
          </span> 版本号只能输入整数或小数</td>
        </tr>
        <tr align="center">
          <td align="center"> 备注 </td>
          <td align="left"><span class="style1">
            <textarea rows="4" cols="25" id="remark" name="remark"></textarea>
          </span> </td>
        </tr>
        <tr align="center">
          <td align="center"> 附件上传 </td>
          <td align="left"><span class="style1">
            <input type="file" id="txt" name="txt" style="width:200px;" />
          </span> </td>
        </tr>
        <tr align="center">
          <td colspan="2" align="center">
            <label>
              <input type="submit" name="button" id="button" value="提交">
              <input type="reset" value="重置">
            </label>
                    </td>
        </tr>
      </table>
      </form>
</body>
</html>
<script type="text/javascript" src="/libmanage/admin/commfiles/js/ajax.js"></script>
<script type="text/javascript">
<!--
function ck(){
	var code = document.getElementById("versionCode").value;
	var remark = document.getElementById("remark").value;
	var txt = document.getElementById("txt").value;
	if(code==""){
	
		alert("版本号不能为空");
		return false;
	}
	var re = "^[0-9]+([.]{1}[0-9]+){0,1}$";
	if(!code.match(re)){
	alert("版本号输入不合法");
	return false;
	}if(txt==""){
	alert("附件不能为空");
	return false;
	}
	
var ajax = new AJAX();
ajax.post("/libmanage/sys.do?action=checkcode&code="+code);
var msg = ajax.getValue();

if(msg == 'n'){
alert("版本号已存在,请重新输入");
return false;
}
}
//-->
</script>

