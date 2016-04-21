<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.entity.Sysuser"%>
<%@page import="com.cz.entity.Sreader"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="/libmanage/admin//commfiles/css/common.css" type="text/css" />
<title>管理导航区域</title>
</head>

<%
String admin = "";
String utype = "";
if(session.getAttribute("admin")!=null)
{
Sysuser map = (Sysuser)request.getSession().getAttribute("admin"); 
 admin = map.getUname();
 utype="管理员";
}

if(session.getAttribute("reader")!=null)
{
Sreader map = (Sreader)request.getSession().getAttribute("reader"); 
 admin = map.getUname();
 utype="读者";
}
%>

<script>
var preClassName = "man_nav_1";
function list_sub_nav(Id,sortname){
	if(preClassName != ""){
      getObject(preClassName).className="bg_image";
   }
   if(getObject(Id).className == "bg_image"){
      getObject(Id).className="bg_image_onclick";
      preClassName = Id;
	  showInnerText(Id);
	  var id=parseInt(Id.substring(8))-1;
	  var indexPage= window.top.frames['leftFrame'].outlookbar;
	  indexPage.getbytitle(sortname);
	  indexPage.getdefaultnav(sortname);
	  var title,src;
	  for(i=0;i<indexPage.titlelist.length;i++){
	  		if(indexPage.titlelist[i].sortname==sortname){
	  			title=indexPage.itemlist[i][0].title;
	  			src=indexPage.itemlist[i][0].key;
	  			break;
	  		}	
	  }
	  window.top.frames['leftFrame'].changeframe("",sortname,src);
   }
}
function showInnerText(Id){
    var switchId = parseInt(Id.substring(8));
	var showText = "对不起没有信息！";
	switch(switchId){
	<%if(utype.equals("管理员")){%>
		case 1:
			showText =  "业务信息";
			break;	
	    case 2:
		   showText =  "信息管理";
		   break;
	    case 3:
		   showText =  "统计分析";		   
		   break;
	    case 4:
		   showText =  "反馈收集";
		   break;		   
	    case 5:
		   showText =  "教材选用";
		   break;	
	    case 6:
		   showText =  "属性配置";
		   break;
		case 7:
		 	showText =  "个人信息管理";
		 	break;
		case 8:
			showText =  "系统管理";
			break;	
		case 9:
			showText =  "系统管理";
			break;
		<%}else{%>
		case 1:
			showText =  "还借信息";
			break;	
	    case 2:
		   showText =  "信息查询";
		   break;
	    case 3:
		   showText =  "个人信息";		   
		   break;
	    
		<%}%>
			
	}
	getObject('show_text').innerHTML = showText;
}
 //获取对象属性兼容方法
 function getObject(objectId) {
    if(document.getElementById && document.getElementById(objectId)) {
	// W3C DOM
	return document.getElementById(objectId);
    } else if (document.all && document.all(objectId)) {
	// MSIE 4 DOM
	return document.all(objectId);
    } else if (document.layers && document.layers[objectId]) {
	// NN 4 DOM.. note: this won't find nested layers
	return document.layers[objectId];
    } else {
	return false;
    }
}
function window.onbeforeunload(){
	<%if(utype.equals("管理员")){%>
	list_sub_nav('man_nav_1','业务信息');
	<%}else{%>
	list_sub_nav('man_nav_1','还借信息');
	<%}%>
}
</script>
<body>
<div id="nav">
    <ul>
    <%if(utype.equals("管理员")){%>
    <li id="man_nav_1" onclick="list_sub_nav(id,'业务信息')"  class="bg_image_onclick">业务信息</li>
	<li id="man_nav_2" onclick="list_sub_nav(id,'信息管理')"  class="bg_image">信息管理</li>
    <li id="man_nav_3" onclick="list_sub_nav(id,'统计分析')"  class="bg_image">统计分析</li>   
    <li id="man_nav_6" onclick="list_sub_nav(id,'属性配置')"  class="bg_image">属性配置</li>
    <li id="man_nav_8" onclick="list_sub_nav(id,'系统管理')"  class="bg_image">系统管理</li>
    <li id="man_nav_7" onclick="list_sub_nav(id,'个人信息')"  class="bg_image">个人信息</li>
    <%}else{ %>
    <li id="man_nav_1" onclick="list_sub_nav(id,'还借信息')"  class="bg_image_onclick">还借信息</li>
	<li id="man_nav_2" onclick="list_sub_nav(id,'信息查询')"  class="bg_image">信息查询</li>
    <li id="man_nav_3" onclick="list_sub_nav(id,'个人信息')"  class="bg_image">个人信息</li> 
    <%} %>
	</ul>
</div>
<div id="sub_info" >&nbsp;&nbsp;  &nbsp;<span id="show_text">欢迎使用图书管理系统</span></div>
</body>
</html>

