package com.essaid.model.impl;

import com.essaid.model.internal.ModelObjectHandler;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ImplUtils {

  public static final Class<?>[] EMPTY_DEFAULTS = new Class[]{};

  public static <T> T[] concatWithArrayCopy(T[] array1, T[] array2, T... additional) {
    T[] result = Arrays.copyOf(array1, array1.length + array2.length + additional.length);
    System.arraycopy(array2, 0, result, array1.length, array2.length);
    System.arraycopy(additional, 0, result, array1.length + array2.length, additional.length);

    return result;
  }

  public static ModelObjectHandler getHandler(Object o){
    if(Proxy.isProxyClass(o.getClass())){
      InvocationHandler invocationHandler = Proxy.getInvocationHandler(o);
      if (invocationHandler instanceof ModelObjectHandler){
        return (ModelObjectHandler) invocationHandler;
      }
    }
    return null;
  }

}
