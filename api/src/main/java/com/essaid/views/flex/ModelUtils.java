package com.essaid.views.flex;

import com.essaid.views.flex.internal.InternalView;
import com.essaid.views.flex.internal.ViewHandler;
import java.lang.reflect.Proxy;

public class ModelUtils {

  public static boolean isModelObject(Object object) {
    return getViewHandler(object) != null;
  }


  public static ViewHandler getViewHandler(Object object) {
    if (object instanceof InternalView) {
      return (ViewHandler) Proxy.getInvocationHandler(object);
    }
    return null;
  }
}
