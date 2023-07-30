package ru.spring.restapi.SpringRestApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PersonDTO {
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 40, message = "Имя должно состоять от 2 до 40 символов")
    private String name;

    @NotEmpty(message = "Email не должен быть пустым")
    @Email(message = "Email не валидный")
    private String email;
    @Min(value = 0, message = "Год должен быть больше 0")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
