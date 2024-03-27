package com.essaid.model.internal;

import com.essaid.model.Visitable;

public interface ModelObject<I extends ModelObject<I>> extends Visitable {

  <T> T _as(Class<T> clazz, Class<?>... defaults);

  default I _internal() {
    return (I) this;
  }


  interface InternalModelObject<I extends InternalModelObject<I>> extends ModelObject<I> {

    ModelObjectHandler _getObjectHandler();

  }

}
