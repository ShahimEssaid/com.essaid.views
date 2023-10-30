package com.essaid.model.model;

import com.essaid.model.Identifiable;

import java.util.List;
import java.util.Map;

public interface Car extends Vehicle, Identifiable {

    //@formatter:off
    // check defaults for primitives that do not have an actual value.
    byte getDefaultByte();
    short getDefaultShort();
    int getDefaultInt();
    long getDefaultLong();
    float getDefaultFloat();
    double getDefaultDouble();
    char getDefaultChar();
    boolean getDefautlboolean();
    boolean isDefautlboolean();

    String getColor();
    void setColor(String color);

    void setOwned(boolean owned);
    boolean isOwned();

    // @formatter:on

    Tire getSpareTire();
    void setSpareTire(Tire tire);
    Tire cgetSpareTire();
    Car csetSpareTire(Tire tire);

    default boolean isHasSpare() {
        return getSpareTire() != null;
    }

    // List stuff
    List<Tire> getTireLoadList();
    void setTireLoadList(List<Tire> tireLoad);

    List<Tire> cgetTireLoadList();
    Car csetTireLoadList(List<Tire> tireLoad);

    Tire addTireLoadList(); // adds a new element instance to the List and return the instance
    Car addTireLoadList(Tire tire);  //  adds the element instance to the list and returns self.


    // Map stuff
    Map<String, Tire> getTireLoadMap();
    void setTireLoadMap(Map<String, Tire> tireLoad);

    Map<String, Tire> cgetTireLoadMap();
    Car csetTireLoadMap(Map<String, Tire> tireLoad);

    Tire putTireLoadMap(String key); // creates a new Tire and puts it with the key.
    Car putTireLoadMap(String key, Tire tire); // puts the args in the map and returns self.





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
