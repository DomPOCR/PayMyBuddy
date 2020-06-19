package com.opc.paymybuddy.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "external_transfert")
public class ExternalTransfert implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name="transfert_id")
    private Long transfert_id;

    @Column(name="fees",columnDefinition = "Decimal(9,2)")
    @NotNull
    private BigDecimal fees;

    public ExternalTransfert() {
    }

    public Long getTransfert_id() {
        return transfert_id;
    }

    public void setTransfert_id(Long transfert_id) {
        this.transfert_id = transfert_id;
    }

    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }
}
