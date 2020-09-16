package com.opc.paymybuddy.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class InternalTransfertDto  {

    @NotNull
    private Integer senderId;
    @NotNull
    private Integer receiverId;
    @NotNull
    private BigDecimal amount;
    private String description;
    private BigDecimal senderBalance;
    private BigDecimal receiverBalance;

    public InternalTransfertDto() {
        super();
    }

    public InternalTransfertDto(@NotNull Integer senderId, @NotNull Integer receiverId, @NotNull BigDecimal amount, String description)    {
        super();
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.description = description;
    }

    public InternalTransfertDto(@NotNull Integer senderId, @NotNull Integer receiverId, @NotNull BigDecimal amount, String description, BigDecimal senderBalance, BigDecimal receiverBalance) {
        super();
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.description = description;
        this.senderBalance = senderBalance;
        this.receiverBalance = receiverBalance;
    }

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

    public BigDecimal getSenderBalance() {
        return senderBalance;
    }

    public void setSenderBalance(BigDecimal senderBalance) {
        this.senderBalance = senderBalance;
    }

    public BigDecimal getReceiverBalance() {
        return receiverBalance;
    }

    public void setReceiverBalance(BigDecimal receiverBalance) {
        this.receiverBalance = receiverBalance;
    }
}
