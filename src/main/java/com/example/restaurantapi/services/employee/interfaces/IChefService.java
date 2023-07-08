package com.example.restaurantapi.services.employee.interfaces;

import com.example.restaurantapi.model.person.Chef;

import java.util.List;

public interface IChefService {

    List<Chef> getAllChefs();
    List<Chef> getAvailableChefs();
    Chef findChefByIdEmployee(String idEmployee);
    String saveChef(Chef chef);
    String updateChef(Chef newChef);
    String deleteChef(String idEmployee);
}
