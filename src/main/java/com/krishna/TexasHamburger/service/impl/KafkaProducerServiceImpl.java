package com.krishna.TexasHamburger.service.impl;
import com.krishna.TexasHamburger.model.TodaysOrder;
import com.krishna.TexasHamburger.repository.TodaysOrdersRepository;
import com.krishna.TexasHamburger.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, TodaysOrder> kafkaTemplate;

    @Autowired
    private TodaysOrdersRepository todaysOrdersRepository;
    private static final String topic = "Hamburger";
    @Override
    public void produce() {
        todaysOrdersRepository.findAll().forEach(x-> kafkaTemplate.send(topic,x));
    }
}
