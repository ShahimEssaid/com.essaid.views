package com.essaid.tmp;

import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;

public class _Main {

  @Test
  void showMethodSignature() throws NoSuchMethodException {
    Method printMethod = System.out.getClass().getMethod("println", new Class[]{String.class});
    String string = printMethod.toString();


    System.out.println(string);
  }


  @Test
  void logicalPrecedence(){
    System.out.println(!false && !true || true);
  }

}
