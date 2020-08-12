package com.opc.paymybuddy.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "relation")
public class Relation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "owner", foreignKey = @ForeignKey(name = "fk_relation_owner"))
    private User owner;

    @ManyToOne
    @JoinColumn(name = "buddy", foreignKey = @ForeignKey(name = "fk_relation_buddy"))
    private User buddy;



    @Override
    public String toString() {
        return "Relation{" +
                "id=" + id +
                ", owner=" + owner.getLastname() + " " + owner.getFirstname() +
                ", buddy=" + buddy.getLastname() + " " + buddy.getFirstname() +
                '}';
    }

    public Relation() {
        super();
    }

    public Relation(User owner, User buddy) {
        this.owner = owner;
        this.buddy = buddy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public User getOwner() {
        return owner;
    }

    @JsonProperty("owner")
    public Integer getOwnerId() {
        return owner.getId();
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @JsonIgnore
    public User getBuddy() {
        return buddy;
    }

    @JsonProperty("buddy")
    public String getBuddyDetail() {
        return toString();
    }

    public void setBuddy(User buddy) {
        this.buddy = buddy;
    }
}

