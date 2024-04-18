package com.example.bankingapp.Controller;

import com.example.bankingapp.Entity.Customer;
import com.example.bankingapp.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountController accountController;

    @PostMapping("/customer")
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
        accountController.createAccount(customer.getAcctID(), 0, "Active");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customer/{acctID}")
    public Customer getCustomerInfo(@PathVariable int acctID) {
        return customerService.getCustomerInfo(acctID);
    }

    @DeleteMapping("/customerdelete")
    public void deleteCustomer(@RequestParam int acctID) {
        customerService.deleteCustomer(acctID);
    }

}