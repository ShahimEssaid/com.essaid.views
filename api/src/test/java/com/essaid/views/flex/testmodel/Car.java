package com.essaid.views.flex.testmodel;

import java.util.List;

public interface Car {

  Tire getSpare();
  void setSpare(Tire spare);

  List<Tire> getTires();
  void setTires(List<Tire> tires);

}
