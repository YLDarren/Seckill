package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entiy.SuccessKilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")

public class SuccessKilledDaoTest {

	@Autowired
	private SuccessKilledDao successKilledDao;
	
	@Test
	public void testInsertSuccessKilled() {
		
		int num = successKilledDao.insertSuccessKilled(1000L, 10753224349L);
		
		System.out.println(num);
	}

	@Test
	public void testQueryByIdWithSeckill() {
		SuccessKilled s = successKilledDao.queryByIdWithSeckill(1000L,10753224349L);
		
		System.out.println(s);
	}

}
