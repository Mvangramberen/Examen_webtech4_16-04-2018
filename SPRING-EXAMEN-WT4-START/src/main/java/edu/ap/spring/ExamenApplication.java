package edu.ap.spring;

import edu.ap.spring.model.InhaalExamen;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExamenApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamenApplication.class, args);
    }

}
