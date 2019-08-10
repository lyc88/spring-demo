package cn.edu.cqvie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

@Controller
public class AsyncController {

    /**
     * 1、控制器返回 Callable
     * 2、Spring 异步处理，将Callable提交到TaskExecutor， 使用一个隔离的线程进行执行
     * 3、DispatcherServlet 和所有的Filter 退出Web容器的线程。 但是 response 保持打开状态
     * 4、Callable 返回，Spring MVC 将请求重新派发给容器，恢复之前的处理
     * 5、根据 Callable 返回的结果， Spring MVC 继续进行视图渲染流程等，（从收请求-视图渲染）
     *
     * preHandle ....
     * main thread start?Thread[http-nio-8087-exec-4,5,main] ===>1565464301535
     * main thread end?Thread[http-nio-8087-exec-4,5,main] ===>1565464301538
     * =================  DispatcherServlet 以及所有的Filter 退出 =================
     *
     * =================  等待执行 Callable ===========================
     * callable thread start?Thread[MvcAsync1,5,main] ===>1565464301552
     * callable thread end?Thread[MvcAsync1,5,main] ===>1565464301552
     * =================   Callable 执行完毕 ===========================
     *
     * preHandle ....  （/springmvc_annotation_war/async01）
     * postHandle ....  （Callable 的之前返回值就是目标方法的返回值）
     * afterCompletion ....
     *
     * 异步拦截器：
     *    1、以原生的  API AsyncListener
     *    2、Spring MVC 实现 AsyncHandlerInterceptor
     * @return
     */
    @ResponseBody
    @RequestMapping("/async01")
    public Callable<String> async01() {
        System.out.println("main thread start：" + Thread.currentThread() + " ===>" + System.currentTimeMillis());

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("callable thread start：" + Thread.currentThread() + " ===>" + System.currentTimeMillis());

                String s = "Callable<String> async01";
                System.out.println("callable thread end：" + Thread.currentThread() + " ===>" + System.currentTimeMillis());

                return s;
            }
        };
        System.out.println("main thread end：" + Thread.currentThread() + " ===>" + System.currentTimeMillis());

        return callable;
    }
}
