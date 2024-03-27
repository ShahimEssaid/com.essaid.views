package com.essaid.tmp.proxy_examples;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Handler implements InvocationHandler {

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    throw new FileNotFoundException();
//    if(method.isDefault()){
//      return InvocationHandler.invokeDefault(proxy, method, args);
//    }
//    return null;
  }
}
