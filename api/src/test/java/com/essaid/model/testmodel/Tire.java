package com.essaid.model.testmodel;

import com.essaid.model.Entity;

public interface Tire extends Entity {


  Object getBrandName();

  void setBrandName(Object bean);

  Object getBrandName_default(Object defaultValue);

  Object getBrandName_set(Object defaultValue);

  Object setBrandName_get(Object bean);

  Tire setBrandName_chain(Object bean);

  boolean isSpare();

  boolean getSpare();

  void setSpare(boolean isSpare);


}
