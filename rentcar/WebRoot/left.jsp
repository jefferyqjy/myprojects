<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div id="category_tree">
	<div class="tit">
		所有车辆分类
	</div>
	<dl class="clearfix">
		<div class="box1 cate" id="cate">
			<s:iterator value="map.cateList">
				<h1>
					<a href="index/cate.action?name=${cateid }" class="f_l">${catename}</a>
				</h1>
			</s:iterator>
			<div style="clear: both"></div>
		</div>
	</dl>
</div>
<div class="blank5"></div>
<div class="box" id='history_div'>
	<div class="box_1">
		<h3>
			<span>推荐车辆</span>
		</h3>
		<div class="boxCenterList clearfix" id='history_list'>
			<s:iterator value="map.hotList">
				<ul class="clearfix">
					<li class="goodsimg">
						<a href="index/detail.action?id=${carsid }" target="_blank"><img
								src="${image }" alt="${carno }" class="B_blue" /> </a>
					</li>
					<li>
						<a href="index/detail.action?id=${carsid }" target="_blank"
							title="${carno }">${carno }</a><br />
						出租价：
						<font class="f1">￥${price }元</font>
						<br />
					</li>
				</ul>
			</s:iterator>
		</div>
	</div>
</div>
<div class="blank5"></div>