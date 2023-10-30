package com.essaid.tmp;

import com.essaid.model.Entity;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class GenericExamples
{

    public static void main(String[] args) {
        try {
            Method mapMethod = GenericExamples.class.getMethod("map");
            Type genericReturnType = mapMethod.getGenericReturnType();
            if (genericReturnType instanceof ParameterizedType pt){
                Type[] actualTypeArguments = pt.getActualTypeArguments();
                System.out.println(actualTypeArguments);
            }

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

    public Map<String, ? extends Entity> map(){
        return  null;
    }
}
