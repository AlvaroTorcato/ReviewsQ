package com.example.service;

import com.example.model.Change;
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

/*@EnableRabbit
@Component
@RabbitListener(queues = "reviewsUpdate1_queue_fanout", id = "listener3")*/
@Service
public class ReceiverUpdate {
    @Autowired
    ReviewRepository repository;
    private static Logger logger = LogManager.getLogger(ReceiverUpdate.class.toString());
    /*@RabbitHandler
    public void receiver(Change change) {
        repository.updateReview(change.getStatus(),change.getId());
        logger.info("MenuOrder listener invoked - Consuming Message with MenuOrder Identifier : " + change.toString());
    }*/
    @RabbitListener(queues= "#{autoDeleteQueueUpdate.name}")
    public void consumeJsonMessage(String rev)throws JsonProcessingException {
        logger.info("MenuOrder listener invoked - Consuming Message with MenuOrder Identifier : " + rev);

        ObjectMapper objectMapper = new ObjectMapper();
        Change obj=objectMapper.readValue(rev, Change.class);


        //jwtService.createJWT(obj);

        repository.updateReview(obj.getStatus(),obj.getId());
    }
}
