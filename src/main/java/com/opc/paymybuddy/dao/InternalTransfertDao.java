package com.opc.paymybuddy.dao;

import com.opc.paymybuddy.model.ExternalTransfert;
import com.opc.paymybuddy.model.InternalTransfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternalTransfertDao extends JpaRepository<InternalTransfert,Long> {
}
