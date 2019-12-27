package com.oliver.aop;

import com.oliver.entity.Customer;
import com.oliver.service.IRedisService;
import com.oliver.service.impl.UserRedisService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import javax.annotation.Resource;

/**
 * com.oliver.aop.FreqBuyAop
 *
 * @author oliver
 * @date 2019/12/27 20:00
 */
@Aspect
public class FreqBuyAop {

    @Resource
    private UserRedisService userRedisService;

    @Pointcut("execution(* com.oliver.controller.goods.GoodsController.buyGoods(..))")
    public void pointAspect() {

    }

    @Before("pointAspect()")
    public void before(JoinPoint point) {
        Object arg = point.getArgs()[0];
        boolean freq = userRedisService.freq(arg.toString());
        if (freq) {

        }
    }

    @Around("pointAspect()")
    public Object around(ProceedingJoinPoint pjp) {
        Object arg = pjp.getArgs()[0];
        boolean freq = userRedisService.freq(arg.toString());
        if (freq) {
            try {
                pjp.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }

}
