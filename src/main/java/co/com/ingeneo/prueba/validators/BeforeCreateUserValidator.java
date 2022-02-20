package co.com.ingeneo.prueba.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import co.com.ingeneo.prueba.entities.User;

@Component
public class BeforeCreateUserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "role.required");
    }
}
