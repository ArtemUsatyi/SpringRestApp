package ru.spring.restapi.SpringRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spring.restapi.SpringRestApp.models.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {
}
