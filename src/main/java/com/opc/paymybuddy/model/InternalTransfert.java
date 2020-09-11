package com.opc.paymybuddy.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "internal_transfert")
@PrimaryKeyJoinColumn(name = "transfert_id")            // PK de l'entité mère
public class InternalTransfert extends Transfert implements Serializable {

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User userSender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User userReceiver;

    public InternalTransfert(){
        super();
    }

    public InternalTransfert(@NotNull User userSender,@NotNull User userReceiver,@NotNull BigDecimal amount, String description, @NotNull Date transactionDate) {
        super();
        this.userSender = userSender;
        this.userReceiver = userReceiver;
        this.setAmount(amount);
        this.setDescription(description);
        this.setTransactionDate(transactionDate);
    }


    public User getUserSender() {
        return userSender;
    }

    public void setUserSender(User userSender) {
        this.userSender = userSender;
    }

    public User getUserReceiver() {
        return userReceiver;
    }

    public void setUserReceiver(User userReceiver) {
        this.userReceiver = userReceiver;
    }
}


