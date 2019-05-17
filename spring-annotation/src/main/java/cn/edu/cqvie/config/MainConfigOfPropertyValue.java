package cn.edu.cqvie.config;

import cn.edu.cqvie.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * PropertySource 读取外部属性文件保存到系统变量中,
 *   加载完成外部配置文件后使用${}取出环境变量的值
 */
@PropertySource(value = {"classpath:/person.properties"} , encoding = "utf-8")
@Configuration
public class MainConfigOfPropertyValue {


    @Bean
    public Person person() {
        return new Person();
    }
}
