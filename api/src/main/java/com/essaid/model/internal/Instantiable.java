package com.essaid.model.internal;

public interface Instantiable<I extends Instantiable<I>> extends ModelObject<I> {

  interface InternalInstantiable<I extends InternalInstantiable<I>> extends Instantiable<I>,
      InternalModelObject<I> {

  }
}
