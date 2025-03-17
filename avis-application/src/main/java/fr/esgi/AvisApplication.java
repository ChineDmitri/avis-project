package fr.esgi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(scanBasePackages = {"fr.esgi", "fr.esgi.controller"})
@SpringBootApplication()
public class AvisApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvisApplication.class, args);
    }

}
