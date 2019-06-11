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
 *     1)、遍历获取容器所有的Bean，依次创建getBean(beanName)
 *        getBean -> doGetBean() -> getSingleton()
 *     2)、创建Bean
 *        【AnnotationAwareAspectJAutoProxyCreators 所在的Bean创建之前会有一个拦截 InstantiationAwareBeanPostProcessor 会调用 postProcessBeforeInstantiation()】
 *        1）、先从缓存中获取当前Bean是否被创建，如果可以获取到，说明Bean是被创建过的，直接使用，否则在创建
 *        只要被创建过的Bean都会被缓存起来
 *        2）、createBean() 创建Bean，AnnotationAwareAspectJAutoProxyCreator会在任何Bean创建之前现尝试返回bean的实例
 *          【BeanPostProcessor】是在Bean对象创建完成初始化前后调用的
 *          【InstantiationAwareBeanPostProcessor】创建Bean实例之前现尝试使用后置处理器返回对象
 *           1)、resolveBeforeInstantiation(beanName, mbdToUse); 解析 BeforeInstantiation
 *             希望后置处理器能在此返回一个代理对象；如果能够返回代理对象就使用，如果不能就继续第二步 doCreateBean()
 *              1）、后置处理器来尝试现返回对象
 *              bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 *                  拿到所有的后置处理器，如果是 InstantiationAwareBeanPostProcessor 就继续执行后置处理器的 postProcessBeforeInstantiation()
 *              if (bean != null) {
 * 						bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *              }
 *           2)、doCreateBean(beanName, mbdToUse, args); 真正的去穿件一个Bean实例；和3.6的流程是一样的
 *           3、
 *
 *   【InstantiationAwareBeanPostProcessor】的作用：
 *     1）、每一个bean创建之前都会调用 postProcessBeforeInstantiation()
 *         关心 MathCalculator、LogAspect 的创建
 *         1）、判断当前Bean是否在advisedBean中 （保存了所有需要增强的bean ）
 *         2) 、 判断当前Bean是否是基础的类型 Advice、Pointcut、Advisor、AopInfrastructureBean 或者是否是切面 （@Aspect）
 *         3）、 是否需要跳过
 *           1）、获取候选的增强器（切面里面的通知方法 【List<Advisor> candidateAdvisors】）
 *             每一个封装的通知方法的增强器是 InstantiationModelAwarePointcutAdvisor
 *             判断每个增强器是否是AspectJPointcutAdvisor的类型返回true
 *           2)、永远返回false
 *     2)、创建对象
 *     postProcessAfterInitialization;
 *       return wrapIfNecessary(bean, beanName, cacheKey); // 包装，如果需要的情况下
 *       1）、获取当前bean的所有增强器（通知方法）
 *          1、找到能在当前Bean候选的增强器（找到哪些通知方法是需要切入当前通知方法的）
 *          2、获取到在当前Bean可用的增强器
 *          3、给增强器排序
 *      2）、保存bean 在advisedBeans;
 *      3)、如果bean 需要增强，创建代理对象
 *      4)、如果当前bean 需要代理，创建当前bean的代理对象；
 *        1）、回去所有增强器（通知方法）
 *        2）、保存到proxyTypes
 *        3) 、创建代理对象: Spring 自动决定
 *           JDkDynamicAopProxy(config)  jdk 动态代理
 *           ObjenesisCglibAopProxy(config) cglib 动态代理
 *     5）、给容器中返回当前组件使用cglib增强代理
 *     6）、以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程
 *
 *
 *     3）、目标方法的执行
 *        容器中保存了组件的代理对象（cglib 增强后的对象），这个独享里面保存了详细信息（比如增强器、目标对象、xxx）
 *        1)、CglibAopProxy.intercept() 拦截目标方法执行
 *        2）、根据ProxyFactory对象获取拦截器链
 *         List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *          1）、List<Object> interceptionAdvice 保存所有拦截器
 *            一个默认的 ExposeInvocationInterceptor 和 4个 增强器;
 *          2)、遍历所有的增强器。将他转换为Interceptor
 *            registry.getInterceptors(advisor);
 *          3)、将增强器转换为List<MethodInterceptor>
 *            如果是MethodInterceptor 直接加入到集合中
 *            如果不是，使用AdvisorAdapter 将增强器转换为 MethodInterceptor
 *            转换完成返回 MethodInterceptor 数组
 *        3）、如果没有拦截器链、直接执行目标方法;
 *            拦截器链（每个通知方法又被包装为方法拦截器，利用MethodInterceptor机制）
 *        4）、如果有拦截器链、把需要执行的目标对象、目标方法、拦截器链等信息传入创建一个CglibMethodInvocation对象
 *         并调用 Object retVal = im.proceed();
 *          new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy).proceed();
 *        5）、拦截器链的触发过程
 *           1）、如果没有拦截器，执行目标方法，或者拦截器的缩影和拦截器数组-1大小一样，（指定到了最后一个拦截器）执行目标方法
 *           2)、链式获取每一个拦截器，拦截器执行invoke 方法， 每一个拦截器等待下一个拦截器执行完成返回以后再来执行；
 *             拦截器链 的机制。保证通知方法和目标方法的执行顺序
 *
 *   总结：
 *       1)、@EnableAspectJAutoProxy 开启AOP功能
 *       2)、@EnableAspectJAutoProxy 会给容器中注册一个组件 AnnotationAwareAspectJAutoProxyCreator 对象
 *       3)、AnnotationAwareAspectJAutoProxyCreator 是一个后置处理器
 *       4)、容器的创建流程：
 *          1）、registerBeanPostProcessors（）注册后置处理器，会创建 AnnotationAwareAspectJAutoProxyCreator 对象
 *          2）、finishBeanFactoryInitialization（）初始化剩下的单实例Bean
 *             1）、创建业务逻辑组件
 *             2）、AnnotationAwareAspectJAutoProxyCreator 拦截组件的创建过程
 *             3）、组件创建完成之后，判断组件是否需要增强：
 *                是：漆面的通知方法，包装层增强器（Advisor）给业务逻辑组件创建一个代理对象（cglib）
 *
 *       5)、执行目标方法：
 *           1)、代理对象执行目标方法
 *           2)、CglibAopProxy.interceptor();
 *             1)、得到目标方法的拦截器链，（增强器包装成拦截器MethodInterceptor）
 *             2）、利用拦截器的链式机制，依次进入拦截器执行
 *             3）、效果
 *                  正常执行：前置通知-> 目标方法-> 后置通知-> 返回通知
 *                  执行异常：前置通知-> 目标方法-> 后置通知-> 异常通知
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
