package com.hhu.websocket;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 配置WebSocket
 * @author WeiguoLiu
 * @data 2018年1月11日
 */
@Component
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

    @Resource
    MyWebSocketHandler handler;
 
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    	
    	//配置对指定的url进行拦截
        registry.addHandler(handler, "/ws").addInterceptors(new HandShakeInterceptor());
 
        //允许客户端使用SokcetJs
        registry.addHandler(handler, "/ws/sockjs").addInterceptors(new HandShakeInterceptor()).withSockJS();
    }

}
