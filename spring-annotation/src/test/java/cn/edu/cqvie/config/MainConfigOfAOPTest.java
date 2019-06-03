package cn.edu.cqvie.config;

import cn.edu.cqvie.aop.MathCalculator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class MainConfigOfAOPTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

        MathCalculator mathCalculator = context.getBean(MathCalculator.class);
        mathCalculator.div(1, 0);

        context.close();
    }
}