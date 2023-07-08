package com.example.restaurantapi.services.user.implementation;

import com.example.restaurantapi.model.EmailDetails;
import com.example.restaurantapi.model.user.User;
import com.example.restaurantapi.repository.UserRepository;
import com.example.restaurantapi.services.email.implementation.EmailService;
import com.example.restaurantapi.services.user.interfaces.IRecoverPasswordService;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RecoverPasswordService implements IRecoverPasswordService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public RecoverPasswordService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    public String recoverPassword(EmailDetails emailDetails) {
        String username = emailDetails.getRecipient();
        User user = userRepository.findUserByUsername(username);
        if(user == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found.");
        }
        String newPassword = generatePassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        String msgBody = """
                Your password has been reset.
                
                To login again you can use the next password:
                
                %s
                """.formatted(newPassword);
        emailDetails.setMsgBody(msgBody);
        return emailService.sendMail(emailDetails);
    }

    private String generatePassword(){
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return "Error generating password with special characters";
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        String password = gen.generatePassword(10, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);
        return password;
    }
}
