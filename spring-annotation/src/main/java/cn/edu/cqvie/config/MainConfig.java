package cn.edu.cqvie.config;

import cn.edu.cqvie.bean.*;
import cn.edu.cqvie.condition.*;
import org.springframework.context.annotation.*;

/**
 * 配置类 == 配置文件
 * 告诉Spring 这个是一个配置类
 *
 * @author ZHENG SHAOHONG
 */
//@Conditional({WindowsCondition.class})
@Configuration
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class}) // 导入组件id 默认是全类名
public class MainConfig {


//    @Bean
//    public Color color() {
//        return new Color();
//    }

    /**
     * 给容器中注册一个Bean; 类型为返回值的类型，ID默认是用方法名作为ID
     *
     * @return
     * @Scope 调整作用域
     * <p>
     * prototype: 多实例的(IOC 容器启动并不会去掉红方法创建对象放在容器中，
     * 每次获取的时候才会去调用方法创建对象)。
     * singleton: 单例的（默认值): ioc 容器启动会调用方法创建对象放到ioc容器中。
     * 以后每次获取就是从容器(map.get())中拿。
     * request: 同一次请求创建一个实例。
     * session: 同一个session 创建一个实例。
     */
    //@Scope("singleton")
    @Lazy
    @Bean("person")
    public Person person01() {
        System.out.println("调用创建Person实例");
        return new Person("zhangsan", 25);
    }

    /**
     * @return
     * @Conditional 如果windows 系统，注册bill 如果是liunx 注册 linus
     */
    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public Person person02() {
        return new Person("Bill Gates", 62);
    }

    @Conditional({LinuxCondition.class})
    @Bean("linus")
    public Person person03() {
        return new Person("linus", 48);
    }

    @Conditional({MacCondition.class})
    @Bean("avie")
    public Person person04() {
        return new Person("Avie Tevanian", 69);
    }


    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }

    @Bean
    public Car car() {
        return new Car();
    }

}
