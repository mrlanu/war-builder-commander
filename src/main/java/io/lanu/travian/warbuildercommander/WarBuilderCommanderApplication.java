package io.lanu.travian.warbuildercommander;

import io.lanu.travian.warbuildercommander.services.WarSenderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WarBuilderCommanderApplication {

    private WarSenderService warSenderService;

    public WarBuilderCommanderApplication(WarSenderService warSenderService) {
        this.warSenderService = warSenderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(WarBuilderCommanderApplication.class, args);
    }

}
