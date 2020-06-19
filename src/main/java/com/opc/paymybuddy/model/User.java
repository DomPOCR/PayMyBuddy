package com.opc.paymybuddy.model;


import java.io.Serializable;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="user")
public class User  implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;
	
	@Column(name="lastname",length=50)
    @NotNull
    private String lastname;

    @Column(name="firstname",length=50)
    @NotNull
    private String firstname;

    @Column(name="email",unique = true)
    @Email
    @NotNull
    private String email;

    @Column(name="password",length=255)
    @NotNull
    private String password;

    @Column(name="balance",columnDefinition = "Decimal(9,2)")
    @NotNull
    private BigDecimal balance;

    @Column(name="createDate")
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name="isActive", columnDefinition = "TINYINT", length = 1)
	@NotNull
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

	public void setActive(boolean active) {
		isActive = active;
	}
}
