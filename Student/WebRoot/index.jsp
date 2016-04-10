<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="sys_jsp/base.jsp" %>
<%@ page import="com.sys.web.left.tree.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	String tp = (String)request.getSession().getAttribute("type");
	String navInfo = NavTree.genNav(tp,"0");
	String sysTitle = NavTree.genTitile();
	request.setAttribute("navInfo",navInfo);
	request.setAttribute("sysTitle",sysTitle);
	String emailName1 = (String)request.getSession().getAttribute("defaultMail");
 %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<title></title>
<script type="text/javascript">
$(function(){
	//setMenuHeight
	$('.menu').height($(window).height()-51-27-26-5);
	$('.sidebar').height($(window).height()-51-27-26-5);
	$('.page').height($(window).height()-51-27-26-5);
	$('.page iframe').width($(window).width()-15-168);
	
	//menu on and off
	$('.btn').click(function(){
		$('.menu').toggle();
		
		if($(".menu").is(":hidden")){
			$('.page iframe').width($(window).width()-15+5);
			}else{
			$('.page iframe').width($(window).width()-15-168);
				}
		});
		
	//
	$('.subMenu a[href="#"]').click(function(){
		$(this).next('ul').toggle();
		return false;
		});
})
</script>


</head>

<body style="overflow-y:hidden">
<div id="wrap">
	<div id="header">
    <div class="logo fleft"></div>
    <div class="nav fleft">
    	<ul>
        	<div class="nav-left fleft"></div>
        	<li class="first"><a onclick="javascript:window.frames['rightMain'].src='siteInfo.jsp'">首页</a></li>
            <li><a href="#"></a></li>
            <li><a href="#"></a></li>
            <li><a href="#"></a></li>
            <li><a href="#"></a></li>
             <li><a>${s_currentTime }</a></li>
            <div class="nav-right fleft"></div>
        </ul>
    </div>
    <a class="logout fright" href="admin/admin_logout.do"> </a>
    <div class="clear"></div>
    <div class="subnav">
    	<div class="subnavLeft fleft"></div>
        <div class="fleft"></div>
        <div class="subnavRight fright"></div>
    </div>
    </div><!--#header -->
    <div id="content">
    <div class="space"></div>
    <div class="menu fleft">
    
    	<ul>
        	<li class="subMenuTitle">您好，<%=request.getSession().getAttribute("userName") %></li>
            ${navInfo }
        </ul>
    </div>
    <div class="sidebar fleft"><div class="btn"></div></div>
    <div class="page">
    <iframe width="100%" scrolling="auto" height="100%" frameborder="false" allowtransparency="true" style="border: medium none;" src="siteInfo.jsp" id="rightMain" name="right"></iframe>
    </div>
    </div><!--#content -->
    <div class="clear"></div>
    <div id="footer">CopyRight &copy; 2012-2014 xxx.xxx.xxx </div><!--#footer -->
   
    
</div><!--#wrap -->
</body>
</html>
