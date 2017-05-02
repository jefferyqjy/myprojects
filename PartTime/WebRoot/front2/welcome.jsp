<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<style type="text/css">
	img{
	border: none;
	}
	</style>
<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery-1.9.1.js"></script>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcom.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <script type="text/javascript"> 
 function loadpage(){
  //self.location.href = "userfiles/userfiles_query4hasnewmessage.do";
 
 }
  </script>
  
  <body onload="loadpage()">
  	<!-- 
    <img alt=""  src="<%=basePath%>/front/images/welcom.jpg">
     -->
     <table style="border-style: none; width: 700px; height: 450px;background-color: #A6BDF4" >
     	
     	
     	<tr height="200px">
     		<td colspan="2">
     				<div id="demo" style="overflow:hidden;width:750px;">
					  <table >
					    <tr>
					      <td id="demo1" valign="top" bgcolor="ffffff">
					     <!-- 特别注意，下面的图片总宽度必须大于上面定义的demo的宽度，如上面demo的宽度为500px,则下面图片总宽度必须大于500,否则会出现些问题！ -->
					      	<table border="0" cellspacing="0" cellpadding="0">
					          <tr align="center">
					            <td><a href="#" target="_blank"><img src="<%=basePath%>/front2/images/1.jpg" width="200" height="200"></a></td>
					            <td><a href="#" target="_blank"><img src="<%=basePath%>/front2/images/2.jpg" width="200" height="200"></a></td>
					            <td><a href="#" target="_blank"><img src="<%=basePath%>/front2/images/3.jpg" width="200" height="200"></a></td>
					            <td><a href="#" target="_blank"><img src="<%=basePath%>/front2/images/4.jpg" width="200" height="200"></a></td>
					            <td><a href="#" target="_blank"><img src="<%=basePath%>/front2/images/5.jpg" width="200" height="200"></a></td>
					            <td><a href="#" target="_blank"><img src="<%=basePath%>/front2/images/6.jpg" width="200" height="200"></a></td>
					            <td><a href="#" target="_blank"><img src="<%=basePath%>/front2/images/3.jpg" width="200" height="200"></a></td>
					          </tr>
					        </table>
					      </td>
					      <td id="demo2" valign="top"></td>
					    </tr>
					  </table>
					</div>
     		<script type="text/javascript"> 
				var speed=30;
				var demo = $("#demo");
				var demo1 = $("#demo1");
				var demo2 = $("#demo2");
				demo2.html(demo1.html());
				function Marquee(){ 
					if(demo.scrollLeft()==0){
						demo.scrollLeft(demo1.width());
					}else{
						demo.scrollLeft(demo.scrollLeft()-1);
					}
				} 
				var MyMar=setInterval(Marquee,speed) 
				demo.mouseover(function() {
					clearInterval(MyMar);
				} )
				demo.mouseout(function() {
					MyMar=setInterval(Marquee,speed);
				} )
				
				</script>
     		</td>
     	</tr>
     </table>
   
  </body>
</html>
