package com.oliver.entity.am;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AmSeckillProductDAO继承基类
 */
@Mapper
@Repository
public interface AmSeckillProductDAO extends MyBatisBaseDao<AmSeckillProduct, Long> {

    List<AmSeckillProduct> selectSecKillProducts(Long seckillId);

}
