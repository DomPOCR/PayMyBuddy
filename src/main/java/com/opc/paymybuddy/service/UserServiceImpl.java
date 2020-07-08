package com.opc.paymybuddy.service;

import com.opc.paymybuddy.dao.BankAccountDao;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.dto.UserDto;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.web.exceptions.DataAlreadyExistException;
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

    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

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
           throw new DataAlreadyExistException(HttpStatus.BAD_REQUEST, mess);

        }

        user.setEmail(addUser.getEmail());
        user.setFirstname(addUser.getFirstName());
        user.setLastname(addUser.getLastName());
        user.setPassword(encoder.encode(addUser.getPassword()));
        user.setBalance(BigDecimal.ZERO);

        user.setCreateDate(date);
        user.setId(0);

        userDao.save(user);
    }


   /* public User addBuddy(User newBuddy) {
        return null;
    }
*/
}
