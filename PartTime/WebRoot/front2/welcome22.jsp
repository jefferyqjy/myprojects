<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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
<style type="text/css">
		body{background-color: #C7E1FA;}
		.menu ul {margin:0;padding:0; list-style:none;}
		.menu ul li{margin:0;padding:0; list-style:none;}
		.menu{width:950px;height:27px;margin:0 auto; background:url(images/bbbb.png) repeat-x;}
		.menu ul{width:950px;float:left;height:27px;display:inline;}
		.menu ul li{width:100px;height:30px;margin:1px 1px 1px 1px; line-height:30px; text-align:center; font-weight:bold; float:left;display:inline; }
		.menu ul li a{width:100px;height:36px; float:left;text-decoration:none; color:#fff; font-size:12px}
		.menu ul li a:hover{ text-decoration:underline;background:url(images/kkkk.png) repeat-x;}
		.header{width:950px;height:200px;margin:1 auto;  background:url(images/jjjj.png) repeat-x;}
		.leftmenu{width:150px;float: left;  vertical-align:top; display:inline; text-align:left;}
		.content{width:750px; float: left; vertical-align: top; display:inline;}
		.leftMenuFont{font-family: serif;font-size: 12px; text-align: left;}
		.category{border:0; bordercolor:#DEDEDE; cellpadding:0; cellspacing:0;}	
		a:hover{background:url(images/kkkk.png) repeat-x; text-decoration: underline;}
		ol li{margin:0;padding:0; list-style:none;}
		a:visited {
			color: #333333;
			text-decoration: underline;
		}

		a:link {
			color: #333333;
			text-decoration:none;
		}
		
		/* jQuery lightBox plugin - Gallery style */
	#gallery {
		background-color:#C7E1FA;
		padding: 0px;
		width: 750px;
	}
	#gallery ul { list-style: none; }
	#gallery ul li { display: inline; }
	#gallery ul img {
		border: 5px solid #A5BDF7;
		border-width: 5px 5px 20px;
	}
	#gallery ul a:hover img {
		border: 5px solid #A07EF6;
		border-width: 5px 5px 20px;
		color: #598BED;
	}
	#gallery ul a:hover { color: #A07EF6; }
	
	html { overflow-x:hidden; }
	</style>
	
	 <script type="text/javascript">
    	$(document).ready(function(){
    		$("#header").css("display","none");
    		$("#header").fadeIn(7000);
    		
    		$("h3").css("background-color","#A5BDF7").css("width","166px").css("height","25px")
    		.css("text-align","center").css("padding-top","15px");
    		$("h3").mouseover(function(){
    			$(this).css("background-color","#598BED");
    		}).mouseout(function(){
    			$(this).css("background-color","#A5BDF7");
    		});
    		$("category").mouseover(function(){
    			$(this).css("background-color","#598BED");
    		}).mouseout(function(){
    			$(this).css("background-color","#A5BDF7");
    		});

    		$(function() {
    	        $('#gallery a').lightBox();
    	    });
    		$("#uploadpage").click(function () {
    			$('.theme-popover-mask').fadeIn(100);
    			$('.theme-popover').slideDown(200);
    		});
    		$('.theme-poptit .close').click(function(){
    			$('.theme-popover-mask').fadeOut(100);
    			$('.theme-popover').slideUp(200);
    		});
    	});


    	
    </script>
  </head>
  
  <body>
    	<div class ="content" id="gallery">
				<!-- 
				<div style="padding-top: 3px;padding-left: 3px; float: left">			
					<a href="front2/userimage/11.jpg" title="美图"> <img alt="" src="front2/front2/userimage//11.jpg" width="70Px" height="72px"> </a>
				</div>
				<div style="padding-top: 3px;padding-left: 3px; float: left">
					<a href="front2/userimage/22.jpg" title="美图"> <img alt="" src="front2/userimage/22.jpg" width="70Px" height="72px"> </a>
				</div>
				<div style="padding-top: 3px;padding-left: 3px; float: left">
					<a href="front2/userimage/33.jpg" title="美图"> <img alt="" src="front2/userimage/33.jpg" width="70Px" height="72px"> </a>
				</div>
				<div style="padding-top: 3px;padding-left: 3px; float: left">
					<a href="front2/userimage/44.jpg" title="美图"> <img alt="" src="front2/userimage/44.jpg" width="70Px" height="72px"> </a>
				</div>
				<div style="padding-top: 3px;padding-left: 3px; float: left">
					<a href="front2/userimage/55.jpg" title="美图"> <img alt="" src="front2/userimage/55.jpg" width="70Px" height="72px"> </a>
				</div>
				
				 -->
				<ul>
			        <li>
			            <a href="front2/userimage/111.jpg" >
			                <img src="front2/userimage/111.jpg" width="72" height="72" alt="" />
			            </a>
			        </li>
			        <li>
			            <a href="front2/userimage/222.jpg" >
			                <img src="front2/userimage/222.jpg" width="72" height="72" alt="" />
			            </a>
			        </li>
			        <li>
			            <a href="front2/userimage/333.jpg" >
			                <img src="front2/userimage/333.jpg" width="72" height="72" alt="" />
			            </a>
			        </li>
			        <li>
			            <a href="front2/userimage/444.jpg" >
			                <img src="front2/userimage/444.jpg" width="72" height="72" alt="" />
			            </a>
			        </li>
			        <li>
			            <a href="front2/userimage/555.jpg" >
			                <img src="front2/userimage/555.jpg" width="72" height="72" alt="" />
			            </a>
			        </li>
			    </ul>
		</div>
		
  </body>
</html>
