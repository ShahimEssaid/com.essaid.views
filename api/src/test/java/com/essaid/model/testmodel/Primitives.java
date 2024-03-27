package com.essaid.model.testmodel;

public interface Primitives {

  byte getByte();

  void setByte(byte value);

  byte getByte_default(byte defaultValue);

  byte getByte_set(byte setValue);

  short getShort();

  void setShort(short value);

  short getShort_orDefault(short defaultValue);

  short getShort_orSet(short setValue);

  int getInt();

  void setInt(int value);

  int getInt_orDefault(int defaultValue);

  int getInt_orSet(int setValue);

  long getLong();

  long getLong_ordefault(long defaultValue);

  long getLong_Orset(long setValue);

  float getFloat();

  double getDouble();

  char getChar();

  boolean getboolean();

  boolean isboolean();


}
