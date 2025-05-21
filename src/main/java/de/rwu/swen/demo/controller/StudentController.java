package de.rwu.swen.demo.controller;

import de.rwu.swen.demo.config.PagingProperties;
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

    private final PagingProperties pagingProperties;

    private final StudentRepository repository;

    public StudentController(PagingProperties pagingProperties, StudentRepository repository) {
        this.pagingProperties = pagingProperties;
        this.repository = repository;
    }

    @GetMapping(path = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Student> getStudentsPaged(@RequestParam int page) {
        // select 10 students from page 'page' (page count starts with 0)
        return repository.findAllByOrderById(PageRequest.of(page, pagingProperties.getPageSize()));
    }

    @GetMapping(path = "/student/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudent(@PathVariable Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @GetMapping(path = "/students/search/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> findStudentByName(@PathVariable String name) {
        return repository.findByFirstNameIgnoreCaseOrLastNameIgnoreCase(name, name);
    }

}
