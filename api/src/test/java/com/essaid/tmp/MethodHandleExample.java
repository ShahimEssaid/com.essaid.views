package com.essaid.tmp;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

// https://stackoverflow.com/questions/5411434/how-to-call-a-superclass-method-using-java-reflection

public class MethodHandleExample extends Base {
    public static void main(String[] args) throws Throwable {
        java.lang.invoke.MethodHandle h1 = MethodHandles.lookup()
                                                        .findSpecial(Base.class,
                                                                     "toString",
                                                                     MethodType.methodType(String.class),
                                                                     MethodHandleExample.class);
        java.lang.invoke.MethodHandle h2 = MethodHandles.lookup()
                                                        .findSpecial(Object.class,
                                                                     "toString",
                                                                     MethodType.methodType(String.class),
                                                                     MethodHandleExample.class);
        System.out.println(h1.invoke(new MethodHandleExample()));   // outputs Base
        System.out.println(h2.invoke(new MethodHandleExample()));   // outputs Base
    }

    @Override
    public String toString() {
        return "Test";
    }

}

class Base {
    @Override
    public String toString() {
        return "Base";
    }
}