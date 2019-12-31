package com.oliver.entity.am;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * AmSeckillPushDAO继承基类
 */
@Mapper
@Repository
public interface AmSeckillPushDAO extends MyBatisBaseDao<AmSeckillPush, Long> {
}