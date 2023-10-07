package com.essaid.model.internalOld.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyUtils {


    public static <I, P extends I> P createProxy(ClassLoader classLoader, Class<I> baseType, Class<P>[] proxyTypes, InvocationHandler handler){
        return (P) Proxy.newProxyInstance(classLoader, proxyTypes, handler);
    }


}
