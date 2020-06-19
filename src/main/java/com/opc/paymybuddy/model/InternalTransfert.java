package com.opc.paymybuddy.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "internal_transfert")
public class InternalTransfert implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="transfert_id")
    @NotNull
    private Long transfert_id;

    public InternalTransfert() {
    }

    public Long getTransfert_id() {
        return transfert_id;
    }

    public void setTransfert_id(Long transfert_id) {
        this.transfert_id = transfert_id;
    }
}


