package com.reddit.backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ApiRedditBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiRedditBackendApplication.class, args);
    }

}
