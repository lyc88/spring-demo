package cn.edu.cqvie.bean;

import org.springframework.stereotype.Component;

@Component
public class Order {

    private String no;

    private Integer pay;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public Order() {
    }

    public Order(String no, Integer pay) {
        this.no = no;
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "Order{" +
                "no='" + no + '\'' +
                ", pay=" + pay +
                '}';
    }
}
