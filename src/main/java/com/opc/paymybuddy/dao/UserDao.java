package com.opc.paymybuddy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.opc.paymybuddy.model.User;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

    User findByEmail(String email);
    boolean existsByEmail(String email);




}
