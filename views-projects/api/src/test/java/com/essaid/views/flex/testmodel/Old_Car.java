package com.essaid.views.flex.testmodel;

import java.util.List;
import java.util.Map;

public interface Old_Car extends Vehicle {

  Old_Tire getSpareTire();

  void setSpareTire(Old_Tire tire);

  Old_Tire getSpareTire_create();

  Old_Tire setSpareTire_get(Old_Tire newTire);

  // List stuff
  List<Old_Tire> getTires();

  void setTires(List<Old_Tire> tireLoad);

  List<Old_Tire> getTires_create();

  Old_Tire getTires_add();

  Old_Tire addTireLoadList(); // adds a new element instance to the List and return the instance

  Old_Car addTireLoadList(Old_Tire tire);  //  adds the element instance to the list and returns self.


  // Map stuff
  Map<String, Old_Tire> getTireLoadMap();

  void setTireLoadMap(Map<String, Old_Tire> tireLoad);

  Map<String, Old_Tire> cgetTireLoadMap();

  Old_Car csetTireLoadMap(Map<String, Old_Tire> tireLoad);

  Old_Tire putTireLoadMap(String key); // creates a new Tire and puts it with the key.

  Old_Car putTireLoadMap(String key, Old_Tire tire); // puts the args in the map and returns self.

  // create or get an instance of Tire, the actual value.  If this was a List/Map, it will create the List/Map if
  // it's not created yet
//    Tire cgetTire();
//
//
//    // This is a derived property. No setter
//    List<Tire> getTiresList();
//
//    List<Tire> cgetTiresList();
//
//
//    Map<String, Tire> getTiresMap();
//
//    void setTiresMap(Map<String, Tire> tireMap);
//
//    Map<String, Tire> cgetTiresMap();
//
//    Tire addToTiresMap(String key);


}
