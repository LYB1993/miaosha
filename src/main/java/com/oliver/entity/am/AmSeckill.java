package com.oliver.entity.am;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * am_seckill
 * @author 
 */
@Data
public class AmSeckill implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 秒杀活动开始时间
     */
    private Date startDatatime;

    /**
     * 秒杀活动结束时间
     */
    private Date endDatatime;

    /**
     * 活动状态-1开启，2关闭
     */
    private Integer status;

    /**
     * 创建日期
     */
    private Date createDatatime;

    private static final long serialVersionUID = 1L;
}