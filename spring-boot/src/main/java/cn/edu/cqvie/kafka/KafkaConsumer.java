package cn.edu.cqvie.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = "myTopic", groupId = "myGroup")
    public void obtainMessage(ConsumerRecord<String, String> consumerRecord) {
        logger.info("obtainMessage invoked!");

        String topic = consumerRecord.topic();
        String key = consumerRecord.key();
        String value = consumerRecord.value();
        int partition = consumerRecord.partition();
        long timestamp = consumerRecord.timestamp();

        logger.info("topic: {}", topic);
        logger.info("key: {}", key);
        logger.info("value: {}", value);
        logger.info("partition: {}", partition);
        logger.info("timestamp: {}", timestamp);

        logger.info("==========================");

    }
}
