package com.example.bankingapp.Controller;

import com.example.bankingapp.Entity.Accounts;
import com.example.bankingapp.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.bankingapp.Entity.Logger;


@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private LoggerController loggerController;


    public void createAccount(int acctID, int balance, String acctStatus) {
        Accounts acct = new Accounts(acctID, balance, acctStatus);
        accountService.createAccount(acct);
    }

    // checkBalance
    @GetMapping("/accountballance")
    public int getBalance(@RequestParam int acctID) {
        return accountService.getBalance(acctID);
    }
//    getAccInfo
    @GetMapping("/account")
    public Accounts getAccountInfo(@RequestParam int acctID) {
        return accountService.getAccountInfo(acctID);
    }

    // depositAmount
    @PostMapping("/deposit")
    public ResponseEntity<Void> depositAmount(@RequestParam int acctID ,int amount) {
        int initBal = getBalance(acctID);
        accountService.depositAmount(acctID, amount);
        Logger logger = new Logger(acctID, "Deposited", "Success", initBal, initBal + amount);
        loggerController.addLog(logger);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // withdrawAmount
    @DeleteMapping("/withdraw")
    public ResponseEntity<Void> withdrawAmount(@RequestParam int acctID, int amount) {
        int initBal = getBalance(acctID);
        accountService.withdrawAmount(acctID, amount);
        Logger logger = new Logger(acctID, "Withdrawn", "Success", initBal, initBal - amount);
        loggerController.addLog(logger);
        return ResponseEntity.ok().build();
    }

    // transferAmount
    @PutMapping("/transfer")
    public void transferAmount(@RequestParam int acctID,  int destAcctID,  int amount) {
        int initBalSender = getBalance(acctID);
        int initBalReceiver = getBalance(destAcctID);
        accountService.transferAmount(acctID, destAcctID, amount);
        Logger loggerSender = new Logger(acctID, "Transferred", "Success", initBalSender, initBalSender - amount);
        loggerController.addLog(loggerSender);
        Logger loggerReceiver = new Logger(destAcctID, "Received", "Success", initBalReceiver,
                initBalReceiver + amount);
        loggerController.addLog(loggerReceiver);
    }

    // deleteAccount
    @DeleteMapping("/deleteacc")
    public void deleteAccount(@RequestParam int acctID) {
        accountService.deleteAccount(acctID);
        loggerController.deleteLog(acctID);
    }




}