package com.project.kafkaconsumer.service;

import com.project.kafkaconsumer.dtos.ObjectTransferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {

//    @KafkaListener(topics = "str-topic",
//            groupId = "group-1",
//            containerFactory = "kafkaListenerContainerFactory",
//            errorHandler = "kafkaErrorHandler")
//    public void listener(String message) {
//        System.out.println(message);
//    }

//    @SneakyThrows
//    @KafkaListener(topics = "str-topic",
//            groupId = "group-2",
//            containerFactory = "validMessageContainerFactory",
//            errorHandler = "kafkaErrorHandler")
//    public void interceptor(String message) {
//        System.out.println(message);
//        throw new IllegalAccessException();
//    }

    @KafkaListener(topics = "str-topic",
            groupId = "group-1",
            containerFactory = "jsonContainerFactory")
    public void listenerTransferObject(@Payload ObjectTransferDto objectTransferDto) {
        System.out.println(objectTransferDto);
    }
}
