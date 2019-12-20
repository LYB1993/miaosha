package com.oliver.entity.eo;

/**
 * com.oliver.entity.eo.LoginStatus
 *
 * @author oliver
 * @date 2019/12/18 15:25
 */
public enum LoginStatus {
    LOGIN(true, "已登录"),
    LOGOUT(false, "以登出"),
    UN_KNOW(false, "未知");

    private boolean status;
    private String msg;

    private LoginStatus(boolean status, String msg) {
        this.msg = msg;
        this.status = status;
    }
}
