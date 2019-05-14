package cn.edu.cqvie.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MacCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // todo 是否是Mac OS

        // bean 对象工厂
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 类加载器
        ClassLoader classLoader = context.getClassLoader();
        // 获取当前环境信息
        Environment environment = context.getEnvironment();
        // 获取到bean 定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();

        String property = environment.getProperty("os.name");
        return property.contains("Mac");
    }
}
