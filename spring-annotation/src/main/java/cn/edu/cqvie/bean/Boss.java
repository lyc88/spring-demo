package cn.edu.cqvie.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Boss {
	
	private Car car;

	public Car getCar() {
		return car;
	}

	/**
	 * 标注在参数上，Spring容器创建对象，就会调用方法，完成赋值
	 * 方法使用的参数，自定义类型值从 ioc 中获取
	 * 
	 * @param car
	 */
	//@Autowired
	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Boss [car=" + car + "]";
	}

	//@Autowired
	public Boss(
			//@Autowired 
			Car car) {
		super();
		this.car = car;
	}
	
//	public Boss() {
//	}
}
