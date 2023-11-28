package com.project.kafkaproducer.service;

import com.project.kafkaproducer.dtos.ObjectTransferDto;
import com.project.kafkaproducer.enums.KafkaHeaders;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, ObjectTransferDto> kafkaTemplate;

    public void sendMessage(String message) {
        var res = new ProducerRecord<String, String>("str-topic", message);

        res.headers()
                .add(KafkaHeaders.CONTENT_TYPE.getValue(), "application/json".getBytes(StandardCharsets.UTF_8))
                .add(KafkaHeaders.APPLICATION_NAME.getValue(), "project-kafka".getBytes(StandardCharsets.UTF_8))
                .add(KafkaHeaders.EVENT_ID.getValue(), UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8))
                .add(KafkaHeaders.EVENT_NAME.getValue(), "CONSTRUCTOR-EVENT".getBytes(StandardCharsets.UTF_8));

//        kafkaTemplate.send(res);
    }

    public void sendMessageWithObject(ObjectTransferDto objectTransferDto) {
        var res = new ProducerRecord<String, ObjectTransferDto>("str-topic", objectTransferDto);

        res.headers()
                .add(KafkaHeaders.CONTENT_TYPE.getValue(), "application/json".getBytes(StandardCharsets.UTF_8))
                .add(KafkaHeaders.APPLICATION_NAME.getValue(), "project-kafka".getBytes(StandardCharsets.UTF_8))
                .add(KafkaHeaders.EVENT_ID.getValue(), UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8))
                .add(KafkaHeaders.EVENT_NAME.getValue(), "CONSTRUCTOR-EVENT".getBytes(StandardCharsets.UTF_8));

        kafkaTemplate.send(res);
    }


}
