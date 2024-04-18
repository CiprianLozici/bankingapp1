package com.example.bankingapp.Repository;

import com.example.bankingapp.Entity.Logger;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoggerRepository extends CrudRepository<Logger, Integer> {
    List<Logger> findAllByAcctID(int acctID);

}