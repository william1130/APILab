package org.wm.apilab.filter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TimeAspect {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* org.wm.apilab.controller ..*(..) )")
    public Object handleControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println(">>>>>>>Time Aspect-Around Start.");

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }

        long startTime = System.currentTimeMillis();

        Object object = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        System.out.println(">>>>>>>Time Aspect-Around Consume " + (endTime - startTime) + " ms");

        System.out.println(">>>>>>>Time Aspect-Around End");

        return object;
    }

    @Before("execution(* org.wm.apilab.controller ..*(..) )")
    public void beforeLog(){
        System.out.println(">>>>>>>Time Aspect-Before.");
    }

    @After("execution(* org.wm.apilab.controller ..*(..) )")
    public void afterLog(){
        System.out.println(">>>>>>>Time Aspect-After.");
    }
}
