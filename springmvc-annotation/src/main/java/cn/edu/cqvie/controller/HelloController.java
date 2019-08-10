package cn.edu.cqvie.controller;


import cn.edu.cqvie.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        String sayHello = helloService.sayHello("Tomcat ....");
        return sayHello;
    }

    @RequestMapping(value = "/suc")
    public String suc() {
        return "/success";
    }
}
