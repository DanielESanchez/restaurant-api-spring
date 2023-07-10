package com.example.restaurantapi.services.employee.implementation;

import com.example.restaurantapi.model.person.Cashier;
import com.example.restaurantapi.repository.CashierRepository;
import com.example.restaurantapi.services.AttributeCheckerService;
import com.example.restaurantapi.services.employee.interfaces.ICashierService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CashierService implements ICashierService {
    private final CashierRepository cashierRepository;
    private final AttributeCheckerService attributeCheckerService;

    public CashierService(CashierRepository cashierRepository, AttributeCheckerService attributeCheckerService) {
        this.cashierRepository = cashierRepository;
        this.attributeCheckerService = attributeCheckerService;
    }

    @Override
    public List<Cashier> getAllCashiers() {
        List<Cashier> cashierList = cashierRepository.findAllCashiers();
        if (cashierList.size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There are no cashiers to show");
        }
        return cashierList;
    }

    @Override
    public List<Cashier> getAvailableCashiers() {
        List<Cashier> allCashiers = getAllCashiers();
        List<Cashier> availableCashiers = new ArrayList<>();
        for(Cashier cashier: allCashiers){
            if(!cashier.getIsWorking() && cashier.getJob().equals("cashier")) availableCashiers.add(cashier);
        }
        return availableCashiers;
    }

    @Override
    public Cashier findCashierByIdEmployee(String idEmployee) {
        Cashier cashier = cashierRepository.findByIdEmployee(idEmployee);
        if (cashier == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cashier not found");
        }
        return cashier;
    }

    @Override
    public String saveCashier(Cashier cashier) {
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(cashier);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        cashier.setIsEmployee(true);
        cashier.setIsWorking(false);
        cashierRepository.save(cashier);
        return cashier.getIdEmployee() + " was successfully added.";
    }

    @Override
    public String updateCashier(Cashier newCashier) {
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(newCashier);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        Cashier oldCashier = cashierRepository.findByIdEmployee(newCashier.getIdEmployee());
        if(oldCashier == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cashier was not found");
        }
        String _id = oldCashier.get_id();
        newCashier.set_id(_id);
        cashierRepository.save(newCashier);
        return newCashier.getIdEmployee() + " cashier was updated.";
    }

    @Override
    public String deleteCashier(String idEmployee) {
        Cashier cashier = cashierRepository.findByIdEmployee(idEmployee);
        if(cashier == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cashier Not Found");
        }
        String _id = cashier.get_id();
        cashierRepository.deleteById(_id);
        return cashier.getIsEmployee() + " was successfully deleted.";
    }
}
