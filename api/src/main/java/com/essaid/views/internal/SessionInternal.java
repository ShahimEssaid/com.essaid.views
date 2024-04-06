package com.essaid.views.internal;

import com.essaid.views.Session;
import com.essaid.views.proxy.internal.Request;
import java.lang.reflect.Method;

public interface SessionInternal extends Session {

  <T> T adapt(Object object, Class<T> viewType, Class<?>... customDefaults);


  Method getClientMethod(Request request);

  void process(Request request);


  ViewInternal setFeature(State state, String featureName, Object value, Request request);

  ViewInternal getFeature(State state, String featureName, Request request);
}
