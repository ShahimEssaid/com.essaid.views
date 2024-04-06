package com.essaid.tmp;

import com.essaid.views.View;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericExamples {

  public static void main(String[] args) throws NoSuchMethodException {
    try {
      Method mapMethod = GenericExamples.class.getMethod("map");
      TypeVariable<Method>[] typeParameters = mapMethod.getTypeParameters();
      Type genericReturnType = mapMethod.getGenericReturnType();
      if (genericReturnType instanceof ParameterizedType pt) {
        Type[] actualTypeArguments = pt.getActualTypeArguments();
        System.out.println(actualTypeArguments);
      }

    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }

    new GenericExamples().run();
  }

  private void run() throws NoSuchMethodException {
    list();
  }

  public <T extends String, S extends String> Map<String, ? extends View> map() {
    return null;
  }

  void list() throws NoSuchMethodException {
//        List<? extends  Number> list = new ArrayList<>();
//        List<Number> list = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(Integer.valueOf(1));
//        list.add(Double.valueOf(1));

    Class<?> aClass = list.getClass();

    Method stringListMethod = GenericExamples.class.getMethod("stringList");
    Type genericReturnType = stringListMethod.getGenericReturnType();

    Method wildecardNumberListMethod = GenericExamples.class.getMethod("wildecardNumberList");
    Type wildecardNumberListMethodReturnType = wildecardNumberListMethod.getGenericReturnType();

    System.out.println();
  }

  public List<String> stringList() {
    return null;
  }

  public List<? extends Number> wildecardNumberList() {
    return null;
  }

}
