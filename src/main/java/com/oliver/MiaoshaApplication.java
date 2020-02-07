package com.oliver;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * com.oliver  MiaoshaApplication
 *
 * @author lyb
 * @date 2019/12/18
 */
@SpringBootApplication
@EnableWebSocket
@EnableRabbit
public class MiaoshaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiaoshaApplication.class, args);
    }
}
