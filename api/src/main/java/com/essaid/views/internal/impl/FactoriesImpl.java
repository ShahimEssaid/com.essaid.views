package com.essaid.views.internal.impl;

import com.essaid.views.View;
import com.essaid.views.adapter.AdaptRequest;
import com.essaid.views.adapter.impl.AdaptRequestImpl;
import com.essaid.views.internal.Factories;
import com.essaid.views.internal.ViewsManagerInternal;
import com.essaid.views.value.Value;
import com.essaid.views.internal.ViewHandler;
import com.essaid.views.session.ViewsSessionInternal;
import com.essaid.views.proxy.Config;
import com.essaid.views.proxy.impl.ViewHandlerImpl;
import com.essaid.views.value.impl.ValueImpl;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class FactoriesImpl implements Factories {

  @Override
  public View createView(ViewHandler viewHandler, ViewsSessionInternal session) {
    if (viewHandler == null){
      // create generic view instance
      Value value = createValue(session);
      viewHandler = createViewHandler(View.class, null, value, session);
    }

    Config config = session.getManager().getConfig();
    Class<?> viewImplementation = config.getImplementation(viewHandler.getViewType());

    if(viewImplementation!= null){

    } else {
      ViewsManagerInternal manager = viewHandler.getValue().getSession().getManager();
      Class<?>[] interfaces = manager.getInterfaces(viewHandler.getViewType(),
          viewHandler.getInterfaces());
      return (View) Proxy.newProxyInstance(viewHandler.getViewType().getClassLoader(), interfaces,
          (InvocationHandler) viewHandler);
    }


    return null;
  }

  @Override
  public ViewHandler createViewHandler(Class<?> viewType, Class<?>[] defaultInterfaces, Value value,
      ViewsSessionInternal session) {
    return new ViewHandlerImpl(viewType, defaultInterfaces, value);
  }

  @Override
  public Value createValue(ViewsSessionInternal session) {
    return new ValueImpl(session);
  }

  @Override
  public AdaptRequest createAdaptRequest(Object adaptee, Class<?> adaptedType,
      Class<?> adaptedUsageType, Class<?>[] defaultInterfaces, ViewsSessionInternal session,
      ViewHandler viewHandler) {
    return new AdaptRequestImpl(adaptee, adaptedType, adaptedUsageType, defaultInterfaces,
        session, viewHandler);
  }


}
