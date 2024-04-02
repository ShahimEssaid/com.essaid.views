package com.essaid.views.flex;

import com.essaid.views.flex.internal.InternalView;

public interface View extends Visitable {

  <T> T _as(Class<T> clazz, Class<?>... defaults);

  default InternalView _internal() {
    return (InternalView) this;
  }


}
