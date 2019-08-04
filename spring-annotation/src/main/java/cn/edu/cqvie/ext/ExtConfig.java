package cn.edu.cqvie.ext;

import cn.edu.cqvie.bean.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 拓展原理
 * BeanPostProcessor：bean 后置处理器，bean创建对象初始化前后进行拦截工作
 * BeanFactoryPostProcessor: beanFactory的后置处理器
 * 再BeanFactory 标准初始化之后调用，所有的bean定义已经保存加载到beanfactory中
 *
 * 1）、IOC容器创建对象
 * 2）、invokeBeanFactoryPostProcessors（beanfactory）；执行BeanFactoryPostProcessor
 *     如何找到所有的BeanFactoryPostProcessor并执行他们的方法
 *         1）、直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor的组件，并执行他们的方法。
 *         2）、在初始化创建其他组件前面执行。
 *
 * 2、BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 *      postProcessBeanDefinitionRegistry()
 *      在所有bean定义信息将要被加载，Bean实例还没创建。
 *
 *      优先于BeanFactoryPostProcessor执行，利用BeanDefinitionRegistryPostProcessor
 *      给容器中再添加一些组件。
 *  原理：
 *    1、IOC 创建对象
 *    2、refresh() -> invokeBeanFactoryPostProcessors(beanFactory)
 *    3、容器中获取所有 BeanDefinitionBeanFactoryPostProcessor 组件
 *          1、 依次触发 postProcessBeanDefinitionRegistry() 方法
 *          2、 再来触发 postProcessBeanFactory() 方法
 *    4、再来从容器中找到 BeanFactoryPostProcessor 组件，然后依次触发 postProcessBeanFactory() 方法
 *
 * 3、ApplicationListener: 监听容器中发布的事件。事件驱动模型开发。
 *     public interface ApplicationListener<E extends ApplicationEvent> extends EventListener
 *       监听 ApplicationListener 及其下面的事件；
 * 步骤：
 *   1）、写一个监听器（ApplicationListener的实现类）来监听某个事件（ApplicationEvent及其子类）。
 *   2）、吧监听器加入容器中。
 *   3）、容器中有现骨干事件的发布，我们就能监听到这个事件。
 *       ContextRefreshedEvent： 容器刷新完成（所有Bean都完全创建）会发布这个事件
 *       ContextClosedEvent：关闭容器会发布这个事件
 *   4）、发布一个事件：
 *             applicationContext.publishEvent()
 * 原理：
 *   ContextRefreshedEvent、IOCTest_Ext$1[source=我发布的事件]、ContextClosedEvent
 * 1）、ContextRefreshedEvent 事件
 *     1）、容器创建对象：refresh()
 *     2) 、finishRefresh(); 容器刷新完成
 *
 * 2）、自己发布事件
 *
 * 3)、容器关闭会发布 ； ContextClosedEvent;
 *
 *  【 事件发布流程： 】
 *   3）、publishEvent(new ContextRefreshedEvent(this));
 *
 *         1）、获取事件的多播器（派发器）：getApplicationEventMulticaster()
 *         2）、multicastEvent 派发事件
 *         3）、获取到所有的ApplicationListener
 *              		for (final ApplicationListener<?> listener : getApplicationListeners(event, type))
 *              		1)、 如果有 Executor 可以支持使用Executor 进行异步派发事件。
 *                              Executor executor = getTaskExecutor();
 *              	    2)、 否则同步的方式直接执行Listener方法。invokeListener(listener, event)
 *              	        拿到listenner 回调 onApplicationEvent() 方法
 *  【 事件多播器（派发器） 】
 *       1)、容器初始化 refresh()
 *       2)、initApplicationEventMulticaster(); 初始化 ApplicationEventMulticaster
 *           1)、先去容器中找有没有 id = applicationEventMulticaster 的组件
 *           2)、如果没有 this.applicationEventMulticaster = new   (beanFactory);
 * 			      并且加入到容器中，我们就可以在其他组件派发事件的时候只需要自动注入 applicationEventMulticaster 就可以了。
 * 			      beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, this.applicationEventMulticaster);
 *
 * 	【 容器中有哪些监听器 】
 *      1）、容器初始化 refresh()
 *      2）、注册监听器 registerListeners();
 *            从容器中拿到所有的监听器，把他们注册到 applicationEventMulticaster 中
 *                String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
 *                //讲Listener注册到ApplicationEventMulticaster派发器中
 *                getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
 *
 *
 */
@ComponentScan("cn.edu.cqvie.ext")
@Configuration
public class ExtConfig {

    @Bean
    public Blue blue() {
        return new Blue();
    }
}
