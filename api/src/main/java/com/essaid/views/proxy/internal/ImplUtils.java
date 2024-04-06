package com.essaid.views.proxy.internal;

import com.essaid.views.View;
import com.essaid.views.internal.ViewInternal;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ImplUtils {

  public static final Class<?>[] EMPTY_DEFAULTS = new Class[]{};

  public static ViewHandler getHandler(Object o) {
    if (Proxy.isProxyClass(o.getClass())) {
      InvocationHandler invocationHandler = Proxy.getInvocationHandler(o);
      if (invocationHandler instanceof ViewHandler) {
        return (ViewHandler) invocationHandler;
      }
    }
    return null;
  }

  public static void assertInterfaces(Class<?>... ifaces) {
    for (Class<?> iface : ifaces) {
      if (!iface.isInterface()) {
        throw new IllegalStateException("Not an interface: " + iface);
      }
    }
  }

  public static void assertImplementation(Class<?>... implementations) {
    for (Class<?> impl : implementations) {
      if (impl.isInterface()) {
        throw new IllegalStateException("Not an implementation: " + impl);
      }
    }
  }


  public static void checkNotViewOrViewInternal(Class<?>... intefraces) {
    for (Class<?> iface : intefraces) {
      if (View.class == iface ||
          ViewInternal.class == iface) {
        throw new IllegalArgumentException("Interface: " + iface + " is not allowed");
      }
    }
  }
}
