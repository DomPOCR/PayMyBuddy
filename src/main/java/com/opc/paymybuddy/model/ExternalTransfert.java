package com.opc.paymybuddy.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "external_transfert")
@PrimaryKeyJoinColumn(name = "transfert_id")            // PK de l'entité mère
public class ExternalTransfert extends Transfert implements Serializable {


    @Column(name = "fees", columnDefinition = "Decimal(9,2)", precision = 9, scale = 2)
    @NotNull
    private BigDecimal fees;

    @ManyToOne
    @JoinColumn(name="bank_account_iban")
    private BankAccount bankAccount;

    public ExternalTransfert(@NotNull BigDecimal amount, String description, @NotNull Date transactionDate) {
        super(amount, description, transactionDate);
    }

    public ExternalTransfert() {
        super();
    }



    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
