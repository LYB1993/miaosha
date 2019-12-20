package com.oliver.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * com.oliver.entity.vo.GoodsVO
 *
 * @author oliver
 * @date 2019/12/18
 */
@Data
public class GoodsVO {
    private int id;
    private BigDecimal price;
    private int number;
    private GoodsStatus status = GoodsStatus.HAVE;

    private static enum GoodsStatus {
        HAVE,
        NO_HAVE;
    }
}
