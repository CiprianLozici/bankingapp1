package com.example.bankingapp.Service;

import com.example.bankingapp.Repository.LoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bankingapp.Entity.Logger;

import java.util.List;


@Service
public class LoggerService {
    @Autowired
    LoggerRepository loggerRepository;

    public void addLog(Logger logger) {
        loggerRepository.save(logger);
    }

    public Logger showLog(int acctID) {
        return loggerRepository.findById(acctID).orElse(null);
    }

    public void deleteLog(int acctID) {
        loggerRepository.deleteById(acctID);
    }

    public List<Logger> getAllLogsById(int acctID) {
        return loggerRepository.findAllByAcctID(acctID);
    }
}