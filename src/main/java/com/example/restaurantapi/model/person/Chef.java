package com.example.restaurantapi.model.person;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("employees")
@ToString
public class Chef{
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
    private Integer age;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String lastName;

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
    private String registered;

    @Getter
    @Setter
    public Boolean isEmployee;

    @Getter
    @Setter
    @Indexed(unique = true)
    private String idEmployee;
    @Getter
    @Setter
    private String job;
    @Getter
    @Setter
    private String  hireDate;
    @Getter
    @Setter
    private Boolean isWorking;
    @Getter
    @Setter
    private Float salary;

    public String getFullName() {
        return name + " " + lastName;
    }

}
