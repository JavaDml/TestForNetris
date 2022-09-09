package com.gribanov.testfornetris;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class TestForNetrisApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestForNetrisApplication.class, args);
    }
}
