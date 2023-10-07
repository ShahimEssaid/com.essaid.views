package com.essaid.model.internal;

public interface ModelBindingConfig<E, B, S> {

    ModelBindingConfig<E, B, S> addEntityBindingConfig(EntityBindingConfig<? extends E, ? extends B, ? extends S> entityBindingConfig);


}
