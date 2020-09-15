package com.opc.paymybuddy.service;

import com.opc.paymybuddy.dto.ExternalTransfertDto;
import com.opc.paymybuddy.dto.InternalTransfertDto;
import com.opc.paymybuddy.model.Transfert;

import java.util.List;

public interface TransfertService {

    List<Transfert> findAll();

    InternalTransfertDto transfertBuddy(InternalTransfertDto transfertBuddy) throws Exception;

    ExternalTransfertDto transfertBank(ExternalTransfertDto transfertBank);
}
