package cn.edu.cqvie.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HelloService", urlPatterns = { "/hello" }, loadOnStartup = 1)
public class HelloService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(Thread.currentThread() + " start ...");

        try {
            sayHello();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //req.getRequestDispatcher("index.jsp").forward(req, resp);
        resp.getWriter().println("say hello! ");
        System.out.println(Thread.currentThread() + " end ...");

    }

    public void sayHello() throws Exception {
        System.out.println(Thread.currentThread() + " processing ...");
        Thread.sleep(2000);
    }
}
