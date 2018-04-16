package org.seckill.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entiy.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {
	
	//注入dao实现类依赖
	@Resource
	private SeckillDao seckillDao;
	
	@Test
	public void testQueryAll() {
		long id = 1000;
		Seckill seckill = seckillDao.queryById(id);
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}
	
	@Test
	public void queryAll() {
		List<Seckill> seckills = seckillDao.queryAll(0, 10);
		
		for(Seckill s : seckills) {
			System.out.println(s);
		}
	}
	
	
	
	@Test
	public void testReduceNumber() {
		Date killTime = new Date();
		int num = seckillDao.reduceNumber(1004L, killTime);
		System.out.println(num);
	}
}
