package com.opc.paymybuddy.web.controller;

import com.opc.paymybuddy.model.InternalTransfert;
import com.opc.paymybuddy.service.TransfertService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransfertController {

    @Autowired
    private TransfertService transfertService;

    // Pour le log4j2
    final Logger logger = LogManager.getLogger(this.getClass().getName());

    // Credit buddy (transfert interne)
    @PostMapping(value = "/transfert/buddy")
    public ResponseEntity<InternalTransfert> transfertBuddy (@RequestBody InternalTransfert transfertBuddy) throws Exception{

        //InternalTransfert transactionBuddyResult = TransfertService.transfertBuddy(transfertBuddy);
        return new ResponseEntity(transfertBuddy, HttpStatus.CREATED);
    }


}
