package de.rwu.swen.demo.repo;

import de.rwu.swen.demo.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstNameOrLastName(String firstName, String lastName);

    Page<Student> findAllByOrderById(Pageable pageable);

}
