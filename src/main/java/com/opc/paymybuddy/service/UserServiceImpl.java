package com.opc.paymybuddy.service;

import com.opc.paymybuddy.dao.BankAccountDao;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.dto.UserDto;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.web.exceptions.DataAlreadyExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    BankAccountDao bankAccountDao;
    @Autowired
    BankAccountService bankAccountService;

    // Encrypt password
    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    // Pour le log4j2
    static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

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
    public void addUser(UserDto addUser) throws Exception {

        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        User user = new User();

       if (userDao.existsByEmail(addUser.getEmail())) {

           String mess= String.format("Le mail %s existe déjà !!", addUser.getEmail());

           logger.info(mess);

           throw new DataAlreadyExistException(HttpStatus.CONFLICT, mess);

        }

        user.setEmail(addUser.getEmail());
        user.setFirstname(addUser.getFirstName());
        user.setLastname(addUser.getLastName());
        user.setPassword(encoder.encode(addUser.getPassword()));
        user.setBalance(BigDecimal.ZERO);

        user.setCreateDate(date);
        user.setId(0);

        userDao.save(user);
        logger.info("Add user OK " + addUser.toString());

    }


   /* public User addBuddy(User newBuddy) {
        return null;
    }
*/
}
