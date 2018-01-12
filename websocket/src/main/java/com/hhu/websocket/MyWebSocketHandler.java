package com.hhu.websocket;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;


/**
 * 用于处理WebSocket的消息，这里是真正握手进行的处理
 * @author Weiguo Liu
 *
 */

@Component
public class MyWebSocketHandler implements WebSocketHandler {
	
	/*存放多个用户的List，解决多客户端访问的多线程问题,但是在实际测试过程中
	 * 并不时线程安全的，在将用户从List移除后，当服务端向客户端推送数据时会报错
	 * 因为在发送消息的方法里应该被移除的Session消息却进入了发送消息的环节，在执
	 * 行getBasicRemote().sendText(clientInfoJson)就会产生异常！！！一定注意，
	 * 
	 * 解决方案：不能通过线程安全的集合来保存Session解决。而应该保存整个类，并通过
	 * CopyOnWriteArraySet容器来操作。
	 */
	private volatile static List<WebSocketSession> users = new ArrayList<>();
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    /**
     * 成功建立连接后（这个连接此时处于待使用的状态）,将会进入此方法，类似于@OnOpen这个注解，触发页面上的onopen方法
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("进入真正的握手类：MyWebSocketHandler,WebSocket连接建立成功");
        
    	/*
    	 * 这边根据自己的需求进行消息的推送，这里是每搁4s向在线的客户端进行数据推送
    	 * 这里实际不能做到每个客户端的都4s推送一次，因为每次来一个客户端都触发这个方法
    	 */
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					for(WebSocketSession user:users) {//向每个在线的客户端推送消息，4秒推送一次
						try {
							user.sendMessage(new TextMessage("服务器推送消息：" + sdf.format(new Date())));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
					try {//服务器每5秒向每个在线的客户端推送消息
						Thread.currentThread().sleep(30000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}).start();
    	
    	
    	users.add(session);
    	System.out.println("在线用户" + users.size() + "人:" + users);
    	
    }
 
    /**
     * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理，相当与@OnMessage注解
     */
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    	System.out.println("接收到客户端:" + session.getId() + "发送的消息:" + message.getPayload().toString());
    	//这里一定要对客户端的心跳作回应动作，不然会不断重连
    	session.sendMessage(new TextMessage("服务器的心跳回应-HeartBeat" + sdf.format(new Date())));
    }
 
    /**
     * 处理来自WebSocket消息传输的错误，类似与@OnError注解
     */
    public void handleTransportError(WebSocketSession session,Throwable exception) throws Exception {
        //一定要移除
        users.remove(session);
        System.out.println("客户端" + session.getId() + "传输异常");
    }
 
    /**
     * 关闭连接后或者发生传输错误时将会调用该方法，尽管会话session可能此时仍然未关闭，但是不建议在此处给客户端发消息，因为
     * 极有可能会发送失败，类似于@OnClose
     */
    public void afterConnectionClosed(WebSocketSession session,CloseStatus closeStatus) throws Exception {
        //这里一定要移除，不然传输会报错
        users.remove(session);
        session.close();
        System.out.println("Websocket客户端" + session.getId() + "已经关闭");
    }
 
    /**
     * 表示是否让WebSocket支持处理大文件的拆分处理，默认为false
     */
    public boolean supportsPartialMessages() {
    	//不需要进行大文件的拆分处理
        return false;
    }

}

