package com.opc.paymybuddy.model;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;


@Entity
@Table(name="user")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;
	
	@Column(length=50)
    @NotNull
    private String lastname;

    @Column(length=50)
    @NotNull
    private String firstname;

    @Column(unique = true)
  /* @Email */ 
    @NotNull
    private String email;

    @Column(length=255)
    @NotNull
   
    private String password;

    @Column(columnDefinition = "Decimal(9,2)")
    @NotNull
    private BigDecimal balance;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @NotNull
    @Column(columnDefinition = "TINYINT", length = 1)
    private boolean isActive;

    public User() {
		super();
	}

	public User(String lastname, String firstname, String email,
			String password) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
