package com.oliver.aop;

import com.oliver.entity.vo.ResultVO;
import com.oliver.service.impl.UserRedisService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * com.oliver.aop.FreqBuyAop
 *
 * @author oliver
 * @date 2019/12/27 20:00
 */
@Aspect
@Component
public class FreqBuyAop {

    private static final Logger LOGGER = LoggerFactory.getLogger(FreqBuyAop.class);

    @Resource
    private UserRedisService userRedisService;

    @Pointcut("execution(* com.oliver.controller.goods.GoodsController.buyGoods(..))")
    public void pointAspect() {

    }


    @Around("pointAspect()")
    public Object around(ProceedingJoinPoint pjp) {
        Object arg = pjp.getArgs()[0];
        boolean freq = userRedisService.freq(arg.toString());
        if (!freq) {
            return ResultVO.error("操作频繁");
        }
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            LOGGER.error("around exception :{}", throwable.getMessage());
            throwable.printStackTrace();
        }
        return ResultVO.error("校验异常");
    }

    @After("pointAspect()")
    public void after(){

    }

}
