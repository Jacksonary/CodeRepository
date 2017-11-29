package com.hhu.classloader;

/**
 * 该类已经实现了BaseManager这个接口
 * 所以这个类具备热加载的功能
 * 
 * 这里需要将将Eclipse中自动编译的功能开启
 * 
 * @author Weiguo Liu
 * @data 2017年11月29日
 */
public class MyMannager implements BaseManager {

	@Override
	public void logic() {
		System.out.println("这个类具备了热加载的特2");
	}

}
