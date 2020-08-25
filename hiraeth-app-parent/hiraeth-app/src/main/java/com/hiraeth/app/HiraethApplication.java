package com.hiraeth.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath*:configuration.xml")
public class HiraethApplication {
    public static void main(String... args) {
        SpringApplication.run(HiraethApplication.class, args);
    }
}
