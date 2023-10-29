package com.essaid.tmp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

public class ProxyMethodOrderExample {

    public static void main(String[] args) {
        Two base = (ProxyMethodOrderExample.Two) Proxy.newProxyInstance(ProxyMethodOrderExample.class.getClassLoader(),
                                                                          new Class[]{ Unrelated.class,    One.class,
                                                                                  Two.class,
                                                                                },
                                                                          new Handler());

        base.hello();

        System.out.println("==============");
        base.there();


    }

    static interface Base {
        void hello();

        void there();
    }

    static interface One extends Base {
        default void hello() {
            System.out.println("One");
        }
    }

    static interface Two extends Base {
        default void hello() {
            System.out.println("Two");
        }

        @Override
        default void there() {
            System.out.println("Two there");
        }
    }

    static interface Unrelated {

        default void there() {
            System.out.println("Unrelated there");
        }
    }

    static class Handler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Class<?> aClass = proxy.getClass();


            System.out.println(method);
            System.out.println(method.isDefault());
            Method hello = proxy.getClass().getMethod("hello", List.of().toArray(new Class[]{}));
            System.out.println(hello);
            System.out.println(hello.isDefault());
            System.out.println(hello.getDeclaringClass());
            return null;
        }
    }
}
