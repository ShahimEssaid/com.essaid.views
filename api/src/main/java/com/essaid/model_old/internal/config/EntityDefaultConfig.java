package com.essaid.model_old.internal.config;

public interface EntityDefaultConfig<E, D> {

    Class<? extends E> getEntityType();

    Class<? extends D>[] getDefaults(Class<? extends E> entityType);
}
