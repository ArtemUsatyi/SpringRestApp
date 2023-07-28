package ru.spring.restapi.SpringRestApp.util;

public class PersonNotCreateException extends RuntimeException{
    public PersonNotCreateException(String message){
        super(message);
    }
}
