package cn.edu.cqvie.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * 方法执行干预类
 *
 * @author ZHENG SHAOHONG
 * @Aspect 告诉Spring当前类是一个切面类
 */
@Aspect
public class LogAspects {

    /**
     * 公共的切入点表达式
     * 1、引用本类
     * 2、其他的切面引用
     */
    @Pointcut("execution (public int cn.edu.cqvie.aop.MathCalculator.*(..))")
    public void pointCut() {

    }

    /**
     * @Before 在目标方法之前切入；切入点表达式（指定那个方法切入）
     */
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println(joinPoint.getSignature().getName() + "除法运行。。。参数列表是：{" + Arrays.asList(args) + "}");
    }

    @After(value = "cn.edu.cqvie.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "除法结束");
    }

    /**
     * JoinPoint 一定要出现在参数列表的第一位
     *
     * @param joinPoint
     * @param object
     */
    @AfterReturning(value = "pointCut()", returning = "object")
    public void logReturn(JoinPoint joinPoint, Object object) {
        System.out.println(joinPoint.getSignature().getName() + "除法正常返回。。。运行结果是：{" + object + "}");
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println(joinPoint.getSignature().getName() + "除法异常。。。异常信息是：{" + exception + "}");
    }
}
