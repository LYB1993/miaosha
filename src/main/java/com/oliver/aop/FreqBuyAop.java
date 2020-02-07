package com.oliver.aop;

import com.oliver.entity.am.AmSeckill;
import com.oliver.entity.vo.ResultVO;
import com.oliver.mq.IMqProducerService;
import com.oliver.service.validation.ValidationService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private ValidationService userRedisService;

    @Resource
    private IMqProducerService<AmSeckill> service;

    @Pointcut("execution(* com.oliver.controller.goods.GoodsController.buyGoods(..))")
    public void pointAspect() {

    }

    @Pointcut("execution(* com.oliver.controller.am.AmController.*Am(..))")
    public void amAspect() {

    }


    @Around("pointAspect()")
    public Object around(ProceedingJoinPoint pjp) {
        Object arg = pjp.getArgs()[0];
        Object arg1 = pjp.getArgs()[1];
        boolean freq = userRedisService.freq(arg.toString());
        if (!freq) {
            return ResultVO.error("操作频繁");
        }
        boolean isStart = userRedisService.isStart(Long.parseLong((String) arg1));
        if (!isStart) {
            return ResultVO.error("活动未开始");
        }
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            LOGGER.error("around exception :{}", throwable.getMessage());
            throwable.printStackTrace();
        }
        return ResultVO.error("校验异常");
    }

    @After("amAspect()")
    public void amAround(JoinPoint pjp) {
        List<Object> list = Arrays.asList(pjp.getArgs());
        List<Object> collect = list.stream().filter(o -> o instanceof AmSeckill).collect(Collectors.toList());
        if (!collect.isEmpty() && !isDelMethod(pjp)) {
            AmSeckill amSeckill = (AmSeckill) collect.get(0);
            if (amSeckill.getId() != null) {
                service.send(amSeckill);
            }
        }
    }

    private boolean isDelMethod(JoinPoint point) {
        return StringUtils.contains(point.getSignature().getName().toLowerCase(), "del");
    }

}
