package cn.edu.cqvie.bean;

public class Color {
	
	private Car car;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	public Color(Car car) {
		this.car = car;
	}
}
