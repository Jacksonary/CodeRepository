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
<title>学生信息管理</title>

<script>	
		var url;
		
		function deleteStudent(){
			//取得单行的行数用"getSelected",多行选中用"getSelections"
			var selectedRows=$('#dg').datagrid('getSelections');
			if(selectedRows==0){
				$.messager.alert("系统提示","请选择要删除的记录！");
				return;
			}
			//用for循环实现批量删除的操作，用数组实现
			var strIds = [];
			for(var i=0;i<selectedRows.length;i++){
				strIds.push(selectedRows[i].stuId);//获取选中的id,这里是学生的id，故是stuId
			}
			var ids=strIds.join(",");//将数组的每个元素用逗号分开
			$.messager.confirm("系统提示", "您确认要删除<font color=red>"+selectedRows.length+"</font>条记录嗎？",function(r){
				//写ajax代码
				if(r){
					$.post("StudentDeleteServlet",{delIds:ids},function(result){
						if(result.success){
							$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条选记录");
							$('#dg').datagrid("reload");
							}else{
							$.messager.alert("系统提示",result.errorMsg);
							}
						},"json")
				}
			});
		}
		
		function newStudent(){
			$('#dlg').dialog('open').dialog('setTitle','添加学生');
			$('#fm').form('clear');
			url='StudentSaveServlet';
		}
		
		
		function editStudent(){
			var selectedRows=$('#dg').datagrid('getSelections');
			if(selectedRows.length>1){
				$.messager.alert("系统提示","您只能选择一条记录进行编辑！");
				return;
			}else if(selectedRows.length==0){
				$.messager.alert("系统提示","请选择一条记录进行编辑！");
				return;
			}else{
				$('#dlg').dialog('open').dialog('setTitle','编辑学生');
				$('#fm').form('load',selectedRows[0]);//将第一条load
				url="StudentSaveServlet?stuId="+selectedRows[0].stuId;
			}
		}
		
		
		function saveStudent(){
			$('#fm').form('submit',{
				url:url,//异步提交的参数
				onSubmit:function(){
					if($('#sex').combobox("getValue")==""){
						$.messager.alert("系统提示","请选择性别！");
						return false;//返回false就不会让用户提交
					}
					if($('#s_gradeId').combobox("getValue")=="") {
						$.messager.alert("系统提示","请选择所属班级！");
						return false;//返回false就不会让用户提交
					}
						
					return $(this).form('validate');//提交之前执行验证
				},
				success:function(result){
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
		function searchStudent(){

			$('#dg').datagrid('load',{
				stuNo:$('#s_stuNo').val(),
				stuName:$('#s_stuName').val(),
				sex:$('#s_sex').combobox("getValue"),
				bbirthday:$('#s_bbirthday').datebox("getValue"),
				ebirthday:$('#s_ebirthday').datebox("getValue"),
				gradeId:$('#s_gradeId').combobox("getValue"),
			});
		}
	
</script>

</head>
<body style="margin:5px;">

    <table id="dg" class="easyui-datagrid" title="学生信息管理" style="width:705px;height:auto" 
           data-options="modal:true,rownumbers:true,fitColumns:true,pagination:true,url:'StudentListServlet',toolbar:'#tb',fit:true">
        <thead>
            <tr>
            	<th field="cb" checkbox="true"></th>
                <th data-options="field:'stuId'" align="center">编号</th>
                <th data-options="field:'stuName'" align="center">姓名</th>
                <th data-options="field:'stuNo'" align="center">学号</th>
                <th data-options="field:'sex'" align="center">性别</th>
                <th data-options="field:'birthday'" align="center">出生日期</th>
                <th data-options="field:'gradeName'" align="center">所在班级</th>
                <th data-options="field:'email'" align="center">email</th>
                <th data-options="field:'stuDesc'" align="center">学生简介</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newStudent()">添加学生</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editStudent()">编辑学生</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteStudent()">删除学生</a>
        </div>
        <div>
        &nbsp;&nbsp;学号：<input type="text" name="s_stuNo" id="s_stuNo" size=10/>
        &nbsp;&nbsp;姓名：<input type="text" name="s_stuName" id="s_stuName" size=10/>
        &nbsp;&nbsp;性别：<select class="easyui-combobox" name="sex" id="s_sex" editable="false" panelHeight="auto">
        				<option value="">--请选择--</option>
        				<option value="男">男</option>
        				<option value="女">女</option>
        				</select>
        &nbsp;&nbsp;出生日期：<input class="easyui-datebox" name="s_birthday" id="s_bbirthday" size=11 editable="false"/>--<input class="easyui-datebox" name="s_birthday" id="s_ebirthday" size=11 editable="false"/>
        &nbsp;&nbsp;所属班级：<input class="easyui-combobox" name="s_gradeId" id="s_gradeId" size=10 editable="false" data-options="valueField:'id',textField:'gradeName',url:'GradeComboListServlet'" />				
        <a href="javascript:searchStudent()" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>

	<!-- 新增/编辑班级的面板 -->
	<div id="dlg" class="easyui-dialog" style="width:auto;height:auto;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="5px;">
				<tr>
					<td>姓名：</td>
					<td><input type="text" name="stuName" id="stuName" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td><select class="easyui-combobox" id="sex" name="sex" editable="false" panelHeight="auto" style="width: 155px">
						<option value="男">男</option>
						<option value="女">女</option>
					</select></td>
				</tr>
				<tr>
					<td>学号：</td>
					<td><input type="text" name="stuNo" id="stuNo" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>所属班级：</td>
					<td>
						<input class="easyui-combobox" name="s_gradeId" id="s_gradeId" size=10 editable="false" data-options="valueField:'id',textField:'gradeName',url:'GradeComboListServlet'" />
					</td>
				</tr>
				<tr>
					<td>出生日期：</td>
					<td><input class="easyui-datebox" name="birthday" id="birthday" required="true" editable="false" /></td>
				</tr>
				<tr>
					<td>Email：</td>
					<td><input type="text" name="email" id="email" class="easyui-validatebox" required="true" validType="email"/></td>
				</tr>
				<tr>
					<td valign="top">学生简介：</td>
					<td colspan="4"><textarea rows="7" cols="30" name="stuDesc" id="stuDesc"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveStudent()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>
</html>