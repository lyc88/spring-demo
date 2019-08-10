package cn.edu.cqvie.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String sayHello(String str) {
        return "say " + str;
    }
}
