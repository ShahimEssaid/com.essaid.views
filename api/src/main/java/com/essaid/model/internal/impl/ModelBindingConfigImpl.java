package com.essaid.model.internal.impl;

import com.essaid.model.internal.EntityBindingConfig;
import com.essaid.model.internal.ModelBindingConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelBindingConfigImpl<E, B, S> implements ModelBindingConfig<E, B, S> {

    private List<EntityBindingConfig<? extends E, ? extends B, ? extends S>> configs = new ArrayList<>();

    public ModelBindingConfigImpl(EntityBindingConfig<? extends E, ? extends B, ? extends S> ... bindingConfigs){
        configs = Arrays.asList(bindingConfigs);
    }


    @Override
    public ModelBindingConfig<E, B, S> addEntityBindingConfig(EntityBindingConfig<? extends E, ? extends B, ? extends S> entityBindingConfig) {
        configs.add(entityBindingConfig);
        return this;
    }
}
