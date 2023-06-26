package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

public class Person {
    @Id
    @Getter
    @Setter
    private String _id;

    @Getter
    @Setter
    private String idPerson;

    @Getter
    @Setter
    private int index;

    @Getter
    @Setter
    private int age;

    @Getter
    @Setter
    private String eyeColor;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String gender;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String about;

    @Getter
    @Setter
    private String registered;

    @Getter
    @Setter
    public boolean isEmployee;
}
