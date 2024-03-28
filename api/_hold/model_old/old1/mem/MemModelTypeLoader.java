package com.essaid.model_old.old1.mem;

import com.essaid.model_old.old1.ModelType;
import com.essaid.model_old.old1.ModelTypeLoader;
import com.essaid.model_old.old1.ModelTypes;

import java.util.List;

public class MemModelTypeLoader implements ModelTypeLoader {

  @Override
  public boolean canLoad(Object typeHint) {
    return typeHint == null;
  }

  @Override
  public ModelType load(Object typeHint) {
    return null;
  }
}
