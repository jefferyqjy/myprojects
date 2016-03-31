<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>启蒙幼儿园管理系统</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	font-size: 12px;
	color: #000000;
}
.STYLE5 {font-size: 12}
.STYLE7 {font-size: 12px; color: #FFFFFF; }
a.title {
	font-family: -webkit-body;
	color: rgb(0, 241, 255);
	font-style: italic;
	font-size: xx-large;
}
-->
</style>
<script language="javascript">
var t = null;
t = setTimeout(time,1000);
function time()
{
  clearTimeout(t);
  dt = new Date();
  var y = dt.getYear();
  var mo = add_zero(dt.getMonth() + 1);
  var d = add_zero(dt.getDate());
  var h=add_zero(dt.getHours());
  var m=add_zero(dt.getMinutes());
  var s=add_zero(dt.getSeconds());
  document.getElementById("timeShow").innerHTML =  "<span class='STYLE7'>服务器时间：" + y + "-" + mo + "-" + d +" " + h + ":" +m + ":" + s +"</span>";
  t = setTimeout(time,1000);
}
function add_zero(temp)
{
 if(temp<10) return "0"+temp;
 else return temp;
}


</script>

</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="57" background="images/main_03.gif">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="378" height="57" background="images/main_03.gif">
	        &nbsp;
	        	<a class="title">启蒙幼儿园管理系统</a>
	        </td>
	        <td>&nbsp;</td>
	        <td width="281" valign="bottom"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td width="33" height="27"><img src="images/main_05.gif" width="33" height="27" /></td>
	            <td width="248" background="images/main_06.gif"><table width="225" border="0" align="center" cellpadding="0" cellspacing="0">
	              <tr>
	                <td height="17"><div align="right"><img src="images/pass.gif" width="69" height="17" /></div></td>
	                <td><div align="right"><img src="images/user.gif" width="69" height="17" /></div></td>
	                <td><div align="right"><img src="images/quit.gif" width="69" height="17" onClick="parent.location.replace('/Nursery/logout')"/></div></td>
	              </tr>
	            </table></td>
	          </tr>
	        </table></td>
	      </tr>
	    </table>
    </td>
  </tr>
  <tr>
    <td height="40" background="images/main_10.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="194" height="40" background="images/main_10.gif">&nbsp;</td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="21"><img src="images/main_13.gif" width="19" height="14" /></td>
            <td width="35" class="STYLE7"><div align="center">首页</div></td>
            <td width="21" class="STYLE7"><img src="images/main_15.gif" width="19" height="14" /></td>
            <td width="35" class="STYLE7"><div align="center">后退</div></td>
            <td width="21" class="STYLE7"><img src="images/main_17.gif" width="19" height="14" /></td>
            <td width="35" class="STYLE7"><div align="center">前进</div></td>
            <td width="21" class="STYLE7"><img src="images/main_19.gif" width="19" height="14" /></td>
            <td width="35" class="STYLE7"><div align="center">刷新</div></td>
            <td width="21" class="STYLE7"><img src="images/main_21.gif" width="19" height="14" /></td>
            <td width="35" class="STYLE7"><div align="center">帮助</div></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
        <td width="248" background="images/main_11.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="16%"><span class="STYLE5"></span></td>
            <td width="75%"><div align="center" id="timeShow"><span class="STYLE7">
</span></div></td>
            <td width="9%">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="30" background="images/main_31.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="8" height="30"><img src="images/main_28.gif" width="8" height="30" /></td>
        <td width="147" background="images/main_29.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="24%">&nbsp;</td>
            <td width="43%" height="20" valign="bottom" class="STYLE1">管理菜单</td>
            <td width="33%">&nbsp;</td>
          </tr>
        </table></td>
        <td width="39"><img src="images/main_30.gif" width="39" height="30" /></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="20" valign="bottom"><span class="STYLE1">当前登录用户：${PRO_USER_NAME } &nbsp;用户角色：${PRO_USER_ROLE }</span></td>
            <td valign="bottom" class="STYLE1"><div align="right"><img src="images/sj.gif" width="6" height="7" />       &nbsp; &nbsp;&nbsp;<img src="images/sj.gif" width="6" height="7" /> &nbsp; &nbsp; &nbsp; <img src="images/sj.gif" width="6" height="7" /> &nbsp;</div></td>
          </tr>
        </table></td>
        <td width="17"><img src="images/main_32.gif" width="17" height="30" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
