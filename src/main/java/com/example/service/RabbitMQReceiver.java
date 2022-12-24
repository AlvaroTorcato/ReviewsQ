package com.example.service;


import com.example.model.Review;

import com.example.repository.ReviewRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@EnableRabbit
//@Component
//@RabbitListener(queues = "reviews1_queue_fanout", id = "listener")
@Service
public class RabbitMQReceiver {
    @Autowired
    ReviewRepository repository;
    private static Logger logger = LogManager.getLogger(RabbitMQReceiver.class.toString());

    @RabbitListener(queues= "#{autoDeleteQueue.name}")
    public void consumeJsonMessage(String rev)throws JsonProcessingException {
        logger.info("MenuOrder listener invoked - Consuming Message with MenuOrder Identifier : " + rev);

        ObjectMapper objectMapper = new ObjectMapper();
        Review obj=objectMapper.readValue(rev, Review.class);


        //jwtService.createJWT(obj);

        repository.save(obj);
    }
    /*@RabbitHandler
    public void receiver(Review review) {
        repository.save(review);
        logger.info("MenuOrder listener invoked - Consuming Message with MenuOrder Identifier : " + review.toString());
    }*/
}
