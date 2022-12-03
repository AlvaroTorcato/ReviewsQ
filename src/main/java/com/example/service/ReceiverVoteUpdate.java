package com.example.service;

import com.example.model.Votes;
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
@RabbitListener(queues = "reviewsVoteUpdate1_queue_fanout", id = "listener4")
public class ReceiverVoteUpdate {
    @Autowired
    ReviewRepository repository;
    private static Logger logger = LogManager.getLogger(RabbitMQReceiver.class.toString());
    @RabbitHandler
    public void receiver(Votes vote) {
        repository.updateReviewWithVote(vote.getId(),vote.getUpVotes(), vote.getDownVotes(), vote.getTotalVotes());
        logger.info("MenuOrder listener invoked - Consuming Message with MenuOrder Identifier : " + vote);
    }
}
