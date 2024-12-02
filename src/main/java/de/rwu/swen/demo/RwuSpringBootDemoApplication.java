package de.rwu.swen.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@EnableJpaRepositories
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class RwuSpringBootDemoApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(RwuSpringBootDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RwuSpringBootDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner startup() {
        return args -> LOGGER.info("Running");
    }

}
