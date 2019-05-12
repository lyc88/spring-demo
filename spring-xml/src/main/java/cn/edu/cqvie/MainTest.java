package cn.edu.cqvie;

import cn.edu.cqvie.bean.Order;
import cn.edu.cqvie.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("beans.xml");

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

        Order order = applicationContext.getBean(Order.class);
        System.out.println(order);
    }
}
