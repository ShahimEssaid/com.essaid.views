package com.essaid.tmp;

import java.beans.*;

public class JavaBeanInfoExample {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(JavaBeanInfoExample.class, Object.class);


        for (PropertyDescriptor pd : info.getPropertyDescriptors()){
            System.out.println(pd);
        }
    }

    // non ascii characters work for property names
    public int getတက်စတင်း(){
        return 0;
    }

    // This is recognized as a getter property
    public Boolean getSomeThing1(){
        return true;
    }

    // This "is" method is not recognized as a bean property because  of the return type
    public Boolean isSomeThing2(){
        return true;
    }

    // check if this is a property, as opposed to it needing to be an "is" method
    // Yes, it is recognized as a bean property as a getter
    public boolean getBooleanGetter(){
        return true;
    }

}
