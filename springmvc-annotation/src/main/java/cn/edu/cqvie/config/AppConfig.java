package cn.edu.cqvie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * Spring MVC 只扫描 @Controller
 *
 * @author ZHENG SHAOHONG
 */
@ComponentScan(value = "cn.edu.cqvie", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)
})
public class AppConfig {
}
