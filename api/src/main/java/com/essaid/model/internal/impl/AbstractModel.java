package com.essaid.model.internal.impl;

import com.essaid.model.Model;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractModel implements Model.Internal {
    private final String id;


    @Override
    public String getId() {
        return id;
    }
}
