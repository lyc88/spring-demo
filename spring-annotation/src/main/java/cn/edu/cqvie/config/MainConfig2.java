package cn.edu.cqvie.config;

import cn.edu.cqvie.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 配置类 == 配置文件
 * 告诉Spring 这个是一个配置类
 *
 * @author ZHENG SHAOHONG
 */
@Configuration
/*
@ComponentScan(value = "cn.edu.cqvie",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
                        Controller.class,
                        Service.class
                })
        },
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
                        Repository.class
                })
        })*/
/*
@ComponentScans(value = {
        @ComponentScan(value = "cn.edu.cqvie",
                excludeFilters = {
                        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
                                Controller.class,
                                Service.class
                        })
                },
                includeFilters = {
                        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
                                Repository.class
                        })
                }, useDefaultFilters = false)
})*/
// 自定义过滤规则

@ComponentScan(value = "cn.edu.cqvie",
        excludeFilters = {
                //@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
                //@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class})
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {
                        MyTypeFilter.class
                })
        })
public class MainConfig2 {
    /**
     * 给容器中注册一个Bean; 类型为返回值的类型，ID默认是用方法名作为ID
     *
     * @return
     */
    @Bean("person")
    public Person person01() {
        return new Person("lisi", 20);
    }
}
