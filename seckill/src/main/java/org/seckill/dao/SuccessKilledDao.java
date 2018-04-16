package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entiy.SuccessKilled;

//成功秒杀的dao接口
public interface SuccessKilledDao {
	
	/**
	 * 插入购买明细，可过滤重复
	 * @param seckillId
	 * @param userPhone
	 * @return
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId ,@Param("userPhone") long userPhone);
	
	/**
	 * 根据seckillId查询成功秒杀并携带秒杀产品对象
	 * @param seckillId
	 * @return
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId , @Param("userPhone") long userPhone );
	
	
}
