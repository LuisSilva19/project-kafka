package com.project.kafkaconsumer.conf;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.RecordInterceptor;
import org.springframework.kafka.support.converter.JsonMessageConverter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Value("${kafka.listener.ack-mode}")
    private ContainerProperties.AckMode ackMode;

    @Value("${kafka.listener.sync-commits}")
    private String syncCommits;

    @Value("${kafka.listener.concurrency}")
    private String concurrency;

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group-1");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.CLIENT_ID_CONFIG, "project-kafka-consumer");
        config.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "300000");
        config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "500");
        config.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,  "20000");

        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> jsonContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, Object>();
        factory.setConsumerFactory(consumerFactory());
        factory.setRecordMessageConverter(new JsonMessageConverter());
        factory.getContainerProperties().setAckMode(ackMode);
        factory.getContainerProperties().setAsyncAcks(Boolean.parseBoolean(syncCommits));
        factory.setConcurrency(Integer.parseInt(concurrency));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(ackMode);
        factory.getContainerProperties().setAsyncAcks(Boolean.parseBoolean(syncCommits));
        factory.setConcurrency(Integer.parseInt(concurrency));

        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> validMessageContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(ackMode);
        factory.getContainerProperties().setAsyncAcks(Boolean.parseBoolean(syncCommits));
        factory.setConcurrency(Integer.parseInt(concurrency));
        factory.setRecordInterceptor(validMessage());

        return factory;
    }

    private RecordInterceptor<String, String> validMessage() {
        return (record, value) -> {
            if (record.value().contains("first")) {
                System.out.println("foi interceptado");
                return record;
            }
            return record;
        };
    }
}
