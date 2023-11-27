package com.project.kafkaproducer.controller;

import com.project.kafkaproducer.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/init")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaService kafkaService;

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody String message) {
        kafkaService.sendMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
