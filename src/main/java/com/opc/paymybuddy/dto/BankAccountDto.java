package com.opc.paymybuddy.dto;

import javax.validation.constraints.NotNull;

public class BankAccountDto {

    @NotNull
    private String iban;
    private String bic;
    private String bankName;
    private String accountName;

    public BankAccountDto(@NotNull String iban, String bic, String bankName, String accountName) {
        this.iban = iban;
        this.bic = bic;
        this.bankName = bankName;
        this.accountName = accountName;
    }

    @Override
    public String toString() {
        return "BankAccountDto{" +
                "iban='" + iban + '\'' +
                ", bic='" + bic + '\'' +
                ", bankName='" + bankName + '\'' +
                ", accountName='" + accountName + '\'' +
                '}';
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
