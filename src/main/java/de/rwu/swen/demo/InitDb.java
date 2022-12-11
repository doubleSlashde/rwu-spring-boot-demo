package de.rwu.swen.demo;

import de.rwu.swen.demo.entity.Student;
import de.rwu.swen.demo.repo.StudentRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class InitDb implements CommandLineRunner {

    Logger LOGGER = LoggerFactory.getLogger(InitDb.class);

    private final StudentRepository studentRepo;

    public InitDb(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public void run(String... args) {
        final long studentsCount = studentRepo.count();
        if (studentsCount > 0) {
            return;
        }

        for (int i = 0; i < 100; i++) {
            final Faker faker = new Faker(Locale.GERMAN);
            Student s = new Student();
            s.setFirstName(faker.name().firstName());
            s.setLastName(faker.name().lastName());
            s.setStreet(faker.address().streetName());
            s.setHouseNr(faker.address().streetAddressNumber());

            LOGGER.info("Inserting {}", s);

            studentRepo.save(s);
        }
    }
}
