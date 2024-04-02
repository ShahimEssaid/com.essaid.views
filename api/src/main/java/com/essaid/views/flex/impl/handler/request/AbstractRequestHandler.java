package com.essaid.views.flex.impl.handler.request;

import com.essaid.views.flex.ModelUtils;
import com.essaid.views.flex.internal.FlexModelInternal;
import com.essaid.views.flex.internal.RequestHandler;
import com.essaid.views.flex.internal.SessionInternal;
import com.essaid.views.flex.internal.StateKeys;
import com.essaid.views.flex.internal.ViewHandler;
import com.essaid.views.flex.internal.ViewRequest;
import com.essaid.views.flex.internal.ViewState;

public abstract class AbstractRequestHandler implements RequestHandler {

  protected final RequestType requestType;
  protected final SessionInternal session;


  public AbstractRequestHandler(ViewRequest request) {
    this.requestType = RequestType.getRequestType(request);
    this.session = request.getViewHandler().getSession();
  }

  public static <T> T get(Object key, Class<T> neededReturnType, ViewState viewState,
      SessionInternal session) {
    Object value = viewState.getFeatureValue(key);
    if (value == null) {
      return null;
    }

    if (neededReturnType.isAssignableFrom(value.getClass())) {
      return (T) value;
    }

    ViewHandler viewHandler = ModelUtils.getViewHandler(value);
    if (viewHandler != null) {
      Object externalValue
          = viewHandler.getState().getFeatureValue(StateKeys.EXTERNAL_VALUE);
      if(externalValue != null && neededReturnType.isAssignableFrom(externalValue.getClass())){
        return (T) externalValue;
      }
      T adapted = session.adapt(value, neededReturnType);
      set(key, adapted, viewState);
      return adapted;
    }

    throw new IllegalStateException(
        "Feature key: " + key + " with value: " + value + " exists but it can't be "
            + "converted to requested type: " + neededReturnType);
  }

  public static <T> T set(Object key, Object value,
      ViewState viewState) {

    if (value == null) {
      return (T) viewState.unsetFeatureValue(key);
    }

    return (T) viewState.setFeatureValue(key, value);
  }
}
