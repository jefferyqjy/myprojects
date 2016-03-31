<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%request.setCharacterEncoding("GBK");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>启蒙幼儿园管理系统</title>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
-->
</style>
<script>
var  highlightcolor='#d5f4fe';
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
try{
	if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=highlightcolor;
		}
}catch(e){}

}

function  changeback(){
	try{
		if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
			return
			if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
			//source.style.backgroundColor=originalcolor
			for(i=0;i<cs.length;i++){
				cs[i].style.backgroundColor="";
			}
	}catch(e){}
}

function  clickto(){
	try{
		source=event.srcElement;
		if  (source.tagName=="TR"||source.tagName=="TABLE")
		return;
		while(source.tagName!="TD")
		source=source.parentElement;
		source=source.parentElement;
		cs  =  source.children;
		//alert(cs.length);
		if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=clickcolor;
		}
		else
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
		}catch(e){}

}

</script>


</head>

<body>
<form action="/Nursery/healthmanager" method="post">
<input type="hidden" name="type" value="update"/>
<input type="hidden" name="id" value="${HEALTHINFO.id}"/>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1">修改健康状况</span></td>
              </tr>
            </table></td>
            <td><div align="right"><span class="STYLE1">
              &nbsp;</span></div></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>

  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
      <tr>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">身份证</span></div></td>
        <td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10"><input type="text" name="bodycard" value="${HEALTHINFO.bodycard}" readonly/></span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">检查日期</span></div></td>
        <td width="25%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10"><input type="text" name="check_date"  value="${HEALTHINFO.check_date}"  /></span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">身高</span></div></td>
        <td width="25%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10"><input type="text" name="height" size="10"  value="${HEALTHINFO.height}" />厘米</span></div></td>
      </tr>

	<tr>
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">体重</span></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><input type="text" name="weight" size="10"  value="${HEALTHINFO.weight}" />公斤</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">年龄</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><input type="text" name="age" size="10" maxlength="2"  value="${HEALTHINFO.age}" /> 岁</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">视力</div></td>
        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">左：<input type="text" name="left_sight" size="10"   value="${HEALTHINFO.left_sight}" /> 右：<input type="text" name="right_sight" size="10" value="${HEALTHINFO.right_sight}" /></div></td>
      </tr>
	<tr>
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">姓名</span></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><input type="text" name="weight" size="17"  value="${HEALTHINFO.name}" readonly/></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">性别</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><input type="text" name="age" size="10" maxlength="2"  value="${HEALTHINFO.sex}" readonly/> </div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"></div></td>
        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21"></div></td>
      </tr>
      <tr>
        <td colspan="6" height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><textarea name = "description" cols="100" rows="10" >${HEALTHINFO.description}</textarea></span></div></td>
      </tr>
        <tr>
        <td colspan="6"  height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><input type="submit" value="更新" /></div></td>
        </tr>
    </table>
  <tr><td><font color="green">${Succ}</font><font color="red">${Error}</font></td></tr>
</table>
</form>
</body>
</html>
