package com.misakamikoto.layout.category.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class CategoryAspect {
	
	@Before("execution(* com.misakamikoto.*.category.CategoryController.list(..))")
    public void beforeLog(JoinPoint joinPoint) {
		System.out.println("execute method name : " + joinPoint.getSignature().getName());
    }
}
