package cn.edu.cqvie;

import cn.edu.cqvie.ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.stream.Stream;

public class IOCTest_Ext {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ExtConfig.class);

        //发布事件
        applicationContext.publishEvent(new ApplicationEvent(new String("我发布的事件")) {
        });
        applicationContext.close();
    }


}
