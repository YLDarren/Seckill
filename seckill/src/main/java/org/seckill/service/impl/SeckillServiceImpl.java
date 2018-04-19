package org.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entiy.Seckill;
import org.seckill.entiy.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;


/**
 * 秒杀业务实现类
 * 
 * @author admin
 *
 */
@Service
public class SeckillServiceImpl implements SeckillService {

	private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SeckillDao seckillDao;

	@Autowired
	private SuccessKilledDao successKilledDao;

	// md5盐值字符串，用户混淆字符串
	private final String salt = "njihdysdyh^&5643*(&Y@^^%Busw32%8hxuiu";

	@Override
	public List<Seckill> getSeckillList() {

		return seckillDao.queryAll(0, 5);
	}

	@Override
	public Seckill getById(long seckillId) {

		return seckillDao.queryById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(long seckillId) {

		Seckill seckill = seckillDao.queryById(seckillId);

		if (seckill == null) {
			return new Exposer(false, seckillId);
		}

		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();

		if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}

		String md5 = getMD5(seckillId);// 转化特定字符串，不可逆
		return new Exposer(true, seckillId, md5);
	}

	/**
	 * 根据id获取md5值
	 * 
	 * @param seckillId
	 * @return
	 */
	private String getMD5(long seckillId) {
		String base = seckillId + "/" + salt;

		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());

		return md5;
	}

	@Override
	/**
	 * 使用注解控制事务方法的优点：
	 * 	1、开发团队达成一致约定，明确标注事务方法的编程风格
	 *  2、保证事务方法的执行时间尽可能短，不要穿插其他的网络请求
	 *  3、不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务操作
	 */
	@Transactional
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {

		if( md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("seckill data rewrite");
		}
		
		try {
			
//			执行秒杀逻辑：减库存+记录购买行为
			Date now = new Date();
			int updateCount = seckillDao.reduceNumber(seckillId, now);
			if( updateCount <= 0 ) {
//				没有更新到记录,秒杀结束
				throw new SeckillCloseException("seckill is close");
			}else {
//				记录购买行为
				int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
				
				if( insertCount <= 0 ) {
//					重复秒杀
					throw new RepeatKillException("seckill repeat");
				}else {
//					秒杀成功
					SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution( seckillId , SeckillStateEnum.SUCCESS , successKilled );
				}
			}
			
		}catch(SeckillCloseException e1) {
			throw e1;
			
		}catch(RepeatKillException e2) {
			throw e2;
			
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
//			所有编译期异常转化为运行期异常
			throw new SeckillException("seckill inner error:"+e.getMessage());
		}
		
	}

}
