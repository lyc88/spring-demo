package cn.edu.cqvie.bean;

import org.springframework.stereotype.Component;

@Component
public class Car {

	private String lable = "Audi";
	
	
    public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	public Car() {
        System.out.println("car constructor ...");
    }

    public void init() {
        System.out.println("car ... init ...");
    }

    public void destroy() {
        System.out.println("car ... destroy ...");
    }

	@Override
	public String toString() {
		return "Car [lable=" + lable + "]";
	}


}
