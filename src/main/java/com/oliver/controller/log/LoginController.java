package com.oliver.controller.log;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.oliver.controller.log.LoginController
 *
 * @author oliver
 * @date 2019/12/18
 */
@RestController()
@RequestMapping("user")
public class LoginController {

    @GetMapping("login")
    public Object login(@RequestParam("loginId") String loginId,@RequestParam("password")String password){
        return null;
    }
    @GetMapping("logout")
    public Object logOut(String userLoginId) {

        return null;
    }
}
