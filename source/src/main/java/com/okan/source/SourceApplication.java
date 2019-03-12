package com.okan.source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class SourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SourceApplication.class, args);
    }

}
