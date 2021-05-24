package org.example.znakapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.example.znakapi")
public class ZnakApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZnakApiApplication.class, args);
    }
}
