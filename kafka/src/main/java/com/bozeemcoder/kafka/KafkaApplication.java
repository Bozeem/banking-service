package com.bozeemcoder.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }
    @Bean
    NewTopic notification() {
        // topic name, partition numbers, replication number
        return new NewTopic("transaction-service", 2, (short) 1);
    }

}
