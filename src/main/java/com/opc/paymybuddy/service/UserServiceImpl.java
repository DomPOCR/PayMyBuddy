package com.opc.paymybuddy.service;

import com.opc.paymybuddy.dao.RelationDao;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.dto.UserDto;
import com.opc.paymybuddy.model.Relation;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.web.exceptions.DataAlreadyExistException;
import com.opc.paymybuddy.web.exceptions.DataMissingException;
import com.opc.paymybuddy.web.exceptions.DataNotExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RelationDao relationDao;

    // Encrypt password
    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    // Pour le log4j2
    final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public long count() {
        return userDao.count();
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean addUser(UserDto addUser) throws Exception {

        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        User user = new User();

        if (addUser.getEmail().isEmpty()) {
            logger.error("inscription : KO");
            throw new DataMissingException("Inscription failed : email is required !!");
        }
        if (addUser.getFirstname().isEmpty()) {
            logger.error("inscription : KO");
            throw new DataMissingException("Inscription failed : firstname is required !!");
        }
        if (addUser.getLastname().isEmpty()) {
            logger.error("inscription : KO");
            throw new DataMissingException("Inscription failed : lastname is required !!");
        }
        if (addUser.getPassword().isEmpty()) {
            logger.error("inscription : KO");
            throw new DataMissingException("Inscription failed : password is required !!");
        }

        if (userDao.existsByEmail(addUser.getEmail())) {

            String mess = String.format("Inscription failed : this mail %s is already exist !!", addUser.getEmail());

            logger.info(mess);

            throw new DataAlreadyExistException(mess);

        }
        user.setEmail(addUser.getEmail());
        user.setFirstname(addUser.getFirstname());
        user.setLastname(addUser.getLastname());
        user.setPassword(encoder.encode(addUser.getPassword()));
        user.setBalance(BigDecimal.ZERO);

        user.setCreateDate(date);
        user.setId(0);

        userDao.save(user);
        logger.info("Add user OK " + addUser.toString());
        return true;
    }

    @Override
    public boolean connectUser(UserDto userToConnect) throws Exception {

        if (userToConnect.getEmail().isEmpty()) {
            logger.error("Connexion : KO");
            throw new DataMissingException("Connexion failed : email is required !!");
        }
        if (userToConnect.getPassword().isEmpty()) {
            logger.error("Connexion : KO");
            throw new DataMissingException("Connexion failed : password is required !!");
        }
        User userRegistered = userDao.findByEmail(userToConnect.getEmail());
        if (userRegistered != null) {

            String pwd1 = userToConnect.getPassword();
            String pwd2 = userRegistered.getPassword();

            if (encoder.matches(pwd1, pwd2)) {
                userToConnect.setFirstname(userRegistered.getFirstname());
                userToConnect.setLastname(userRegistered.getLastname());
                return true;
            }
        }
        return false;
    }

    @Override
    public User addBuddy(User newBuddy, Integer userIdToUpdate) throws Exception {

        if (newBuddy.getEmail().isEmpty()) {
            logger.error("inscription : KO");
            throw new DataMissingException("Inscription failed : email is required !!");
        }

        if (userDao.existsByEmail(newBuddy.getEmail())) {

            Optional<User> buddyToAdd = Optional.ofNullable(userDao.findByEmail(newBuddy.getEmail()));
            Optional<User> userToUpdate = userDao.findById(userIdToUpdate);

            if (userToUpdate.isPresent()) {

                newBuddy.setId(buddyToAdd.get().getId());
                newBuddy.setLastname(buddyToAdd.get().getLastname());
                newBuddy.setFirstname(buddyToAdd.get().getFirstname());
                newBuddy.setPassword(buddyToAdd.get().getPassword());
                newBuddy.setBalance(buddyToAdd.get().getBalance());
                newBuddy.setCreateDate(buddyToAdd.get().getCreateDate());
                newBuddy.setListBankAccounts(buddyToAdd.get().getListBankAccounts());
                newBuddy.setListRelations(buddyToAdd.get().getListRelations());

                Relation userToAddRelation = new Relation(userToUpdate.get(), newBuddy);

                // Test buddy inexistant
                if (!userToUpdate.get().getListRelations().contains(userToAddRelation)){

                    List<Relation> listRelation = new ArrayList();
                    listRelation.add(userToAddRelation);
                    userToUpdate.get().setListRelations(listRelation);

                }
                relationDao.save(userToAddRelation);
                userDao.save(userToUpdate.get());
                return newBuddy;

            } else {
                String mess = String.format("Creation buddy failed : user %s does not exist !!", userIdToUpdate);
                logger.info(mess);
                throw new DataNotExistException(mess);
            }
        } else {
            String mess = String.format("Add buddy failed : this email %s does not exist !!", newBuddy.getEmail());
            logger.info(mess);
            throw new DataAlreadyExistException(mess);
        }

    }
}
