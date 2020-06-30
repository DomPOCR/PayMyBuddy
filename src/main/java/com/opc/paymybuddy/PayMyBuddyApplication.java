package com.opc.paymybuddy;

import com.opc.paymybuddy.dao.ExternalTransfertDao;
import com.opc.paymybuddy.dao.InternalTransfertDao;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.model.Relation;
import com.opc.paymybuddy.model.User;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@EnableEncryptableProperties
@SpringBootApplication
public class PayMyBuddyApplication {

    public static void main(String[] args) {

        // ---------- Lancement des TESTS


        ConfigurableApplicationContext context = SpringApplication.run(PayMyBuddyApplication.class, args);

        /*
        // Test de la connexion JPA
        UserDao userDao = context.getBean(UserDao.class);
        System.out.println("Liste des users : " + userDao.findAll());

        // Test liaison user et BankAccount
        List<User> userList = userDao.findAll();
        System.out.println("IBAN n°1 : " + userList.get(0).getListBankAccounts().get(0).getIban());
        // System.out.println(userList.get(0).getListBankAccounts().get(1).getIban());

        // Test liaison user et relation

         */
        // Test Transferts
        ExternalTransfertDao externalTransfertDao = context.getBean(ExternalTransfertDao.class);
        System.out.println(externalTransfertDao.findAll().get(0).getStatus());

        InternalTransfertDao internalTransfertDao = context.getBean(InternalTransfertDao.class);
        System.out.println(internalTransfertDao.findAll().get(0).getDescription());



        // Lancement normal
        // SpringApplication.run(PayMyBuddyApplication.class, args);
    }

}
