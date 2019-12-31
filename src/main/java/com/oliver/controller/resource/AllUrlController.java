package com.oliver.controller.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * com.oliver.controller.resource.AllUrlController
 *
 * @author oliver
 * @date 2019/12/30 14:13
 */
@RestController
@RequestMapping("url")
public class AllUrlController {

    @Resource
    private RequestMappingHandlerMapping mapping;

    @GetMapping("all")
    public Object getAllUrl() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
        Set<String> sets = new HashSet<>();
        handlerMethods.keySet().forEach(handlerMethod -> sets.addAll(handlerMethod.getPatternsCondition().getPatterns()));
        return sets;
    }
}
