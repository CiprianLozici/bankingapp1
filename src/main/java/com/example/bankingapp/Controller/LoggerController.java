package com.example.bankingapp.Controller;

import com.example.bankingapp.Service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.bankingapp.Entity.Logger;

import java.util.List;


@RestController
public class LoggerController {
    @Autowired
    private LoggerService loggerService;

    // addLog
    public void addLog(Logger logger) {
        loggerService.addLog(logger);
    }

    // showLog
    @GetMapping("/accountidlastlog")
    public Logger showLog(@RequestParam int acctID) {
        return loggerService.showLog(acctID);
    }

    @GetMapping("/accountidlogs")
    public ResponseEntity<List<Logger>> getAllLogsById(@RequestParam int acctID) {
        List<Logger> logs = loggerService.getAllLogsById(acctID);
        if (logs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(logs);
    }

    public void deleteLog(int acctID) {
        loggerService.deleteLog(acctID);
    }
}