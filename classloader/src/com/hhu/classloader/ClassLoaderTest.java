package com.hhu.classloader;

/**
 * 测试类
 * @author Weiguo Liu
 * @data 2017年11月29日
 */
public class ClassLoaderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MsgHandler mh = new MsgHandler();
		Thread t = new Thread(mh); 
		t.start();
	}

}
