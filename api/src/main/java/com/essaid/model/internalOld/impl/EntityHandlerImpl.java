package com.essaid.model.internalOld.impl;

import com.essaid.model.internalOld.EntityHandler;
import com.essaid.model.internal.EntityState;

import java.lang.reflect.Method;

public class EntityHandlerImpl extends StateImpl implements EntityHandler, EntityState {

    public EntityHandlerImpl(Class<?> entityType) {
        super();
        set(EntityState.ENTITY_TYPE_KEY, entityType);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
