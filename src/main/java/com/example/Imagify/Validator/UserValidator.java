package com.example.Imagify.Validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserValidator {
    
    public static boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }
    
    public static boolean isValidName(String name) {
        return StringUtils.isAlphanumeric(name) && name.length() >= 3;    
    }
    
    public static boolean isValidPassword(String password) {
        return password.length() <= 8 && StringUtils.isAlphanumeric(password);
    }
    
}

