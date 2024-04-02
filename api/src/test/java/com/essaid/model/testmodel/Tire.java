package com.essaid.model.testmodel;

import com.essaid.views.flex.View;

public interface Tire extends View {


  String getBrandName();

  void setBrandName(String bean);

  String getBrandName_default(String defaultValue);

  String getBrandName_set(String defaultValue);

  String setBrandName_get(String bean);

  Tire setBrandName_chain(String bean);



  boolean isSpare();

  boolean getSpare();

  void setSpare(boolean isSpare);


}
