package com.opc.paymybuddy.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class InternalTransfertDto {

    @NotNull
    private Integer senderId;
    @NotNull
    private Integer receiverId;
    @NotNull
    private BigDecimal amount;
    private String description;

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
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
}
