package com.essaid.tmp.interfaces;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy {

  public static void main(String[] args) {

    B b = (B) java.lang.reflect.Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
        new Class[]{ADefault.class, C.class, A.class, B.class},
        new InvocationHandler() {
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            boolean aDefault = method.isDefault();
            return null;
          }
        });

    Class<?>[] interfaces = b.getClass().getInterfaces();
    A a = b.a();
    System.out.println(b);
  }

}
