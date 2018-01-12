<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getServerName() + ":" + request.getServerPort() + path + "/";
	String basePath2 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h2>WebSocket心跳重连测试</h2>
</body>

<!-- websocket的配置 -->
<script>

	var path = '<%=basePath%>';
	var ws;//websocket实例
	var lockReconnect = false;//避免重复连接
	var wsUrl = getwsurl();
	
	createWebSocket(wsUrl);
	
	function getwsurl() {
		//作兼容性连接
		if ('WebSocket' in window) {
			return "ws://" + path + "/ws";
		} else if ('MozWebSocket' in window) {
			return "ws://" + path + "/ws" + uid;
		} else {
			return "http://" + path + "/ws/sockjs" + uid;
		}
	}
	
	function createWebSocket(url) {
	
	    try {
	        ws = new WebSocket(url);
	        initEventHandle();
	    } catch (e) {
	        reconnect(url);
	    }
	    
	}
	
	function initEventHandle() {
	    ws.onclose = function () {
	        console.info("连接关闭");
	       // reconnect(wsUrl);
	    };
	    ws.onerror = function () {
	        console.info("传输异常");
	        reconnect(wsUrl);
	    };
	    ws.onopen = function () {
	        //心跳检测重置
	        heartCheck.reset().start();
	    };
	    ws.onmessage = function (event) {
	        console.info(event.data);
	
	        //如果获取到消息，心跳检测重置
	        heartCheck.reset().start();
	    }
	}
	
	function reconnect(url) {
	    if(lockReconnect) return;
	    lockReconnect = true;
	    //没连接上会一直重连，设置延迟避免请求过多
	    setTimeout(function () {
	        console.info("尝试重连..." + new Date().format("yyyy-MM-dd hh:mm:ss"));
	        createWebSocket(url);
	        lockReconnect = false;
	    }, 5000);
	}
	
	
	//心跳检测,每20s心跳一次
	var heartCheck = {
	    timeout: 20000,
	    timeoutObj: null,
	    serverTimeoutObj: null,
	    reset: function(){
	        clearTimeout(this.timeoutObj);
	        clearTimeout(this.serverTimeoutObj);
	        return this;
	    },
	    start: function(){
	        var self = this;
	        this.timeoutObj = setTimeout(function(){
	            //这里发送一个心跳，后端收到后，返回一个心跳消息，
	            //onmessage拿到返回的心跳就说明连接正常
	            ws.send("HeartBeat" + new Date().format("yyyy-MM-dd hh:mm:ss"));
	            console.info("客户端发送心跳：" + new Date().format("yyyy-MM-dd hh:mm:ss"));
	            self.serverTimeoutObj = setTimeout(function(){//如果超过一定时间还没重置，说明后端主动断开了
	                ws.close();//如果onclose会执行reconnect，我们执行ws.close()就行了.如果直接执行reconnect 会触发onclose导致重连两次
	            }, self.timeout)
	        }, this.timeout)
	    }
	}

	//js中格式化日期，调用的时候直接：new Date().format("yyyy-MM-dd hh:mm:ss")
	Date.prototype.format = function(fmt) {
	     var o = {
	        "M+" : this.getMonth()+1,                 //月份 
	        "d+" : this.getDate(),                    //日 
	        "h+" : this.getHours(),                   //小时 
	        "m+" : this.getMinutes(),                 //分 
	        "s+" : this.getSeconds(),                 //秒 
	        "q+" : Math.floor((this.getMonth()+3)/3), //季度
	        "S"  : this.getMilliseconds()             //毫秒 
	   	}; 
	    if(/(y+)/.test(fmt)) {
	            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	    }
	    for(var k in o) {
	       if(new RegExp("("+ k +")").test(fmt)){
	            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	         }
	     }
	    return fmt; 
	}    

	</script>



</html>
