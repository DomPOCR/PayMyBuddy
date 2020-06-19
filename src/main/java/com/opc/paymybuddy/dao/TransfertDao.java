package com.opc.paymybuddy.dao;

import com.opc.paymybuddy.model.InternalTransfert;
import com.opc.paymybuddy.model.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransfertDao extends JpaRepository<Transfert,Long> {
}
