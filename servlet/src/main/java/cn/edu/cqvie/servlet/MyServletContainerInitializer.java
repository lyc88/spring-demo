package cn.edu.cqvie.servlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * 容器启动的时候的会将 @HandlesTypes 制定的这个类型下面的之类 （实现类、子接口等）传递过来
 * 传入感兴趣的类型
 */
@HandlesTypes(value = {HelloService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {

    /**
     * 启动应用的时候，会运行 onStartup 方法
     *
     * @param c   感兴趣的类型的所有子类型
     * @param ctx 代表当前Web应用的ServletContext； 一个Web 应用一个 ServletContext
     * @throws ServletException
     *
     * 1、使用 ServletContext注册Web组件（Servlet、Filter、Listener）
     * 2、使用编码的方式，在项目启动的时候给ServletContext里面添加组件
     *    必须在项目启动的时候添加
     *      1）、使用 ServletContext注册Web组件（Servlet、Filter、Listener）
     *      2）、ServletContextListener 得到 ServletContext
     *
     */
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("感兴趣的类型");

        for (Class<?> claz : c) {
            System.out.println(claz);
        }

        ServletRegistration.Dynamic dynamic =
                ctx.addServlet("userServlet", new UserServlet());
        dynamic.addMapping("/user");

        ctx.addListener(UserListener.class);

        FilterRegistration.Dynamic userFilter =
                ctx.addFilter("userFilter", UserFilter.class);
        userFilter.addMappingForUrlPatterns(
                EnumSet.of(DispatcherType.REQUEST),
                true,
                "/**");
    }
}
