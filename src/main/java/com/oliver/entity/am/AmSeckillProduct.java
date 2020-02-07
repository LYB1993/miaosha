package com.oliver.entity.am;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * am_seckill_product
 * @author
 */
@Data
public class AmSeckillProduct implements Serializable {
    /**
     * 主键
     */
    private Long id;

    private Long seckillId;

    /**
     * 商品id
     */
    private Long seckillProductId;

    /**
     * 秒杀商品价格
     */
    private BigDecimal seckillProductPrice;

    /**
     * 秒杀商品数量
     */
    private Integer seckillCount;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 创建日期
     */
    private Date createDatetime;

    private static final long serialVersionUID = 1L;
}
