package com.qirsam;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class MyCamundaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyCamundaApplication.class, args);
    }
}