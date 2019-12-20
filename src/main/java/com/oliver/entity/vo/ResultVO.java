package com.oliver.entity.vo;

import lombok.Data;

/**
 * com.oliver.entity.vo.ResultVO
 *
 * @author oliver
 * @date 2019/12/18
 */
@Data
public class ResultVO {
    private int code;
    private String msg;
    private Object data;
}
