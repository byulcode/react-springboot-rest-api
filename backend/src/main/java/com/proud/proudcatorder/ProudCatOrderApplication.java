package com.proud.proudcatorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProudCatOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProudCatOrderApplication.class, args);
    }

}
