package com.essaid.tmp.proxy_examples;

import java.io.FileNotFoundException;
import java.lang.reflect.Proxy;
import org.junit.jupiter.api.Test;

public class ProxyExamples {


  @Test
  void example_1() throws FileNotFoundException {
    Class<?>[] classes = new Class[]{A1.class, A2.class};
    A1 a1 = (A1) Proxy.newProxyInstance(getClass().getClassLoader(), classes, new Handler());
    System.out.println(a1.value());

  }

}
