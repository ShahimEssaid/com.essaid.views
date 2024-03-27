package com.essaid.tmp.proxy_examples;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface A1 {

  default String value() throws FileNotFoundException {
    return "A1";
  }
}
