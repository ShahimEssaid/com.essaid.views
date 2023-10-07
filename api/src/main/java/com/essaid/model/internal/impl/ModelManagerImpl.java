package com.essaid.model.internal.impl;

import com.essaid.model.Model;
import com.essaid.model.ModelManager;
import com.essaid.model.BinderInterfaces;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ModelManagerImpl<E, M> implements ModelManager<E, M> {

    private final Class<E> entityBaseType;
    private final Class<M> mappingBaseType;

    @Override
    public Model<E, M> createModel(BinderInterfaces mapping) {
        return new ModelImpl<E, M>(this, entityBaseType, mapping);
    }
}
