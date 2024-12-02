package de.rwu.swen.demo;

import de.rwu.swen.demo.repo.GreetRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true",
        // use a random port for the REST API
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RwuSpringBootDemoApplicationTests {

    public static final int HTTP_OK = HttpStatus.OK.value();
    private static final int HTTP_NOT_FOUND = HttpStatus.NOT_FOUND.value();

    /**
     * Replace with mock, so that no entries are created in student DB table.
     */
    @MockitoBean
    private InitDb initDb;

    @TestConfiguration
    static class TestConfig {

        /**
         * Replace GreetRepository by a fake implementation.
         */
        @Bean
        @Primary
        GreetRepository greetRepository() {
            return new TestGreetRepository();
        }
    }

    @LocalServerPort
    private int port;

    @BeforeEach
    public void beforeEach() {
        // initialize REST Assured to use the random port
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    void testRepoChangedForTest() {

        given()
            .when()
                .get("/greet")
            .then()
                .statusCode(HTTP_OK)
                .body("greeting", equalTo("Hello Spring Boot Test!"));
    }

    @Test
    @Sql("/sql/clear_student_table.sql")
    @Sql("/sql/insert_students.sql")
    void studentsPagination() {
        // page size is configured in test/resources/application.properties
        given()
                .when()
                    .get("/students?page=0")
                .then()
                    .statusCode(HTTP_OK)
                    .body("content.size()", equalTo(3));
    }

    @Test
    @Sql("/sql/clear_student_table.sql")
    @Sql("/sql/insert_students.sql")
    void notFound() {
        // Student with ID 0 does not exist in DB
        // Should be handled by the Rest ErrorHandler
        given()
                .when()
                    .get("/student/0")
                .then()
                    .statusCode(HTTP_NOT_FOUND)
                    .body("errorMsg", equalTo("Student with ID '0' does not exist"));
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
