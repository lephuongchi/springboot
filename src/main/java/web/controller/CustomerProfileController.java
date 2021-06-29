package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.Customer;
import web.service.CustomerService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CustomerProfileController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/api/users/user/{id}", produces = "application/json")
    public Customer getUserDetail(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @PostMapping(value = "/token/save")
    public void createCustomer(@RequestParam String username, @RequestParam String password) {
        Customer customer = new Customer();
        customer.setUserName(username);
        customer.setPassword(password);
        customerService.saveCustomer(customer);
    }
}
