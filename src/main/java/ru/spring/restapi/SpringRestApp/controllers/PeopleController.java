package ru.spring.restapi.SpringRestApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.spring.restapi.SpringRestApp.dto.PersonDTO;
import ru.spring.restapi.SpringRestApp.models.Person;
import ru.spring.restapi.SpringRestApp.services.PeopleService;
import ru.spring.restapi.SpringRestApp.util.PersonNotCreateException;
import ru.spring.restapi.SpringRestApp.util.PersonNotFoundException;
import ru.spring.restapi.SpringRestApp.util.PersonErrorResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<PersonDTO> findAll() {
        return peopleService.findAll().stream().map(this::convertToPersonDTO).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public PersonDTO findOne(@PathVariable("id") int id) {
        return convertToPersonDTO(peopleService.findOne(id));
    }
    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            StringBuilder errorsMes = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorsMes.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new PersonNotCreateException(errorsMes.toString());
        }
        peopleService.save(convertToPerson(personDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {
        PersonErrorResponse errorResponse = new PersonErrorResponse("Person not found!", System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); // NOT_FOUND - ошибка, 404 статус
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreateException e) {
        PersonErrorResponse errorResponse = new PersonErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); // BAD_REQUEST - ошибка, 400 статус
    }

    public Person convertToPerson(PersonDTO personDTO){
        return modelMapper.map(personDTO, Person.class);
    }
    public PersonDTO convertToPersonDTO(Person person){
        return modelMapper.map(person, PersonDTO.class);
    }
}
