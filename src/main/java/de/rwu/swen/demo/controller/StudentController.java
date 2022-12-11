package de.rwu.swen.demo.controller;

import de.rwu.swen.demo.entity.Student;
import de.rwu.swen.demo.exception.NotFoundException;
import de.rwu.swen.demo.repo.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Student> getStudentsPaged(@RequestParam("page") int page) {
        // select 10 students from page 'page' (page count starts with 0)
        return repository.findAllByOrderById(PageRequest.of(page, 10));
    }

    @GetMapping(path = "/student/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudent(@PathVariable("id") Long id) throws NotFoundException {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @GetMapping(path = "/student/search/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> findStudentByName(@PathVariable("name") String name) {
        return repository.findByFirstNameOrLastName(name, name);
    }

}
