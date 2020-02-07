package com.oliver.entity.am;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * am_seckill_push
 * @author 
 */
@Data
public class AmSeckillPush implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 会员id
     */
    private Integer memberId;

    /**
     * 商品id
     */
    private Long seckillProductId;

    /**
     * 会员电话
     */
    private String memberPhone;

    /**
     * 商品名称
     */
    private String seckillProductName;

    /**
     * 会员订阅时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date subscribeTime;

    /**
     * 发送时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /**
     * 创建日期
     */
    private Date createDatatime;

    private static final long serialVersionUID = 1L;
}