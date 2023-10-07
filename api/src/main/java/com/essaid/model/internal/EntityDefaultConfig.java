package com.essaid.model.internal;

public interface EntityDefaultConfig<E, D> {

    Class<? extends E> getEntityType();

    Class<? extends D>[] getDefaults(Class<? extends E> entityType);
}
