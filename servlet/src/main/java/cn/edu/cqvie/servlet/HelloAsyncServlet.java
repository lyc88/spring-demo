package cn.edu.cqvie.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/async", asyncSupported = true)
public class HelloAsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        //1、支持异步处理asyncSupported = true
        //2、开启异步模式
        System.out.println("主线程开始：" + Thread.currentThread() + " ===>" + System.currentTimeMillis());
        AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(3000);

        //3、业务陈逻辑进行异步处理，开始异步处理
        asyncContext.start(() -> {
            try {
                System.out.println("子线程开始：" + Thread.currentThread() + " ===>" + System.currentTimeMillis());

                sayHello();
                asyncContext.complete();
                //获取异步的上下文
                AsyncContext asyncContext1 = req.getAsyncContext();

                //4、获取相应
                ServletResponse response = asyncContext1.getResponse();
                response.getWriter().write("async hello word!");
                System.out.println("子线程结束：" + Thread.currentThread() + " ===>" + System.currentTimeMillis());

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("主线程结束：" + Thread.currentThread() + " ===>" + System.currentTimeMillis());

    }


    public void sayHello() throws Exception {
        System.out.println(Thread.currentThread() + " processing ...");
        Thread.sleep(2000);
    }
}
