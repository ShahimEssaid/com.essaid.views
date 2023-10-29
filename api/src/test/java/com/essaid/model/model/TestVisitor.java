package com.essaid.model.model;

import com.essaid.model.Visitor;

import java.util.Objects;

public class TestVisitor implements Visitor {


    @Override
    public void visit(Object object) {
        System.out.println("Object: called " );
    }

//    public void visit(Vehicle car) {
//        System.out.println("Vehicle color: " + car.getColor());
//    }
//    public void visit(Car car) {
//        System.out.println("Car color: " + car.getColor());
//    }
}
