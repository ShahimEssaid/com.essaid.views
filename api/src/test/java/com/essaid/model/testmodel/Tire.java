package com.essaid.model.testmodel;

import com.essaid.model.Entity;

public interface Tire extends Entity {



    Object getBrandName();
    Object getBrandName_default(Object defaultValue);
    Object getBrandName_set(Object defaultValue);

    void setBrandName(Object bean);
    Object setBrandName_get(Object bean);
    Tire setBrandName_chain(Object bean);

    void setSpare(boolean isSpare);
    boolean isSpare();
    boolean getSpare();


}
