package com.essaid.views.flex.testmodel;

public interface Primitives {


  byte getByte();

  void setByte(byte value);

  short getShort();

  void setShort(short value);

  int getInt();

  void setInt(int value);

  long getLong();

  void setLong(long value);

  float getFloat();
  void setFloat(float value);

  double getDouble();
  void setDouble(double value);

  char getChar();
  void setChar(char value);

  boolean getboolean();
  void setBoolean(boolean value);
  boolean isboolean();

  ///////////////


  byte getByte_default(byte defaultValue);

  byte getByte_set(byte setValue);


  short getShort_orDefault(short defaultValue);

  short getShort_orSet(short setValue);

  int getInt_orDefault(int defaultValue);

  int getInt_orSet(int setValue);

  long getLong_ordefault(long defaultValue);

  long getLong_Orset(long setValue);



}
