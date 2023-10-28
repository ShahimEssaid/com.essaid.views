package com.essaid.model_old.internal.impl;

import com.essaid.model_old.internal.config.EntityDefaultConfig;
import com.essaid.model_old.internal.config.ModelDefaultConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelDefaultConfigImpl<E, D> implements ModelDefaultConfig<E, D> {

    private List<EntityDefaultConfig<? extends E, ? extends D>> configs = new ArrayList<>();

    public ModelDefaultConfigImpl(EntityDefaultConfig<? extends E, ?  extends  D> ... defaultConfigs){
        configs = Arrays.asList(defaultConfigs);
    }

    @Override
    public ModelDefaultConfig<E, D> addEntityDefaultConfig(EntityDefaultConfig<? extends E, ? extends D> defaultConfig) {
        configs.add(defaultConfig);
        return this;
    }
}
