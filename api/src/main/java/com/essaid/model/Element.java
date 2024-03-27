package com.essaid.model;

import com.essaid.model.Element.InternalElement;
import com.essaid.model.internal.Instantiable;

public interface Element extends Instantiable<InternalElement> {

  interface InternalElement extends Element,
      InternalInstantiable<InternalElement> {

    Element _getContainer();

    void _setContainer(Element container);

  }
}
