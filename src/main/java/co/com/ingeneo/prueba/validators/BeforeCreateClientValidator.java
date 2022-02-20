package co.com.ingeneo.prueba.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import co.com.ingeneo.prueba.entities.Client;

@Component
public class BeforeCreateClientValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
    }
}
