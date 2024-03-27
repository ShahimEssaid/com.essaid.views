package com.essaid.model.testmodel;

public interface Primitives {

  byte getByte();
  byte getByte_default(byte defaultValue);
  byte getByte_set(byte setValue);
  void setByte(byte value);

  short getShort();
  short getShort_orDefault(short defaultValue);
  short getShort_orSet(short setValue);
  void setShort(short value);

  int getInt();
  int getInt_orDefault(int defaultValue);
  int getInt_orSet(int setValue);
  void setInt(int value);

  long getLong();
  long getLong_ordefault(long defaultValue);
  long getLong_Orset(long setValue);

  float getFloat();

  double getDouble();

  char getChar();

  boolean getboolean();

  boolean isboolean();



}
