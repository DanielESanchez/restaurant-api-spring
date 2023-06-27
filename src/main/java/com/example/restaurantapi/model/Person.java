package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class Person {
    @Id
    @Getter
    @Setter
    private String _id;

    @Getter
    @Setter
    @Indexed(unique = true)
    private String idPerson;

    @Getter
    @Setter
    private Integer index;

    @Getter
    @Setter
    private Integer age;

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
    @Indexed(unique = true)
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
    public Boolean isEmployee;

    public String getFullName() {
        return name + " " + lastName;
    }
}
