package de.rwu.swen.demo.controller;

import de.rwu.swen.demo.repo.GreetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    private final GreetRepository greetRepo;

    public GreetController(GreetRepository greetRepo) {
        this.greetRepo = greetRepo;
    }

    @GetMapping(path = "/greet")
    public GreetResponse greet() {
        return new GreetResponse(greetRepo.getGreeting());
    }

}

record GreetResponse(String greeting) {}
