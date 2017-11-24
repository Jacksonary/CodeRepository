package com.hhu.config;

import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

//这里实现的是ServerApplicationConfig，有两个方法，一种是注解启动，一种接口启动
//这是一个启动类
public class DemoConfig implements ServerApplicationConfig {

	//注解方式 启动,这里它是自动执行的,自动扫描带有@ServerEndPoint（即终端）注解的类来自动注册
	//这里我们通常都是用注解的方式来完成，有几个类（加了注解的）scan的size（）就是多少
	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scan) {
		System.out.println("config.........." + scan.size());
		
		//返回scan,如果返回，服务器不会帮助注册任何Socket,提供了一定的过滤作用
		return scan;
	}

	//接口方式 启动,由于使用了注解的方式，那这个接口的方式我们不予关注
	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
