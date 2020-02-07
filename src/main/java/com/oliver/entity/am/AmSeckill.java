package com.oliver.entity.am;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * am_seckill
 *
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDatetime;

    /**
     * 秒杀活动结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDatetime;

    /**
     * 活动状态-1开启，2关闭
     */
    private Integer status;

    /**
     * 创建日期
     */
    private Date createDatetime;

    private static final long serialVersionUID = 1L;
}
