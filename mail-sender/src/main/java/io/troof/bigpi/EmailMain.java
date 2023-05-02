package io.troof.bigpi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"io.troof.bigpi", "com.example.mail"})
public class EmailMain {

    public static void main(String[] args) {
        SpringApplication.run(EmailMain.class, args);
    }

}
