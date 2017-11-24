<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聊天室</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	//var username = "${sessionScope.username}";
	/*当然也可以用上面的写法，这里需要注意的是一定要加引号，通过el表达式获取的是
	*一个变量，只有加了引号才是字符串
	*/
	var username = '${username}';
	//进入页面就直接打开socket通道
	var ws;
	var target = "ws://localhost:8080/WebChat/chatSocket?username="+username;
	window.onload = function() { 

		//判定浏览器是否支持websocket协议
		if('WebSocket' in window) {
			ws = new WebSocket(target);
		} else if('MoWebSocket' in window) {
			ws = new MozWebSocket(target);
		} else {
			alert("当前浏览器不支持WebSocket");
			return;
		}


		//接受来自服务器的消息,返回一个事件对象，具体的返回内容在data属性中
		ws.onmessage = function(event) {
			//由于传过来的变成了json内容的字符串，所以这里所处理，eval() 函数可计算某个字符串，并执行其中的的 JavaScript 代码
			eval("var msg=" + event.data + ";");
			
			if(undefined!=msg.welcom){
				//在div中追加消息
				$("#content").append(msg.welcom+"</br>");
			}
			
			if(undefined!=msg.usernames){
				//刷新的时候清空用户列表，否则刷新会造成用户的重复添加
				$("#userList").html("");
				//这是遍历的用户列表
				$(msg.usernames).each(function(){
					$("#userList").append("<input type=checkbox value='" + this +"'/>" + this + "</br>");
				});
			}

			//接受到服务端的消息后
			if(undefined!=content) {
				//在聊天追加用户发送的内容
				$("#content").append(msg.content);
			}
			
				
		}
		
	}

	//向后发送消息
	function subSend() {
	    //获取输入框中的值,这里的获取和清空都是jquery中的写法
		var val = $("#msg").val();
		console.info("msg===" + val);
		
		//清空输入框中的值
		$("#msg").val("");

		//获取复选框选中了几个,这里注意有的时候写成size()，有时写成length,根据jquery的版本不同做适当调整
		console.info($('#userList :checked').length);

		
		var obj = null;
		
		if($('#userList :checked').length==0) {
			obj = {
				msg:val,
				type:1//1表示广播 2表示单聊
			}
		} else {
			
			//获取发送对象，即复选框中的选中值
			var to = $('#userList :checked').val();
			//获取复选框中的选中值
			console.info(to);
			
			obj = {
				to:to,
				msg:val,
				type:2//1表示广播 2表示单聊
			}
		}

		var str = JSON.stringify(obj);//将obj转换成JSON字符串
		ws.send(str);
		$("#msg").val("");
	}

</script>
</head>
<body>
	<!--聊天的主体窗口  -->
	<div id="container" style="border: 1px solid black;width: 400px;height: 400px;float: left;">
		<div id="content" style="height: 350px;">
		
		</div>
		<div style="border-top: 1px solid black;width: 400px;height: 55px;">
			<input id="msg" style="border: 1px solid white;width: 348px;height:55px;"/>
			<button onclick="subSend()">发送</button>
		</div>
	</div>
	
	<!-- 聊天用户列表 -->
	<div id="userList" style="border: 1px solid black;width: 100px;height: 390px;float: left;">
		
	</div>
	
</body>
</html>