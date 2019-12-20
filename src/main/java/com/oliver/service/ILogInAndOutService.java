package com.oliver.service;

import com.oliver.entity.eo.LoginStatus;

/**
 * com.oliver.service.ILogInAndOutService
 *
 * @author oliver
 * @date 2019/12/18 15:24
 */
public interface ILogInAndOutService {

    LoginStatus isLogin();

    LoginStatus Login();

    LoginStatus LogOut();
}
