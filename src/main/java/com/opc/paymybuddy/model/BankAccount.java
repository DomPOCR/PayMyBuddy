package com.opc.paymybuddy.model;

import javax.jws.soap.SOAPBinding;
import javax.validation.constraints.NotNull;;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public BankAccount() {
    }

    public BankAccount(String iban, String bic, String bankName, String accountName, User user) {
        this.iban = iban;
        this.bic = bic;
        this.bankName = bankName;
        this.accountName = accountName;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
