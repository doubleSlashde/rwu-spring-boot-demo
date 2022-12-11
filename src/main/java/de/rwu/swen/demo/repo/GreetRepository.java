package de.rwu.swen.demo.repo;

import org.springframework.stereotype.Repository;

@Repository
public class GreetRepository {
        public String getGreeting() {
        return "Hallo RWU!";
    }
}

