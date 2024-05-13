package com.essaid.views;

import com.essaid.views.internal.ViewHandler;
import com.essaid.views.proxy.Visitable;

public interface View extends Visitable {


  ViewHandler __viewHandler();

  default <T> T adaptToView(Class<T> viewType, Class<?>... defaultInterfaces) {


    return null;
  }

}
