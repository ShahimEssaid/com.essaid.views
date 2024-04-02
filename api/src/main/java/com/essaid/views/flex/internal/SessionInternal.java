package com.essaid.views.flex.internal;

import com.essaid.views.flex.Session;
import java.lang.reflect.Method;

public interface SessionInternal extends Session {

  <T> T adapt(Object object, Class<T> viewType, Class<?>... customDefaults);

  ViewHandler createViewHandler(ViewState viewState);

  Method getClientMethod(ViewRequest viewRequest);

  void process(ViewRequest request);


}
