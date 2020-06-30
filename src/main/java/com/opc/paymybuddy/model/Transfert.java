package com.opc.paymybuddy.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transfert")
@Inheritance(strategy = InheritanceType.JOINED) // La PK est partagée dans 2 sous entités
public class Transfert implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;

    @Column(name = "amount", columnDefinition = "Decimal(9,2)", precision = 9,scale = 2)
    @NotNull
    private BigDecimal amount;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "transactionDate")
    @NotNull
    private Date transactionDate;

    @NotNull
    @Enumerated(EnumType.STRING)  // Persistance en mode texte plutôt que position dans la liste
    private TransfertStatus status;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransfertStatus getStatus() {
        return status;
    }

    public void setStatus(TransfertStatus status) {
        this.status = status;
    }
}

