package com.example.restaurantapi.services.user.interfaces;

import com.example.restaurantapi.model.EmailDetails;

public interface IRecoverPassword {
    String recoverPassword(EmailDetails emailDetails);
}
