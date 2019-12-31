package com.oliver.entity.am;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * AmSeckillDAO继承基类
 */
@Mapper
@Repository
public interface AmSeckillDAO extends MyBatisBaseDao<AmSeckill, Long> {
}