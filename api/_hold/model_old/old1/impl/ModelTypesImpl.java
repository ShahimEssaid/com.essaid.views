package com.essaid.model_old.old1.impl;

import com.essaid.model_old.old1.ModelType;
import com.essaid.model_old.old1.ModelTypeLoader;
import com.essaid.model_old.old1.ModelTypes;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class ModelTypesImpl implements ModelTypes {


  private List<ModelTypeLoader> loaders;

  @Override
  public ModelType getType(Object environmentHint) {
    return null;
  }

  @Override
  public List<ModelTypeLoader> getModelTypeLoaders() {
    if (loaders == null) {
      loaders = ServiceLoader.load(ModelTypeLoader.class).stream().map(ServiceLoader.Provider::get)
          .collect(
              Collectors.toList());
    }
    return loaders;
  }


}
