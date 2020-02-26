package cn.edu.cqvie.controller;

import cn.edu.cqvie.kafka.KafkaMessage;
import cn.edu.cqvie.kafka.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "kafka")
public class KafkaController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping(value = "message", method = RequestMethod.GET)
    public KafkaMessage sendMessage(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password) {

        logger.info("sendMessage invoked!");
        KafkaMessage message = new KafkaMessage();
        message.setId(id);
        message.setUsername(username);
        message.setPassword(password);
        message.setDate(new Date());

        kafkaProducer.sendKafkaMessage(message);
        return message;
    }

    @RequestMapping(value = "message", method = RequestMethod.POST)
    public KafkaMessage sendMessage2(@RequestBody KafkaMessage message) {
        logger.info("sendMessage2 invoked!");
        kafkaProducer.sendKafkaMessage(message);
        return message;
    }
}
