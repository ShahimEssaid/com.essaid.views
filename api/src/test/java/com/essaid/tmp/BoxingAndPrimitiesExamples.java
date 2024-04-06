package com.essaid.tmp;

public class BoxingAndPrimitiesExamples {

  static Object value;
  static char somechar = 'a';

  public static void main(String[] args) {

    returnPrimitiveInt_FromInteger();
    returnPrimitiveInt_FromShort();
//    value = (byte) 0;
//    System.out.println(value.getClass());
//
//    System.out.println(somechar);
//
//    char otherChar = 0;
//    value = (char) 0;
//    System.out.println(char.class);
//    System.out.println(int.class == Integer.TYPE);
  }


  static int returnPrimitiveInt_FromInteger() {
    Object value = Integer.valueOf(1);

    return (int) value;
  }

  static int returnPrimitiveInt_FromShort() {
    Object value = Short.valueOf((short)1);

    try {
      return (int) value;
    } catch (ClassCastException e){
      System.out.println(e);
    }

    return 1;
  }


}
