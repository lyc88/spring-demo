package cn.edu.cqvie.condition;

import cn.edu.cqvie.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {


    /**
     * 把所有需要注册的类进行注册
     *
     * @param importingClassMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean red = registry.containsBeanDefinition("red");
        boolean blue = registry.containsBeanDefinition("blue");

        if (red && blue) {
            RootBeanDefinition rainBow = new RootBeanDefinition(RainBow.class);
            registry.registerBeanDefinition("rainBow", rainBow);

        }
    }
}
