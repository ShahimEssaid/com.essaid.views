package com.essaid.model.internal;

public interface ModelDefaultConfig<E, D> {

    ModelDefaultConfig<E, D> addEntityDefaultConfig(EntityDefaultConfig<? extends E, ? extends D> defaultConfig);
}
