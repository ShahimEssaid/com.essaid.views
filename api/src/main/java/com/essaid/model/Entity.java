package com.essaid.model;

import com.essaid.model.internal.EntityState;

public interface Entity {

    default Internal internal() {
        return (Internal) this;
    }

    interface Internal extends Entity {

        Class<?> getEntityType();

        EntityState getState();

    }
}
