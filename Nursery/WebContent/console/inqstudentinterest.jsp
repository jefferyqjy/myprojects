<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="java.util.List,java.util.Map" %>
    <%request.setCharacterEncoding("utf-8");%>
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
<%
List list = (List)request.getAttribute("STUDENTINTERESTLIST");
String userRole = (String)request.getSession().getAttribute("PRO_USER_ROLE");
%>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr>
    	<td height="30">
    		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	      		<tr>
	        		<td height="24" bgcolor="#353c44">
	        			<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          				<tr>
	            				<td>
	            					<table width="100%" border="0" cellspacing="0" cellpadding="0">
	              						<tr>
	                						<td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
	                						<td width="94%" valign="bottom"><span class="STYLE1">学生兴趣班查询</span></td>
	              						</tr>
	            					</table>
	           					</td>
	            				<td><div align="right"><span class="STYLE1">&nbsp;</span></div></td>
	       					</tr>
	        			</table>
	        		</td>
	      		</tr>
    		</table>
    	</td>
  	</tr>
  	<tr>
    	<td>
    		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
      			<tr>
        			<td width="4%" height="20" bgcolor="d3eaef" class="STYLE10">
        				<div align="center">
        					<form action="/Nursery/interestmanager?type=inq" method="post">
          						身份证： <input type="text" name="student_bodycard" value="${ON_SELECT_STUDENT_BODYCARD }" maxlength="18"/> |
          						兴趣班：
          						<select name="interest">
          							<option value="">-请选择-</option>
      								<option value="reading">读书</option>
				          			<option value="singing">唱歌</option>
			          				<option value="dancing">跳舞</option>
				          			<option value="drawing">画画</option>
          						</select>
          						<input type="submit" value="查询" /><font color="red">${Error}</font><font color="green">${Succ}</font>
          					</form>
        				</div>
        			</td>
      			</tr>
    		</table>
    	</td>
  	</tr>
    <%
  	if (list != null && list.size() > 0) {
	%>
   	<tr>
	    <td>
	    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
	      		<tr>
	        		<td width="5%" height="20" bgcolor="d3eaef" class="STYLE10">
	        			<div align="center">
	          				<input type="checkbox" name="checkbox" id="checkbox" />
	        			</div>
	        		</td>
	        		<td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">身份证</span></div></td>
	        		<td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">兴趣班</span></div></td>
	        		<td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
	      		</tr>
				<%
				for(int i = 0; i < list.size(); i++) {
					Map map = (Map)list.get(i);
				%>
					<tr>
	        			<td height="20" bgcolor="#FFFFFF">
	        				<div align="center">
	          					<input type="checkbox" name="checkbox2" id="checkbox2" />
	        				</div>
	        			</td>
			        	<td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=map.get("student_bodycard") %></div></td>
			        	<td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=map.get("interestInfo") %></div></td>
			        	<td height="20" bgcolor="#FFFFFF">
	        				<div align="center" class="STYLE21"> 
	        					<a href="/Nursery/interestmanager?type=view&id=<%=map.get("id") %>">查看</a> 
					        	<%
								if ("admin".equals(userRole)) {
								%> 
									| <a href="/Nursery/interestmanager?type=delete&id=<%=map.get("id") %>">删除</a>
								<%
								}
								%>
							</div>
						</td>
	      			</tr>
				<%
				}
				%>
	    	</table>
		</td>
  	</tr>
	<%
	}
	%>
  	<tr>
    	<td height="30">
    		<table width="100%" border="0" cellspacing="0" cellpadding="0">
      			<tr>
        			<td width="33%">
        				<div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong> ${control.totalnum}</strong> 条记录，当前第<strong> ${control.curpage}</strong> 页，共 <strong>${control.totalpage}</strong> 页</span></div>
       				</td>
       				<td width="67%">
       					<table width="312" border="0" align="right" cellpadding="0" cellspacing="0">
          					<tr>
            					<td width="49"><div align="center"><a href="${URL_PARA}&curpage=1"><img src="/Nursery/console/images/main_54.gif" width="40" height="15" /></a></div></td>
            					<td width="49"><div align="center"><a href="${URL_PARA}&curpage=${control.prepage}"><img src="/Nursery/console/images/main_56.gif" width="45" height="15" /></a></div></td>
            					<td width="49"><div align="center"><a href="${URL_PARA}&curpage=${control.nextpage}"><img src="/Nursery/console/images/main_58.gif" width="45" height="15" /></a></div></td>
            					<td width="49"><div align="center"><a href="${URL_PARA}&curpage=${control.totalpage}"><img src="/Nursery/console/images/main_60.gif" width="40" height="15" /></a></div></td>
            					<td width="37" class="STYLE22"><div align="center">转到</div></td>
            					<td width="22">
            						<div align="center">
              							<input type="text" name="textfield" id="textfield"  style="width:20px; height:12px; font-size:12px; border:solid 1px #7aaebd;"/>
            						</div>
            					</td>
            					<td width="22" class="STYLE22"><div align="center">页</div></td>
            					<td width="35"><img src="/Nursery/console/images/main_62.gif" width="26" height="15" /></td>
          					</tr>
        				</table>
        			</td>
      			</tr>
    		</table>
    	</td>
  	</tr>
</table>
</body>
</html>