package com.opc.paymybuddy.model;


import javax.validation.constraints.NotNull;;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "relation")
public class Relation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="owner")
    private User owner;

    @ManyToOne
    @JoinColumn(name="buddy")
    private User buddy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getBuddy() {
        return buddy;
    }

    public void setBuddy(User buddy) {
        this.buddy = buddy;
    }
}
