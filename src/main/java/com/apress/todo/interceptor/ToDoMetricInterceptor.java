package com.apress.todo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.MappedInterceptor;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ToDoMetricInterceptor implements HandlerInterceptor {
    private static Logger log = LoggerFactory.getLogger(ToDoMetricInterceptor.class);

    private MeterRegistry registry;
    private String URI, pathKey, METHOD;

    public ToDoMetricInterceptor(MeterRegistry registry){
        this.registry = registry;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response, 
        Object Handler,Exception ex) throws Exception{
            URI = request.getRequestURI();
            METHOD = request.getMethod();
            if(!URI.contains("prometheus")){
                log.info(" >> PATH: {}", URI);
                log.info(" >> METHOD: {}", METHOD);
                pathKey = "api_".concat(METHOD.toLowerCase()).concat(URI.replaceAll("/", "_")
                    .toLowerCase());
                this.registry.counter(pathKey).increment();    
            }
    }

    @Bean
    public MappedInterceptor metricInterceptor(MeterRegistry registry){
        return new MappedInterceptor(new String[]{"/**"}, new ToDoMetricInterceptor(registry));
    }
}
