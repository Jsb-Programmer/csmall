package com.cskaoyan.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidationUtil {
    public static String dealWithFieldError(BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String message = fieldError.getDefaultMessage();
            return message;
        }
        return null;
    }
}

