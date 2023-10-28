package com.essaid.model_old.internal.config;

public interface ModelDefaultConfig<E, D> {

    ModelDefaultConfig<E, D> addEntityDefaultConfig(EntityDefaultConfig<? extends E, ? extends D> defaultConfig);
}
