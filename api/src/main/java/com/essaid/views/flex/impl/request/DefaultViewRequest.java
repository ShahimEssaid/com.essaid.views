package com.essaid.views.flex.impl.request;

import com.essaid.views.flex.internal.InternalView;
import com.essaid.views.flex.internal.ViewHandler;
import com.essaid.views.flex.internal.ViewRequest;
import com.essaid.views.flex.internal.ViewResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class DefaultViewRequest implements ViewRequest {

  @Getter
  private final InternalView view;
  @Getter
  private final Class<?> viewType;
  @Getter
  private final Method invokedMethod;
  @Getter
  @Setter
  private Method clientMethod;
  @Getter
  private final Object[] invokedArgs;
  @Getter
  private final ViewHandler viewHandler;
  @Getter
  private final ViewResponse response = new DefaultViewResponse();

  private Map<Object, Object> metatdata;

  @Override
  public Map<Object, Object> getMetadata(boolean create) {
    if (metatdata == null) {
      metatdata = new HashMap<>();
    }
    return metatdata;
  }

  public Method getClientMethod() {
    if(clientMethod == null){
      clientMethod = getViewHandler().getSession().getFlexModel().getClientMethod(this);
    }
    return clientMethod;
  }
}
