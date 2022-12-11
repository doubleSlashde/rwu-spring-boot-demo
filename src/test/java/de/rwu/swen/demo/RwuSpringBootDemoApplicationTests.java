package de.rwu.swen.demo;

import de.rwu.swen.demo.repo.GreetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RwuSpringBootDemoApplicationTests {

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.5");

    /**
     * Prevent DB entries from being created
     */
    @MockBean
    private InitDb initDb;

    @TestConfiguration
    static class TestConfig {

        @Bean
        @Primary
        GreetRepository greetRepository() {
            return new TestGreetRepository();
        }
    }

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testRepoChangedForTest() {
        String url = String.format("http://localhost:%s/greet", port);

        final String json = restTemplate.getForObject(url, String.class);

        assertThat(json).isEqualTo("""
                {"greeting":"Hello Spring Boot Test!"}"""
        );
    }
}

/**
 * Subclass of {@link GreetRepository} which overrides the getGreeting() method.
 * Will be used in @SpringBootTest instead of the "real" one.
 */
class TestGreetRepository extends GreetRepository {

    @Override
    public String getGreeting() {
        return "Hello Spring Boot Test!";
    }
}
