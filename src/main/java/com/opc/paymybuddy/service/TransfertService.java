package com.opc.paymybuddy.service;

import com.opc.paymybuddy.dto.InternalTransfertDto;
import com.opc.paymybuddy.model.InternalTransfert;
import com.opc.paymybuddy.model.Transfert;

import java.util.List;

public interface TransfertService {

   InternalTransfertDto transfertBuddy(InternalTransfertDto transfertBuddy) throws Exception;

    List<Transfert> findAll();

}
