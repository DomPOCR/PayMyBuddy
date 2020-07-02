package com.opc.paymybuddy.model;

import java.io.Serializable;

public class RelationId implements Serializable {

    private int owner_id;
    private int buddy_id;

    public RelationId(){
        super();
    }

    public RelationId(int owner_id, int buddy_id) {
        this.owner_id = owner_id;
        this.buddy_id = buddy_id;
    }

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
}
