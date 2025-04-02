package com.example.tagtaskschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TagTaskScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TagTaskScheduleApplication.class, args);
    }

}
