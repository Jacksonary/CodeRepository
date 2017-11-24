package com.hhu;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hhu.dao.ProductDao;
import com.hhu.domain.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatalogApplicationTests {

	@Autowired
	ProductDao productDao;
	
	@Test
	public void testInsert() {
		Product p = new Product();
		p.setName("dell computer");
		p.setOnlineTime(new Date());
		p.setBuyPrice(new BigDecimal("29.5"));
		p.setCategory("computer");
		p.setDetail("This DELL notebook");
		p.setUpdateTime(new Date());
		p.setProvider("Dell");
		p.setSellPrice(new BigDecimal("35.2"));
		if(p!=null) {
			productDao.save(p);
		}
	}
	
	@Test
	public void testDelete() {
		productDao.delete(3L);
	}
	
	@Test
	public void testUpdate() {
		Product p = productDao.findOne(3L);//这里传进去的是数据库中的Product的id
		p.setName("testUpdate");
		p.setBuyPrice(new BigDecimal("500.23"));
		p.setOnlineTime(new Date());
		productDao.save(p);
	}

}
