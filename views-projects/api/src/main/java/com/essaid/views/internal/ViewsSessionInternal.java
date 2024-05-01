package com.essaid.views.internal;

import com.essaid.views.View;
import com.essaid.views.ViewsSession;
import com.essaid.views.proxy.internal.Request;
import java.lang.reflect.Method;

public interface ViewsSessionInternal extends ViewsSession {

  <T> T adapt(Object object, Class<T> viewType, Class<?>... customDefaults);


  Method getClientMethod(Request request);

  void process(Request request);


  View setFeature(Value state, String featureName, Object value, Request request);

  View getFeature(Value state, String featureName, Request request);
}
