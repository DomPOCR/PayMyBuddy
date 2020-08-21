package com.opc.paymybuddy.service;


import com.opc.paymybuddy.dao.TransfertDao;
import com.opc.paymybuddy.model.InternalTransfert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TransfertServiceImpl implements TransfertService {

    @Autowired
    TransfertDao transfertDao;

    @Override
    public void transfertBuddy(InternalTransfert transfertBuddy){

    }
}
