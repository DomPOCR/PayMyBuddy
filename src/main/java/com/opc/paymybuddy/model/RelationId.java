package com.opc.paymybuddy.model;

import java.io.Serializable;

public class RelationId implements Serializable {

    private int owner_id;
    private int buddy_id;

    public RelationId(int owner_id, int buddy_id) {
        this.owner_id = owner_id;
        this.buddy_id = buddy_id;
    }
}
