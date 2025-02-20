package com.example.bankingapp.Service;

import com.example.bankingapp.Entity.Customer;
import com.example.bankingapp.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }



    public Customer getCustomerInfo(int acctID) {
        return customerRepository.findById(acctID).orElse(null);
    }

    public void deleteCustomer(int acctID) {
        customerRepository.deleteById(acctID);
    }

}