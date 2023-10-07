package com.essaid.model.internal.impl;

import com.essaid.model.internal.EntityBindingConfig;

public class EntityBindingConfigImpl<E, B, S> implements EntityBindingConfig<E, B, S> {

    private final Class<? extends E> entityInterface;
    private final Class<? extends B>[] bindingInterfaces;

    public EntityBindingConfigImpl(Class<? extends E> entityInterface, Class<? extends B>... bindingInterfaces) {
        this.entityInterface = entityInterface;
        this.bindingInterfaces = bindingInterfaces;
    }

    @Override
    public Class<? extends E> getEntityInterface() {
        return entityInterface;
    }

    @Override
    public Class<? extends B>[] getBindingInterfaces(Class<? extends E> entityInterface) {
        return bindingInterfaces;
    }

    @Override
    public <T extends S> T createBindingState() {
        return null;
    }
}
