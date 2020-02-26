package cn.edu.cqvie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

@Controller
public class JspController {

    @GetMapping("/jsp")
    public String jsp(Map<String, Object> model) {
        model.put("date", new Date());
        model.put("message", "hello world");
        return "result";
    }

    @RequestMapping("/jspError")
    public String jspError(Map<String, Object> model) {
        throw new RuntimeException("jspError");
    }
}
