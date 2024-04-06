package com.essaid.tmp;

import static org.assertj.core.api.Assertions.assertThat;

import com.essaid.views.proxy.Configs;
import com.essaid.views.ViewsManager;
import com.essaid.views.flex._Main2Test.Shape;

public class RunProfiler {
  static ViewsManager manager = Configs.createDefaultModelManager2();


  public static void main(String[] args) {
    Shape shape = null;
    long startTime = 0;
    long created = 0;
    long setName = 0;
    long setSides = 0;

    for (int i = 0; i < 1000000 ; ++i){
      startTime = System.nanoTime();
      shape = manager.createSession().createView(Shape.class, null);
      created = System.nanoTime();
      shape.setName("Square");
      setName = System.nanoTime();
      shape.setSides(4);
      setSides = System.nanoTime();
    }


//    assertThat(shape.getName()).isEqualTo("Square");

    System.out.println(created - startTime + " to create shape");
    System.out.println(setName - created+ " to set name");
    System.out.println(setSides - setName + " to set sides");
  }

}
