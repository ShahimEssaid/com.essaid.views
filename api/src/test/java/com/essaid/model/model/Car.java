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
