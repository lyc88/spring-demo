package cn.edu.cqvie.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Dog implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public Dog() {

        System.out.println("dog constructor ...");
    }

    /**
     * 对象创建并且属性赋值后
     */
    @PostConstruct
    public void init() {
        System.out.println("dog init ...");
    }

    /**
     * 容器移出对象之前
     */
    @PreDestroy
    public void destroy(){
        System.out.println("dog destroy ...");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
