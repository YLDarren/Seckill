package org.seckill.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entiy.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class SeckillServiceImplTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SeckillService seckillService;

	@Test
	public void testGetSeckillList() {
		List<Seckill> list = seckillService.getSeckillList();

		// logger.info("list={}", list);

		for (Seckill s : list) {
			System.out.println(s);
		}
	}

	@Test
	public void testGetById() {
		Seckill seckill = seckillService.getById(1000L);

		// logger.info("seckill={}", seckill);

		System.out.println(seckill);
	}

	@Test
	public void testExportSeckillUrl() {
		Exposer exposer = seckillService.exportSeckillUrl(1004L);

		System.out.println(exposer);
		// 1326c823c4ef037bbe78348bf08c5aaa
	}

	@Test
	public void testExecuteSeckill() {
		SeckillExecution seckillExecution = seckillService.executeSeckill(1004L, 18724532378L,
				"1326c823c4ef037bbe78348bf08c5aaa");

		System.out.println(seckillExecution);

	}
	
	@Test
	public void testSeckillLogic() {
		Exposer exposer = seckillService.exportSeckillUrl(1000L);
		
		if(exposer.isExposed()) {
			System.out.println(exposer.getSeckillId());
			
			try {
				SeckillExecution seckillExecution = seckillService.executeSeckill(1004L, 18726782378L,
						exposer.getMd5());
				
				System.out.println(seckillExecution);
			}catch(RepeatKillException e1) {
				System.out.println(e1);
			}catch(SeckillCloseException e2) {
				System.out.println(e2);
			}
			
		}else {
			System.out.println("秒杀开启/或以结束");
		}
		
	}

}
