package com.essaid.views.session;

import com.essaid.views.View;
import com.essaid.views.internal.Factories;
import com.essaid.views.internal.ViewHandler;
import com.essaid.views.internal.ViewsManagerInternal;
import com.essaid.views.adapter.AdaptRequest;
import com.essaid.views.value.Value;
import com.essaid.views.proxy.internal.Request;

public interface ViewsSessionInternal extends ViewsSession {

  void process(Request request);


  View setFeature(Value state, String featureName, Object value, Request request);

  View getFeature(Value state, String featureName, Request request);

  ViewsManagerInternal getManager();

  default Factories getObjectFactory() {
    return getService(Factories.class);
  }

  default <T> T getService(Class<T> serviceType) {
    return getManager().getConfig().getService(serviceType);
  }

  <T> T adapt(Object adaptee, Class<T> adaptedType, Class<?> adaptedUsageType, ViewHandler viewHandler,
      Class<?>... defaultInterfaces);

  <T> T adapt(AdaptRequest request);
}
