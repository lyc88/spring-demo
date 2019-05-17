package cn.edu.cqvie;

import cn.edu.cqvie.bean.Person;
import cn.edu.cqvie.config.MainConfigOfPropertyValue;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTestPropertyValue {

    // 1.创建IOC容器
    AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(MainConfigOfPropertyValue.class);

    @Test
    public void test01() {
        printBean(applicationContext);
        System.out.println("====>" + (Person) applicationContext.getBean("person"));
        applicationContext.close();
    }

    private void printBean(ApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }
}
