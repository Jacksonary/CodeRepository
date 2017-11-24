package com.hhu.jedis.jedis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo1 {

	/*
	 * 单个链接的方式
	 */
	@Test
	public void demo1() {
		//设置IP地址和端口
		Jedis jedis = new Jedis("10.4.62.6",6379);
		//保存数据
		jedis.set("name", "Hello Redis!");
		//获取值
		String value = jedis.get("name");
		jedis.del("name");
		System.out.println(value);
		//释放资源
		jedis.close();
	}
	
	/*
	 * 使用连接池链接
	 */
	@Test
	public void demo2() {
		//创建连接池的配置对象
		JedisPoolConfig config = new JedisPoolConfig();
		//设置最大连接数
		config.setMaxTotal(30);
		//设置最大空闲连接数
		config.setMaxIdle(10);
		
		//获得连接池
		JedisPool jedisPool = new JedisPool(config, "10.4.62.6", 6379);
		
		//获取和新对象
		Jedis jedis = null;
		try {
			//通过连接池获得jedis
			jedis = jedisPool.getResource();
			jedis.set("jedisPool", "利用jedisPool设置的值");
			System.out.println(jedis.get("jedisPool"));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis!=null) {
				jedis.close();
			}
			if(jedisPool!=null) {
				jedisPool.close();
			}
		}
	}
}
