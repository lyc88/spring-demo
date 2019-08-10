package cn.edu.cqvie.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class UserListener implements ServletContextListener {
    /**
     * 监听 ServletContext 启动初始化
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("UserListener .... contextInitialized .....");
    }

    /**
     * 监听 ServletContext 销毁
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("UserListener .... contextDestroyed .....");

    }
}
