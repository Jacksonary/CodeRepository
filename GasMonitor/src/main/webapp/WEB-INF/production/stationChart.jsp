<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="path"
	scope="page" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>表单</title>

<!-- Bootstrap -->
<link href="../vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="../vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- iCheck -->
<link href="../vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- Datatables -->
<link
	href="../vendors/datatables.net-bs/css/dataTables.bootstrap.min.css"
	rel="stylesheet">
<link
	href="../vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css"
	rel="stylesheet">
<link
	href="../vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css"
	rel="stylesheet">
<link
	href="../vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css"
	rel="stylesheet">
<link
	href="../vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="../build/css/custom.min.css" rel="stylesheet">

<!-- 动态制图的插件样式 -->
<link rel="stylesheet" type="text/css" href="../build/css/hsd.css?d3fa1" />
<link rel="stylesheet" type="text/css" href="../build/css/finance.css?d3fa1" />
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


								<li><a href="${path }/index" class="a_post"><i
										class="fa fa-home"></i> 主页 </a></li>


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
										<li><a href="${path }/index/tables">表单</a></li>
										<li><a href="${path }/index/getChart">图表</a></li>
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
						<div class="menu_section">
							<h3>Live On</h3>
							<ul class="nav side-menu">
								<li><a><i class="fa fa-bug"></i> Additional Pages <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="e_commerce.html">E-commerce</a></li>
										<li><a href="projects.html">Projects</a></li>
										<li><a href="project_detail.html">Project Detail</a></li>
										<li><a href="contacts.html">Contacts</a></li>
										<li><a href="profile.html">Profile</a></li>
									</ul></li>
								<li><a><i class="fa fa-windows"></i> Extras <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="page_403.html">403 Error</a></li>
										<li><a href="page_404.html">404 Error</a></li>
										<li><a href="page_500.html">500 Error</a></li>
										<li><a href="plain_page.html">Plain Page</a></li>
										<li><a href="login.html">Login Page</a></li>
										<li><a href="pricing_tables.html">Pricing Tables</a></li>
									</ul></li>
								<li><a><i class="fa fa-sitemap"></i> Multilevel Menu <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="#level1_1">Level One</a>
										<li><a>Level One<span class="fa fa-chevron-down"></span></a>
											<ul class="nav child_menu">
												<li class="sub_menu"><a href="level2.html">Level
														Two</a></li>
												<li><a href="#level2_1">Level Two</a></li>
												<li><a href="#level2_2">Level Two</a></li>
											</ul></li>
										<li><a href="#level1_2">Level One</a></li>
									</ul></li>
								<li><a href="javascript:void(0)"><i
										class="fa fa-laptop"></i> Landing Page <span
										class="label label-success pull-right">Coming Soon</span></a></li>
							</ul>
						</div>

					</div>
					<!-- /左侧菜单栏 -->


					<!-- /底部菜单栏 -->
					<div class="sidebar-footer hidden-small">
						<a data-toggle="tooltip" data-placement="top" title="设置"> <span
							class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="全屏"> <span
							class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="锁定"> <span
							class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="登出"
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
									alt="">${loginUser.fCaption } <span
									class=" fa fa-angle-down"></span>
							</a>
								<ul class="dropdown-menu dropdown-usermenu pull-right">
									<li><a href="javascript:;">资料</a></li>
									<li><a href="javascript:;"> <span
											class="badge bg-red pull-right">50%</span> <span>设置</span>
									</a></li>
									<li><a href="javascript:;">帮助</a></li>
									<li><a href="login.html"><i
											class="fa fa-sign-out pull-right"></i>登出</a></li>
								</ul></li>
						</ul>
					</nav>
				</div>
			</div>
			<!-- /顶部导航栏 -->

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>
								动态数据<small>制图</small>
							</h3>
						</div>

						<div class="title_right">
							<div
								class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="搜索...">
									<span class="input-group-btn">
										<button class="btn btn-default" type="button">Go!</button>
									</span>
								</div>
							</div>
						</div>
					</div>

					<div class="clearfix"></div>

					<div class="row">


						<div class="clearfix"></div>


						<div class="clearfix"></div>


						<!-- 站点数据表格,Humble Finance插件 -->
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>
										图表<small>具体站点的各项参数随时间的变化情况</small>
									</h2>
									<ul class="nav navbar-right panel_toolbox">
										<li><a class="collapse-link">
										<i class="fa fa-chevron-up"></i></a>
										</li>
										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-expanded="false">
											<i class="fa fa-wrench"></i></a>
											<ul class="dropdown-menu" role="menu">
												<li><a href="#">操作1</a></li>
												<li><a href="#">操作2</a></li>
											</ul>
										</li>
										<li><a class="close-link"><i class="fa fa-close"></i></a></li>
									</ul>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<p class="text-muted font-13 m-b-30">站点情况的动态显示</p>
									
									
									
									<div id="content-container">
										<div id="content">
											<div id="finance">
												<div id="labels">
													<div id="financeTitle">NASDAQ:GOOG</div>
													<div id="time">
														<a onclick="HumbleFinance.zoom(5);">1w</a>
														<a onclick="HumbleFinance.zoom(21);">1m</a>
														<a onclick="HumbleFinance.zoom(65);">3m</a>
														<a onclick="HumbleFinance.zoom(127);">6m</a>
														<a onclick="HumbleFinance.zoom(254);">1y</a>
														<a onclick="HumbleFinance.zoom(1265);">5y</a> </div>
														<div id="dateRange"></div>
												</div>
											</div>
										</div>
									</div>
									
									
									
								</div>
							</div>
						</div>
						
						
						
						
						
						
						

					</div>
				</div>
			</div>
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
	<script src="../vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="../vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="../vendors/nprogress/nprogress.js"></script>
	<!-- iCheck -->
	<script src="../vendors/iCheck/icheck.min.js"></script>
	<!-- Datatables -->
	<script src="../vendors/datatables.net/js/jquery.dataTables.min.js"></script>
	<script
		src="../vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
	<script
		src="../vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="../vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
	<script src="../vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
	<script src="../vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
	<script src="../vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
	<script
		src="../vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
	<script
		src="../vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
	<script
		src="../vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="../vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
	<script
		src="../vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
	<script src="../vendors/jszip/dist/jszip.min.js"></script>
	<script src="../vendors/pdfmake/build/pdfmake.min.js"></script>
	<script src="../vendors/pdfmake/build/vfs_fonts.js"></script>

	<!-- Custom Theme Scripts -->
	<script src="../build/js/custom.min.js"></script>
	
	<!-- 动态制图的js文件 -->
	<script src="../build/js/hsd.js?d3fa1"></script>
	<script src="../build/js/prototype.min.js?d3fa1"></script>
	<script src="../build/js/Finance.js?d3fa1"></script>
	<script src="../build/js/excanvas.js?d3fa1"></script>
	<script src="../build/js/base64.js?d3fa1"></script>
	<script src="../build/js/canvas2image.js?d3fa1"></script>
	<script src="../build/js/canvastext.js?d3fa1"></script>
	<script src="../build/js/flotr.js?d3fa1"></script>
	<script src="../build/js/HumbleFinance.js?d3fa1"></script>
	<script src="../build/js/data.js?d3fa1"></script>
	<script src="../build/js/demo.js?d3fa1"></script>
	
</body>
</html>