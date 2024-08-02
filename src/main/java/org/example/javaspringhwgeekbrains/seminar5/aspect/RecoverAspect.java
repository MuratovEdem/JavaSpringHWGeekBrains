package org.example.javaspringhwgeekbrains.seminar5.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.usertype.DynamicParameterizedType;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@Aspect
@Component
public class RecoverAspect {

    @Pointcut("@annotation(org.example.javaspringhwgeekbrains.seminar5.aspect.Recover)") // method
    public void recoverMethodsPointcut() {}

    @Around(value = "recoverMethodsPointcut()")
    public Object afterTimesheetServiceFindById(ProceedingJoinPoint pjp) {
        try {
            return pjp.proceed();
        } catch (Throwable ex) {
            String className = pjp.getTarget().getClass().getSimpleName();
            String methodName = pjp.getSignature().getName();
            log.info("Recovering {}#{} after Exception[{}, {}]", className, methodName, ex.getClass().getSimpleName(), ex.getMessage());

            Method method = Arrays.stream(pjp.getTarget().getClass().getMethods())
                    .filter(m -> m.getName().equals(methodName))
                    .findFirst()
                    .orElse(null);

            Class<?> returnType = method.getReturnType();

            if (returnType.isPrimitive()) {
                return switch (returnType.getName()) {
                    case "boolean" -> false;
                    case "byte" -> (byte) 0;
                    case "short" -> (short) 0;
                    case "int" -> 0;
                    case "long" -> 0L;
                    case "float" -> 0.0f;
                    case "double" -> 0.0;
                    case "char" -> '\u0000';
                    default -> throw new IllegalArgumentException("Не является примитивным типом: " + returnType.getName());
                };
            }
            return null;
        }


    }
}
