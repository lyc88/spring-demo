package cn.edu.cqvie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * Spring 不扫描 @Controller
 * useDefaultFilters = false 禁用掉默认规则
 * @author ZHENG SHAOHONG
 */
@ComponentScan(value = "cn.edu.cqvie", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)
}, useDefaultFilters = false)
public class RootConfig {
}
