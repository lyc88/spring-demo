package cn.edu.cqvie.config;

import cn.edu.cqvie.controller.MyFirstInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Spring MVC 只扫描 @Controller
 *
 * @author ZHENG SHAOHONG
 */
@ComponentScan(value = "cn.edu.cqvie", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)
})
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

    /**
     * 视图解析器
     *
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //super.configureViewResolvers(registry);
        //默认所有的页面都从 /WEB-INF/xxx.jsp
        registry.jsp("/WEB-INF/views", ".jsp");
    }

    /**
     * 静态资源访问
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //super.addInterceptors(registry);

        registry.addInterceptor(new MyFirstInterceptor()).addPathPatterns("/**");

    }
}
