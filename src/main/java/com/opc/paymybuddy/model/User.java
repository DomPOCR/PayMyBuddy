package com.opc.paymybuddy.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "uniqueEmailConstraint")})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;

    @Column(name = "lastname", length = 50)
    @NotNull
    private String lastname;

    @Column(name = "firstname", length = 50)
    @NotNull
    private String firstname;

    @Column(name = "email", length = 255, unique = true)
    @Email
    @NotNull
    private String email;

    @Column(name = "password", length = 255)
    @NotNull
    @JsonIgnore
    private String password;

    @Column(name = "balance", columnDefinition = "Decimal(9,2)")
    @NotNull
    private BigDecimal balance;

    @Column
    @NotNull
    private Date createDate;

    @OneToMany(mappedBy = "user")
    private List<BankAccount> listBankAccounts;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "owner")      // Table relation
    private List<Relation> listRelations;

    public User() {
        super();
    }

    public User(@NotNull String lastname, @NotNull String firstname, @Email @NotNull String email, @NotNull String password, @NotNull BigDecimal balance, @NotNull Date createDate) {
        super();
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.createDate = (Date)createDate.clone();
    }

    public User(@NotNull Integer id, @NotNull String lastname, @NotNull String firstname, @Email @NotNull String email, @NotNull String password, @NotNull BigDecimal balance, @NotNull Date createDate) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.createDate = (Date)createDate.clone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getCreateDate() {
        return (Date)createDate.clone();
    }

    public void setCreateDate(Date createDate) {
        this.createDate = (Date)createDate.clone();
    }

    public List<BankAccount> getListBankAccounts() {
        return listBankAccounts;
    }

    public void setListBankAccounts(List<BankAccount> listBankAccounts) {
        this.listBankAccounts = listBankAccounts;
    }

    public List<Relation> getListRelations() {
        return listRelations;
    }

    public void setListRelations(List<Relation> listRelations) {
        this.listRelations = listRelations;
    }
}