package com.essaid.model.internal.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyUtils {


    public static <I> I createProxy(ClassLoader classLoader,
                                    InvocationHandler handler,
                                    Class<I> baseType,
                                    Class<? extends I>... proxyTypes) {
        Class<? extends I>[] interfaces = Arrays.copyOf(proxyTypes, proxyTypes.length + 1);
        interfaces[0] = baseType;
        System.arraycopy(proxyTypes, 0, interfaces, 1, proxyTypes.length);
        return (I) Proxy.newProxyInstance(classLoader, interfaces, handler);
    }

}
