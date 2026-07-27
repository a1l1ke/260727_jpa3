package org.example.jpa3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Jpa3Application {

    public static void main(String[] args) {
        SpringApplication.run(Jpa3Application.class, args);
    }

}
