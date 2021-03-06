package com.oliver.entity.vo;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * com.oliver.entity.vo.ResultVO
 *
 * @author oliver
 * @date 2019/12/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO implements Serializable {

    private static final long serialVersionUID = -3774376929091486495L;
    private int code;
    private boolean status;
    private String msg;
    private Object data;

    public static ResultVO success(String msg) {
        return new ResultVO(200,true, StringUtils.isBlank(msg) ? "success" : msg, null);
    }

    public static ResultVO success(Object data, String msg) {
        return new ResultVO(200,true ,StringUtils.isBlank(msg) ? "success" : msg, data);
    }

    public static ResultVO success(Object data) {
        return new ResultVO(200,true, "success", data);
    }

    public static ResultVO error(String msg) {
        return new ResultVO(500,false, StringUtils.isBlank(msg) ? "error" : msg, null);
    }


}
