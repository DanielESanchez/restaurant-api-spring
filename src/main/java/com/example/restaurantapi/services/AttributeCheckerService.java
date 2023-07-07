package com.example.restaurantapi.services.implementation;

import com.example.restaurantapi.services.IAttributeCheckerService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Objects;

@Service
public class AttributeCheckerService implements IAttributeCheckerService {
    @Override
    public String checkNullsInObject(Object ob){
        String resultTestNull = null;
        for (Field f : ob.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (Objects.isNull(f.get(ob)) && !f.getName().equals("_id")) {
                    resultTestNull =  "Could not find a value for " + f.getName();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultTestNull;
    }
}
