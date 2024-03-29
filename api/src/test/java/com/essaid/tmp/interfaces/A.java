package com.essaid.tmp.interfaces;

public interface A {

  default String getName(){
    return "Default name";
  }

  default A a(){
    return new A() {
      @Override
      public String getName() {
        return "A";
      }
    };
  }

}
