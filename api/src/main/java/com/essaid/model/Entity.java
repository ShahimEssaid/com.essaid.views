package com.essaid.model;

import com.essaid.model.Entity.InternalEntity;
import com.essaid.model.internal.Instantiable;
import com.essaid.model.internal.Instantiable.InternalInstantiable;
import com.essaid.model.internal.ModelObject;

public interface Entity extends Instantiable<InternalEntity> {

  interface InternalEntity extends Entity, InternalInstantiable<InternalEntity> {

  }
}
