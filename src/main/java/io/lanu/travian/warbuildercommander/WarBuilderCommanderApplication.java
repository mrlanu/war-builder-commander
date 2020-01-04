package io.lanu.travian.warbuildercommander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WarBuilderCommanderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarBuilderCommanderApplication.class, args);
    }
}
