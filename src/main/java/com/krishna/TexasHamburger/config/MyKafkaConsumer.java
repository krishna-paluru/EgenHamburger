package com.krishna.TexasHamburger.config;
import com.krishna.TexasHamburger.model.TodaysOrder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class MyKafkaConsumer {

    Logger log = LoggerFactory.getLogger(MyKafkaConsumer.class);

    @KafkaListener(topics = "${my.kafka.consumer.topic}")

    public void listen(ConsumerRecord<String, TodaysOrder> kafkaMessage) {
        log.info(String.format("Received data     = %s", kafkaMessage.value()));
    }
}
