package cn.edu.cqvie;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.edu.cqvie.bean.Boss;
import cn.edu.cqvie.bean.Car;
import cn.edu.cqvie.bean.Color;
import cn.edu.cqvie.config.MainConfigOfAutowired;

public class IOCTestAutiwired {

	@Test
	public void test01() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
//		BookService service = applicationContext.getBean(BookService.class);
//		BookDao dao = applicationContext.getBean(BookDao.class);
//
//		System.out.println(dao);
//		service.print();
	
		Boss boss = applicationContext.getBean(Boss.class);
//		Car car1 = applicationContext.getBean(Car.class);

		System.out.println(boss.getCar());
//		System.out.println(car1);

		Color color = applicationContext.getBean(Color.class);
		Car car = applicationContext.getBean(Car.class);
		
		System.out.println(color.getCar());
		System.out.println(car);
	}

}
