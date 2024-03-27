package com.essaid.tmp.proxy_examples;

import java.io.FileNotFoundException;

public interface A1 {

  default String value() throws FileNotFoundException {
    return "A1";
  }
}
