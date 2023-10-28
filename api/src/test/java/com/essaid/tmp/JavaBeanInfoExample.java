package com.essaid.tmp;

import java.beans.*;

public class JavaBeanInfoExample {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(JavaBeanInfoExample.class, Object.class);


        for (PropertyDescriptor pd : info.getPropertyDescriptors()){
            System.out.println(pd);
        }
    }

    public int getတက်စတင်း(){
        return 0;
    }


}
