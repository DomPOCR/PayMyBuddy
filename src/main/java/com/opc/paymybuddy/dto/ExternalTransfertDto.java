package com.opc.paymybuddy.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ExternalTransfertDto {

    @NotNull
    private Integer userId;
    @NotNull
    private String iban;
    @NotNull
    private BigDecimal amount;
    private String description;
    private BigDecimal accountBalance;


    public ExternalTransfertDto(@NotNull Integer userId, @NotNull String iban, @NotNull BigDecimal amount, String description, BigDecimal accountBalance) {
        super();
        this.userId = userId;
        this.iban = iban;
        this.amount = amount;
        this.description = description;
        this.accountBalance = accountBalance;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

}

