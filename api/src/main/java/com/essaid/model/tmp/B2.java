package com.essaid.model.tmp;

public interface B2 extends A {

  String a();

  default String aDefault() {
    return "";
  }


}
