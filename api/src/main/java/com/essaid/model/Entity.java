package com.essaid.model;

import com.essaid.model.Entity.InternalEntity;
import com.essaid.model.internal.Instantiable;

public interface Entity extends Instantiable<InternalEntity> {

  interface InternalEntity extends Entity, InternalInstantiable<InternalEntity> {

  }
}
