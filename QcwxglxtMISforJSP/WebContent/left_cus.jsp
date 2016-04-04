<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*,java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<style type="text/css">
body{margin:0;padding:0;overflow-x:hidden;}
html, body{height:100%;}
img{border:none;}
dl,dt,dd{display:block;margin:0;}
a{text-decoration:none;}

.container{width:100%;height:100%;margin:auto;}

/*left*/
.leftsidebar_box{width:150px;background-color:#9DFCAF;height:450px;}
.leftsidebar_box dt{padding-left:40px;padding-right:10px;background-repeat:no-repeat;background-position:10px center;color:#000000;font-size:16px;position:relative;line-height:48px;cursor:pointer;}
.leftsidebar_box dd{background-color:#99CCFF;padding-left:40px; height:30px;}
.leftsidebar_box dd a{color:#003399;line-height:40px;}
.leftsidebar_box dt img{position:absolute;right:10px;top:20px;}
.pingjia dt{background-image:url(img/left/system.png)}
.people dt{background-image:url(img/left/custom.png)}
.part dt{background-image:url(img/left/channel.png)}
.arrange dt{background-image:url(img/left/cloud.png)}
.syetem_management dt{background-image:url(img/left/syetem_management.png)}
.caiwu dt{background-image:url(img/left/source.png)}
.statistics dt{background-image:url(img/left/statistics.png)}
.leftsidebar_box dl dd:last-child{padding-bottom:10px;}
</style>


<body id="bg">

	<div class="container">

		<div class="leftsidebar_box">
			<div class="line"></div>
			<dl class="people">
				<dt onClick="changeImage()">
					客户管理<img src="img/left/select_xl01.png">
				</dt>
				<c:if test="${sessionScope.type =='customer'}">
				<dd>
					<a href="CustomerQuery?currentPage=1&&pagerMethod=1&&operator=0">客户管理</a>
				</dd>
				</c:if>
			</dl>
			<dl class="arrange">
				<dt onClick="changeImage()">
					预约<img src="img/left/select_xl01.png">
				</dt>
				<dd class="first_dd">
					<a href="yuyue_cus.jsp" >预约管理</a>
				</dd>
			</dl>
			<dl class="caiwu">
				<dt onClick="changeImage()">
					结账管理<img src="img/left/select_xl01.png">
				</dt>
				<dd class="first_dd">
					<a href="checkouts_cus.jsp">结账管理</a>
				</dd>
			</dl>

			<dl class="syetem_management">
				<dt onClick="changeImage()">
					系统管理<img src="img/left/select_xl01.png">
				</dt>
				<dd class="first_dd">
					<a href="passwordupdate_cus.jsp">修改密码</a>
				</dd>
				<dd>
					<a href="login_cus.jsp" onclick="return confirm('确定要退出系统吗？')">注销登录</a>
				</dd>
			</dl>
		</div>

	</div>

	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		$(".leftsidebar_box dt").css({
			"background-color" : "#9DFCAF"
		});
		$(".leftsidebar_box dt img").attr("src", "img/left/select_xl01.png");
		$(function() {
			$(".leftsidebar_box dd").hide();
			$(".leftsidebar_box dt").click(
					function() {
						$(".leftsidebar_box dt").css({
							"background-color" : "#9DFCAF"
						})
						$(this).css({
							"background-color" : "#9DFCAF"
						});
						$(this).parent().find('dd').removeClass("menu_chioce");
						$(".leftsidebar_box dt img").attr("src",
								"img/left/select_xl01.png");
						$(this).parent().find('img').attr("src",
								"img/left/select_xl.png");
						$(".menu_chioce").slideUp();
						$(this).parent().find('dd').slideToggle();
						$(this).parent().find('dd').addClass("menu_chioce");
					});
		})
	</script>
</body>
</html>