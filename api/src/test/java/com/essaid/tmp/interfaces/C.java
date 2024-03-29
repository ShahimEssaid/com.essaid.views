package com.essaid.tmp.interfaces;

public interface C {

  default String getName(){
    return "Default name";
  }

  default B a(){
    return new B() {
      @Override
      public B a() {
        return null;
      }
    };
  }

}
