package com.opc.paymybuddy.service;


import com.opc.paymybuddy.dao.*;
import com.opc.paymybuddy.dto.ExternalTransfertDto;
import com.opc.paymybuddy.dto.InternalTransfertDto;
import com.opc.paymybuddy.model.*;
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
import java.util.ResourceBundle;

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

    @Autowired
    BankAccountDao bankAccountDao;

    @Override
    public List<Transfert> findAll() {
        return transfertDao.findAll();
    }


    /**************************************************************************************/
    /***                        Transfert interne vers un ami                           ***/
    /**************************************************************************************/

    @Override
    public InternalTransfertDto transfertBuddy(InternalTransfertDto transfertBuddy) throws Exception {

        Optional<User> userSender = userDao.findById(transfertBuddy.getSenderId());
        Optional<User> userReceiver = userDao.findById(transfertBuddy.getReceiverId());


        // Données manquantes ou incorrectes
        // *********************************

        if (!userSender.isPresent()) {
            String mess = String.format("Transfert failed : senderId %d does not exist !!", transfertBuddy.getSenderId());
            logger.info(mess);
            throw new DataNotExistException(mess);
        }
        if (!userReceiver.isPresent()) {
            String mess = String.format("Transfert failed : receiverId %d does not exist !!", transfertBuddy.getReceiverId());
            logger.info(mess);
            throw new DataNotExistException(mess);
        }

        if (userSender.get().getBalance().intValue() <= transfertBuddy.getAmount().intValue()) {
            String mess = String.format("Transfert failed : Insufficient balance : %d for this user (id %d %s) !!",
                    userSender.get().getBalance().intValue(), userSender.get().getId(), userSender.get().getEmail());
            logger.info(mess);
            throw new DataIncorrectException(mess);
        }
        if (transfertBuddy.getSenderId().equals(transfertBuddy.getReceiverId())) {
            String mess = String.format("Transfert failed : senderId and receiverId cannot be the same !!");
            logger.info(mess);
            throw new DataIncorrectException(mess);
        }
        if (transfertBuddy.getAmount().intValue() <= 0) {
            String mess = String.format("Transfert failed : the amount to be credited must be greater than 0 !!");
            logger.info(mess);
            throw new DataIncorrectException(mess);
        }

        Relation myBuddy = new Relation(userSender.get(), userReceiver.get());

        if (!userSender.get().getListRelations().contains(myBuddy)) {
            String mess = String.format("Transfert failed : this user (id %d  %s) is not in this user's (id %d %s) friend list !! Add it before making a transfer !!",
                    userReceiver.get().getId(), userReceiver.get().getEmail(), userSender.get().getId(), userSender.get().getEmail());
            logger.info(mess);
            throw new DataNotExistException(mess);
        }

        logger.info("Internal transfert to buddy is beginning");

        BigDecimal receiverNewBalance;
        BigDecimal senderNewBalance;

        // Mise à jour des soldes
        // **********************

        // Receiver

        receiverNewBalance = BigDecimal.valueOf(userReceiver.get().getBalance().intValue() + transfertBuddy.getAmount().intValue());

        userReceiver.get().setBalance(receiverNewBalance);
        userDao.save(userReceiver.get());

        transfertBuddy.setReceiverBalance(userReceiver.get().getBalance());

        // Sender

        senderNewBalance = BigDecimal.valueOf(userSender.get().getBalance().intValue() - transfertBuddy.getAmount().intValue());

        userSender.get().setBalance(senderNewBalance);
        userDao.save(userSender.get());

        transfertBuddy.setSenderBalance(userSender.get().getBalance());

        // Sauvegarde transaction
        // **********************

        Date now = new Date();

        InternalTransfert internalTransfert = new InternalTransfert(userSender.get(), userReceiver.get(),
                transfertBuddy.getAmount(), transfertBuddy.getDescription(), now);

        internalTransfertDao.save(internalTransfert);


        String mess = String.format("Transfert buddy OK, from user id %d to user id %d amount = %d",
                transfertBuddy.getSenderId(),
                transfertBuddy.getReceiverId(),
                transfertBuddy.getAmount().intValue());
        logger.info(mess);

        return transfertBuddy;

    }

    /***                        Transfert externe
     ***    -  depuis le compte interne vers la banque si montant négatif
     ***    -  depuis la banque vers le compte interne si montant positif
     *************************************************************************************/

    @Override
    public ExternalTransfertDto transfertBank(ExternalTransfertDto transfertBank) {

        Optional<User> user = userDao.findById(transfertBank.getUserId());
        Optional<BankAccount> bankAccount = Optional.ofNullable(bankAccountDao.findByIban(transfertBank.getIban()));
        User userBank = userDao.findUsersByListBankAccounts(bankAccount);

        BigDecimal fees ;
        BigDecimal amountDebited = new BigDecimal("0.00");
        BigDecimal amountCredited ;
        BigDecimal userNewBalance ;
        BigDecimal diffAmount ;

        //Get parameters values
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        double feeTx = Double.parseDouble(bundle.getString("application.fee.rate"));

        // Données manquantes ou incorrectes
        // *********************************

        if (!user.isPresent()) {
            String mess = String.format("Transfert failed : user Id %d does not exist !!", transfertBank.getUserId());
            logger.info(mess);
            throw new DataNotExistException(mess);
        }
        if (!bankAccount.isPresent() || (transfertBank.getUserId() != userBank.getId()) ) {
            String mess = String.format("Transfert failed : IBAN %s does not exist for this user Id %d !!", transfertBank.getIban(), transfertBank.getUserId());
            logger.info(mess);
            throw new DataNotExistException(mess);
        }
        if (transfertBank.getAmount().intValue() == 0) {
            String mess = String.format("Transfert failed : the amount cannot be equal to 0 !!");
            logger.info(mess);
            throw new DataIncorrectException(mess);
        }


        if (transfertBank.getAmount().intValue() <= 0) {    // Transfert compte interne vers banque

            logger.info("External transfert TO the bank is beginning ");

            // Mise à jour du solde
            // ********************
            fees = BigDecimal.valueOf(feeTx).multiply(transfertBank.getAmount());

            amountDebited = transfertBank.getAmount().add(fees);
            diffAmount = user.get().getBalance().add(amountDebited);

            amountCredited = transfertBank.getAmount().abs().add(fees);

            if (diffAmount.compareTo(BigDecimal.ZERO) < 0) {
                String mess = String.format("Transfert failed : Insufficient balance : %d for this user (id %d %s) !! Need with fees %.2f",
                        user.get().getBalance().intValue(), user.get().getId(), user.get().getEmail(), amountDebited.abs());
                logger.info(mess);
                throw new DataIncorrectException(mess);
            }

            // Add car montant négatif (débit) pour amountDebited
            userNewBalance = user.get().getBalance().add(amountDebited);

            user.get().setBalance(userNewBalance);
            userDao.save(user.get());

            transfertBank.setAccountBalance(user.get().getBalance());
            transfertBank.setFees(fees);

            // Sauvegarde transaction
            // **********************

            Date now = new Date();

            ExternalTransfert externalTransfert = new ExternalTransfert();

            externalTransfert.setFees(fees);
            externalTransfert.setBankAccount(bankAccount.get());
            externalTransfert.setAmount(amountDebited);
            externalTransfert.setDescription(transfertBank.getDescription());
            externalTransfert.setTransactionDate(now);

            externalTransfertDao.save(externalTransfert);

            ExternalTransfertDto externalTransfertDto = new ExternalTransfertDto(
                    transfertBank.getUserId(), transfertBank.getIban(), transfertBank.getAmount(), fees.abs(), amountDebited.abs(), amountCredited,
                    transfertBank.getDescription(), transfertBank.getAccountBalance());

            String mess = String.format("Transfert TO the bank OK, IBAN %s user id %d amount minus fees = %.2f",
                    transfertBank.getIban(),
                    transfertBank.getUserId(),
                    amountCredited.doubleValue());
            logger.info(mess);

            return externalTransfertDto;

        } else {               // Transfert banque vers compte interne

            logger.info("External transfert FROM the bank is beginning ");

            // Mise à jour du solde
            // ********************
            fees = BigDecimal.valueOf(feeTx).multiply(transfertBank.getAmount());

            amountCredited = transfertBank.getAmount().subtract(fees);

            userNewBalance = user.get().getBalance().add(amountCredited);

            user.get().setBalance(userNewBalance);
            userDao.save(user.get());

            transfertBank.setAccountBalance(user.get().getBalance());
            transfertBank.setFees(fees);

            // Sauvegarde transaction
            // **********************

            Date now = new Date();

            ExternalTransfert externalTransfert = new ExternalTransfert();

            externalTransfert.setFees(fees);
            externalTransfert.setBankAccount(bankAccount.get());
            externalTransfert.setAmount(amountDebited);
            externalTransfert.setDescription(transfertBank.getDescription());
            externalTransfert.setTransactionDate(now);

            externalTransfertDao.save(externalTransfert);

            ExternalTransfertDto externalTransfertDto = new ExternalTransfertDto(
                    transfertBank.getUserId(), transfertBank.getIban(), transfertBank.getAmount(), fees.abs(), amountDebited.abs(), amountCredited,
                    transfertBank.getDescription(), transfertBank.getAccountBalance());

            String mess = String.format("Transfert FROM the bank OK, IBAN %s user id %d amount minus fees = %.2f",
                    transfertBank.getIban(),
                    transfertBank.getUserId(),
                    amountCredited.doubleValue());
            logger.info(mess);

            return externalTransfertDto;


        }

    }
}
