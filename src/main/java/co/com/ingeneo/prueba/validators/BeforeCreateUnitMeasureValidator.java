package co.com.ingeneo.prueba.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import co.com.ingeneo.prueba.entities.UnitMeasure;

@Component
public class BeforeCreateUnitMeasureValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UnitMeasure.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");        
    }
}
