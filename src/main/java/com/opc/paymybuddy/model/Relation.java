package com.opc.paymybuddy.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name="relation")
public class Relation implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
	@Column(name="owner_id")
    private Long owner_id;
}
