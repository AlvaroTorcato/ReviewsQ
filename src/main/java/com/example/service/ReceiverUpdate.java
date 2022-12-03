package com.example.service;

import com.example.model.Change;
import com.example.repository.ReviewRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
@RabbitListener(queues = "reviewsUpdate1_queue_fanout", id = "listener3")
public class ReceiverUpdate {
    @Autowired
    ReviewRepository repository;
    private static Logger logger = LogManager.getLogger(ReceiverUpdate.class.toString());
    @RabbitHandler
    public void receiver(Change change) {
        repository.updateReview(change.getStatus(),change.getId());
        logger.info("MenuOrder listener invoked - Consuming Message with MenuOrder Identifier : " + change.toString());
    }
}
