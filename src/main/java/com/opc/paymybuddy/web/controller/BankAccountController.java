package com.opc.paymybuddy.web.controller;

import com.opc.paymybuddy.model.BankAccount;
import com.opc.paymybuddy.service.BankAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.logging.FileHandler;

@RestController
public class BankAccountController {

    //@Qualifier("bankAccountServiceImpl")
    @Autowired
    private BankAccountService bankAccountService;

    // Pour le log4j2
    final Logger logger = LogManager.getLogger(this.getClass().getName());

    // Liste des comptes

    @GetMapping("/Accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<BankAccount> ListBankAccount() {

       return bankAccountService.findAll();
    }

    // Ajout d'un compte

    @PostMapping("/AddAccount/{userId}")
    public ResponseEntity <BankAccount> AddAccount(@RequestBody BankAccount addAccount, @PathVariable Integer userId ) throws Exception {

        BankAccount bankAccountAdd = bankAccountService.addBankAccount(addAccount, userId);
        logger.info("Add bank account for userid" + userId + " OK");
        return new ResponseEntity(bankAccountAdd, HttpStatus.CREATED);

    }
}
