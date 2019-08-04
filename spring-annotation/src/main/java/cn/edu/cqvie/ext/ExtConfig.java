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
