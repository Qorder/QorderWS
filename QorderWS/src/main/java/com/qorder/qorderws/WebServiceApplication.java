package com.qorder.qorderws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Spring boot Application initializer, that also serves as spring root context.
 *
 * @author Grigorios
 */
@SpringBootApplication
@EnableJpaRepositories("com.qorder.qorderws.repository")
public class WebServiceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebServiceApplication.class);

    /**
     * Invokes spring boot application runner to start application.
     *
     * @param args run arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }

}
