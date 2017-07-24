package com.misakamikoto.layout.category.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by Misaka on 2017-03-14.
 */
@Aspect
public class CategoryAspect {
    /**
     * Before log.
     *
     * @param joinPoint the join point
     */
    @Before("execution(* com.misakamikoto.*.category.CategoryController.list(..))")
    public void beforeLog(JoinPoint joinPoint) {
        System.out.println("execute method name : " + joinPoint.getSignature().getName());
    }
}
