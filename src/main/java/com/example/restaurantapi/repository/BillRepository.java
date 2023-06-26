package com.example.restaurantapi.repository;

import com.example.restaurantapi.model.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends MongoRepository<Bill, String> {

    @Query(value = "{idBill:'?0'}", fields="{'_id' : 1, idBill: 1}")
    Bill findBillByIdBill(String productId);
}
