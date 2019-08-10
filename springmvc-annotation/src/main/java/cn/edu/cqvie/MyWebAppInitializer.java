package cn.edu.cqvie;

import cn.edu.cqvie.config.AppConfig;
import cn.edu.cqvie.config.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * WEB 容器启动的时候创建对象，调用方法来初始化容器以前端控制器
 *
 * @author ZHENG SHAOHONG
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 获取配置类。（Spring 的配置文件）父容器
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                RootConfig.class
        };
    }

    /**
     * 获取Web 容器的配置类（Spring MVC 的配置文件）子容器
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                AppConfig.class
        };
    }

    /**
     * 获取 DispatcherServlet 的映射信息
     * 1、拦截所有请求（包括静态资源（xx.js、xx.png））,但是不包括 *.jsp
     * 2、/* 拦截所有请求，连*.jsp 页面都拦截，jsp是有Tomcat的jsp引擎解析的
     *
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
