package cn.edu.cqvie.aop;

/**
 * 业务逻辑类
 *
 * @author ZHENG SHAOHONG
 */
public class MathCalculator {

    public int div(int i, int j) {
        System.out.println("MathCalculator.div ... ");
        return i / j;
    }
}
