package com.essaid.tmp;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class PublicLookupExample {
    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
//        MethodHandles.Lookup lookup = MethodHandles.publicLookup();
        MethodHandle toString = lookup.findVirtual(Object.class, "toString", MethodType.methodType(String.class));
        String abc = new String("abc");
        MethodHandle methodHandle = toString.bindTo(abc);


        Helper.drunHandler(methodHandle);
    }
}

class Helper {
    static void drunHandler(MethodHandle handle) throws Throwable {
        System.out.println(handle.invoke());
    }
}
