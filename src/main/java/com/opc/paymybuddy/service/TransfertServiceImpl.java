package com.opc.paymybuddy.service;


import com.opc.paymybuddy.dao.ExternalTransfertDao;
import com.opc.paymybuddy.dao.InternalTransfertDao;
import com.opc.paymybuddy.dao.TransfertDao;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.dto.InternalTransfertDto;
import com.opc.paymybuddy.model.InternalTransfert;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.web.exceptions.DataMissingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class TransfertServiceImpl implements TransfertService {

    // Pour le log4j2
    final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    TransfertDao transfertDao;

    @Autowired
    InternalTransfertDao internalTransfertDao;

    @Autowired
    ExternalTransfertDao externalTransfertDao;

    @Autowired
    UserDao userDao;

    @Override
    public InternalTransfert transfertBuddy(InternalTransfertDto transfertBuddy) throws Exception {

        Optional<User> userSender = userDao.findById(transfertBuddy.getSenderId());
        Optional<User> userReceiver = userDao.findById(transfertBuddy.getReceiverId());

        return null;

    }
}
