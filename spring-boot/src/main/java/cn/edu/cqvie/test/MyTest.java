package cn.edu.cqvie.test;

import java.lang.reflect.Method;

public class MyTest {

    public static void main(String[] args) throws Throwable {
        Class<?> clazz = MyTest2.class;
        Method method = clazz.getDeclaredMethod("main", String[].class);
        //这里可以证明静态方法不归属与对象只是归属与这个类的Class对象。
        method.invoke(null, args);
    }
}
