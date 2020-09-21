package com.opc.paymybuddy.model;


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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "amount", columnDefinition = "Decimal(9,2)", precision = 9,scale = 2)
    @NotNull
    private BigDecimal amount;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "transactionDate")
    @NotNull
    private Date transactionDate;

    public Transfert(@NotNull BigDecimal amount, String description, @NotNull Date transactionDate) {
        super();
        this.amount = amount;
        this.description = description;
        this.transactionDate = (Date)transactionDate.clone();
    }

    public Transfert(@NotNull Integer id,@NotNull BigDecimal amount, String description, @NotNull Date transactionDate) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.transactionDate = (Date)transactionDate.clone();
    }

    public Transfert() {
        super();
    }

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
        return (Date)transactionDate.clone();
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = (Date)transactionDate.clone();
    }

}

