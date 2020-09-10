package com.opc.paymybuddy.web.controller;

import com.opc.paymybuddy.dto.InternalTransfertDto;
import com.opc.paymybuddy.model.InternalTransfert;
import com.opc.paymybuddy.service.TransfertService;

import com.opc.paymybuddy.web.exceptions.DataMissingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TransfertController {

    @Autowired
    private TransfertService transfertService;

    // Pour le log4j2
    final Logger logger = LogManager.getLogger(this.getClass().getName());

    // Credit buddy (transfert interne)
    @PostMapping(value = "/transfert/buddy")
    public ResponseEntity<InternalTransfert> transfertBuddy (@RequestBody @Valid InternalTransfertDto transfertBuddy, Errors errors) throws Exception{


        if (errors.hasErrors()) {
            logger.error("Transfert : KO");
            throw new DataMissingException("Transfert failed : id sender, id receiver and amount are required !!");
        }

       InternalTransfert transfertBuddyResult = transfertService.transfertBuddy(transfertBuddy);

        //logger.info("Transfert de " + transfertBuddy.getUserSender() + "vers " + transfertBuddy.getUserReceiver() + " OK");
        return new ResponseEntity(transfertBuddy, HttpStatus.CREATED);
    }


}
