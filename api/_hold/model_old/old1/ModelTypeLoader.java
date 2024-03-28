package com.essaid.model_old.old1;

import java.util.List;

public interface ModelTypeLoader {

  boolean canLoad(Object typeHint);

  <E extends Entity> ModelType<E> load(Object typeHint);

}
