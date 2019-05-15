package cn.edu.cqvie;

import cn.edu.cqvie.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class IOCTestLifeCycle {


    @Test
    public void test01() {
        AbstractApplicationContext appContext =
                new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);

        System.out.println("容器创建完成");

        appContext.close();
    }
}
