package com.oliver.entity.am;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * AmSeckillProductDAO继承基类
 */
@Mapper
@Repository
public interface AmSeckillProductDAO extends MyBatisBaseDao<AmSeckillProduct, Long> {
}