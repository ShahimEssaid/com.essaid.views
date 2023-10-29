package com.essaid.model.model;

import java.util.List;
import java.util.Map;

public interface Car extends Vehicle {

    boolean isTruck();

    int getDefaultIntValue();

    String getColor();

    void setColor(String color);

    void setOwned(boolean owned);
    boolean isOwned();

    Tire getSpareTire();

    void setSpareTire(Tire tire);

    default boolean isHasSpare() {
        return getSpareTire() != null;
    }

    // create or get an instance of Tire, the actual value.  If this was a List/Map, it will create the List/Map if
    // it's not created yet
    Tire cgetTire();


    // This is a derived property. No setter
    List<Tire> getTiresList();

    List<Tire> cgetTiresList();


    Map<String, Tire> getTiresMap();

    void setTiresMap(Map<String, Tire> tireMap);

    Map<String, Tire> cgetTiresMap();

    Tire addToTiresMap(String key);


}
