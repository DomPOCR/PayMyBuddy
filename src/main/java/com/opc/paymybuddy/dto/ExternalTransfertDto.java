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
    private BigDecimal fees;
    private BigDecimal amountDebited;
    private BigDecimal amountCredited;
    private String description;
    private BigDecimal accountBalance;

    public ExternalTransfertDto() {
        super();
    }

    public ExternalTransfertDto(@NotNull Integer userId, @NotNull String iban, @NotNull BigDecimal amount, BigDecimal fees, BigDecimal amountDebited, BigDecimal amountCredited, String description, BigDecimal accountBalance) {
        super();
        this.userId = userId;
        this.iban = iban;
        this.amount = amount;
        this.fees = fees;
        this.amountDebited = amountDebited;
        this.amountCredited = amountCredited;
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

    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }

    public BigDecimal getAmountDebited() {
        return amountDebited;
    }

    public void setAmountDebited(BigDecimal amountDebited) {
        this.amountDebited = amountDebited;
    }

    public BigDecimal getAmountCredited() {
        return amountCredited;
    }

    public void setAmountCredited(BigDecimal amountCredited) {
        this.amountCredited = amountCredited;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

}

