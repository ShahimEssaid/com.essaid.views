package com.essaid.tmp;

import java.util.Objects;

public class BoxingAndPrimitiesExamples {
    static Object value;
    static char somechar ='a';

    public static void main(String[] args) {
        value = (byte) 0;
        System.out.println(value.getClass());

        System.out.println(somechar);



        char otherChar = 0;
        value = (char) 0;
        System.out.println(char.class);
        System.out.println(int.class == Integer.TYPE);
    }
}
