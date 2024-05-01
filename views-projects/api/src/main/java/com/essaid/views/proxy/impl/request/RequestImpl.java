package com.essaid.views.proxy.impl.request;

import com.essaid.views.View;
import com.essaid.views.proxy.internal.Request;
import com.essaid.views.proxy.internal.Response;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class RequestImpl implements Request {

  @Getter
  private final View view;
  @Getter
  private final Method invokedMethod;
  @Getter
  private final Object[] invokedArgs;
  @Getter
  private final Response response = new ResponseImpl();

  private Map<Object, Object> metatdata;

  @Override
  public Map<Object, Object> getMetadata(boolean create) {

    if (metatdata == null) {
      metatdata = new HashMap<>();
    }
    return metatdata;
  }


}
