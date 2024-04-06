package com.essaid.tmp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class proxy_with_default_method_with_super_type_of_argument {


  public static void main(String[] args) {
    ClassLoader classLoader = proxy_with_default_method_with_super_type_of_argument.class.getClassLoader();

    GarageString garageStringObjectDefault = (GarageString) Proxy.newProxyInstance(classLoader,
        new Class<?>[]{GarageObjectDefault.class, GarageString.class}, new InvocationHandler() {
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("\t" + method);
            return null;
          }
        });
    ;

    System.out.println("Called method with GarageString and Object default");
    garageStringObjectDefault.setVehicle("");

    ///////////////////////

    GarageString garageString_StringDefault = (GarageString) Proxy.newProxyInstance(classLoader,
        new Class<?>[]{GarageStringDefault.class, GarageString.class}, new InvocationHandler() {
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("\t" + method);
            return null;
          }
        });
    ;

    System.out.println("Called method with GarageString and String default");
    garageString_StringDefault.setVehicle("");

    //////////////////

    GarageObject garageObject_ObjectDefault = (GarageObject) Proxy.newProxyInstance(classLoader,
        new Class<?>[]{GarageObjectDefault.class, GarageObject.class}, new InvocationHandler() {
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("\t" + method);
            return null;
          }
        });
    ;

    System.out.println("Called method with GarageObject and Object default");
    garageObject_ObjectDefault.setVehicle("");

    //////////////////

    GarageObject garageObject_StringDefault = (GarageObject) Proxy.newProxyInstance(classLoader,
        new Class<?>[]{GarageStringDefault.class, GarageObject.class}, new InvocationHandler() {
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("\t" + method);
            return null;
          }
        });
    ;

    System.out.println("Called method with GarageObject and String default");
    garageObject_StringDefault.setVehicle("");

    //////////////////

  }


  interface GarageString {

    String setVehicle(String car);
  }

  interface GarageObject {

    void setVehicle(Object car);
  }

  interface GarageStringDefault  {

    default Object setVehicle(String vehicle) {
      return null;
    }
  }

  interface GarageObjectDefault {

    default void setVehicle(Object car) {
    }
  }

}
