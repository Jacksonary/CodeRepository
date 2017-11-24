package com.hhu.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.hhu.util.ContentVo;
import com.hhu.util.Message;

/**
 * 建立聊天室的webSocket程序
 * @author Weiguo Liu
 *
 */
 
//标记前台请求的地址
@ServerEndpoint("/chatSocket")
public class ChatSocket {

	private String username;
	//这里声明为static类型的变量是为了将sessions整个变量变为公共的变量才能共同使用
	private static List<Session> sessions = new ArrayList<Session>();
	
	private static List<String> names = new ArrayList<String>();
	
	private static Map<String,Session> map = new HashMap<String,Session>();
	
	//在聊天室链接后(即成功创建聊天室的Socket)所要干的事儿
	@OnOpen
	public void open(Session session) {
		 
		//这里的session和Servlet中的session不是同一个session，
		//要获取用户的名字，就要先从Servlet中获取，然后再在请求的时候将参数传过来即可
		//getQueryString()可以将问号后面全部拿出来,在SpringMVC中又是不一样的
		String user = session.getQueryString();
		
		//将username=xx中的xx取出来
		username = user.split("=")[1];
		
		
		//将当前的session放到List中
		this.sessions.add(session);
				
		//将用户添加进List
		this.names.add(username);
		
		this.map.put(this.username, session);
		
		Message msg = new Message();
		msg.setWelcom("欢迎" + this.username + "进入聊天室！");
		msg.setUsernames(names);
		
		//进行广播，将这条消息广播到每一个session中
		broadcast(this.sessions, msg.toJson());
	}
	
	
	
	
	//进行广播,将消息发送给每个session中,需要遍历每一个session
	public void broadcast(List<Session> ss,String msg) {
		Iterator<Session> iterator=ss.iterator();
		while(iterator.hasNext()){
			//获取每个通信通道
			Session session = (Session)iterator.next();
			try {
				//向每个用户广播一条消息
				session.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} 
	
	//session关闭时候的触发的方法
	@OnClose
	public void close(Session session) {
		//在浏览器关闭时需要，将该session从List中移除，否则再进行广播的时候，该还会遍历这
		//个已经移除的session,将会报错
		this.sessions.remove(session);
		this.names.remove(this.username);
		
		Message message = new Message();
		message.setWelcom(this.username + "已退出聊天室！");
		message.setUsernames(this.names);
		
		broadcast(this.sessions, message.toJson());
	}
	
	private static Gson gson = new Gson();
	
	//接受到来自客户端发送的消息
	@OnMessage
	public void message(Session session, String json) {
		
		//将json字符串转成ContentVo对象
		ContentVo vo = gson.fromJson(json, ContentVo.class);
		
		if(vo.getType()==1) {//广播
			Message message = new Message();
			//设置消息的目标人物和内容
			message.setContent(this.username,vo.getMsg());
			
			broadcast(sessions, message.toJson());
		} else {//单聊
			String to = vo.getTo();
			Session to_session = this.map.get(to);
			
			Message message = new Message();
			
			message.setContent(this.username, "<font color=red>私聊:</font>"+vo.getMsg());
			
			try {
				//这里不再是广播，而是选择性进行发送
				to_session.getBasicRemote().sendText(message.toJson());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
}
