package cn.edu.cqvie;

import cn.edu.cqvie.bean.Blue;
import cn.edu.cqvie.bean.Person;
import cn.edu.cqvie.config.MainConfig;
import cn.edu.cqvie.config.MainConfig2;
import cn.edu.cqvie.ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;
import java.util.stream.Stream;

public class IOCTest_Ext {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ExtConfig.class);

        applicationContext.close();
    }


}
