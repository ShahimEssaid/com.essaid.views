package com.essaid.views.flex;

import com.essaid.views.proxy.Configs;
import com.essaid.views.ViewsManager;
import com.essaid.views.internal.ViewsSessionInternal;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class _Main2Test {

  public static interface Shape {
    String getName();
    void setName(String name);

    int getSides();
    void setSides(int sides);


  }

  static ViewsManager manager = Configs.createDefaultModelManager2();
  static ViewsSessionInternal session = (ViewsSessionInternal) manager.createSession();

  @Test
  public void square(){
    Shape shape = null;
    long startTime = 0;
    long created = 0;
    long setName = 0;
    long setSides = 0;

    for (int i = 0; i < 20 ; ++i){
       startTime = System.nanoTime();
      shape = session.createView(Shape.class, null);
       created = System.nanoTime();
      shape.setName("Square");
       setName = System.nanoTime();
      shape.setSides(4);
       setSides = System.nanoTime();
    }


    assertThat(shape.getName()).isEqualTo("Square");

    System.out.println(created - startTime + " to create shape");
    System.out.println(setName - created+ " to set name");
    System.out.println(setSides - setName + " to set sides");
  }


//  @Test
//  public void squareClass(){
//    ShapeClass shape = null;
//    long startTime = 0;
//    long created = 0;
//    long setName = 0;
//    long setSides = 0;
//
//    for (int i = 0; i < 2000 ; ++i){
//      startTime = System.nanoTime();
//      shape = new ShapeClass();
//      created = System.nanoTime();
//      shape.setName("Square");
//      setName = System.nanoTime();
//      shape.setSides(4);
//      setSides = System.nanoTime();
//    }
//
//
//    assertThat(shape.getName()).isEqualTo("Square");
//
//    System.out.println(created - startTime + " to create shape");
//    System.out.println(setName - created+ " to set name");
//    System.out.println(setSides - setName + " to set sides");
//  }

  @Getter
  @Setter
  public static class ShapeClass {
    private String name;
    private int sides;


  }
}
