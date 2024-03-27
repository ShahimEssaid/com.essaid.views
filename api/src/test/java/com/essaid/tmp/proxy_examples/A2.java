package com.essaid.tmp.proxy_examples;

import java.io.EOFException;
import java.io.FileNotFoundException;

public interface A2 {

  default String value() throws EOFException, FileNotFoundException {
    return "A2";
  }

}
