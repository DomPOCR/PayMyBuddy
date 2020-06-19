package com.opc.paymybuddy.dao;

import com.opc.paymybuddy.model.Relation;
import com.opc.paymybuddy.model.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationDao extends JpaRepository<Relation,Long> {
}
