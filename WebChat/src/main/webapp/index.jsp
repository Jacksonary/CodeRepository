<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
<h2>Hello World!</h2>

<input id="msg"/>

<button onclick="subOpen()">链接</button>
<button onclick="subSend()">发送</button>

<div id="servermsg"></div>


<script type="text/javascript">
	var ws; //一个ws对象就是一个通信管道
	//target表示的是请求地址，你要链接谁
	var target = "ws://localhost:8080/WebSocketTest/echo";


	function subOpen(){
		if('WebSocket'in window) {
			ws = new WebSocket(target);
		} else if('MozWebSocket'in window) {
			ws = new MozWebSocket(target);
		} else {
			alert("当前浏览器不支持WebSocket！");
			return;
		}

		//客户端从服务端接收信息也是基于事件的，异步，所以在open的时候就要注册一个message
		ws.onmessage = function(MsgEvent) {//这里返回的是一个事件对象！！
			console.info(MsgEvent.data);//真实的服务端发送的消息在事件对象的data变量中
			//往div中注入内容用document.getElementById("divid").innerHTML
			//追加内容的时候就用+=，不追加就直接用=就好
			document.getElementById("servermsg").innerHTML += MsgEvent.data;
		}
    }

	//发送消息
    function subSend() {
        //获取输入框的内容
		var msg = document.getElementById("msg").value;
		//发送消息(客户端给服务端发送消息)
		ws.send(msg);
		//清空输入框中的内容
		document.getElementById("msg").value = "";
    }

    
</script>
</body>
</html>
