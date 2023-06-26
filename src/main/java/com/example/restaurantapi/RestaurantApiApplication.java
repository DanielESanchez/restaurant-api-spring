package com.example.restaurantapi;

import com.example.restaurantapi.repository.MenuRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class RestaurantApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantApiApplication.class, args);
    }

}
