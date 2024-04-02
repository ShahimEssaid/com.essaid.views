package com.essaid.views.flex.impl.request;

import com.essaid.views.flex.internal.ViewResponse;
import java.util.Map;

public class DefaultViewResponse implements ViewResponse {

  private Object response;

  @Override
  public void setValue(Object value) {
      response = value;
  }

  @Override
  public Object getValue() {
    return response;
  }

  @Override
  public Map<Object, Object> getMetadata(boolean create) {

    return null;
  }
}
