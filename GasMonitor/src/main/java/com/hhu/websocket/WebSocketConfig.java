package com.hhu.websocket;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.hhu.interceptor.HandShakeInterceptor;
 
/**
 * WebScoket的配置类
 */
@Component
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
 
    @Resource
    MyWebSocketHandler handler;
 
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    	
    	//配置对指定的url进行拦截
        registry.addHandler(handler, "/ws").addInterceptors(new HandShakeInterceptor());
 
        registry.addHandler(handler, "/ws/sockjs").addInterceptors(new HandShakeInterceptor()).withSockJS();
    }
 
}
