package com.hhu.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 自定义类加载器来是实现java类的热加载
 * 继承java.lang.ClassLoader，重写其中
 * 的findClass方法
 * 
 * @author Weiguo Liu
 * @data 2017年11月29日
 */
public class MyClassloader extends ClassLoader {

	//要加载的Java类的classpath路径
	private String classpath;
	
	public MyClassloader(String classpath) {
		super(ClassLoader.getSystemClassLoader());
		this.classpath = classpath;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		byte[] data = this.loadClassData(name);
		return super.defineClass(name, data, 0, data.length);
	}
	
	/*
	 * 记载class文件中的内容
	 */
	private byte[] loadClassData(String name) {
		try {
			//因为包名包含.要将它替换成//
			name = name.replace(".", "//");
			//文件输入流
			FileInputStream is =new FileInputStream(new File(classpath + name + ".class"));
			//字节数组输出流
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int b = 0;
			while((b=is.read())!=-1) {
				baos.write(b);
			}
			is.close();
			return baos.toByteArray();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
