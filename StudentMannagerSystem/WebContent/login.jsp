<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息管理系统</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.5.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.5.1/themes/icon.css">
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.5.1/demo/demo.css">
<script type="text/javascript" src="js/jquery-easyui-1.5.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    function reset(){
        $('#ff').form('clear');
</script>
</head>
<body>
	<div align="center" style="padding-top:50px;">
    	<form id="ff" action="LoginServlet" method="post">
        <table background="images/login.jpg"  width="740" height="500">
            <tr height="180">
            	<td colspan="4"></td>
            </tr>
            <tr height="10">
            	<td width="40%"></td>
                <td width="10%">用户名:</td>
                <td><input type="text" name="userName" value="${userName }" ></td>
                <td width="30%"></td>
            </tr>
            <tr height="10">
            	<td width="40%"></td>
                <td width="10%">密&nbsp;&nbsp;&nbsp;码:</td>
                <td><input type="password" name="password" value="${password}" ></td>
                <td width="30%"></td>
            </tr>
            <tr height="10">
            	<td width="40%"></td>
            	<td><input type="submit" value="登录"></td>
            	<td><input type="button" value="重置" onclick="reset()"></td>
            	<td width="30%"></td>
            </tr>
            <tr height="10">
            	<td width="40%"></td>
            	<td colspan="3">
            		<font color="red">${error}</font>
            	</td>
            </tr>
            <tr height="180">
            	<td colspan="4"></td>
            </tr>
        </table>
        </form>
	</div>
</body>
</html>