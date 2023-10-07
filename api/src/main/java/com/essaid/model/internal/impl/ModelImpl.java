package com.essaid.model.internal.impl;

import com.essaid.model.Model;
import com.essaid.model.ModelManager;
import com.essaid.model.BinderInterfaces;
import com.essaid.model.internalOld.Stateful;
import com.essaid.model.internalOld.impl.StateImpl;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

public class ModelImpl<E, B, D, S> extends StateImpl implements Model.Internal<E, B, D, S> {

    public ModelImpl(){

    }

    @Override
    public <I extends E> I create(Class<I> iface) {
        return null;
    }

    @Override
    public Internal<E, B, D, S> internal() {
        return this;
    }
}
