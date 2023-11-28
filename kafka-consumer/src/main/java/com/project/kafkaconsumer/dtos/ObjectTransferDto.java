package com.project.kafkaconsumer.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ObjectTransferDto {
    String name;
    String age;
    String country;
}