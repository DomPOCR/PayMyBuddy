package com.opc.paymybuddy.dao;

import com.opc.paymybuddy.model.BankAccount;
import com.opc.paymybuddy.model.ExternalTransfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalTransfertDao extends JpaRepository<ExternalTransfert,Integer> {

}
