package com.essaid.tmp.interfaces;

public interface ADefault {

  default Object a(){
    return "A default as string";
//    return new A() {
//    };
  }

}
