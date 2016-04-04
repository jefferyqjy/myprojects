<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>零件销售出库统计图</title>
<link href="Style/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<table width="1200" border="0" align="center" height="100%"
		style="border-left: 1px solid #7BD676; border-right: 1px solid #7BD676; border-top: 1px solid #7bd676; border-bottom: 1px solid #7BD676;">
		<tr height="120">
			<td colspan="2"><jsp:include flush="true" page="/top.jsp" /></td>
		</tr>
		<tr>
			<td width="150" valign="top"><jsp:include flush="true"
					page="/left.jsp" /></td>
			<td width="1050" align="center" valign="top">
				<table width="1050" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" valign="top" height="390">
							<table width="1050" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="30">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr align="center" style="font-size: 25px;">
												<td width="15" height="30"><img src="img/tab_03.gif"
													width="15" height="30" /></td>
												<td width="1101" background="img/tab_05.gif"></td>
												<td width="14"><img src="img/tab_07.gif" width="14"
													height="30" /></td>
											</tr>
										</table>
									</td>
								</tr>

								<tr>
									<td><table width="100%" border="0" cellspacing="0"
											cellpadding="0">
											<tr>
												<td width="9" background="img/tab_12.gif"></td>
												<td bgcolor="#f3ffe3" height="390" valign="top"
													align="center">
													<table border="0" width="100%" height="380">
														<tr>
															<td valign="top">
																<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
																<div style="margin: auto; text-align: center;">
																	零件销售出库统计图
																	<div style="padding: 10px;">
																		<br /> 起始时间： <input id="beginMonth" type="text"
																			onFocus="WdatePicker()" /> 结束时间： <input
																			id="endMonth" type="text" onFocus="WdatePicker()" />
																		<input value="查询" type="button"
																			onclick="show();display();" /> <br />
																	</div>
																	<div id="main" style="height: 300px; width: 980px;"></div>
																</div>
															</td>
														</tr>
													</table>
												</td>
												<td width="9" background="img/tab_16.gif"></td>
											</tr>
										</table></td>
								</tr>

								<tr>
									<td height="30"><table width="100%" border="0"
											cellspacing="0" cellpadding="0">
											<tr>
												<td width="15" height="30"><img src="img/tab_20.gif"
													width="15" height="30" /></td>
												<td width="1101" background="img/tab_21.gif"></td>
												<td width="14"><img src="img/tab_22.gif" width="14"
													height="30" /></td>
											</tr>
										</table></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height="24">
			<td colspan="2"><jsp:include flush="true" page="/down.jsp" /></td>
		</tr>
	</table>
	<!-- ECharts单文件引入 -->
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/esl.js"></script>
	<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		var resx = [];
		var resy = [];
		var time = [];
		var mySeries = [];
		function show() {
			resx = [];
			resy = [];
			time = [];
			mySeries = [];
			var beginMonth = $("#beginMonth").val();
			var endMonth = $("#endMonth").val();

			$
					.ajax({
						url : "PartssBill",
						type : "post",
						async : false, //同步执行
						data : {
							"beginMonth" : beginMonth,
							"endMonth" : endMonth
						},
						dataType : "json", //返回数据形式为json  
						success : function(data) {
							if (data) {
								var name = {};
								var byTime = {};
								var numsByName = {};
								for ( var i = 0; i < data.length; i++) {
									if (!byTime[data[i].ssdate]) {
										byTime[data[i].ssdate] = [];
									}
									byTime[data[i].ssdate][data[i].partname] = data[i].partssnum;
									if (!name[data[i].partname]) {
										name[data[i].partname] = 0;
									}
									name[data[i].partname] += data[i].partssnum;
								}
								for ( var n in name) {
									numsByName[n] = [];
									for ( var t in byTime) {
										numsByName[n].push(0);
									}
								}
								var x = 0;
								for ( var t in byTime) {
									time.push(t);
									for ( var n in byTime[t]) {
										numsByName[n][x] = byTime[t][n];
									}
									x++;
								}
								for ( var n in numsByName) {
									mySeries.push({
										"name" : n,
										"type" : "bar",
										"stack" : "总量",
										"data" : numsByName[n]
									})
								}
								display();
							}
						},
						error : function(res, err) {
							alert(" 出错了");
						}
					})
		}

		function display() {
			// 路径配置
			require.config({
				paths : {
					echarts : './js/echarts'
				}
			});

			// 使用
			require([ 'echarts', 'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
			'echarts/chart/line' ], function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('main'));

				var option = {
					tooltip : {
						trigger : 'axis',
					},
					legend : {
						data : [ '销售出库一览表' ]
					},
					toolbox : {
						show : true,
						feature : {
							mark : {
								show : true
							},
							dataView : {
								show : true,
								readOnly : false
							},
							magicType : {
								show : true,
								type : [ 'line', 'bar' ]
							},
							restore : {
								show : true
							},
							saveAsImage : {
								show : true
							}
						}
					},
					xAxis : [ {
						axisLabel : {
							rotate : 45,//横坐标倾斜度
						},
						type : 'category',
						data : time
					} ],
					yAxis : [ {
						type : 'value'
					} ],
					series : mySeries
				};

				// 为echarts对象加载数据 
				myChart.setOption(option);
			});
		}
		show();
		display();
	</script>
</body>
</html>