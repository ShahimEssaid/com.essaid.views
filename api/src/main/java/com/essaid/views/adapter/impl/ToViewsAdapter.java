package com.essaid.views.adapter.impl;

import com.essaid.views.View;
import com.essaid.views.adapter.AdaptRequest;
import com.essaid.views.adapter.Adapter;
import com.essaid.views.internal.Factories;
import com.essaid.views.internal.ViewHandler;
import com.essaid.views.value.Value;

/**
 * This adapter handles adapting all sorts of values into a views related instance of the requested
 * type.
 */
public class ToViewsAdapter implements Adapter {

  @Override
  public boolean adapt(AdaptRequest request) {
    Object adaptee = request.getAdaptee();

    // don't handle null
    if (adaptee == null) {
      return false;
    }

    // don't handle other  usage types
    Class<?> usage = request.getAdaptedUsageType();
    if (usage != View.class && usage != ViewHandler.class && usage != Value.class) {
      return false;
    }

    Class<?> adapteeType = adaptee.getClass();
    if (request.getAdaptedType().isAssignableFrom(adapteeType)) {
      request.setAdapted(adaptee);
      return true;
    }

    Factories objectFactory = request.getSession().getObjectFactory();
    Value value = null;

    if (adaptee instanceof View) {
      value = ((View) adaptee).__viewHandler().getValue();
    } else if (adaptee instanceof ViewHandler) {
      value = ((ViewHandler) adaptee).getValue();
    } else if (adaptee instanceof Value) {
      value = (Value) adaptee;
    } else {
      value = objectFactory.createValue(request.getSession());
      value.setValue(adaptee);
    }

    if (Value.class == usage) {
      request.setAdapted(value);
      return true;
    } else if (ViewHandler.class == usage) {
      ViewHandler viewHandler = objectFactory.createViewHandler(request.getAdaptedType(),
          request.getDefaultInterfaces(), value, request.getSession());
      request.setAdapted(viewHandler);
      return true;
    } else {
      ViewHandler viewHandler = objectFactory.createViewHandler(request.getAdaptedType(),
          request.getDefaultInterfaces(), value, request.getSession());
      View view = objectFactory.createView(viewHandler, request.getSession());
      request.setAdapted(view);
      return true;
    }

  }
}
