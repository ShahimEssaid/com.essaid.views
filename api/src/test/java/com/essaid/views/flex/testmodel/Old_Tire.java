package com.essaid.views.flex.testmodel;

import com.essaid.views.View;

public interface Old_Tire extends View {


  String getBrandName();

  void setBrandName(String bean);

  String getBrandName_default(String defaultValue);

  String getBrandName_set(String defaultValue);

  String setBrandName_get(String bean);

  Old_Tire setBrandName_chain(String bean);



  boolean isSpare();

  boolean getSpare();

  void setSpare(boolean isSpare);


}
