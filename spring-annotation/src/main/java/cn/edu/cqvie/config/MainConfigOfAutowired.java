package cn.edu.cqvie.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import cn.edu.cqvie.bean.Car;
import cn.edu.cqvie.bean.Color;
import cn.edu.cqvie.dao.BookDao;

@Configuration
@ComponentScan({ "cn.edu.cqvie.bean", "cn.edu.cqvie.dao", "cn.edu.cqvie.service", "cn.edu.cqvie.controller" })
public class MainConfigOfAutowired {

	@Primary
	@Bean("bookDao2")
	public BookDao bookDao2() {
		BookDao bookDao = new BookDao();
		bookDao.setLable("2");
		return bookDao;
	}

	@Bean
	public Color color(@Qualifier("car") Car car) {
		return new Color(car);
	}

	@Bean
	@Primary
	public Car car1() {
		Car car = new Car();
		car.setLable("BMW");
		return car;
	}
}
