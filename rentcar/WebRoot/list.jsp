<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>${title }</title>
		<link href="themes/ecmoban_benlai/style.css" rel="stylesheet"
			type="text/css" />
		<style type="text/css">
.goodsItem {
	margin-left: 33.5px;
	padding: 10px 2px 15px 0;
}
</style>
		<style type="text/css">
.container,.container * {
	margin: 0;
	padding: 0;
}

.container {
	width: 994px;
	height: 410px;
	overflow: hidden;
	position: relative;
}

.slider {
	position: absolute;
}

.slider li {
	list-style: none;
	display: inline;
}

.slider img {
	width: 994px;
	height: 410px;
	display: block;
}

.slider2 {
	width: 2000px;
}

.slider2 li {
	float: left;
}

.num {
	position: absolute;
	right: 15px;
	bottom: 15px;
}

.num li {
	float: left;
	color: #d71437;
	text-align: center;
	line-height: 16px;
	width: 16px;
	height: 16px;
	font-family: Arial;
	font-size: 12px;
	cursor: pointer;
	overflow: hidden;
	margin: 0 5px;
	background-color: #94CB00;
	border-radius: 8px;
}

.num li.on {
	background-color: #336601;
}
</style>

	</head>

	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<div class="block box">
			<div class="blank"></div>
			<div id="ur_here">
				当前位置:
				<a href="<%=basePath%>">首页</a>
				<code>
					&gt;
				</code>
				车辆列表
			</div>
		</div>
		<div class="blank"></div>
		<div class="block clearfix">

			<div class="AreaL">
				<jsp:include page="left.jsp"></jsp:include>
				<div class="blank"></div>
			</div>


			<div class="AreaR">
				<div class="box">
					<div class="box_1">
						<h3>
							<span>车辆列表</span>
						</h3>
						<div class="clearfix goodsBox"
							style="border: none; padding: 11px 0 10px 0px;">
							<s:iterator value="map.carsList">
								<div class="goodsItem">
									<a href="index/detail.action?id=${carsid }"><img
											src="${image }" alt="${carno }" class="goodsimg" /> </a>
									<br />
									<p class="f1">
										<a href="index/detail.action?name=${carsid }">${carno }</a>
									</p>
									价格
									<font class="f1">￥${price }元</font>
								</div>
							</s:iterator>
						</div>
					</div>
				</div>
				<div class="blank5"></div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>
