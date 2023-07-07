package com.example.restaurantapi.services.email.interfaces;

import com.example.restaurantapi.model.EmailDetails;

public interface IEmailService {

    String sendMail(EmailDetails details);

}
