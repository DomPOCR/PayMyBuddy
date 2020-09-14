package com.opc.paymybuddy.service;


import com.opc.paymybuddy.dao.ExternalTransfertDao;
import com.opc.paymybuddy.dao.InternalTransfertDao;
import com.opc.paymybuddy.dao.TransfertDao;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.dto.InternalTransfertDto;
import com.opc.paymybuddy.model.InternalTransfert;
import com.opc.paymybuddy.model.Relation;
import com.opc.paymybuddy.model.Transfert;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.web.exceptions.DataIncorrectException;
import com.opc.paymybuddy.web.exceptions.DataNotExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
    public List<Transfert> findAll(){
            return transfertDao.findAll();
    }

    @Override
    public InternalTransfertDto transfertBuddy(InternalTransfertDto transfertBuddy) throws Exception {

        Optional<User> userSender = userDao.findById(transfertBuddy.getSenderId());
        Optional<User> userReceiver = userDao.findById(transfertBuddy.getReceiverId());


        // Données manquantes ou incorrectes
        // *********************************

        if (!userSender.isPresent()) {
            String mess = String.format("Transert failed : senderId %d does not exist !!", transfertBuddy.getSenderId());
            logger.info(mess);
            throw new DataNotExistException(mess);
        }
        if (!userReceiver.isPresent()) {
            String mess = String.format("Transert failed : receiverId %d does not exist !!", transfertBuddy.getReceiverId());
            logger.info(mess);
            throw new DataNotExistException(mess);
        }

        if (userSender.get().getBalance().intValue() <= transfertBuddy.getAmount().intValue()) {
            String mess = String.format("Transert failed : Insufficient balance : %d for this user (id %d %s) !!",
                    userSender.get().getBalance().intValue(),userSender.get().getId(),userSender.get().getEmail());
            logger.info(mess);
            throw new DataIncorrectException(mess);
        }
        if (transfertBuddy.getSenderId() == transfertBuddy.getReceiverId()) {
            String mess = String.format("Transert failed : senderId and receiverId cannot be the same !!");
            logger.info(mess);
            throw new DataIncorrectException(mess);
        }
        if (transfertBuddy.getAmount().intValue() <= 0) {
            String mess = String.format("Transert failed : the amount to be credited must be greater than 0 !!");
            logger.info(mess);
            throw new DataIncorrectException(mess);
        }

        Relation myBuddy = new Relation(userSender.get(), userReceiver.get());

        if (!userSender.get().getListRelations().contains(myBuddy)) {
            String mess = String.format("Transert failed : this user (id %d  %s) is not in this user's (id %d %s) friend list !! Add it before making a transfer !!",
                    userReceiver.get().getId(), userReceiver.get().getEmail(), userSender.get().getId(), userSender.get().getEmail());
            logger.info(mess);
            throw new DataNotExistException(mess);
        }

        // Mise à jour des soldes
        // **********************

        // Receiver

        BigDecimal receiverNewBalance = BigDecimal.valueOf(userReceiver.get().getBalance().intValue() + transfertBuddy.getAmount().intValue());

        userReceiver.get().setBalance(receiverNewBalance);
        userDao.save(userReceiver.get());

        transfertBuddy.setReceiverBalance(userReceiver.get().getBalance());

        // Sender

        BigDecimal senderNewBalance = BigDecimal.valueOf(userSender.get().getBalance().intValue() - transfertBuddy.getAmount().intValue());

        userSender.get().setBalance(senderNewBalance);
        userDao.save(userSender.get());

        transfertBuddy.setSenderBalance(userSender.get().getBalance());

        // Sauvegarde transaction
        // **********************

        Date now = new Date();

        InternalTransfert internalTransfert = new InternalTransfert(userSender.get(),userReceiver.get(),
                                                                    transfertBuddy.getAmount(),transfertBuddy.getDescription(),now);

         internalTransfertDao.save(internalTransfert);


        String mess = String.format("Transfert buddy OK, from user id %d to user id %d amount = %d",
                transfertBuddy.getSenderId(),
                transfertBuddy.getReceiverId(),
                transfertBuddy.getAmount().intValue());
        logger.info(mess);

        return transfertBuddy;

    }
}
