package hello.proxy.aop.exam.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class NotNullAspect {

    @Before("@annotation(hello.proxy.aop.exam.annotation.NotNull) && execution(* hello.proxy.aop.exam.dto.ExamDto.*.Builder.build())")
    public void isNotNull(JoinPoint joinPoint) throws Throwable {
        log.info("testtest");
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg == null) {
                throw new IllegalArgumentException("Field cannot be null");
            }
        }
    }

}
