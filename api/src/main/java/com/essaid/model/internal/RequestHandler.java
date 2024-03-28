package com.essaid.model.internal;

import java.lang.reflect.Method;

public interface RequestHandler {

  Object handle(Object proxy, Method method, Object[] args,
      ViewHandler objectHandler);
}
