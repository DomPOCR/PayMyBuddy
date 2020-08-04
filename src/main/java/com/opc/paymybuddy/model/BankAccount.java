package com.opc.paymybuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_bank_account_user1"))
    private User user;


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


    @JsonIgnore
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public Integer getUserId() {
        return user.getId();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
