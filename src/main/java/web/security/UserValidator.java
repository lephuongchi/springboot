package web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import web.model.Customer;
import web.repository.CustomerRepository;

public class UserValidator implements Validator {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        Customer customer = (Customer) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (customer.getUserName().length() < 6 || customer.getUserName().length() > 32) {
            errors.rejectValue("username", "Size.userForm.userName");
        }
        if (customerRepository.findByUserName(customer.getUserName()) != null) {
            errors.rejectValue("username", "Duplicate.userName");
        }
    }
}
