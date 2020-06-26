package com.opc.paymybuddy.model;


import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "relation")
@IdClass(RelationId.class)
public class Relation implements Serializable {

    @Id
    @NotNull
    @Column(name = "owner_id")
    private int owner_id;

    @Id
    @NotNull
    @Column(name = "buddy_id")
    private int buddy_id;

    @ManyToMany
    @JoinColumn(name = "owner_id")
    private List<User> ownerList;

    @ManyToMany
    @JoinColumn(name = "buddy_id")
    private List<User> buddyList;

    public Relation(){};

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int getBuddy_id() {
        return buddy_id;
    }

    public void setBuddy_id(int buddy_id) {
        this.buddy_id = buddy_id;
    }

    public List<User> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(List<User> ownerList) {
        this.ownerList = ownerList;
    }

    public List<User> getBuddyList() {
        return buddyList;
    }

    public void setBuddyList(List<User> buddyList) {
        this.buddyList = buddyList;
    }
}
