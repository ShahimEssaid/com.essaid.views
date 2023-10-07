package com.essaid.model.internal;

public interface EntityBindingConfig<E, B, S> {

    Class<? extends E> getEntityInterface();

    Class<? extends B>[] getBindingInterfaces(Class<? extends E> entityInterface);

    <T extends S> T createBindingState();

}
