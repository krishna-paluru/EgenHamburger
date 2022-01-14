package com.krishna.TexasHamburger.controller;
import com.krishna.TexasHamburger.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/TexasHamburger")
public class KafkaProducerController {
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @PostMapping("/produce")
    public void produce()
    {
        kafkaProducerService.produce();
    }



}
