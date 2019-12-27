package com.oliver.controller.mq;

import com.oliver.mq.IMqService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * com.oliver.controller.mq.MqController
 *
 * @author oliver
 * @date 2019/12/27 19:40
 */
@RestController
@RequestMapping("/mq")
public class MqController {

    @Resource
    private IMqService mqService;

    @PostMapping("/msg")
    public String sendMsg(String msg) {
        mqService.send();
        return "1";
    }
}
