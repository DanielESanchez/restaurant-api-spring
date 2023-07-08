package com.example.restaurantapi.services.restaurant.implementation;

import com.example.restaurantapi.model.restaurant.Bill;
import com.example.restaurantapi.repository.BillRepository;
import com.example.restaurantapi.services.AttributeCheckerService;
import com.example.restaurantapi.services.restaurant.interfaces.IBillService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BillService implements IBillService {

    private final BillRepository billRepository;
    private final AttributeCheckerService attributeCheckerService;

    public BillService(BillRepository billRepository, AttributeCheckerService attributeCheckerService) {
        this.billRepository = billRepository;
        this.attributeCheckerService = attributeCheckerService;
    }

    @Override
    public List<Bill> allBill() {
        List<Bill> billList = billRepository.findAll();
        if (billList.size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There are no bills to show.");
        }
        return billRepository.findAll();
    }

    @Override
    public Bill findById(String idBill) {
        Bill bill = billRepository.findBillByIdBill(idBill);
        if(bill == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Bill not found.");
        }
        return bill;
    }

    @Override
    public String newBill(Bill bill) {
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(bill);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        billRepository.save(bill);
        return bill.getIdBill() + " was successfully saved.";
    }

    @Override
    public String updateBill(Bill newBill) {
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(newBill);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        Bill oldBill = billRepository.findBillByIdBill(newBill.getIdBill());
        if(oldBill == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Bill to update not found.");
        }
        String _id = oldBill.get_id();
        return billRepository.findById(_id)
                .map(bill -> {
                    bill.setIdBill(newBill.getIdBill());
                    bill.setDateBill(newBill.getDateBill());
                    bill.setCashier(newBill.getCashier());
                    bill.setCustomer(newBill.getCustomer());
                    bill.setSubtotal(newBill.getSubtotal());
                    bill.setTable(newBill.getTable());
                    bill.setTaxesPercentage(newBill.getTaxesPercentage());
                    bill.setTaxesTotal(newBill.getTaxesTotal());
                    bill.setDiscount(newBill.getDiscount());
                    bill.setDiscountTotal(newBill.getDiscountTotal());
                    bill.setTotal(newBill.getTotal());
                    bill.setItems(newBill.getItems());
                    billRepository.save(bill);
                    return bill.getIdBill() + " was successfully updated.";
                })
                .orElseGet(() -> {
                    billRepository.save(newBill);
                    return newBill.getIdBill() + " was saved.";
                });
    }

    @Override
    public String deleteBill(String idBill) {
        Bill bill = billRepository.findBillByIdBill(idBill);
        if(bill == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Menu Item Not Found");
        }
        String _id = bill.get_id();
        billRepository.deleteById(_id);
        return bill.getIdBill() + " was successfully deleted.";
    }
}
