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
}
