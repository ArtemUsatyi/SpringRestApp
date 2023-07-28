package ru.spring.restapi.SpringRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.restapi.SpringRestApp.models.People;
import ru.spring.restapi.SpringRestApp.repositories.PeopleRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<People> findAll() {
        return peopleRepository.findAll();
    }
    public People findOne(int id){
        return peopleRepository.findById(id).orElse(null);
    }
}
