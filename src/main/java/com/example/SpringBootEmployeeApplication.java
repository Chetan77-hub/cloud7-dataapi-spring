package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // <== This annotation is required here
public class SpringBootEmployeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEmployeeApplication.class, args);
    }
}
