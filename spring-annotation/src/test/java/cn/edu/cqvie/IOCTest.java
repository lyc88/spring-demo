package cn.edu.cqvie;

import cn.edu.cqvie.bean.Blue;
import cn.edu.cqvie.bean.Person;
import cn.edu.cqvie.config.MainConfig;
import cn.edu.cqvie.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;
import java.util.stream.Stream;

public class IOCTest {

    ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(MainConfig.class);

    @Test
    public void test01() {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfig2.class);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Stream.of(beanDefinitionNames).forEach(System.out::println);
    }

    @Test
    public void test02() {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfig.class);

        System.out.println("IOC 容器创建过程");
        Object bean = applicationContext.getBean("person");
        Object bean2 = applicationContext.getBean("person");
        System.out.println(bean);

        System.out.println(bean == bean2);
    }


    @Test
    public void test03() {
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        Stream.of(beanNamesForType).forEach(System.out::println);

        Environment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        // System.out.println(property);

        System.out.println(" ---- 分割线 ----");
        Map<String, Person> beans = applicationContext.getBeansOfType(Person.class);
        beans.entrySet().forEach(System.out::println);
    }

    private void printBean(ApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Stream.of(beanDefinitionNames).forEach(System.out::println);
    }


    @Test
    public void test04() {
        printBean(applicationContext);
    }


    @Test
    public void testImport() {
        printBean(applicationContext);

        Blue bean = applicationContext.getBean(Blue.class);
        System.out.println(bean);
    }
}
