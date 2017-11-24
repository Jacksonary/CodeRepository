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

<title>日志记录</title>

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


<link rel="stylesheet" href="${pageContext.request.contextPath }/vendors/jquery-ui-1.12.1/jquery-ui.css">
<!-- Custom Theme Style -->
<link href="../build/css/custom.min.css" rel="stylesheet">
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

								<li><a href="${path }/index" class="a_post"> <i
										class="fa fa-home"></i> 主页
								</a></li>

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
										<li><a href="${path }/index/log">日志记录</a></li>
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
							href="${path }/index/login"> <span
							class="glyphicon glyphicon-off" aria-hidden="true"></span>
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

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">


					<div class="clearfix"></div>

					<div class="row">


						<div class="clearfix"></div>


						<div class="clearfix"></div>

						<!--  <div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>
										站点信息 <small>xxx</small>
									</h2>
									<ul class="nav navbar-right panel_toolbox">
										<li><a class="collapse-link"><i
												class="fa fa-chevron-up"></i></a></li>
										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-expanded="false"><i
												class="fa fa-wrench"></i></a>
											<ul class="dropdown-menu" role="menu">
												<li><a href="#">操作1</a></li>
												<li><a href="#">操作2</a></li>
											</ul></li>
										<li><a class="close-link"><i class="fa fa-close"></i></a>
										</li>
									</ul>
									<div class="clearfix"></div>
								</div>

								<div class="x_content">

									<p>小提示......</p>

									<div class="table-responsive">
										<table class="table table-striped jambo_table bulk_action">
											<thead>
												<tr class="headings">
													<th><input type="checkbox" id="check-all" class="flat">
													</th>
													<th class="column-title">Gi</th>
													<th class="column-title">F_Code</th>
													<th class="column-title">F_Caption</th>
													<th class="column-title">F_OrgID</th>
													<th class="column-title">F_MasUserID</th>
													<th class="column-title">F_TYGStyle</th>
													<th class="column-title">F_TYGStruct</th>
													<th class="column-title">F_TYGCode</th>
													<th class="column-title">F_ProductCode</th>
													<th class="column-title">F_ProductSupply</th>
													<th class="column-title">F_MacCode</th>
													<th class="column-title">F_MacVersion</th>
													<th class="column-title">F_MacCompany</th>
													<th class="column-title">F_MacStyle</th>
													<th class="column-title">F_MacPowerType</th>
													<th class="column-title">F_MacStartState</th>
													<th class="column-title">F_MacInstallState</th>
													<th class="column-title">F_MacInstallDate</th>
													<th class="column-title">F_MacDesc</th>
													<th class="column-title">F_SimTel</th>
													<th class="column-title">F_SimStream</th>
													<th class="column-title">F_SimMoney</th>
													<th class="column-title">F_Memo</th>
													<th class="column-title">F_X</th>
													<th class="column-title">F_Y</th>
													<th class="column-title">F_Area</th>
													<th class="column-title">F_Address</th>
													<th class="column-title">F_Wu0</th>
													<th class="column-title">F_Wu1</th>
													<th class="column-title">F_Wu2</th>
													<th class="column-title">F_Wu3</th>
													<th class="column-title">F_Wu4</th>
													<th class="column-title">F_Wd0</th>
													<th class="column-title">F_Wd1</th>
													<th class="column-title">F_Wd2</th>
													<th class="column-title">F_Wd3</th>
													<th class="column-title">F_Wd4</th>
													<th class="column-title">F_V0</th>
													<th class="column-title">F_V1</th>
													<th class="column-title">F_V2</th>
													<th class="column-title">F_V3</th>
													<th class="column-title">F_V4</th>
													<th class="column-title">F_S0</th>
													<th class="column-title">F_S1</th>
													<th class="column-title">F_S2</th>
													<th class="column-title">F_S3</th>
													<th class="column-title">F_S4</th>
													<th class="column-title">F_VTM</th>
													<th class="column-title">F_YzUserID</th>
													<th class="column-title">F_F0</th>
													<th class="column-title">F_F1</th>
													<th class="column-title">F_C</th>
													<th class="column-title">F_P</th>
													<th class="column-title">F_F2</th>
													<th class="column-title no-link last"><span
														class="nobr">操作</span></th>
													<th class="bulk-actions" colspan="7">
														这里选中记录的行数提示的汉化在build..js..cutom.min.js的action-cnt中修改
														<a class="antoo" style="color: #fff; font-weight: 500;">已选中
															<span class="action-cnt"> </span> <i
															class="fa fa-chevron-down"></i>
													</a>
													</th>
												</tr>
											</thead>

											<tbody>
												<tr class="even pointer">
													<td class="a-center "><input type="checkbox"
														class="flat" name="table_records"></td>
													<td class=" ">121000040</td>
													<td class="TABLE-LAYOUT:fixed;WORD-WRAP:break_word ">May
														23, 2014 11:47:56 PM</td>
													<td class=" ">121000210 <i
														class="success fa fa-long-arrow-up"></i></td>
													<td class=" ">John Blank L</td>
													<td class=" ">Paid</td>
													<td class="a-right a-right ">$7.45</td>
													<td class=" last"><a href="#">View</a></td>
												</tr>

											</tbody>
										</table>
									</div>


								</div>
							</div>
						</div>
 -->

						<!-- 日志记录表 -->
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>
										日志记录 <small>故障记录及诊断</small>
									</h2>
									<ul class="nav navbar-right panel_toolbox">
										<li><a class="collapse-link"> <i
												class="fa fa-chevron-up"></i></a></li>
										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-expanded="false"></li>
									</ul>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<table id="activityList"
										class="table table-bordered table-hover">
										<p>
											开始日期：<input type="text" id="datepickerstart"> &nbsp;&nbsp;&nbsp;&nbsp;结束日期：<input
												type="text" id="datepickerend">
											<button id="search" onclick="search()">搜索</button>
										</p>

										<thead>
											<tr>
												<th width="140">时间</th>
												<th>故障类型</th>
												<th width="400">故障可能原因</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
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
	<script src="${pageContext.request.contextPath }/vendors/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<!-- 日期选择器 -->
	<script type="text/javascript" src="${pageContext.request.contextPath }/vendors/jquery-ui-1.12.1/jquery-ui.js"></script>

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


	<script type="text/javascript">
	<!--第三步：初始化Datatables,从后台的json中获取数据-->
	
		$(document)
				.ready(
						function() {
							$("#activityList")
									.dataTable(
											{
												"paging" : true,//是否分页
												"ordering" : false,//是否提供排序的功能
												"scrollX" : true,//启用水平滚动条
												"bScrollCollapse" : true,//当显示行的数量不是满状态时，允许表的高度减小
												"searching" : false,//功能控制搜索（过滤）功能,关闭
												"autoWith" : true,//是否启用智能列宽处理
												"pagingType" : "full",//除首页、上一页、下一页、末页四个按钮还有页数按钮  
												"bProcessing" : true, // 是否显示取数据时的那个等待提示  
												"bServerSide" : true,//这个用来指明是通过服务端来取数据  
												"bInfo" : true,//显示底部信息栏
												"sAjaxSource" : "${path}/getLogRecords",//这个是请求的地址
												"aoColumns" : [
														{
															data : function(obj) {
																var now = obj.time.time;
																return new Date(
																		parseInt(now))
																		.Format("yyyy-MM-dd HH:mm:ss");
															}
														}, {
															data : "faultTypes"
														}, {
															data : "faultCause"
														}, ],

												// 汉化
												"oLanguage" : {
													"sProcessing" : "正在加载数据...",
													"sLengthMenu" : "每页显示 _MENU_ 条记录 ",
													"sZeroRecords" : "没有您要搜索的内容",
													"sInfo" : "从第 _START_ 到第 _END_ 条记录/共 _TOTAL_ 条记录",
													"sInfoEmpty" : "记录数为0",
													"sInfoFiltered" : "(全部记录数 _MAX_  条)",
													"sInfoPostFix" : "",
													"sSearch" : "搜索",
													"sUrl" : "",
													"oPaginate" : {
														"sFirst" : "首页",
														"sPrevious" : " 上一页 ",
														"sNext" : " 下一页 ",
														"sLast" : " 末页"
													}
												},
												"fnServerData" : retrieveData
											// 获取数据的处理函数  
											});

							// 3个参数的名字可以随便命名,但必须是3个参数,少一个都不行  
							function retrieveData(sSource, aoData, fnCallback) {
								var starttime = $("#datepickerstart").val();
								var endtime = $("#datepickerend").val();
								if((starttime==null&&endtime!=null)||(starttime!=null&&endtime==null)||(starttime>endtime)) {
									alert("请输入有效的时间范围！");
									return;
								}
								$.ajax({
									url : sSource,//这个就是请求地址对应sAjaxSource  
									data : {
										"aoData" : JSON.stringify(aoData),
										"startTime": starttime,//多个参数后台接受的时候也要用多个参数
										"endTime": endtime	
									},//这个是把datatable的一些基本数据变成json字符串再传给后台,比如起始位置,每页显示的行数  
									type : 'post',
									dataSrc : '',
									dataType : 'json',
									async : false,
									success : function(result) {
										fnCallback(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的  
									},
									error : function(msg) {
										alert("报错了：" + msg);
									}
								});
							};


						});

		//进行后台查询功能
		function search() {
			var table = $('#activityList').DataTable();
			//根据日期选择器重绘datatable而不改变分页信息
			table.ajax.reload( null, false);
		}

		//时间格式化,动态语言
		Date.prototype.Format = function(fmt) {
			var o = {
				//月份,这里就是普通的占位符标志，类似于yyyy/MM/dd HH:mm:ss这里面的M，可以自定义
				"M+" : this.getMonth() + 1,
				//日
				"d+" : this.getDate(),
				//小时
				"H+" : this.getHours(),
				//分
				"m+" : this.getMinutes(),
				//秒
				"s+" : this.getSeconds(),
				//季度
				"q+" : Math.floor((this.getMonth() + 3) / 3),
				//毫秒
				"S" : this.getMilliseconds()
			};
			if (/(y+)/.test(fmt)) {
				fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
						.substr(4 - RegExp.$1.length));
			}
			for ( var k in o) {
				if (new RegExp("(" + k + ")").test(fmt)) {
					fmt = fmt.replace(RegExp.$1,
							(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
									.substr(("" + o[k]).length)));
				}
			}
			return fmt;
		};
	</script>


	<!-- 日期选择器 -->
	<script>
		$(function() {
			//开始日期
			$("#datepickerstart").datepicker();
			//结束日期
			$("#datepickerend").datepicker();

			$.datepicker.regional['zh-CN'] = {
				clearText : '清除',
				clearStatus : '清除已选日期',
				closeText : '关闭',
				closeStatus : '不改变当前选择',
				prevText : '<上月',
				prevStatus : '显示上月',
				prevBigText : '<<',
				prevBigStatus: '显示上一年',
				nextText: '下月>',
				nextStatus : '显示下月',
				nextBigText : '>>',
				nextBigStatus : '显示下一年',
				currentText : '今天',
				currentStatus : '显示本月',
				monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
						'九月', '十月', '十一月', '十二月' ],
				monthNamesShort : [ '一', '二', '三', '四', '五', '六', '七', '八',
						'九', '十', '十一', '十二' ],
				monthStatus : '选择月份',
				yearStatus : '选择年份',
				weekHeader : '周',
				weekStatus : '年内周次',
				dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
				dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
				dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
				dayStatus : '设置 DD 为一周起始',
				dateStatus : '选择 m月 d日,DD',
				dateFormat : 'yy-mm-dd',
				firstDay : 1,
				initStatus : '请选择日期',
				isRTL : false
			};
			$.datepicker.setDefaults($.datepicker.regional['zh-CN']);

		});
	</script>
	

</body>
</html>