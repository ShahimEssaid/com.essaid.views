package com.essaid.model.testmodel;

import com.essaid.model.Visitor;

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
