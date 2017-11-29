package com.hhu.classloader;

/**
 * 实现热加载的扫描线程类
 * 
 * 后台会不断的刷新实现BaseMannager接口的类
 * 
 * @author Weiguo Liu
 * @data 2017年11月29日
 */
public class MsgHandler implements Runnable {

	@Override
	public void run() {
		while (true) {
			BaseManager manager = MannagerFactory.getManager(MannagerFactory.MY_MANAGER);
			manager.logic();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
