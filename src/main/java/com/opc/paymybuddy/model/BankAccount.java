package com.opc.paymybuddy.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bank_account")
public class BankAccount {

    @Id
    @Column(length=34)
    @NotNull
    private String iban;

    @Column(length=50)
    @NotNull
    private String bic;

    @Column(length=50)
    @NotNull
    private String bankName;

    @Column(length=50)
    @NotNull
    private String accountName;
}
