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
		<script type="text/javascript">
			function returnCar() {
				var r = confirm("确认申请还车吗？");
				if(r == true) {
					document.getElementById("returnCar").click();					
				}
			}
		</script>
	</head>

	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<div class="block box">
			<div class="blank"></div>
			<div id="ur_here">
				当前位置:
				<a href=".">首页</a>
				<code>
					&gt;
				</code>
				用户资料
			</div>
		</div>
		<div class="blank"></div>

		<div class="blank"></div>
		<div class="block clearfix">

			<div class="AreaL">
				<div class="box">
					<div class="box_1">
						<div class="userCenterBox">
							<jsp:include page="usermenu.jsp"></jsp:include>
						</div>
					</div>
				</div>
			</div>


			<div class="AreaR">
				<div class="box">
					<div class="box_1">
						<div class="userCenterBox boxCenterList clearfix"
							style="_height: 1%;">
							<h5>
								<span>用户资料</span>
							</h5>
							<div class="blank"></div>
							<table width="100%" border="0" cellpadding="5" cellspacing="1"
								bgcolor="#dddddd">
								<tr>
									<th bgcolor="#ffffff">
										订单号
									</th>
									<th bgcolor="#ffffff">
										车辆
									</th>
									<th bgcolor="#ffffff">
										开始日期
									</th>
									<th bgcolor="#ffffff">
										结束日期
									</th>
									<th bgcolor="#ffffff">
										取车地点
									</th>
									<th bgcolor="#ffffff">
										还车地点
									</th>
									<th bgcolor="#ffffff">
										总金额
									</th>
									<th bgcolor="#ffffff">
										预定日期
									</th>
									<th bgcolor="#ffffff">
										订单状态
									</th>
									<th bgcolor="#ffffff">
										操作
									</th>
								</tr>
								<s:iterator value="map.orderList" id="order">
									<tr>
										<td bgcolor="#ffffff" align="center">
											<s:property value="ordercode" />
										</td>
										<td align="center" bgcolor="#ffffff">
											<s:property value="cars.carno" />
										</td>
										<td bgcolor="#ffffff" align="center">
											<s:property value="thestart" />
										</td>
										<td align="center" bgcolor="#ffffff">
											<s:property value="theend" />
										</td>
										<td bgcolor="#ffffff" align="center">
											<s:property value="place" />
										</td>
										<td bgcolor="#ffffff" align="center">
											<s:property value="address" />
										</td>
										<td bgcolor="#ffffff" align="center">
											<s:property value="money" />
										</td>
										<td bgcolor="#ffffff" align="center">
											<s:property value="addtime" />
										</td>
										<td align="center" bgcolor="#ffffff">
											<s:property value="status" />
										</td>
										<td align="center" bgcolor="#ffffff">
											<span class="STYLE1">
											<s:if test="#order.status == '已预约'">
												<a onclick="returnCar()" style="cursor:pointer;color:red">还车</a>
												<a style="display:none" id="returnCar" href="index/applyReturn.action?id=${ordercode }" >returnCar</a>
											</s:if>
											<s:else>
												--
											</s:else>
											</span>
										</td>
									</tr>
								</s:iterator>
							</table>
							<div class="blank5"></div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<div class="blank"></div>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>
