package com.essaid.model.internal;

import com.essaid.model.internal.Instantiable.InternalInstantiable;

public interface Instantiable<I extends Instantiable<I>> extends ModelObject<I> {

  interface InternalInstantiable<I extends InternalInstantiable<I>> extends Instantiable<I>,
      InternalModelObject<I> {

  }
}
