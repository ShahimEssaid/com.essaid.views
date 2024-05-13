package com.essaid.views;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class Temp {


  @Test
  void nullNotEqual(){
    assertThat(null != Object.class).isTrue();
  }

}
