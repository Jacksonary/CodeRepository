package com.hhu.classloader;

/**
 * 用于封装加载类的信息
 * @author Weiguo Liu
 * @data 2017年11月29日
 */
public class LoadInfo {
	
	//自定义的类加载
	private MyClassloader myLoader;
	
	//记录要加载类的时间戳
	private long loadTime;
	
	private BaseManager mannager;
	
	public LoadInfo(MyClassloader myLoader, long loadTime) {
		super();
		this.myLoader=myLoader;
		this.loadTime = loadTime;
	}

	public MyClassloader getMyLoader() {
		return myLoader;
	}

	public void setMyLoader(MyClassloader myLoader) {
		this.myLoader = myLoader;
	}

	public long getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(long loadTime) {
		this.loadTime = loadTime;
	}

	public BaseManager getMannager() {
		return mannager;
	}

	public void setMannager(BaseManager mannager) {
		this.mannager = mannager;
	}
	
}
