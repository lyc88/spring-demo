package cn.edu.cqvie.config;

import cn.edu.cqvie.aop.LogAspects;
import cn.edu.cqvie.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *
 * AOP : [动态代理]
 *
 *   指在程序运行期间动态的将某段代码切入到指定方法位置进行运行的编程方式；
 *
 *  1、导入AOP模块: Spring AOP (spring-aspects)
 *  2、定义一个逻辑类(MathCalculator)；在运行的业务逻辑过程中打印（方法运行之前，方法异常等）
 *  3、定义一个日志切面类（LogAspects），切面类里面的方法需要动态感知 MathCalculator.div 运行到那一步，执行对应的日志逻辑
 *
 *         通知方法：
 *             前置通知：(@Before) logStart, 在目标方法（dev）运行之前执行
 *             后置通知：(@After) logEnd: 在目标方法（dev）运行结束之后执行 (不论是否正常结束，都要执行)
 *             返回通知：(@AfterReturning) logReturn: 在目标方法（dev）正常返回之后执行
 *             异常通知：(@AfterThrowing) logException: 在目标方法（dev）出现异常以后执行
 *             环绕通知：(@Around) 动态台灯里，手动推进目标方法运行（joinPoint.procced()）
 * 4、给切面类的目标方法标注何时运行（通知注解）：
 * 5、切面类、业务逻辑类（目标方法所在类）都加入容器中
 * 6、必须告诉Spring那个类是切面类（给切面类上加一个注解： @Aspect）
 * 7、给配置类加一个注解；@EnableAspectJAutoProxy (基于注解的AOP模式)
 *      在Spring中的很多 @EnableXXX;
 *
 * 三步：
 *   1）、将业务逻辑组件和切面类都加入到容器中，告诉Spring那个类似切面类（@Aspect）
 *   2）、在切面类上的每一个通知方法上标注通知注解，告诉Spring 何时何地运行（切入点表达式）
 *   3）、开启基于注解的AOP模式: @EnableAspectJAutoProxy
 *
 * AOP 原理 【容器中注册了哪些组件，这个组件提供了哪些功能】
 *
 *    @EnableAspectJAutoProxy
 * 1、@EnableAspectJAutoProxy 是什么？
 *    @Improt(AspectJAutoProxyRegistrar.class): 给容器中导入 AspectJAutoProxyRegistrar
 *        利用 AspectJAutoProxyRegistrar 自定义给容器中注册Bean;
 *        internalAutoProxyCreator = AnnotationAwareAspectJAutoProxyCreator
 *    给容器中注册一个 AnnotationAwareAspectJAutoProxyCreator
 *
 * 2、AnnotationAwareAspectJAutoProxyCreator
 *         AnnotationAwareAspectJAutoProxyCreator
 *             -> AspectJAwareAdvisorAutoProxyCreator
 *                 -> AbstractAdvisorAutoProxyCreator
 *                    -> AbstractAutoProxyCreator
 *                          implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                          关注后置处理器（在Bean初始化完成前后桌的事情，自动装配BeanFactoryAware）
 *
 * AbstractAutoProxyCreator.setBeanFactory()
 * AbstractAutoProxyCreator.有后置处理器的逻辑
 *
 * AbstractAdvisorAutoProxyCreator.setBeanFactory() -> initBeanFactory()
 *
 * AspectJAwareAdvisorAutoProxyCreator
 *
 * AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()
 *
 * 流程:
 *  1、传入配置类，创建IOC容器
 *  2、注册配置类，调用refresh() 刷新容器
 *  3、registerBeanPostProcessors(beanFactory); 注册bean的后置处理器来方便拦截bean的创建。
 *      1）、现获取IOC容器已经定义了的需要创建对象的所有BeanPostProcessor。
 *      2）、给容器中加载别的BeanPostProcessor。
 *      3）、优先注册实现了PriorityOrdered接口的BeanPostProcessor。
 *      4）、再给容器中注册实现了Ordered接口的BeanPostProcessor。
 *      5）、注册没有实现优先级接口的BeanPostProcessor。
 *      6）、注册BeanPostProcessor, 实际上就是车床件BeanPostProcessor对象，保存到容器中。
 *         创建 internalAutoProxyCreator 的BeanPostProcessor
 *            1）、创建Bean的实例
 *            2）、populateBean  给Bean的各种属性赋值
 *            3）、initializeBean： 初始化Bean
 *                 1）、invokeAwareMethods： 处理Aware接口方法回调
 *                 2）、applyBeanPostProcessorsBeforeInitialization（）） 应用后置处理的 postProcessBeforeInitialization（）
 *                 3）、invokeInitMethods() :指定自定义的初始化方法
 *                 4）、applyBeanPostProcessorsAfterInitialization（） 执行后置处理器的 postProcessAfterInitialization（）；
 *            4）、BeanPostProcessor(AnnotationAwareAspectAutoAProxyCreator) 创建成功 --> aspectJAdvisorsBuilder
 *      7)、把BeanPostProcessor注册到BeanFactory中
 *        beanFactory.addBeanPostProcessor(postProcessor);
 *  ============================= 以上是创建和注册 AnnotationAwareAspectJAutoProxyCreator的过程 ===========================
 *
 *  4、finishBeanFactoryInitialization (beanFactory) 完成BeanFactory初始化工作，创建剩下的单实例Bean
 *  5、
 *  6、
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

    /**
     * 业务逻辑类
     * @return
     */
    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

    /**
     * 切面类
     *
     * @return
     */
    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }
}
