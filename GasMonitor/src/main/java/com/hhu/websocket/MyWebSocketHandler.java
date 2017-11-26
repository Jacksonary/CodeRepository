package com.hhu.websocket;


import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.hhu.threads.AllThread;
import com.hhu.threads.DataSimulationThread;
import com.hhu.threads.MonitorThread;
import com.hhu.threads.StationThread;

import ch.qos.logback.classic.Logger;

/**
 * 用于处理WebSocket的消息，这里是真正握手进行的处理
 * @author Weiguo Liu
 *
 */

@Component
public class MyWebSocketHandler implements WebSocketHandler {
	
	private static final ArrayList<WebSocketSession> users = null;
	
    /**
     * 建立连接后,触发页面上的onopen方法
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("进入真正的握手类：MyWebSocketHandler,成功链接");
        
        if(session.isOpen()) {//如果连接通道是开着的状态
        	
//        	//创建写入数据的模拟线程
//        	new Date().getTime();
//        	Thread dt = new DataSimulationThread();
//        	dt.start();
//        	
//        	//挂起写入线程
//        	
//        	
//        	//创建检测线程
//        	Thread mt = new MonitorThread(session);
//        	mt.start();
//        	
//        	//挂起检测线程
//        	
//        	
//        	//创建数据推送线程（推送到前台的数据）
//        	Thread st = new StationThread(session);
//        	st.start();
        	
        	//这里直接开启总线程
        	AllThread at = new AllThread(session);
        	at.start();
        	
        }
        
    }
 
    /**
     * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
     */
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    	System.out.println("接收到客户端发送的消息");
    }
 
    /**
     * 消息传输错误处理
     */
    public void handleTransportError(WebSocketSession session,Throwable exception) throws Exception {
        
    	if (session.isOpen()) {
        	System.out.println("发生异常，关闭连接");
            session.close();
        }
        System.out.println("连接已关闭");
    }
 
    /**
     * 关闭连接后
     */
    public void afterConnectionClosed(WebSocketSession session,CloseStatus closeStatus) throws Exception {
        System.out.println("Websocket已经关闭,正在常识重新连接");
        
        session.close();
    }
 
    public boolean supportsPartialMessages() {
        return false;
    }

}
