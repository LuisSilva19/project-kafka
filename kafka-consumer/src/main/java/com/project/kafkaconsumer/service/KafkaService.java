package com.project.kafkaconsumer.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {

    @KafkaListener(topics = "str-topic",
            groupId = "group-1",
            containerFactory = "kafkaListenerContainerFactory",
            errorHandler = "kafkaErrorHandler")
    public void listener(String message) {
        System.out.println(message);
    }

    @KafkaListener(topics = "str-topic",
            groupId = "group-2",
            containerFactory = "validMessageContainerFactory",
            errorHandler = "kafkaErrorHandler")
    @SneakyThrows
    public void interceptor(String message) {
        System.out.println(message);
        throw new IllegalAccessException();
    }
}
