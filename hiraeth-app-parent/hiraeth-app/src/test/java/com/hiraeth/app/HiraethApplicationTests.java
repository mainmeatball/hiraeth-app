package com.hiraeth.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = "com.hiraeth.*")
class HiraethApplicationTests {

    @Test
    void contextLoads() {
    }
}
