package com.essaid.model.tmp;

public interface B1  extends  A {

  String a();

  default String aDefault() {
    return "";
  }


}
