package com.example.speciesrest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class getAspect {
        @After("execution(* com.example.speciesrest..get*(..))")
        public void getLog(JoinPoint joinPoint) {
            System.out.println("get appelé : " + joinPoint);
        }
        @After("execution(public * com.example.speciesrest.controller.*.*(..))")
        public void methodNameLog(JoinPoint joinPoint) {
            System.out.println(joinPoint.getSignature().getName());
        }
        @AfterThrowing(value = "execution(public * com.example.speciesrest.service.AnimalService.create(..))", throwing = "e")
        public void exceptionLog(JoinPoint joinPoint, Throwable e) {
            System.out.println(e.getMessage() + " // joinPoint : " + joinPoint);
        }

        @Around(value = "execution(public * com.example.speciesrest.service.*Service.*(..))")
        public void serviceMethodChrono(ProceedingJoinPoint joinPoint) throws Throwable {
            System.out.println(joinPoint.getSignature().getName());
            long start = System.currentTimeMillis();
            System.out.println("start : " + start);
            try{
                joinPoint.proceed();
            } finally {
                long end = System.currentTimeMillis();
                System.out.println("end : " + end);
                long duration = end - start;
                System.out.println("Duration : " + duration + " milliseconds");
            }
        }

}
