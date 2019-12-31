package com.oliver.controller.log;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * com.oliver.controller.log.LoginController
 *
 * @author oliver
 * @date 2019/12/18
 */
@RestController()
@RequestMapping("user")
public class LoginController {

    @Resource
    private SecurityManager securityManager;


    @GetMapping("logout")
    public Object logOut(String userLoginId) {

        return null;
    }

    @RequestMapping("/index")
    public Object index(){
        return "index";
    }
}
