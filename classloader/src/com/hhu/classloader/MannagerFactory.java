package com.hhu.classloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 加载mannager的工厂
 * @author Weiguo Liu
 * @data 2017年11月29日
 */
public class MannagerFactory {
	//记录热加载类的加载信息
	private static final Map<String,LoadInfo> loadTimeMap = new HashMap<String,LoadInfo>();
	
	//这个路径就是该Project所在目录下的bin目录，因为工程下的java文件编译后的class文件就是生成到这个目录下面的
	public static final String CLASSPATH = "D:/我的文档/Eclipse wokspace/classloader/bin/"; 

	//实现热加载的全名称(包名+类名)
	public static final String MY_MANAGER = "com.hhu.classloader.MyMannager";
	
	public static BaseManager getManager(String className) {
		//将.转化为/
		File loadFile = new File(CLASSPATH + className.replaceAll("\\.", "/") + ".class");
		long lastModified = loadFile.lastModified();
		//loadTimeMap中不包含className为key的loadInfo信息，证明这个类没有被加载，需要加载这个类到JVM
		if(loadTimeMap.get(className)==null) {
			load(className, lastModified);
		} else if(loadTimeMap.get(className).getLoadTime()!=lastModified) {
			//如果时间戳发生变化了，那也要重新加载
			load(className, lastModified);
		}
		return loadTimeMap.get(className).getMannager();
	}
	
	private static void load(String className, long lastModified) {
		MyClassloader myClassloader = new MyClassloader(CLASSPATH);
		Class<?> loadClass = null;
		try {
			/*
			 * 如果使用loadClass那么跑测试程序时必须使用Debug模式
			 * 如果使用findClass那么跑测试程序是可以直接Run As java Application
			 */
//			loadClass = myClassloader.loadClass(className);
			loadClass = myClassloader.findClass(MY_MANAGER);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		BaseManager manager = newInstance(loadClass);
		LoadInfo loadInfo = new LoadInfo(myClassloader, lastModified);
		loadInfo.setMannager(manager);
		loadTimeMap.put(className, loadInfo);
	}
	
	//利用反射创建BaseManager子类对象
	private static BaseManager newInstance(Class<?> loadClass) {
		try {
			return (BaseManager)loadClass.getConstructor(new Class[]{}).newInstance(new Object[]{});
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
