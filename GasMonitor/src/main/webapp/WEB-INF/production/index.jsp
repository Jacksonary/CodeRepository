<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getServerName() + ":" + request.getServerPort() + path + "/";
	String basePath2 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<!-- 在IE运行最新的渲染模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 初始化移动浏览显示  -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=5yko95XjkosWvgjmm6IDxeMMSGwgiqTS"></script>


<title>主页</title>

<!-- Bootstrap -->
<link href="${path }/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="${path }/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="${path }/vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- iCheck -->
<link href="${path }/vendors/iCheck/skins/flat/green.css"
	rel="stylesheet">

<!-- bootstrap-progressbar -->
<link
	href="${path }/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css"
	rel="stylesheet">
<!-- JQVMap -->
<link href="${path }/vendors/jqvmap/dist/jqvmap.min.css"
	rel="stylesheet" />
<!-- bootstrap-daterangepicker -->
<link
	href="${path }/vendors/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="${path }/build/css/custom.min.css" rel="stylesheet">

<!--引入echart插件  -->
<script src="${path }/build/js/echarts.js"></script>
<script type="text/javascript" src="${path }/build/js/echarts-gl.js"></script>
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="index.html" class="site_title"><i class="fa fa-paw"></i>
							<span>实时监测系统</span></a>
					</div>

					<div class="clearfix"></div>

					<!-- menu profile quick info -->
					<div class="profile clearfix">
						<div class="profile_pic">
							<img src="${path }/images/img.jpg" alt="..."
								class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>欢迎您,</span>
							<h2>${loginUser.fCaption }</h2>
						</div>
					</div>
					<!-- /menu profile quick info -->

					<br />

					<!-- 左侧菜单栏 -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<h3>总览</h3>
							<ul class="nav side-menu">

								<li><a href="${path }/index" class="a_post"><i class="fa fa-home"></i> 主页 </a></li>

								<li><a><i class="fa fa-edit"></i> 表单<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="form.html">General Form</a></li>
										<li><a href="form_advanced.html">Advanced Components</a></li>
										<li><a href="form_validation.html">Form Validation</a></li>
										<li><a href="form_wizards.html">Form Wizard</a></li>
										<li><a href="form_upload.html">Form Upload</a></li>
										<li><a href="form_buttons.html">Form Buttons</a></li>
									</ul></li>
								<li><a><i class="fa fa-desktop"></i> 用户界面 <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="general_elements.html">General Elements</a></li>
										<li><a href="media_gallery.html">Media Gallery</a></li>
										<li><a href="typography.html">Typography</a></li>
										<li><a href="icons.html">Icons</a></li>
										<li><a href="glyphicons.html">Glyphicons</a></li>
										<li><a href="widgets.html">Widgets</a></li>
										<li><a href="invoice.html">Invoice</a></li>
										<li><a href="inbox.html">Inbox</a></li>
										<li><a href="calendar.html">Calendar</a></li>
									</ul></li>
								<li><a><i class="fa fa-table"></i> 表格 <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="${path }/index/tables" target="_blank">表单</a></li>
										<li><a href="${path }/index/log" target="_blank">日志记录</a></li>
									</ul></li>
								<li><a><i class="fa fa-bar-chart-o"></i> 数据统计图 <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="chartjs.html">Chart JS</a></li>
										<li><a href="chartjs2.html">Chart JS2</a></li>
										<li><a href="morisjs.html">Moris JS</a></li>
										<li><a href="echarts.html">ECharts</a></li>
										<li><a href="other_charts.html">Other Charts</a></li>
									</ul></li>
								<li><a><i class="fa fa-clone"></i> 布局 <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="fixed_sidebar.html">Fixed Sidebar</a></li>
										<li><a href="fixed_footer.html">Fixed Footer</a></li>
									</ul></li>
							</ul>
						</div>

					</div>
					<!-- /左侧菜单栏 -->

					<!-- /底部菜单栏 -->
					<div class="sidebar-footer hidden-small">
						<a data-toggle="tooltip" data-placement="top" title="登出"
							href="login.jsp"> <span class="glyphicon glyphicon-off"
							aria-hidden="true"></span>
						</a>
					</div>
					<!-- /底部菜单栏 -->
				</div>
			</div>

			<!-- 顶部导航栏 -->
			<div class="top_nav">
				<div class="nav_menu">
					<nav>
					<div class="nav toggle">
						<a id="menu_toggle"><i class="fa fa-bars"></i></a>
					</div>

					<ul class="nav navbar-nav navbar-right">
						<li class=""><a href="javascript:;"
							class="user-profile dropdown-toggle" data-toggle="dropdown"
							aria-expanded="false"> <img src="${path }/images/img.jpg"
								alt="">${loginUser.fCaption }
						</a></li>

					</ul>
					</nav>
				</div>
			</div>
			<!-- /顶部导航栏 -->

			<!-- 顶部数据 -->
			<div class="right_col" role="main">
				<!-- top tiles -->
				<div class="row tile_count">
					<div class="col-md-12 col-sm-4 col-xs-6 tile_stats_count",style="float:left"">
						<iframe width="650" scrolling="no" height="50" frameborder="0"
							allowtransparency="true"
							src="http://i.tianqi.com/index.php?c=code&id=12&icon=2&num=5&site=12"></iframe>
					</div>
					
				</div>
				<!-- /顶部数据 -->

				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="dashboard_graph">

							<div class="row x_title">
								<div class="col-md-6">
									<h3>
										管道情况 <small>各项指标参数</small>
									</h3>
								</div>
							</div>

							<div class="col-md-9 col-sm-9 col-xs-12">
								<!-- 为图表准备一个具备大小（宽高）的Dom -->
								<div id="main" style="min-width: 400px; height: 450px"></div>

							</div>

							<!-- 旁边的补充说明内容 -->
							<div class="col-md-3 col-sm-3 col-xs-12 bg-white">
								<div class="x_title">
									<h2>参数监控</h2>
									<div class="clearfix"></div>
								</div>

								<div style="height:25px"></div>
								
								<div class="col-md-12 col-sm-12 col-xs-6">
									<p>
										一级入口压力&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
											id="inletPressures"></span>MPa
									</p>
									<div class="">
										<div class="progress progress_sm" style="width: 76%;">
											<!-- 这个参数才是进度条的所占比例显示的部分 -->
											<div id="inletPressuresProgress" class="" role="progressbar"
												data-transitiongoal=""></div>
										</div>
									</div>

									<p>
										一级出口压力&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
											id="outletPressures"></span>MPa
									</p>
									<div class="">
										<div class="progress progress_sm" style="width: 76%;">
											<div id="outletPressuresProgress" class="progress-bar bg-red"
												role="progressbar" data-transitiongoal="80"></div>
										</div>
									</div>
									
									
									<p>
										二级入口压力&nbsp;&nbsp;&nbsp;<span id=inletPressures2></span>MPa
									</p>
									<div class="">
										<div class="progress progress_sm" style="width: 76%;">
											<div id="inletPressuresProgress2" class="progress-bar bg-red"
												role="progressbar" data-transitiongoal="80"></div>
										</div>
									</div>
									
									<p>
										二级出口压力&nbsp;&nbsp;&nbsp;<span id=outletPressures2></span>MPa
									</p>
									<div class="">
										<div class="progress progress_sm" style="width: 76%;">
											<div id="outletPressuresProgress2" class="progress-bar bg-red"
												role="progressbar" data-transitiongoal="80"></div>
										</div>
									</div>

									<p>
										温&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
											id=temperature></span>°C
									</p>
									<div class="">
										<div class="progress progress_sm" style="width: 76%;">
											<div id="temperatureProgress" class="progress-bar bg-red"
												role="progressbar" data-transitiongoal="80"></div>
										</div>
									</div>

									<p>
										过滤器压差&nbsp;&nbsp;&nbsp;<span id=filterd></span>MPa
									</p>
									<div class="">
										<div class="progress progress_sm" style="width: 76%;">
											<div id="filterdProgress" class="progress-bar bg-red"
												role="progressbar" data-transitiongoal="80"></div>
										</div>
									</div>
									
									<p>
										实&nbsp;&nbsp;&nbsp;时&nbsp;&nbsp;&nbsp;流&nbsp;&nbsp;&nbsp;量&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
											id=flow></span>Nm³/h
									</p>
									<div class="">
										<div class="progress progress_sm" style="width: 76%;">
											<div id="flowProgress" class="progress-bar bg-red"
												role="progressbar" data-transitiongoal="80"></div>
										</div>
									</div>


								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>

				</div>
				<br />
			</div>

			<!-- 报警器位置，这里去除了controls关键字，表示不向用户展示控件界面,而且在用户进入页面后语音已经提前加载完毕 
			<!-- 改进的后的语音 -->
			<!-- 报警器位置，这里去除了controls关键字，表示不向用户展示控件界面,而且在用户进入页面后语音已经提前加载完毕 -->
			<!-- 一级入口压力低压报警 -->
			<audio id="audio1" preload="load"> 
				<source src="${path }/warning/audio1.mp3" type="audio/mpeg" />
			</audio>
			<!-- 一级入口压力高压报警 -->
			<audio id="audio2" preload="load"> 
				<source src="${path }/warning/audio2.mp3" type="audio/mpeg" />
			</audio>
			<!-- 一级出口压力低压报警 -->
			<audio id="audio3" preload="load"> 
				<source src="${path }/warning/audio3.mp3" type="audio/mpeg" />
			</audio>
			<!-- 一级出口压力高压报警 -->
			<audio id="audio4" preload="load"> 
				<source src="${path }/warning/audio4.mp3" type="audio/mpeg" />
			</audio>
			<!-- 二级入口压力低压报警 -->
			<audio id="audio5" preload="load"> 
				<source src="${path }/warning/audio5.mp3" type="audio/mpeg" />
			</audio>
			<!-- 二级入口压力高压报警 -->
			<audio id="audio6" preload="load"> 
				<source src="${path }/warning/audio6.mp3" type="audio/mpeg" />
			</audio>
			<!-- 二级出口压力低压报警 -->
			<audio id="audio7" preload="load"> 
				<source src="${path }/warning/audio7.mp3" type="audio/mpeg" />
			</audio>
			<!-- 二级出口压力高压报警 -->
			<audio id="audio8" preload="load"> 
				<source src="${path }/warning/audio8.mp3" type="audio/mpeg" />
			</audio>
			<!-- 低温压报警 -->
			<audio id="audio9" preload="load"> 
				<source src="${path }/warning/audio9.mp3" type="audio/mpeg" />
			</audio>
			<!-- 高温低压报警 -->
			<audio id="audio10" preload="load"> 
				<source src="${path }/warning/audio10.mp3" type="audio/mpeg" />
			</audio>
			<!-- 高压差报警 -->
			<audio id="audio11" preload="load"> 
				<source src="${path }/warning/audio11.mp3" type="audio/mpeg" />
			</audio>
			

			<!--  百度地图插件
        <div class="col-md-12 col-sm-12 col-xs-6">
        <div>
          <div id="allmap" style="min-width:400px;height:400px"></div>
        </div>
        </div>
        -->
			<!-- /page content -->

			<!-- footer content -->
			<footer>
			<div class="pull-right">河海大学版权所有</div>
			<div class="clearfix"></div>
			</footer>
			<!-- /footer content -->
		</div>
	</div>

	<!-- jQuery -->
	<script src="${path }/vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="${path }/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="${path }/vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="${path }/vendors/nprogress/nprogress.js"></script>
	<!-- Chart.js -->
	<script src="${path }/vendors/Chart.js/dist/Chart.min.js"></script>
	<!-- gauge.js -->
	<script src="${path }/vendors/gauge.js/dist/gauge.min.js"></script>
	<!-- bootstrap-progressbar -->
	<script
		src="${path }/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
	<!-- iCheck -->
	<script src="${path }/vendors/iCheck/icheck.min.js"></script>
	<!-- Skycons -->
	<script src="${path }/vendors/skycons/skycons.js"></script>
	<!-- Flot -->
	<script src="${path }/vendors/Flot/jquery.flot.js"></script>
	<script src="${path }/vendors/Flot/jquery.flot.pie.js"></script>
	<script src="${path }/vendors/Flot/jquery.flot.time.js"></script>
	<script src="${path }/vendors/Flot/jquery.flot.stack.js"></script>
	<script src="${path }/vendors/Flot/jquery.flot.resize.js"></script>
	<!-- Flot plugins -->
	<script
		src="${path }/vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
	<script src="${path }/vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
	<script src="${path }/vendors/flot.curvedlines/curvedLines.js"></script>
	<!-- DateJS -->
	<script src="${path }/vendors/DateJS/build/date.js"></script>
	<!-- JQVMap -->
	<script src="${path }/vendors/jqvmap/dist/jquery.vmap.js"></script>
	<script src="${path }/vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
	<script
		src="${path }/vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
	<!-- bootstrap-daterangepicker -->
	<script src="${path }/vendors/moment/min/moment.min.js"></script>
	<script
		src="${path }/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
	<!-- 引入highcharts的插件 -->
	<script type="text/javascript" src="${path }/build/js/highcharts.js"></script>
	<script type="text/javascript" src="${path }/build/js/highcharts-more.js"></script>
	<!-- 引入导出功能的插件 -->
	<script type="text/javascript" src="${path }/build/js/exporting.js"></script>

	<script>
	$(".a_post").on("click",function(event){
	    event.preventDefault();//使a自带的方法失效，即无法调整到href中的URL(http://www.baidu.com)
	    $.ajax({
	           type: "POST",
	           url: "${path }/index",
	           data: "username=${loginUser.fCode },password=${loginUser.fPwd }",
	    });
	});
	</script>

	<!-- Custom Theme Scripts -->
	<script src="${path }/build/js/custom.min.js"></script>

	<!-- 百度地图的调用 
    <script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,12);

	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var mk = new BMap.Marker(r.point);
			map.addOverlay(mk);
			map.panTo(r.point);
		}
		else {
			alert('获取当前位置失败');
		}        
	},{enableHighAccuracy: true})
</script>
-->

	<!-- websocket的配置 -->
	<script>
	//打开websocket和服务端的接口
	var path = '<%=basePath%>';

		var websocket;
		if ('WebSocket' in window) {
			websocket = new WebSocket("ws://" + path + "/ws");
		} else if ('MozWebSocket' in window) {
			websocket = new MozWebSocket("ws://" + path + "/ws" + uid);
		} else {
			websocket = new SockJS("http://" + path + "/ws/sockjs" + uid);
		}

		//请求后获取数据,以便后面使用
		var mydata = null;

		var mychart = null;

		Highcharts.setOptions({
			global : {//横轴为时间轴，下面的语句是设置时区
				useUTC : false
			}
		});

		function activeLastPointToolip(chart) {
			var points = chart.series[0].points;
			chart.tooltip.refresh(points[points.length - 1]);
		}

		var options = {
			chart : {
				type : 'spline',
				renderTo : 'main',
				animation : Highcharts.svg,
				marginRight : 10,
				events : {
					load : function() {//load表示的是在图表加载完时触发
						var series = this.series[0], chart = this;
					}
				}
			},

			lang : {//汉化菜单栏
				printChart : "打印图表",
				downloadJPEG : "下载JPEG图片",
				downloadPDF : "下载PDF文档",
				downloadPNG : "下载PNG图片",
				downloadSVG : "下载SVG矢量图",
				contextButtonTitle : "导出菜单"
			},

			title : {
				text : '燃气管道实时数据'
			},

			xAxis : {
				type : 'datetime',
				tickPixelInterval : 150
			//设置刻度间隔 。该参数对分类坐标轴无效。对于 Y 轴，其默认值是72，X 轴则是 100
			},

			yAxis : {
				title : {
					align : 'high',
					offset : 40,
					text : '压力(MPa)',
					rotation : 0
				},
				plotLines : [ {
					value : 0,
					width : 1,
					color : '#808080'
				} ]
			},

			tooltip : {
				formatter : function() {
					return '<b>'
							+ this.series.name
							+ '</b><br/>'
							+ Highcharts
									.dateFormat('%Y-%m-%d %H:%M:%S', this.x)
							+ '<br/>' + Highcharts.numberFormat(this.y, 4);//保留4位小数
				}
			},

			legend : {//图列配置,每张表只能存在一个图例
				// enabled: false 
				layout : 'horizontal', //图例的排列方式(多个图例才能看到效果)，垂直：vertical，水平：horizontal
				align : 'center',
				verticalAlign : 'bottom',
				borderWidth : 0
			},

			exporting : {//是否提供导出功能，相关菜单栏的汉化在最上面的全局配置中
				enabled : true
			},

			credits : {//配置右下角的版权信息
				enabled : true,//是否显示版权信息，默认：true。若想去除图表右下角highcharts官网链接则设置为false。
				text : "www.hhu.edu.cn",//版权信息显示内容，默认：Highcharts.com。
				href : "http://www.hhu.edu.cn/"//版权信息链接地址，默认：http://www.highcharts.com。
			},

			series : [ {
				id : 'series-1',
				name : '一级入口压力',
				data : (function() {
					//console.info("mydata=" + mydata);
					var data = [], time = (new Date()).getTime(), i;
					for (i = -19; i <= 0; i += 1) {//这里是限制图表中最多显示20个点
						data.push({
							x : time + i * 1000,
							y : mydata
						});
					}
					return data;
				}())
			}, {
				id : 'series-2',
				name : '一级出口压力',
				data : (function() {
					//console.info("mydata=" + mydata);
					// generate an array of random data
					var data = [], time = (new Date()).getTime(), i;
					for (i = -19; i <= 0; i += 1) {//这里是限制图表中最多显示20个点
						data.push({
							x : time + i * 1000,
							y : mydata
						});
					}
					return data;
				}())
			} ]
		}

		//图表初始化
		mychart = new Highcharts.chart(options, function(c) {
			activeLastPointToolip(c)
		});

		websocket.onopen = function(event) {
			console.log("WebSocket:已连接");
		};

		websocket.onmessage = function(event) {
			var data = event.data;
			//将返回的数据转变成json对象
			var obj = JSON.parse(data);

			var audio1 = document.getElementById("audio1");
			var audio2 = document.getElementById("audio2");
			var audio3 = document.getElementById("audio3");
			var audio4 = document.getElementById("audio4");
			var audio5 = document.getElementById("audio5");
			var audio6 = document.getElementById("audio6");
			var audio7 = document.getElementById("audio7");
			var audio8 = document.getElementById("audio8");
			var audio9 = document.getElementById("audio9");
			var audio10 = document.getElementById("audio10");
			var audio11 = document.getElementById("audio11");

			
			//1.这里进行数据的交互,动态网里面添加进口压力
			var x = (new Date()).getTime(); // current time
			//这里不能直接取，取出来的东西是String类型的，需要转换成数值类型的变量，否则则无法计算
			var y = parseFloat(obj.fX); //表示一级入口压力
			//console.info("x=" + x + "y=" + y);
			//获取highcharts中的series对象
			var series1 = mychart.get('series-1');
			//console.info("获取成功，series的名字为" + series1.name);
			//将数据放入图表中
			series1.addPoint([ x, y ], true, true);
			activeLastPointToolip(mychart);
			//正常报警安全的级别是：蓝色（低级别的报警）、黄色、橙色、红色（高级别的预警）
			$("#inletPressures").html(y.toFixed(4));
			var ippercent = (y / 5.5).toFixed(4) * 100;

			//根据数据动态显示进度条,小数转百分比,js中保留小数点后4位这么写：y.toFixed(4)，y为原来的数
			//这里注意追加progressbar()才能实时刷新，否则不能不能更新数据
			$("#inletPressuresProgress").attr("data-transitiongoal", ippercent).progressbar();

			if (y < 3.6) {
				$("#inletPressuresProgress").attr("class","progress-bar bg-blue");
				//低压语音报警,授权码有效期为一个月，注意更换
				//http://tsn.baidu.com/text2audio?
				//tex=%E4%BD%8E%E5%8E%8B%E6%8A%A5%E8%AD%A6
				//&lan=zh
				//&cuid=08-3E-8E-A3-3B-E5
				//&ctp=1
				//&tok=24.c04cc771cadf25c1f9c0c8542952424f.2592000.1504245724.282335-9959843

				/* $.post("http://localhost:8080/GasMonitor/warning", function(
						data) {
					//先将字符串转json对象
					//调用接口获取的返回值
					alert("调用成功--data.audio:" + data.audio);
				}, "json"); */
				//入口压力低压报警
				//$("#audio1").play();这里必须是jquery支持的方法才能用，这里它不支持play()方法
				 audio1.play();
				 //这里返回的是audio.ended方法返回的是boolean类型的值
				 /* if(audio1.ended==false) {
				 console.info("audio的正在播放....");
				 } */
			} else if (y >= 3.6 && y <= 5) {//这里是正常情况
				//改变进度条的颜色
				$("#inletPressuresProgress").attr("class","progress-bar bg-green");
			} else if (y > 5 && y <= 5.5) {//即将超限，5是标定的数值
				$("#inletPressuresProgress").attr("class","progress-bar bg-orange");
			} else {//这里是最高级别的报警
				//入口压力超限报警
				audio2.play();
				//改变进度条的颜色
				$("#inletPressuresProgress").attr("class","progress-bar bg-red");
			}

			//2.这里进行数据的交互,动态网里面添加出口压力
			//这里不能直接取，取出来的东西是String类型的，需要转换成数值类型的变量，否则则无法计算
			var y1 = parseFloat(obj.fy);//一级出口压力

			//console.info("x=" + x + "y1=" + y1);
			//获取highcharts中的series对象
			var series2 = mychart.get('series-2');
			//console.info("获取成功，series的名字为" + series2.name);
			//将数据放入图表中
			series2.addPoint([ x, y1 ], true, true);
			activeLastPointToolip(mychart);
			//正常报警安全的级别是：蓝色（低级别的报警）、黄色、橙色、红色（高级别的预警）

			$("#outletPressures").html(y1.toFixed(4));
			var oppercent = (y1 / 4.4).toFixed(4) * 100;
			//根据数据动态显示进度条,小数转百分比,js中保留小数点后4位这么写：y.toFixed(4)，y为原来的数
			//这里注意追加progressbar()才能实时刷新，否则不能不能更新数据
			$("#outletPressuresProgress").attr("data-transitiongoal", oppercent).progressbar();

			if (y1 < 3.6) {//低压情况
				//一级出口低压报警
				if(audio1.ended==true&&audio2.ended==true) {
					audio3.play();
				}
				//改变颜色
				$("#outletPressuresProgress").attr("class","progress-bar bg-blue");
			} else if (y1 >= 3.6 && y1 <= 4.4) {//正常情况
				$("#outletPressuresProgress").attr("class","progress-bar bg-green");
			} else {//这里是最高级别的报警
				//出口高压报警
				if(audio1.ended==true&&audio2.ended==true) {
					audio4.play();
				}
				//改变进度条的颜色
				$("#outletPressuresProgress").attr("class","progress-bar bg-red");
			}


			/*
			* 二级入口和出口的报警
			*/
			 //表示二级入口压力
			var yin2 = parseFloat(obj.fX2);
			//正常报警安全的级别是：蓝色（低级别的报警）、黄色、橙色、红色（高级别的预警）
			$("#inletPressures2").html(yin2.toFixed(4));
			var ippercent2 = (yin2 / 4.4).toFixed(4) * 100;

			//根据数据动态显示进度条,小数转百分比,js中保留小数点后4位这么写：y.toFixed(4)，y为原来的数
			//这里注意追加progressbar()才能实时刷新，否则不能不能更新数据
			$("#inletPressuresProgress2").attr("data-transitiongoal", ippercent2).progressbar();

			if (yin2 < 3.6) {
				if(audio1.ended==true&&audio2.ended==true&&audio3.ended==true&&audio4.ended==true) {
					audio5.play();
				}
				$("#inletPressuresProgress2").attr("class","progress-bar bg-blue");
			} else if (yin2 >= 3.6 && yin2 <= 4.4) {//这里是正常情况
				//console.info("3.6<=" + yin2 + "<=4.4");
				//改变进度条的颜色
				$("#inletPressuresProgress2").attr("class","progress-bar bg-green");
			} else {//这里是最高级别的报警
				//console.info(yin2 + ">4.4");
				//$("#inletPressures").html("<font color=red>" + y.toFixed(4) + "</font>");
				//改变进度条的颜色
				$("#inletPressuresProgress2").attr("class","progress-bar bg-red");
				if(audio1.ended==true&&audio2.ended==true&&audio3.ended==true&&audio4.ended==true) {
					//入口压力超限报警
					audio6.play();
				}
			}

			//二级出口压力
			var you2 = parseFloat(obj.fy2);
			$("#outletPressures2").html(you2.toFixed(4));
			var oppercent2 = (you2 / 0.48).toFixed(4) * 100;
			//根据数据动态显示进度条,小数转百分比,js中保留小数点后4位这么写：y.toFixed(4)，y为原来的数
			//这里注意追加progressbar()才能实时刷新，否则不能不能更新数据
			$("#outletPressuresProgress2").attr("data-transitiongoal", oppercent2).progressbar();
			if (you2 < 0.36) {//低压情况
				//改变颜色
				$("#outletPressuresProgress2").attr("class","progress-bar bg-blue");
				//二级出口低压报警
				if(audio1.ended==true&&audio2.ended==true&&audio3.ended==true&&audio4.ended==true&&audio5.ended==true&&audio6.ended==true) {
					audio7.play();
				}
			} else if (you2 >= 0.36 && you2 <= 0.48) {//正常情况
				$("#outletPressuresProgress2").attr("class","progress-bar bg-green");
			} else {//这里是最高级别的报警
				//改变进度条的颜色
				$("#outletPressuresProgress2").attr("class","progress-bar bg-red");
				//出口高压报警
				if(audio1.ended==true&&audio2.ended==true&&audio3.ended==true&&audio4.ended==true&&audio5.ended==true&&audio6.ended==true) {
					audio8.play();
				}
			}

			

			//3.这里进行数据的交互,动态网里面添加温度
			//这里不能直接取，取出来的东西是String类型的，需要转换成数值类型的变量，否则则无法计算
			var y2 = parseFloat(obj.fs0);

			//console.info("x=" + x + "y2=" + y1);

			//正常报警安全的级别是：蓝色（低级别的报警）、黄色、橙色、红色（高级别的预警）

			$("#temperature").html(y2);
			var tppercent = ((y2 + 20) / 80).toFixed(4) * 100;
			//根据数据动态显示进度条,小数转百分比,js中保留小数点后4位这么写：y.toFixed(4)，y为原来的数
			//这里注意追加progressbar()才能实时刷新，否则不能不能更新数据
			$("#temperatureProgress").attr("data-transitiongoal", tppercent).progressbar();

			if (y2 < -20) {//低压情况
				//低温报警
				if(audio1.ended==true&&audio2.ended==true&&audio3.ended==true&&audio4.ended==true&&
					audio5.ended==true&&audio6.ended==true&&audio7.ended==true&&audio8.ended==true) {
					audio9.play();
				}
				$("#temperaturePressuresProgress").attr("class","progress-bar bg-blue");
			} else if (y2 >= -20 && y2 <= 60) {//正常情况
				$("#temperatureProgress").attr("class", "progress-bar bg-green");
			} else {//这里是最高级别的报警
				//高温报警
				if(audio1.ended==true&&audio2.ended==true&&audio3.ended==true&&audio4.ended==true&&
					audio5.ended==true&&audio6.ended==true&&audio7.ended==true&&audio8.ended==true) {
					audio10.play();
				}
				//改变进度条的颜色
				$("#temperatureProgress").attr("class", "progress-bar bg-red");
			}

			//4.这里进行数据的交互,动态网里面添加流量数据
			//这里不能直接取，取出来的东西是String类型的，需要转换成数值类型的变量，否则则无法计算
			var y3 = parseFloat(obj.fs1);

			//正常报警安全的级别是：蓝色（低级别的报警）、黄色、橙色、红色（高级别的预警）
			$("#flow").html(y3);
			var fpercent = (y3 / 60000).toFixed(4) * 100;
			//根据数据动态显示进度条,小数转百分比,js中保留小数点后4位这么写：y.toFixed(4)，y为原来的数
			//这里注意追加progressbar()才能实时刷新，否则不能不能更新数据
			$("#flowProgress").attr("data-transitiongoal", fpercent).progressbar();

			if (y3 < 0) {//低压情况
				$("#flowProgress").attr("class", "progress-bar bg-blue");
			} else if (y3 >= 0 && y3 <= 60000) {//正常情况
				$("#flowProgress").attr("class", "progress-bar bg-green");
			} else {//这里是最高级别的报警
				//改变进度条的颜色
				$("#flowProgress").attr("class", "progress-bar bg-red");
			}

			//5.这里进行数据的交互,动态网里面添加压差数据
			//这里不能直接取，取出来的东西是String类型的，需要转换成数值类型的变量，否则则无法计算
			var y4 = parseFloat(obj.fs2);

			//console.info("x=" + x + "y4=" + y4);

			//正常报警安全的级别是：蓝色（低级别的报警）、黄色、橙色、红色（高级别的预警）

			$("#filterd").html(y4);
			var fdpercent = (y4 / 100).toFixed(4) * 100;
			//根据数据动态显示进度条,小数转百分比,js中保留小数点后4位这么写：y.toFixed(4)，y为原来的数
			//这里注意追加progressbar()才能实时刷新，否则不能不能更新数据
			$("#filterdProgress").attr("data-transitiongoal", fdpercent).progressbar();

			if (y4 < 10) {//低压情况
				//低压差报警
				$("#filterProgress").attr("class", "progress-bar bg-blue");
			} else if (y4 >= 10 && y4 <= 100) {//正常情况
				$("#filterdProgress").attr("class", "progress-bar bg-green");
			} else {//这里是最高级别的报警
				//高压差报警
				if(audio1.ended==true&&audio2.ended==true&&audio3.ended==true&&audio4.ended==true&&audio5.ended==true&&audio6.ended==true&&
					audio7.ended==true&&audio8.ended==true&&audio9.ended==true&&audio10.ended==true) {
					audio11.play();
				}
				//改变进度条的颜色
				$("#filterdProgress").attr("class", "progress-bar bg-red");
			}

		};

		websocket.onerror = function(event) {
			console.log("WebSocket:发生错误 ");
			console.log(event);
		};
		websocket.onclose = function(event) {
			console.log("WebSocket:已关闭");
			console.log(event);
		}
	</script>
	
</body>
</html>