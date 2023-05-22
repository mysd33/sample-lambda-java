package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.xray.AWSXRay;

@SpringBootApplication
public class UserServiceApplication {
    
    public static void main(String[] args) {
        AWSXRay.beginSegment("sample-lambda-java");
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
