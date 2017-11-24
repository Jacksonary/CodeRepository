<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.5.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.5.1/themes/icon.css">
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.5.1/demo/demo.css">
<script type="text/javascript" src="js/jquery-easyui-1.5.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级信息管理</title>

<script>	
		var url;
		
		function deleteGrade(){
			//取得单行的行数用"getSelected",多行选中用"getSelections"
			var selectedRow=$('#dg').datagrid('getSelected');
			if(selectedRows==0){
				$.messager.alert("系统提示","请选择要删除的记录！");
				return;
			}
			//用for循环实现批量删除的操作，用数组实现
			var strIds = [];
			for(var i=0;i<selectedRows.length;i++){
				strIds.push(selectedRows[i].id);//获取选中的id
			}
			var ids=strIds.join(",");//将数组的每个元素用逗号分开
			$.messager.confirm("系统提示", "您确认要删除<font color=red>"+selectedRows.length+"</font>条记录嗎？",function(r){
				//写ajax代码
				if(r){
					$.post("GradeDeleteServlet",{delIds:ids},function(result){
						if(result.success){
							$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条选记录");
							$('#dg').datagrid("reload");
							}else{
							$.messager.alert("系统提示","<font color=red>" + selectedRows[result.errorIndex].gradeName+"</font>"+result.errorMsg);
							}
						},"json")
				}
			});
		}
		
		function newGrade(){
			$('#dlg').dialog('open').dialog('setTitle','添加班级');
			$('#fm').form('clear');
			url='GradeSaveServlet';
		}
		
		
		function editGrade(){
			var selectedRows=$('#dg').datagrid('getSelections');
			if(selectedRows.length>1){
				$.messager.alert("系统提示","您只能选择一条记录进行编辑！");
				return;
			}else if(selectedRows.length==0){
				$.messager.alert("系统提示","请选择一条记录进行编辑！");
				return;
			}else{
				$('#dlg').dialog('open').dialog('setTitle','编辑班级');
				$('#fm').form('load',selectedRows[0]);//将第一条load
				url="GradeSaveServlet?id="+selectedRows[0].id;
			}
		}
		
		
		function saveGrade(){
			$('#fm').form('submit',{
				url:url,//异步提交的参数
				onSubmit:function(){
					return $(this).form('validate');//提交之前执行
				},
				success:function(result){
					var result=eval("("+ result +")");
					if(result.errorMsg){
						$.messager.alert("系统提示",result.errorMsg);
						return ;
					}else{
						$.messager.alert("系统提示","保存成功");
						$('#dlg').dialog('close');
						$('#dg').datagrid('reload');
					}
				}
			});
		}

		//搜索功能
		function searchGrade(){
			$('#dg').datagrid('load',{
				gradeName:$('#s_gradeName').val()
			});
		}
	
</script>

</head>
<body style="margin:5px;">

    <table id="dg" class="easyui-datagrid" title="班级信息管理" style="width:705px;height:auto" 
            data-options="rownumbers:true,fitColumns:true,pagination:true,url:'GradeListServlet',toolbar:'#tb',fit:true">
        <thead>
            <tr>
            	<th field="cb" checkbox="true"></th>
                <th data-options="field:'id'" align="center">编号</th>
                <th data-options="field:'gradeName'" align="center">班级名称</th>
                <th data-options="field:'gradeDesc'" align="center">班级简介</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newGrade()">添加班级</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editGrade()">编辑班级</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteGrade()">删除班级</a>
        </div>
        <div>&nbsp;&nbsp;班级名称：<input type="text" name="s_gradeName" id="s_gradeName"/>
        <a href="javascript:searchGrade()" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>

	<!-- 添加/编辑用户的窗口 ,模态窗口-->
    <div id="dlg" class="easyui-dialog"  style="width:400px;height:auto;padding:10px"
            data-options=" modal:true, closed:true, buttons:'#dlg-buttons' ">
         <form id="fm" method="post">   
         <table>
            <tr>
                <td>班级名称:</td>
                <td><input class="easyui-validatebox" name="gradeName" data-options="required:true,validType:'length[2,10]'"></td>
            </tr>
            <tr>
                <td valign="top">班级简介:</td>
                <td><textarea rows="7" cols="30" name="gradeDesc"></textarea></td>
            </tr>
        </table>
        </form>
    </div>
    
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveGrade()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
</body>
</html>