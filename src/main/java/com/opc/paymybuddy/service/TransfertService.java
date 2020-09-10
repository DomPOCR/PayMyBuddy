package com.opc.paymybuddy.service;

import com.opc.paymybuddy.dto.InternalTransfertDto;
import com.opc.paymybuddy.model.InternalTransfert;

public interface TransfertService {

   InternalTransfert transfertBuddy(InternalTransfertDto transfertBuddy) throws Exception;

}
