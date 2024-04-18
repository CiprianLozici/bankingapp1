package com.example.bankingapp.Repository;

import com.example.bankingapp.Entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    @Override
    List<Customer> findAll();
}