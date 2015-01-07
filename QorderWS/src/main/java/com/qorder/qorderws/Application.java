package com.qorder.qorderws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import java.util.Locale;


@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    /**
     * Invokes spring boot application runner to start application.
     *
     * @param args run arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
