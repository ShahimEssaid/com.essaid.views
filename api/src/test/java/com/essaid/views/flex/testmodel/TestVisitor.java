package com.essaid.views.flex.testmodel;

import com.essaid.views.proxy.Visitor;

public class TestVisitor implements Visitor {


  @Override
  public void visit(Object object) {
    System.out.println("Object: called ");
  }

//    public void visit(Vehicle car) {
//        System.out.println("Vehicle color: " + car.getColor());
//    }
//    public void visit(Car car) {
//        System.out.println("Car color: " + car.getColor());
//    }
}
