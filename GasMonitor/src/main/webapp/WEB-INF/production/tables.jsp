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

								<li><a href="${path }/index" class="a_post">
									<i class="fa fa-home"></i> 主页 </a></li>

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
							href="${path }/index/login"> <span class="glyphicon glyphicon-off"
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
							<li class="">
								<a href="javascript:;"
									class="user-profile dropdown-toggle" data-toggle="dropdown"
									aria-expanded="false"> <img src="${path }/images/img.jpg"
									alt="">${loginUser.fCaption } 
								</a>
						    </li>
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

						<!-- 站点数据表格 -->
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>
										站点信息 <small>所有站点信息的基本信息</small>
									</h2>
									<ul class="nav navbar-right panel_toolbox">
										<li><a class="collapse-link">
										<i class="fa fa-chevron-up"></i></a>
										</li>
										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-expanded="false">
										</li>
									</ul>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<table id="activityList"
										class="table table-bordered table-hover">
										<thead>
											<tr>
												<th >F_ID</th>
												<th >Gi</th>
												<th >F_Code</th>
												<th width="100">F_Caption</th>
												<th >F_OrgID</th>
												<th >F_MasUserID</th>  
   												<th width="100">F_TYGStyle</th>
   												<th >F_TYGStruct</th>
   												<th >F_TYGCode</th>
   												<th >F_ProductCode</th>
   												<th width="220">F_ProductSupply</th>
   												<th >F_MacCode</th>
   												<th >F_MacVersion</th>
   												<th width="220">F_MacCompany</th>
   												<th >F_MacStyle</th>
   												<th >F_MacPowerType</th>
   												
   												<th >F_MacStartState</th>
												<th >F_MacInstallState</th>
												<th width="140">F_MacInstallDate</th>
												<th width="320">F_MacDesc</th>
												<th >F_SimTel</th>
												<th >F_SimStream</th>  
   												<th >F_SimMoney</th>
   												<th width="380">F_Memo</th>
   												<th >F_X</th>
   												<th >F_Y</th>
   												<th >F_Area</th>
   												<th width="200">F_Address</th>
   												<th >F_Wu0</th>
   												<th >F_Wu1</th>
   												<th >F_Wu2</th>
   												<th >F_Wu3</th>
   												
   												<th >F_Wu4</th>
												<th >F_Wd0</th>
												<th >F_Wd1</th>
												<th >F_Wd2</th>
												<th >F_Wd3</th>
												<th >F_Wd4</th>
												<th >F_V0</th> 
   												<th >F_V1</th>
   												<th >F_V2</th>
   												<th >F_V3</th>
   												<th >F_V4</th>
   												<th >F_S0</th>
   												<th >F_S1</th>
   												<th >F_S2</th>
   												<th >F_S3</th>
   												<th >F_S4</th>
   												<th width="140">F_VTM</th>
   												
   												<th >F_YzUserID</th>
												<th >F_F0</th>
												<th >F_F1</th>
												<th >F_C</th>
												<th >F_P</th>
												<th >F_F2</th>  
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
	$(document).ready(function() {
		 $("#activityList").dataTable({
			"paging" : true,//是否分页
			"ordering" : false,//是否提供排序的功能
			"scrollX" : true,//启用水平滚动条
			"bScrollCollapse" :true,//当显示行的数量不是满状态时，允许表的高度减小
			"searching": false,//功能控制搜索（过滤）功能
			"autoWith":true,//是否启用智能列宽处理
			"pagingType" : "full",//除首页、上一页、下一页、末页四个按钮还有页数按钮  
			"bProcessing" : true, // 是否显示取数据时的那个等待提示  
			"bServerSide" : true,//这个用来指明是通过服务端来取数据  
			"bInfo" : true,//显示底部信息栏
			"sAjaxSource" : "${path}/getStation",//这个是请求的地址
			"aoColumns" : [
				{data : "fId"},
                {data : "gI"}, 
                {data : "fCode"}, 
                {data : "fCaption"},
                {data : "fOrgId"},
                {data : "fMasUserId"},
                {data : "fTygStyle"},
                {data : "fTygStruct"},
                {data : "fTygCode"},
                {data : "fProductCode"},
                {data : "fProductSupply"},
                {data : "fMacCode"}, 
                {data : "fMacVersion"}, 
                {data : "fMacCompany"},
                {data : "fMacStyle"},
                {data : "fMacPowerType"},
                {data : "fMacStartState"},
                {data : "fMacInstallState"},
                {data : "fMacInstallDate"},
                {data : "fMacDesc"},
                {data : "fSimTel"},
                {data : "fSimStream"}, 
                {data : "fSimMoney"}, 
                {data : "fMemo"},
                {data : "fX"},
                {data : "fY"},
                {data : "fArea"},
                {data : "fAddress"},
                {data : "fWu0"},
                {data : "fWu1"},
                {data : "fWu2"},
                {data : "fWu3"}, 
                {data : "fWu4"}, 
                {data : "fWd0"},
                {data : "fWd1"},
                {data : "fWd2"},
                {data : "fWd3"},
                {data : "fWd4"},
                {data : "fV0"},
                {data : "fV1"},
                {data : "fV2"},
                {data : "fV3"}, 
                {data : "fV4"}, 
                {data : "fS0"},
                {data : "fS1"},
                {data : "fS2"},
                {data : "fS3"},
                {data : "fS4"},
                {data : "fVtm"},
                {data : "fYzUserId"},
                {data : "fF0"},
                {data : "fF1"},
                {data : "fC"},
                {data : "fP"},
                {data : "fF2"} 
			],

			//自定义列属性，这是方法一，也可以直接在aoColumns属性中搞，详见log.jsp中的写法
			columnDefs: [
                {
                    targets: [18,48],//定位到表格的第19列和第49列
                    render: function (data, type, row, meta) {//这里的data就是后台传过来的对象
                        return new Date(parseInt(data.time)).Format("yyyy-MM-dd HH:mm:ss");
                    }
                }
             ],
			
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
			$.ajax({
				url : sSource,//这个就是请求地址对应sAjaxSource  
				data : {
					"aoData" : JSON.stringify(aoData),
					
				},//这个是把datatable的一些基本数据变成json字符串再传给后台,比如起始位置,每页显示的行数  
				type : 'post',
				dataSrc : '',
				dataType : 'json',
				async : false,
				success : function(result) {
					//console.log(result);
					fnCallback(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的  
				},
				error : function(msg) {
					//alert("报错了：" + msg);
					alert("服务器发生位置错误，请稍候再试！");
				}
			});
		}; 

	});	 



	//时间格式化
    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            //月份
            "M+": this.getMonth() + 1,
            //日
            "d+": this.getDate(),
            //小时
            "H+": this.getHours(),
            //分
            "m+": this.getMinutes(),
            //秒
            "s+": this.getSeconds(),
            //季度
            "q+": Math.floor((this.getMonth() + 3) / 3),
            //毫秒
            "S": this.getMilliseconds() 
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    };
		
	</script>
	
</body>
</html>