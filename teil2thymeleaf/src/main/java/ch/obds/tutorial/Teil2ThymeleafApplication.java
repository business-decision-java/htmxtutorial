package ch.obds.tutorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Teil2ThymeleafApplication {

    private static final Logger log = LoggerFactory.getLogger(Teil2ThymeleafApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(Teil2ThymeleafApplication.class, args);
    }

}
