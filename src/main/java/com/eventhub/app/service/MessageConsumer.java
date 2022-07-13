package com.eventhub.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageConsumer {
    int counter;
    @RetryableTopic(attempts = "2",
            backoff = @Backoff(delay = 1000,
                    multiplier = 1.0, maxDelay = 0),
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            dltStrategy = DltStrategy.FAIL_ON_ERROR,
            autoCreateTopics = "false",
            include = {ArithmeticException.class})
    @KafkaListener(topics = "${topic.name}",groupId = "$Default")
    //@KafkaListener (topics= "$(topic.name}", topicPartitions= @TopicPartition(topic = "${topic.name}",
    //    partitions = "1-10")) // Here you can provide partitions that you can subscribe from - to like 1.....10
    public void consume(String message) {
        counter ++;
        log.info("In consumer, {}",counter);
        log.info("Message recieved -> {}", message);
    }


    @DltHandler
    public void failureCallback(String msg) {
        log.info("In Dlt handler after failing... {} ", msg);
        counter =0;
    }
}
