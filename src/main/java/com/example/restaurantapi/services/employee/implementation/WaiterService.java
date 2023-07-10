package com.example.restaurantapi.services.employee.implementation;

import com.example.restaurantapi.model.person.Waiter;
import com.example.restaurantapi.repository.WaiterRepository;
import com.example.restaurantapi.services.AttributeCheckerService;
import com.example.restaurantapi.services.employee.interfaces.IWaiterService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class WaiterService implements IWaiterService {
    private final WaiterRepository waiterRepository;
    private final AttributeCheckerService attributeCheckerService;

    public WaiterService(WaiterRepository waiterRepository, AttributeCheckerService attributeCheckerService) {
        this.waiterRepository = waiterRepository;
        this.attributeCheckerService = attributeCheckerService;
    }

    @Override
    public List<Waiter> getAllWaiters() {
        List<Waiter> waiterList = waiterRepository.findAllWaiter();
        if (waiterList.size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There are no waiters to show");
        }
        return waiterList;
    }

    @Override
    public List<Waiter> getAvailableWaiters() {
        List<Waiter> allWaiters = getAllWaiters();
        List<Waiter> availableWaiters = new ArrayList<>();
        for(Waiter waiter: allWaiters){
            if(!waiter.getIsWorking() && waiter.getJob().equals("waiter")) availableWaiters.add(waiter);
        }
        return availableWaiters;
    }

    @Override
    public Waiter findWaiterByIdEmployee(String idEmployee) {
        Waiter waiter = waiterRepository.findByIdEmployee(idEmployee);
        if (waiter == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Waiter not found");
        }
        return waiter;
    }

    @Override
    public String saveWaiter(Waiter waiter) {
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(waiter);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        waiter.setIsEmployee(true);
        waiter.setIsWorking(false);
        waiterRepository.save(waiter);
        return waiter.getIdEmployee() + " was successfully added.";
    }

    @Override
    public String updateWaiter(Waiter newWaiter) {
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(newWaiter);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        Waiter oldWaiter = waiterRepository.findByIdEmployee(newWaiter.getIdEmployee());
        if(oldWaiter == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Waiter not found");
        }
        String _id = oldWaiter.get_id();
        newWaiter.set_id(_id);
        waiterRepository.save(newWaiter);
        return newWaiter.getIdEmployee() + " waiter was updated.";
    }

    @Override
    public String deleteWaiter(String idEmployee) {
        Waiter waiter = waiterRepository.findByIdEmployee(idEmployee);
        if(waiter == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Waiter Not Found");
        }
        String _id = waiter.get_id();
        waiterRepository.deleteById(_id);
        return waiter.getIsEmployee() + " was successfully deleted.";
    }
}
