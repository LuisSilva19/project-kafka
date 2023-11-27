package com.project.kafkaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class KafkaConsumerApp{
    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApp.class, args);
    }
}
