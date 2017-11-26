package com.hhu.dao.cache;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.hhu.entity.Station;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 通过Redis来传递和获取对象
 * @author Weiguo Liu
 *
 */
public class RedisDao {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	//jedis是redis对java端的支持
	private JedisPool jedisPool;
	
	//Redis的构造方法，在Spring配置文件中已经配置过ip和port,所以使用的时候直接注入即可
	public RedisDao(String ip, int port) {
		jedisPool = new JedisPool(ip, port);
	}
	
//	private static int i = 0;
	
	private RuntimeSchema<Station> schema = RuntimeSchema.createFrom(Station.class);
	
	//从redis中拿到station对象,反序列化
	public Station getStation(int fId){
		//redis逻辑操作
		try{
			Jedis jedis = jedisPool.getResource();
			try {
				String key = "station:" + fId;
				//并没有实现内部序列化操作
				//get ->byte[]拿到字节数组  ->反序列化 ->Object(Station)反序列化为对象
				//采用自定义的序列化,protostuff:pojo
				byte[] bytes = jedis.get(key.getBytes());
				//缓存重新获取,将一个字节数组转换成Station对象
				if(bytes!=null){
					//这种自定义的序列化方式比原生的JDK那种实现序列化接口的方式好很多：空间可以压缩到原来的1/10到1/5，压缩速度可以达到2个数量级，更节省CPU
					//new一个Station类的空对象
					Station station = schema.newMessage();
					//将数据传入到空对象中station中,station此时已经被赋值，反序列化
					ProtostuffIOUtil.mergeFrom(bytes, station, schema);
					//Station被反序列
					return station;
				}
			} finally {
				jedis.close();
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	//获取redis中的所有存储对象
	public List<Station> getAllStation() { 
		//从Redis的连接池中获取redis的连接对象，之后的操作直接通过这个对象对redis进行即可
		try {
			Jedis jedis = jedisPool.getResource();
			List<Station> list = new ArrayList<Station>();
			try {
				
				//获取redis中key的集合,TreeSet会自动排序
				//Set s = jedis.keys("*");
				TreeSet<String> s= new TreeSet<String>(jedis.keys("*"));
				
				Iterator<String> it = s.iterator();
				
				while(it.hasNext()) {
					String key = (String)it.next();
					
					byte[] bytes = jedis.get(key.getBytes());
					if(bytes!=null) {
						//new一个Station类的空对象
						Station station = schema.newMessage();
						//将数据传入到空对象中station中,station此时已经被赋值，反序列化
						ProtostuffIOUtil.mergeFrom(bytes, station, schema);
						list.add(station);
						
						System.out.println(key + ":" + station.getfX());
					}
				}
				return list;
				
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} 
		return null;
	}
	
	//获取所有站点的集合
	public static List<List<Station>> getAllStationList() {
		
		List<List<Station>> list = new ArrayList<List<Station>>();
		
		
		
		return list;
	}
	
	//序列化
	public String putStation(Station station){
		//set Object(Station)拿到对象-->序列化-->byte[]序列化为字节数组
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				//设置redis中key值
				String key = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分ss秒").format(new Date().getTime());
						
				byte[] bytes = ProtostuffIOUtil.toByteArray(station, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));//最后一个参数是缓存器
				
				//超时缓存,这里设置了缓存时间,指定key的（存活）有效期，单位是秒，超过指定时间后的key将自动删除
				int timeout = 30;
				//正确的话返回“OK”,错误返回错误信息
				String result = jedis.setex(key.getBytes(), timeout, bytes);
				
				return result;
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
