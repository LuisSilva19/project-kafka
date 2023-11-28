package com.project.kafkaproducer.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ObjectTransferDto {
    String name;
    String age;
    String country;
}
