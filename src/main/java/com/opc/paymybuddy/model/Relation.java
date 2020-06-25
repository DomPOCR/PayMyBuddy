package com.opc.paymybuddy.model;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="relation")
public class Relation implements Serializable {

	@Id
    @JoinColumn(name = "owner_id")
    @ManyToMany
    private User owner_id;

	@Column(name="buddy_id")
	private User buddy_id;



}
