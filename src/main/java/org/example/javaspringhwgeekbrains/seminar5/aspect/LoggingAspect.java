package org.example.javaspringhwgeekbrains.seminar5.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j // Slf4j - Simple logging facade for java
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* org.example.javaspringhwgeekbrains.seminar5.service.TimesheetService.*(..))")
    public void timesheetServiceMethodsPointcut() {

    }

    // Pointcut - точка входа в аспект
    @Before(value = "timesheetServiceMethodsPointcut()")
    public void beforeTimesheetServiceFindById(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        StringBuilder argsBuilder = new StringBuilder();

        for (Object arg: args) {
            if (!argsBuilder.isEmpty()) {
                argsBuilder.append(", ");
            }
            argsBuilder.append(arg.getClass().getSimpleName()).append(" = ").append(arg);
        }

        log.info("Before -> TimesheetService#{}({})", methodName, argsBuilder);
    }

    @AfterThrowing(value = "timesheetServiceMethodsPointcut()", throwing = "ex")
    public void afterTimesheetServiceFindById(JoinPoint jp, Exception ex) {
        String methodName = jp.getSignature().getName();
        log.info("AfterThrowing -> TimesheetService#{} -> {}", methodName, ex.getClass().getName());
    }
}
