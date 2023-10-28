package com.essaid.model_old.internal.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyUtils {


    public static <I, P extends I> I createProxy(ClassLoader classLoader,
                                                 InvocationHandler handler,
                                                 Class<I> baseType,
                                                 Class<P>... proxyTypes) {

        
        return (I) Proxy.newProxyInstance(classLoader, proxyTypes, handler);
    }


}
