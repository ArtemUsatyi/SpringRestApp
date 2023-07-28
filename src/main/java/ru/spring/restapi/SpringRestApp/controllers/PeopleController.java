package ru.spring.restapi.SpringRestApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.restapi.SpringRestApp.models.People;
import ru.spring.restapi.SpringRestApp.services.PeopleService;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public List<People> findAll() {
        return peopleService.findAll();
    }

    @GetMapping("{id}")
    public People findOne(@PathVariable("id") int id) {
        return peopleService.findOne(id);
    }
}
