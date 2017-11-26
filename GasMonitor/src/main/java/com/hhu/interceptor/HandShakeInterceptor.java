package com.hhu.interceptor;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * 每次建立连接都会进行握手,这个拦截器是用于处理握手前后的预处理工作
 * @author Weiguo Liu
 *
 */
public class HandShakeInterceptor implements HandshakeInterceptor {

    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("将要进入WebSocket预处理过程");
        return true;
    }
 
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    	System.out.println("WebSocket预处理中....");
    	System.out.println("webSocket预处理完毕");
    }

}
