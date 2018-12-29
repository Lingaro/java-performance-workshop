package com.lingaro.web.person;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("v1")
    @Transactional(readOnly = true)
    public Set<String> list1() {
        return personRepository
                .findAll().stream()
                .map(Objects::toString)
                .collect(toSet());
    }

}
