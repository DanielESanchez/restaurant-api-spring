package com.example.restaurantapi.services.user.interfaces;

import com.example.restaurantapi.model.EmailDetails;

public interface IEmailService {

    String sendMail(EmailDetails details);

}
