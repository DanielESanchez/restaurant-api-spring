package com.example.restaurantapi.services.employee.implementation;

import com.example.restaurantapi.model.person.Chef;
import com.example.restaurantapi.repository.ChefRepository;
import com.example.restaurantapi.services.AttributeCheckerService;
import com.example.restaurantapi.services.employee.interfaces.IChefService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ChefService implements IChefService {
    private final ChefRepository chefRepository;
    private final AttributeCheckerService attributeCheckerService;

    public ChefService(ChefRepository chefRepository, AttributeCheckerService attributeCheckerService) {
        this.chefRepository = chefRepository;
        this.attributeCheckerService = attributeCheckerService;
    }

    @Override
    public List<Chef> getAllChefs() {
        List<Chef> chefList = chefRepository.findAllChefs();
        if (chefList.size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There are no chefs to show");
        }
        return chefList;
    }

    @Override
    public List<Chef> getAvailableChefs() {
        List<Chef> allChefs = getAllChefs();
        List<Chef> availableChefs = new ArrayList<>();
        for(Chef chef: allChefs){
            if(!chef.getIsWorking() && chef.getJob().equals("chef")) availableChefs.add(chef);
        }
        return availableChefs;
    }

    @Override
    public Chef findChefByIdEmployee(String idEmployee) {
        Chef chef = chefRepository.findByIdEmployee(idEmployee);
        if (chef == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Chef not found");
        }
        return chef;
    }

    @Override
    public String saveChef(Chef chef) {
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(chef);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        chef.setIsEmployee(true);
        chef.setIsWorking(false);
        chefRepository.save(chef);
        return chef.getIdEmployee() + " was successfully added.";
    }

    @Override
    public String updateChef(Chef newChef) {
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(newChef);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        Chef oldChef = chefRepository.findByIdEmployee(newChef.getIdEmployee());
        if(oldChef == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Chef not found");
        }
        String _id = oldChef.get_id();
        newChef.set_id(_id);
        chefRepository.save(newChef);
        return newChef.getIdEmployee() + " chef was updated.";
    }

    @Override
    public String deleteChef(String idEmployee) {
        Chef chef = chefRepository.findByIdEmployee(idEmployee);
        if(chef == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Chef Not Found");
        }
        String _id = chef.get_id();
        chefRepository.deleteById(_id);
        return chef.getIsEmployee() + " was successfully deleted.";
    }
}
