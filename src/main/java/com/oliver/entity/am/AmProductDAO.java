package com.oliver.entity.am;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * AmProductDAO继承基类
 */
@Mapper
@Repository
public interface AmProductDAO extends MyBatisBaseDao<AmProduct, Long> {
}
