package com.essaid.model.internal.impl;

import com.essaid.model.internal.EntityDefaultConfig;

public class EntityDefaultConfigImpl<E, D> implements EntityDefaultConfig<E, D> {

    private final Class<? extends E> entityType;
    private final Class<? extends D>[] defaultTypes;


    public EntityDefaultConfigImpl(Class<? extends E> entityType, Class<? extends D>... defaultTypes) {
        this.entityType = entityType;
        this.defaultTypes = defaultTypes;
    }

    @Override
    public Class<? extends E> getEntityType() {
        return entityType;
    }

    @Override
    public Class<? extends D>[] getDefaults(Class<? extends E> entityType) {
        return defaultTypes;
    }
}
