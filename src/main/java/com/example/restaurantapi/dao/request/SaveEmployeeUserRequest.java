package com.example.restaurantapi.dao.request;

import com.example.restaurantapi.model.Employee;
import com.example.restaurantapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveEmployeeUserRequest {
    private String username;
    private String password;
    private String idEmployee;
}
