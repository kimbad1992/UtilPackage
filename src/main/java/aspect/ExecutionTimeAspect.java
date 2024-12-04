package aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Aspect
@Component
@Slf4j
public class ExecutionTimeAspect {

    @Around("@annotation(com.myutilpackage.annotations.TrackExecutionTime")
    public Object trackingTime(final ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = pjp.proceed();

        long end = System.currentTimeMillis();
        double executionTimeInSecs = (end - start) / 1000.0;

        BigDecimal bd = new BigDecimal(executionTimeInSecs)
                .setScale(2, RoundingMode.HALF_UP);

        log.debug("{} executed in {}s", pjp.getSignature().getName(), bd.doubleValue());
        return proceed;
    }
}
