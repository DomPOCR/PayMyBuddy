package com.opc.paymybuddy.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "bank_account")
public class BankAccount implements Serializable {

    @Id
    @Column(name = "iban", length = 34)
    @NotNull
    private String iban;

    @Column(name = "bic", length = 50)
    @NotNull
    private String bic;

    @Column(name = "bankName", length = 50)
    @NotNull
    private String bankName;

    @Column(name = "accountName", length = 50)
    @NotNull
    private String accountName;

    public BankAccount() {
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}