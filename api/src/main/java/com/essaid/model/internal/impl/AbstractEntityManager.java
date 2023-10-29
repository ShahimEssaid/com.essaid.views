package com.essaid.model.internal.impl;

import com.essaid.model.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractEntityManager implements EntityManager.Internal {
    private final String id;


    @Override
    public String getId() {
        return id;
    }
}
