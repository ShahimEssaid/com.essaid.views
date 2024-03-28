package com.essaid.model;

import com.essaid.model.internal.ViewHandler;

public interface View extends Visitable {

  <T> T _as(Class<T> clazz, Class<?>... defaults);

  default InternalView _internal() {
    return (InternalView) this;
  }


  interface InternalView extends View {

    default Class<? extends InternalView> getInternalType() {
      return InternalView.class;
    }

    ViewHandler _getViewHandler();

  }

}
