package com.example.pcproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PcProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PcProjectApplication.class, args);
    }

}
