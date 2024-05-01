package com.essaid.views;

import com.essaid.views.internal.ViewHandler;
import com.essaid.views.proxy.Visitable;

public interface View extends Visitable {

//  <T> T _as(Class<T> clazz, Class<?>... defaults);
//
//  default ViewInternal _internal() {
//    return (ViewInternal) this;
//  }

  ViewHandler _getViewHandler();

}
