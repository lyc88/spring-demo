package cn.edu.cqvie.controller;

import cn.edu.cqvie.config.MyConfigBean;
import cn.edu.cqvie.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/api")
public class MyController {

    //@Value("${myConfig.myObject.myName}")
    //private String myName;

    //@Value("${myConfig.myObject.myAge}")
    //private int myAge;


    //@Autowired
    //private MyConfigBean myConfigBean;

    @GetMapping("/test")
    public Person test() {
        Person person = new Person();
        person.setId(20);
        person.setName("San Zhang111");
        person.setBirthday(new Date());

//        System.out.println(this.myName);
//        System.out.println(this.myAge);
//
//        System.out.println(myConfigBean.getMyName());
//        System.out.println(myConfigBean.getMyAge());

        return person;
    }
}
