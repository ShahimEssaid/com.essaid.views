package com.essaid.views.internal;

import com.essaid.views.ViewsManager;
import com.essaid.views.adapter.Adapter;
import com.essaid.views.proxy.internal.Request;
import java.lang.reflect.Method;
import java.util.List;

public interface ViewsManagerInternal extends ViewsManager {

//  <T> T adapt(Object object, Class<T> viewType, Class<?>... customDefaults);
//
//  ViewHandler createViewHandler(ViewState viewState);
//
  Method getClientMethod(Request request);
//
//  void process(ViewRequest request);

  Class<?>[] getInterfaces(Class<?> view, Class<?>... viewDefaults);

  List<Adapter> getAdapters();

}
