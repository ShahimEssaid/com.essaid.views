package com.essaid.views.flex.impl;

import com.essaid.views.flex.ModelUtils;
import com.essaid.views.flex.Session;
import com.essaid.views.flex.internal.FlexModelInternal;
import com.essaid.views.flex.internal.StateKeys;
import com.essaid.views.flex.internal.ViewHandler;
import com.essaid.views.flex.internal.ViewRequest;
import com.essaid.views.flex.internal.ViewState;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class DefaultSession extends AbstractSession {


  public DefaultSession(FlexModelInternal flexModel) {
    super(flexModel);
  }

  @Override
  public <T> T create(Class<T> viewType, Class<?>... customDefaults) {
    Class<?> implementation = flexModel.getConfig().getImplementation(viewType);
    if (implementation != null) {
      try {
        return (T) implementation.getConstructor().newInstance();
      } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
               IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }

    Class<?>[] allInterfaces = calculateInterfaces(viewType, customDefaults);
    ViewState viewState = createState();
    ViewHandler viewHandler = new DefaultViewHandler(this, viewState);
    return (T) Proxy.newProxyInstance(viewType.getClassLoader(), allInterfaces, viewHandler);
  }



  @Override
  public <T> T adapt(Object object, Class<T> viewType, Class<?>... customDefaults) {
    ViewHandler viewHandler = ModelUtils.getViewHandler(object);
    if (viewHandler != null) {
      if (viewHandler.getSession() != this) {
        throw new IllegalArgumentException(
            "Can't adapt an object that belongs to a different " + "manager. Object: " + object);
      }

      if (viewType.isAssignableFrom(object.getClass())) {
        return (T) object;
      }

      Class<?>[] allInterfaces = calculateInterfaces(viewType, customDefaults);
      return (T) Proxy.newProxyInstance(viewType.getClassLoader(), allInterfaces,
          viewHandler);
    } else {
      T internalView = create(viewType, customDefaults);
      ViewHandler viewHandler1 = ModelUtils.getViewHandler(internalView);
      viewHandler1.getState().setFeatureValue(StateKeys.EXTERNAL_VALUE, object);
      return internalView;
    }
  }

  @Override
  public ViewHandler createViewHandler(ViewState viewState) {
    return new DefaultViewHandler(this, viewState);
  }

  @Override
  public Method getClientMethod(ViewRequest viewRequest) {
    return flexModel.getClientMethod(viewRequest);
  }

  @Override
  public void process(ViewRequest request) {
    getRequestHandler(request).handle(request);
  }


}
