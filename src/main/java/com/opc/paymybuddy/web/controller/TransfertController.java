package com.opc.paymybuddy.web.controller;

import com.opc.paymybuddy.dto.InternalTransfertDto;
import com.opc.paymybuddy.model.InternalTransfert;
import com.opc.paymybuddy.model.Transfert;
import com.opc.paymybuddy.service.TransfertService;

import com.opc.paymybuddy.web.exceptions.DataMissingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransfertController {

    @Autowired
    private TransfertService transfertService;

    // Pour le log4j2
    final Logger logger = LogManager.getLogger(this.getClass().getName());

    // Liste des transferts
    @GetMapping(value = "/transfert")
    public List<Transfert> ListTransfert() {

        return transfertService.findAll();

    }


    // Credit buddy (transfert interne)
    @PostMapping(value = "/transfert/buddy")
    public ResponseEntity<InternalTransfert> transfertBuddy (@RequestBody @Valid InternalTransfertDto transfertBuddy, Errors errors) throws Exception{


        if (errors.hasErrors()) {
            logger.error("Transfert : KO - id sender, id receiver and amount are required !!");
            throw new DataMissingException("Transfert failed : id sender, id receiver and amount are required !!");
        }

       InternalTransfertDto transfertBuddyResult = transfertService.transfertBuddy(transfertBuddy);

        //logger.info("Transfert de " + transfertBuddy.getUserSender() + "vers " + transfertBuddy.getUserReceiver() + " OK");
        return new ResponseEntity(transfertBuddyResult, HttpStatus.CREATED);
    }


}
