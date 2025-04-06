package com.odk.pjt.dicematchbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories(
        basePackages = "com.odk.pjt.dicematchbe",
        includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = { Repository.class }) }
)
@EnableJpaAuditing
@SpringBootApplication
public class DiceMatchBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiceMatchBeApplication.class, args);
    }

}
