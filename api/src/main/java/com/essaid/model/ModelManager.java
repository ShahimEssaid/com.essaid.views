package com.essaid.model;

import com.essaid.model.internal.ModelBindingConfig;
import com.essaid.model.internal.ModelDefaultConfig;

public interface ModelManager<E, B, D, S> {

    Model<E, B, D, S> createModel(ModelDefaultConfig<? extends E, ? extends D> defaultConfig,
                                  ModelBindingConfig<? extends E, ? extends B, ? extends S> bindingConfig);
}
